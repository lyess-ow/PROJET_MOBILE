package com.example.kerkouche.tp;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kerkouche.tp.util.Consts;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;


public class ServiceNotification extends IntentService {
    static boolean ServiceIsRun=false;
    static int rdvID=0;
    String url = Consts.web_host + "/getRDVNotification";
    public String msg="";


    public ServiceNotification() {super("MyWebRequestService");}


    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
        Toast.makeText(this, "service started ... ", Toast.LENGTH_SHORT).show();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "service stopped ... ", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {

        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        final String Id_user = sharedPreferences.getString("Id_user","");

        synchronized (this){

            while (ServiceIsRun && !Id_user.equals("")){

                //----------------------------------------------------------------------------------
                RequestQueue queue = Volley.newRequestQueue(this);

                StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {

                        try {
                            JSONArray mJsonArray = new JSONArray(s);
                            msg="";
                            for (int i=0;i<mJsonArray.length();i++){

                                String valider = mJsonArray.getJSONObject(i).getString("Valider");
                                if (valider.equals("notYet")) {

                                    String id_rdv = mJsonArray.getJSONObject(i).getString("id_rdv");
                                    String annonce_titre = mJsonArray.getJSONObject(i).getString("annonce_titre");
                                    String nom_user_rdv = mJsonArray.getJSONObject(i).getString("nom_user_rdv");
                                    String prenom_user_rdv = mJsonArray.getJSONObject(i).getString("prenom_user_rdv");
                                    String date2_rdv = mJsonArray.getJSONObject(i).getString("date2_rdv");
                                    String time_rdv = mJsonArray.getJSONObject(i).getString("time_rdv");
                                    String Lieu = mJsonArray.getJSONObject(i).getString("Lieu");

                                    msg+="Vous avez un Rendez-vous pour l'annonce " + annonce_titre + " avec "+ nom_user_rdv + " " +
                                            prenom_user_rdv + " " + " le " + date2_rdv + " " + time_rdv + " à " + Lieu + " \n\n";
                                    rdvID= Integer.parseInt(id_rdv);
                                }
                            }
                            Intent intent2 = new Intent();
                            intent2.setAction("com.example.Broadcast");
                            intent2.putExtra("msg",msg);
                            sendBroadcast(intent2);



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {

                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> map = new HashMap<String, String>();

                        map.put("Id_rdv", String.valueOf(rdvID));
                        map.put("Id_proprio_rdv", Id_user);

                        return map;
                    }
                };

                queue.add(request);
                //----------------------------------------------------------------------------------

                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
