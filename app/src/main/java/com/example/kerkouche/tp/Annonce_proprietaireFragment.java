package com.example.kerkouche.tp;


import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Annonce_proprietaireFragment extends Fragment {

    public List<Annonce_s> listAnnonce = new ArrayList<Annonce_s>();
    public  List<User> listUser = new ArrayList<User>();

    public Annonce_proprietaireFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_annonce_proprietaire, container, false);

        final ListView listView = (ListView) v.findViewById(R.id.listAnnonceProprio);

       // prepareAnnonceData();

        CustomAdapter customAdapter = new CustomAdapter(getActivity(),listAnnonce);
        listView.setAdapter(customAdapter);

        final Context context = getActivity();

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, final View view, final int position, long id) {
                String message= getString(R.string.string1) + listAnnonce.get(position).getTitre()
                        + " ?";
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage(message)
                        .setTitle(R.string.string2)
                        .setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listAnnonce.remove(listAnnonce.get(position));
                                CustomAdapter customAdapter = new CustomAdapter(context,listAnnonce);
                                listView.setAdapter(customAdapter);
                            }
                        })
                        .setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        });













        return v;
    }

}
