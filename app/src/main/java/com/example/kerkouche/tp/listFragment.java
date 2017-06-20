package com.example.kerkouche.tp;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.kerkouche.tp.Pattern.RequestQueueSingleton;
import com.example.kerkouche.tp.util.*;
import com.google.gson.Gson;

import com.dd.CircularProgressButton;
import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class listFragment extends Fragment {
    // public String url = "http://10.0.3.2:8080/getAnnonce?dens=mdpi";
    // public String url = Consts.web_host+"/getAnnonce?dens="+new DeviceService( getContext()).getScreenDensity();
    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public List<Annonce_s> listAnnonce_s = new ArrayList<Annonce_s>();
    public  List<User> listUser = new ArrayList<User>();
    CircularProgressButton circularProgressButton;
    public ListView listView;
    CustomAdapter customAdapter;
    public ProgressBar progressBar;
    public  View v;
    public listFragment() {
        // Required empty public constructor
    }
    String region_,typeLogement_,budgetMin_,budgetMax_,surfaceMin_,surfaceMax_,nbrChambreMin_,nbrChambreMax_;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        v = inflater.inflate(R.layout.fragment_list, container, false);

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar2);
        //******************************************************************************************
        //Auto-complete region
        final AutoCompleteTextView regionEdit;
        regionEdit = (AutoCompleteTextView) v.findViewById(R.id.searchEdit);
        regionEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                region_ = regionEdit.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });

        String[] region = getResources().getStringArray(R.array.region);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getActivity(),android.R.layout.simple_list_item_1,region);
        regionEdit.setAdapter(adapter);
        //******************************************************************************************
        //Auto-complete type appartement
        final AutoCompleteTextView typeBienEdit;
        typeBienEdit = (AutoCompleteTextView) v.findViewById(R.id.typeBien);
        typeBienEdit.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                typeLogement_ = typeBienEdit.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        String[] typeBien= getResources().getStringArray(R.array.Type_de_bien);
        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>
                (getActivity(),android.R.layout.simple_list_item_1,typeBien);
        typeBienEdit.setAdapter(adapter2);
        //******************************************************************************************
        final TextView textView = (TextView) v.findViewById(R.id.textViewRechercheFiltree);

        final EditText budgetMin = (EditText) v.findViewById(R.id.budgetMin);
        budgetMin.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                budgetMin_ = budgetMin.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        final EditText budgetMax = (EditText) v.findViewById(R.id.budgetMax);
        budgetMax.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                budgetMax_ = budgetMax.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        final EditText nbrChambreMin = (EditText) v.findViewById(R.id.chambreMin);
        nbrChambreMin.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                nbrChambreMin_ = nbrChambreMin.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        final EditText nbrChambreMax = (EditText) v.findViewById(R.id.chambreMax);
        nbrChambreMax.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                nbrChambreMax_ = nbrChambreMax.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        final EditText surfaceMin = (EditText) v.findViewById(R.id.SurfaceMin);
        surfaceMin.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                surfaceMin_ = surfaceMin.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });
        final EditText surfaceMax = (EditText) v.findViewById(R.id.SurfaceMax);
        surfaceMax.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                surfaceMax_ = surfaceMax.getText().toString();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int arg1, int arg2, int arg3) {

            }

            @Override
            public void afterTextChanged(Editable arg0) {

            }

        });

        regionEdit.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        typeBienEdit.setVisibility(View.INVISIBLE);
        budgetMin.setVisibility(View.INVISIBLE);
        budgetMax.setVisibility(View.INVISIBLE);
        nbrChambreMin.setVisibility(View.INVISIBLE);
        nbrChambreMax.setVisibility(View.INVISIBLE);
        surfaceMin.setVisibility(View.INVISIBLE);
        surfaceMax.setVisibility(View.INVISIBLE);
        //******************************************************************************************
        CircleMenu circleMenu = (CircleMenu) v.findViewById(R.id.circle_menu);

        circleMenu.setMainMenu(Color.parseColor("#03a9f4"),R.drawable.ic_action_search,R.drawable.ic_action_cancel);

        circleMenu.addSubMenu(Color.parseColor("#fbc02d"),R.drawable.ic_action_money)
                .addSubMenu(Color.parseColor("#ff7043"),R.drawable.ic_action_room)
                .addSubMenu(Color.parseColor("#e91e63"),R.drawable.ic_action_surface)
                .addSubMenu(Color.parseColor("#90a4ae"),R.drawable.ic_action_type)
                .addSubMenu(Color.parseColor("#2EC309"),R.drawable.ic_action_region);

        circleMenu.setOnMenuSelectedListener(new OnMenuSelectedListener() {
            @Override
            public void onMenuSelected(int i) {

                regionEdit.setVisibility(View.INVISIBLE);
                textView.setVisibility(View.INVISIBLE);
                typeBienEdit.setVisibility(View.INVISIBLE);
                budgetMin.setVisibility(View.INVISIBLE);
                budgetMax.setVisibility(View.INVISIBLE);
                nbrChambreMin.setVisibility(View.INVISIBLE);
                nbrChambreMax.setVisibility(View.INVISIBLE);
                surfaceMin.setVisibility(View.INVISIBLE);
                surfaceMax.setVisibility(View.INVISIBLE);

                switch (i){
                    case 0:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(R.string.string5);
                        budgetMin.setVisibility(View.VISIBLE);
                        budgetMax.setVisibility(View.VISIBLE);
                        break;
                    case 1:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(R.string.string6);
                        nbrChambreMin.setVisibility(View.VISIBLE);
                        nbrChambreMax.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        textView.setVisibility(View.VISIBLE);
                        textView.setText(R.string.string7);
                        surfaceMin.setVisibility(View.VISIBLE);
                        surfaceMax.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        typeBienEdit.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        regionEdit.setVisibility(View.VISIBLE);
                        break;
                }
            }
        });
        circleMenu.setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {
            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }
        });
        Button rechercheFiltre = (Button) v.findViewById(R.id.rechercheFiltre);
        rechercheFiltre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (region_==null || region_.length()<2)  region_="null" ;
                if (typeLogement_==null || typeLogement_.length()<2) typeLogement_="null";
                msj(region_,typeLogement_);
            }
        });
        prepareAnnonceData_main(100);

        return v;
    }
    public void prepareAnnonceData_main( int in) {

        final String url = Consts.web_host+"/getAnnonce?dens="+new DeviceService( getActivity()).getScreenDensity()+"&autre="+in;
        RequestQueue queue = RequestQueueSingleton.getInstance( getActivity()).getRequestQueue();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                listAnnonce_s = Arrays.asList(new Gson().fromJson(jsonArray.toString(),Annonce_s[].class));
                listView = (ListView) v.findViewById(R.id.listAnnonce);
                progressBar.setVisibility(View.INVISIBLE);
                customAdapter = new CustomAdapter(getActivity(),listAnnonce_s);

                listView.setAdapter(customAdapter);
                Utility.setListViewHeightBasedOnChildren(listView);
                customAdapter.notifyDataSetChanged();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (((MainActivity)getActivity()).isTwoPane()) {
                            DetailFragment detailFragment = new DetailFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putInt("Id_annonce", listAnnonce_s.get(position).getId());
                            detailFragment.setArguments(bundle);
                            ft.replace(R.id.frame2,detailFragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        } else {
                            DetailFragment detailFragment = new DetailFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putInt("Id_annonce", listAnnonce_s.get(position).getId());
                            bundle.putString("Titre", listAnnonce_s.get(position).getTitre());
                            bundle.putString("Url", listAnnonce_s.get(position).getUrl());
                            bundle.putInt("Prix", listAnnonce_s.get(position).getPrix());
                            detailFragment.setArguments(bundle);
                            ft.replace(R.id.frame1,detailFragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(),"erreur", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(arrayRequest);
    }

    public void msj( String region ,String typeLogement) {

        final String url = Consts.web_host+"/recherche?dens="+new DeviceService( getActivity()).getScreenDensity()+"&region="+region+"&tlgm=" +typeLogement ;
        RequestQueue queue = RequestQueueSingleton.getInstance( getActivity()).getRequestQueue();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                listAnnonce_s = Arrays.asList(new Gson().fromJson(jsonArray.toString(),Annonce_s[].class));
                listView = (ListView) v.findViewById(R.id.listAnnonce);
                customAdapter = new CustomAdapter(getActivity(),listAnnonce_s);
                listView.setAdapter(customAdapter);
                Utility.setListViewHeightBasedOnChildren(listView);
                customAdapter.notifyDataSetChanged();

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (((MainActivity)getActivity()).isTwoPane()) {
                            DetailFragment detailFragment = new DetailFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putInt("Id_annonce", listAnnonce_s.get(position).getId());
                            detailFragment.setArguments(bundle);
                            ft.replace(R.id.frame2,detailFragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        } else {
                            DetailFragment detailFragment = new DetailFragment();
                            FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                            Bundle bundle = new Bundle();
                            bundle.putInt("Id_annonce", listAnnonce_s.get(position).getId());
                            bundle.putString("Titre", listAnnonce_s.get(position).getTitre());
                            bundle.putString("Url", listAnnonce_s.get(position).getUrl());
                            bundle.putInt("Prix", listAnnonce_s.get(position).getPrix());
                            detailFragment.setArguments(bundle);
                            ft.replace(R.id.frame1,detailFragment);
                            ft.addToBackStack(null);
                            ft.commit();
                        }
                    }
                });
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"erreur", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(arrayRequest);
    }


}
