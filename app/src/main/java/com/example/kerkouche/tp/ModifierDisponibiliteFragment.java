package com.example.kerkouche.tp;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class ModifierDisponibiliteFragment extends Fragment {

    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public  List<User> listUser = new ArrayList<User>();

    public List<String> list = new ArrayList<String>();

    public ModifierDisponibiliteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_modifier_disponibilite, container, false);
        //******************************************************************************************

        /*
        String message="";
        int s=0;
        for (int i=0;i<listAnnonce.get(0).getUtilisateur().getListDisponibilite().size();i++){
            s=s+1;
            message = getString(R.string.string8)+ s + " : \n" + getString(R.string.string9) + listAnnonce.get(0).getUtilisateur().getListDisponibilite().get(i).getDatedispoDebut() +"\n"+
                    getString(R.string.string10)+ listAnnonce.get(0).getUtilisateur().getListDisponibilite().get(i).getDatedispofin() + "\n\n";

            list.add(message);
        }


        final ListView listView = (ListView) v.findViewById(R.id.listDisponibilite);

        final ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, list);
        listView.setAdapter(itemsAdapter);


        //******************************************************************************************
        //supprimer ou modifier une disponibilité
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage(list.get(position))
                        .setTitle(R.string.string11)
                        .setPositiveButton(R.string.string12, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .setNegativeButton(R.string.string13, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                            }
                        })
                        .show();
            }
        }); */
        //******************************************************************************************
        final Button add = (Button) v.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater=getActivity().getLayoutInflater();
                //this is what I did to added the layout to the alert dialog
                View layout=inflater.inflate(R.layout.dialog_disponibilite,null);
                alert.setView(layout);

                final EditText date=(EditText)layout.findViewById(R.id.dateDispo);
                final EditText heureDebut=(EditText)layout.findViewById(R.id.heureDebut);
                final EditText heureFin=(EditText)layout.findViewById(R.id.heureFin);

                alert.setPositiveButton(R.string.string14, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setNegativeButton(R.string.string15, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                alert.setTitle(R.string.string16);
                alert.show();
            }
        });


        //******************************************************************************************
        return v;
    }



}
