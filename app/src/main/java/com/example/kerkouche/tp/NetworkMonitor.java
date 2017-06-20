package com.example.kerkouche.tp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kerkouche.tp.util.Consts;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kerkouche on 17/06/17.
 */

public class NetworkMonitor extends BroadcastReceiver {

    String url2 = Consts.web_host + "/validerRDV";

    @Override
    public void onReceive(final Context context, Intent intent) {
            if (checkNetworkConnection(context)){

                final DbHelper dbHelper = new DbHelper(context);
                final SQLiteDatabase database = dbHelper.getWritableDatabase();
                Cursor cursor = dbHelper.readFromLoaclDatabase(database);
                while (cursor.moveToNext()){
                    int sync_status = cursor.getInt(cursor.getColumnIndex("syncstatus"));
                    if (sync_status == Consts.SYNC_STATUS_FAILED){
                        final int Id_rdv = cursor.getInt(cursor.getColumnIndex("Id_rdv"));
                        final String Valider = cursor.getString(cursor.getColumnIndex("Valider"));

                        RequestQueue queue = Volley.newRequestQueue(context);

                        StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String s) {
                                //dbHelper.updateLocalDatabase(Id_rdv,Consts.SYNC_STATUS_OK,database);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Toast.makeText(context, volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        }
                        ) {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {

                                Map<String,String> map = new HashMap<String, String>();

                                map.put("id_rdv", String.valueOf(Id_rdv));
                                map.put("valider", Valider);

                                return map;
                            }
                        };
                        queue.add(request);
                    }
                }
                dbHelper.close();
                cursor.close();
            }
    }

    public boolean checkNetworkConnection(Context context){
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }
}
