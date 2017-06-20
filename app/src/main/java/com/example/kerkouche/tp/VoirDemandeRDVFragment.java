package com.example.kerkouche.tp;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
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

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.kerkouche.tp.R.id.listCommentaire;


/**
 * A simple {@link Fragment} subclass.
 */
public class VoirDemandeRDVFragment extends Fragment {

    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public  List<User> listUser = new ArrayList<User>();
    public List<RendezVous> listRDV = new ArrayList<RendezVous>();

    String url = Consts.web_host + "/getRDV";
    String url2 = Consts.web_host + "/validerRDV";

    public VoirDemandeRDVFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_voir_demande_rdv, container, false);

        final Context context = getActivity();
        final CustumAdapterRDV customAdapterRDV = new CustumAdapterRDV(getActivity(),listRDV);
        final ListView listView = (ListView) v.findViewById(R.id.listRdv);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
        final String Id_user = sharedPreferences.getString("Id_user","");

        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {

                try {
                    JSONArray mJsonArray = new JSONArray(s);

                    for (int i=0;i<mJsonArray.length();i++){

                        String valider = mJsonArray.getJSONObject(i).getString("Valider");
                        if (valider.equals("notYet") || valider.equals("OK")) {

                            RendezVous rendezVous = new RendezVous();

                            rendezVous.setId_rdv(mJsonArray.getJSONObject(i).getString("id_rdv"));
                            rendezVous.setAnnonce_titre(mJsonArray.getJSONObject(i).getString("annonce_titre"));
                            rendezVous.setNom_user_rdv(mJsonArray.getJSONObject(i).getString("nom_user_rdv"));
                            rendezVous.setPrenom_user_rdv(mJsonArray.getJSONObject(i).getString("prenom_user_rdv"));
                            rendezVous.setDate2_rdv(mJsonArray.getJSONObject(i).getString("date2_rdv"));
                            rendezVous.setTime_rdv(mJsonArray.getJSONObject(i).getString("time_rdv"));
                            rendezVous.setLieu(mJsonArray.getJSONObject(i).getString("Lieu"));
                            rendezVous.setValider(valider);
                            listRDV.add(rendezVous);
                        }
                    }

                    listView.setAdapter(customAdapterRDV);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                //progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String, String>();

                map.put("Id_proprio_rdv", Id_user);

                return map;
            }
        };

        queue.add(request);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Valider ou refuser la demande de rendez-vous")
                        .setTitle(R.string.string21)
                        .setPositiveButton(R.string.string22, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                saveToLocalStorage(Integer.parseInt(listRDV.get(position).getId_rdv()),"OK");

                            }
                        })
                        .setNegativeButton(R.string.string23, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                saveToLocalStorage(Integer.parseInt(listRDV.get(position).getId_rdv()),"NO");
                            }
                        })
                        .show();
            }
        });
        return v;
    }

    public boolean checkNetworkConnection(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    private void readFromLocalStorage(){
        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase database = dbHelper.getReadableDatabase();
        Cursor cursor = dbHelper.readFromLoaclDatabase(database);
        while (cursor.moveToNext()){

            int Id_rdv = cursor.getInt(cursor.getColumnIndex("Id_rdv"));
            String Valider = cursor.getString(cursor.getColumnIndex("Valider"));
            int syncstatus = cursor.getInt(cursor.getColumnIndex("syncstatus"));
        }
        cursor.close();
        dbHelper.close();
    }

    private void saveToLocalStorage(final int Id_rdv, final String Valider){
        DbHelper dbHelper = new DbHelper(getActivity());
        SQLiteDatabase database = dbHelper.getReadableDatabase();

        if (checkNetworkConnection()){
            //--------------------------------------------------------------------------------------
            RequestQueue queue = Volley.newRequestQueue(getActivity());

            StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                @Override
                public void onResponse(String s) {
                    Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError volleyError) {
                    Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
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
            //--------------------------------------------------------------------------------------
        } else {
            dbHelper.saveToLocalDatabase(Id_rdv, Valider, Consts.SYNC_STATUS_FAILED, database);
        }
            //--------------------------------------------------------------------------------------
        readFromLocalStorage();
        dbHelper.close();
    }
}
