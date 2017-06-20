package com.example.kerkouche.tp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
public class listAnnonceProprioFragment extends Fragment {

    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public  List<User> listUser = new ArrayList<User>();


    public listAnnonceProprioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_list_annonce_proprio, container, false);
        //******************************************************************************************

        //prepareAnnonceData();
        ListView listView = (ListView) v.findViewById(R.id.listAnnonceProprioModifDispo);
        //CustomAdapter customAdapter = new CustomAdapter(getActivity(),listAnnonce);
        //listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (((MainActivity)getActivity()).isTwoPane()) {

                    ModifierDisponibiliteFragment modifierDisponibiliteFragment = new ModifierDisponibiliteFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.frame2,modifierDisponibiliteFragment );
                    ft.addToBackStack(null);
                    ft.commit();

                } else {
                    ModifierDisponibiliteFragment modifierDisponibiliteFragment = new ModifierDisponibiliteFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();

                    ft.replace(R.id.frame1,modifierDisponibiliteFragment );
                    ft.addToBackStack(null);
                    ft.commit();
                }
            }
        });





        //******************************************************************************************
        return v;
    }

}
