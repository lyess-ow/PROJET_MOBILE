package com.example.kerkouche.tp;


import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.kerkouche.tp.Pattern.RequestQueueSingleton;
import com.example.kerkouche.tp.util.Consts;
import com.example.kerkouche.tp.util.DeviceService;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.gson.Gson;
import com.sdsmdg.tastytoast.TastyToast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    public ProgressBar progressBar;
    public ListView listView;
    public CustumAdapterCommentaire custumAdapterCommentaire;
    public View v;
    public int var=0;
    public int price;

    String url = Consts.web_host + "/addCommentaire";
    String url2 = Consts.web_host + "/getDisponibilite";
    String url3 = Consts.web_host + "/getCommentaire";
    String url4 = Consts.web_host + "/addRDV";

    List<Commentaire> listCommentaire = new ArrayList<Commentaire>();;

    List<Disponibilite> listDisponibilite = new ArrayList<Disponibilite>();
    List<Annonce> listAnn = new ArrayList<Annonce>();
    List<RendezVous> listRendezVous= new ArrayList<RendezVous>();
    public String titre_,mainurl;
    public int idanc;

    User user = new User("Kerkouche","Lyes","lyessow","1994","dl_kerkouche@esi.dz",listDisponibilite,listAnn,listRendezVous);


    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public  List<User> listUser = new ArrayList<User>();

    public Annonce annonce = new Annonce();

    ViewPager viewPager;
    CustumSwipeAdapter custumSwipeAdapter;

    private static final int PLACE_PICKER_REQUEST = 1;

    public DetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         v = inflater.inflate(R.layout.fragment_detail, container, false);
        //******************************************************************************************

        //******************************************************************************************
        idanc= this.getArguments().getInt("Id_annonce");
        titre_= this.getArguments().getString("Titre");
        price = this.getArguments().getInt("Prix");
        mainurl= this.getArguments().getString("Url");
        prepareAnnonceData_main();


        //******************************************************************************************
        // Afficher la liste de commentaire

        progressBar = (ProgressBar) v.findViewById(R.id.progressBar);
        listView = (ListView) v.findViewById(R.id.listCommentaire);

        final CustumAdapterCommentaire custumAdapterCommentaire = new
                CustumAdapterCommentaire(getActivity(),listCommentaire);

        afficherCommentaire(progressBar,listView,custumAdapterCommentaire);


        //******************************************************************************************
        // ajouter un nouveau commentaire
        final Context context = getActivity();
        final RatingBar ratingBar = (RatingBar) v.findViewById(R.id.ratingBar);
        final EditText commentaireEdit = (EditText) v.findViewById(R.id.commentaireEdit);
        Button commenterBtn = (Button) v.findViewById(R.id.commenterBtn);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            }
        });
        commenterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                final String Id_user = sharedPreferences.getString("Id_user","");

                if (!Id_user.equals("")){ // le user est connecté donc peut ajoute un commentaire

                    RequestQueue queue = Volley.newRequestQueue(getActivity());

                    StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String s) {
                            // recharger la liste des commentaires de la BDD avec le nouveau commentaire ajouté
                            custumAdapterCommentaire.clearData();
                            afficherCommentaire(progressBar,listView,custumAdapterCommentaire);
                            custumAdapterCommentaire.notifyDataSetChanged();
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError volleyError) {
                            Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getActivity(), "erreur", Toast.LENGTH_SHORT).show();
                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams() throws AuthFailureError {

                            Map<String,String> map = new HashMap<String, String>();

                            map.put("Id_annonce_comm", String.valueOf(idanc));
                            map.put("Id_user_comm", Id_user);
                            map.put("Text",commentaireEdit.getText().toString());
                            map.put("Note",String.valueOf((int)ratingBar.getRating()));

                            long millis=System.currentTimeMillis();
                            java.sql.Date date=new java.sql.Date(millis);
                            map.put("Date_comm", String.valueOf(date));

                            long millis2=System.currentTimeMillis();
                            java.sql.Time time =new java.sql.Time(millis2);
                            map.put("Time_comm", time.toString());

                            return map;
                        }
                    };

                    queue.add(request);


                    TastyToast.makeText(getActivity(),"Commentaire ajouté !"
                            ,TastyToast.LENGTH_LONG,TastyToast.SUCCESS);
                } else {
                    TastyToast.makeText(getActivity(),"Vous devez d'abord vous connecter ou " +
                                    "vous inscrire !",TastyToast.LENGTH_LONG,TastyToast.WARNING);
                }
                

            }
        });

        //******************************************************************************************
        // Voir diponibilité du proprio de l'annonce
        ImageButton voirDisponibilite = (ImageButton) v.findViewById(R.id.voirDisponibilite);
        voirDisponibilite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestQueue queue = Volley.newRequestQueue(getActivity());

                StringRequest request = new StringRequest(Request.Method.POST, url2, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        try {
                            JSONArray mJsonArray = new JSONArray(s);

                            String message="";
                            for (int i=0;i<mJsonArray.length();i++){
                                s=s+1;
                                message = message + getString(R.string.disponibilite)+ " "+ i + " : \n" +
                                        getString(R.string.debut) + mJsonArray.getJSONObject(i).getString("datedispoDebut") +" \n"+
                                        getString(R.string.fin)+ mJsonArray.getJSONObject(i).getString("datedispofin") + " \n\n";
                            }
                            AlertDialog.Builder builder = new AlertDialog.Builder(context);
                            builder.setMessage(message)
                                    .setTitle(R.string.dispo_proprio)
                                    .setPositiveButton(R.string.fermer, new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {

                                        }
                                    })
                                    .show();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), "erreur", Toast.LENGTH_SHORT).show();
                    }
                }
                ) {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map<String,String> map = new HashMap<String, String>();

                        map.put("id_annonce", String.valueOf(idanc));

                        return map;
                    }
                };

                queue.add(request);

            }
        });
        //******************************************************************************************
        //Prendre un rendez-vous pour une visite
        ImageButton prendreRDV = (ImageButton) v.findViewById(R.id.prendreRDV);
        prendreRDV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                final String Id_user = sharedPreferences.getString("Id_user","");

                if (!Id_user.equals("")) {

                    AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
                    LayoutInflater inflater = getActivity().getLayoutInflater();
                    //this is what I did to added the layout to the alert dialog
                    View layout = inflater.inflate(R.layout.dialog_rdv, null);
                    alert.setView(layout);

                    final EditText date = (EditText) layout.findViewById(R.id.editDate);
                    final EditText time = (EditText) layout.findViewById(R.id.editTime);
                    final EditText lieu = (EditText) layout.findViewById(R.id.editLieu);


                    date.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // TODO Auto-generated method stub
                            //To show current date in the datepicker
                            Calendar mcurrentDate = Calendar.getInstance();
                            int mYear = mcurrentDate.get(Calendar.YEAR);
                            int mMonth = mcurrentDate.get(Calendar.MONTH);
                            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);

                            DatePickerDialog mDatePicker;
                            mDatePicker = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                    // TODO Auto-generated method stub
                    /*      Your code   to get date and time    */
                                    selectedmonth = selectedmonth + 1;
                                    date.setText(selectedyear + "-" + selectedmonth + "-" + selectedday);
                                }
                            }, mYear, mMonth, mDay);
                            mDatePicker.setTitle("Selectionner la date du Rendez-vous");
                            mDatePicker.show();
                        }
                    });

                    time.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            // TODO Auto-generated method stub
                            Calendar mcurrentTime = Calendar.getInstance();
                            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                            int minute = mcurrentTime.get(Calendar.MINUTE);
                            TimePickerDialog mTimePicker;
                            mTimePicker = new TimePickerDialog(getActivity(), new TimePickerDialog.OnTimeSetListener() {
                                @Override
                                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                                    time.setText(selectedHour + ":" + selectedMinute);
                                }
                            }, hour, minute, false);
                            mTimePicker.setTitle("Selectionner l'heure du Rendez-vous");
                            mTimePicker.show();
                        }
                    });

                    alert.setPositiveButton(R.string.valider, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences("user", Context.MODE_PRIVATE);
                            final String Id_user = sharedPreferences.getString("Id_user", "");

                            if (!Id_user.equals("")) { // le user est connecté donc peut ajoute un commentaire

                                RequestQueue queue = Volley.newRequestQueue(getActivity());

                                StringRequest request = new StringRequest(Request.Method.POST, url4, new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String s) {
                                        Toast.makeText(getActivity(), s, Toast.LENGTH_SHORT).show();
                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError volleyError) {
                                        Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
                                        Toast.makeText(getActivity(), "erreur", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                ) {
                                    @Override
                                    protected Map<String, String> getParams() throws AuthFailureError {

                                        Map<String, String> map = new HashMap<String, String>();

                                        map.put("Id_annonce_rdv", String.valueOf(idanc));
                                        map.put("Id_user_rdv", Id_user);
                                        map.put("Id_proprio_rdv", annonce.getUtilisateur());
                                        map.put("Lieu", lieu.getText().toString());
                                        map.put("Note", String.valueOf((int) ratingBar.getRating()));
                                        map.put("Date_rdv", date.getText().toString());
                                        map.put("Heure_rdv", time.getText().toString() + ":00");
                                        map.put("Valider", "notYet");
                                        return map;
                                    }
                                };

                                queue.add(request);



                                TastyToast.makeText(getActivity(), "Rendez-vous ajouté !"
                                        , TastyToast.LENGTH_LONG, TastyToast.SUCCESS);


                        }}
                    });
                    alert.setNegativeButton(R.string.annuler, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    alert.show();
                    } else {
                    TastyToast.makeText(getActivity(), "Vous devez d'abord vous connecter ou " +
                            "vous inscrire !", TastyToast.LENGTH_LONG, TastyToast.WARNING);
                }
            }
        });
        //******************************************************************************************
        //Localisation du logement sur la carte
        ImageButton localisationBtn = (ImageButton) v.findViewById(R.id.localisationBtn);
        localisationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),MapsActivity.class);
                intent.putExtra("latitudeArrive",annonce.getLatitude());
                intent.putExtra("longitudeArrive",annonce.getLongitude());
                intent.putExtra("region",annonce.getRegion());
                startActivity(intent);
            }
        });

        //******************************************************************************************
        ImageButton placeBtn = (ImageButton) v.findViewById(R.id.placeBtn);
        placeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                placePicker(annonce.getLatitude(),annonce.getLongitude());
            }
        });

        //******************************************************************************************
        return v;
    }

    public void afficherCommentaire(final ProgressBar progressBar, final ListView listView,
                                    final CustumAdapterCommentaire custumAdapterCommentaire){
        RequestQueue queue = Volley.newRequestQueue(getActivity());

        StringRequest request = new StringRequest(Request.Method.POST, url3, new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                progressBar.setVisibility(View.INVISIBLE);

                try {
                    JSONArray mJsonArray = new JSONArray(s);

                    for (int i=0;i<mJsonArray.length();i++){

                        Commentaire commentaire = new Commentaire();

                        commentaire.setNom(mJsonArray.getJSONObject(i).getString("nom"));
                        commentaire.setPrenom(mJsonArray.getJSONObject(i).getString("prenom"));
                        commentaire.setText(mJsonArray.getJSONObject(i).getString("text"));
                        commentaire.setNote(mJsonArray.getJSONObject(i).getString("note"));
                        commentaire.setDate_comm(mJsonArray.getJSONObject(i).getString("date_comm"));
                        commentaire.setTime_comm(mJsonArray.getJSONObject(i).getString("time_comm"));

                        listCommentaire.add(commentaire);
                    }

                    listView.setAdapter(custumAdapterCommentaire);
                    Utility.setListViewHeightBasedOnChildren(listView);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(getActivity(), volleyError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String,String> map = new HashMap<String, String>();

                map.put("id_annonce", String.valueOf(idanc));

                return map;
            }
        };

        queue.add(request);

    }

    //----------------------------------------------------------------------------------------------
    //PLACE PICKER
    public void placePicker(double latitudeArrive,double longitudeArrive){
        LatLngBounds BOUNDS_MOUNTAIN_VIEW;

        double latRadian = Math.toRadians(latitudeArrive);

        double degLatKm = 110.574235;
        double degLongKm = 110.572833 * Math.cos(latRadian);
        double deltaLat = 100 / 1000.0 / degLatKm;
        double deltaLong = 100 / 1000.0 / degLongKm;

        double minLat = latitudeArrive - deltaLat;
        double minLong = longitudeArrive - deltaLong;
        double maxLat = latitudeArrive + deltaLat;
        double maxLong = longitudeArrive + deltaLong;


        BOUNDS_MOUNTAIN_VIEW = new LatLngBounds(
                new LatLng(minLat, minLong),new LatLng(maxLat, maxLong));

        try {
            PlacePicker.IntentBuilder intentBuilder =
                    new PlacePicker.IntentBuilder();
            intentBuilder.setLatLngBounds(BOUNDS_MOUNTAIN_VIEW);
            Intent intent2 = intentBuilder.build(getActivity());
            startActivityForResult(intent2, PLACE_PICKER_REQUEST);

        } catch (GooglePlayServicesRepairableException
                | GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    public void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {

        if (requestCode == PLACE_PICKER_REQUEST
                && resultCode == Activity.RESULT_OK) {

            final Place place = PlacePicker.getPlace(getActivity(), data);
            LatLng address = place.getLatLng();
            placePicker(address.latitude,address.longitude);

        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
    //----------------------------------------------------------------------------------------------

    public void prepareAnnonceData_main() {
        String url = Consts.web_host+"/getDetail?dens="+new DeviceService( getActivity()).getScreenDensity()+"&idanc="+idanc;
        RequestQueue queue = RequestQueueSingleton.getInstance( getActivity()).getRequestQueue();
        JsonArrayRequest arrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {
                List<Annonce> listAnnonce = Arrays.asList(new Gson().fromJson(jsonArray.toString(),Annonce[].class));
                annonce = listAnnonce.get(0);
                viewPager= (ViewPager) v.findViewById(R.id.view_pager);
                custumSwipeAdapter=new CustumSwipeAdapter(getActivity(),listAnnonce.get(0).getListeImageDetai());
                viewPager.setAdapter(custumSwipeAdapter);

                //ImageView gradeImage = (ImageView) findViewById(R.id.grandeImage);
                TextView titre = (TextView) v.findViewById(R.id.titre);
                TextView prix = (TextView) v.findViewById(R.id.prix);
                TextView description = (TextView) v.findViewById(R.id.description);
                TextView surface = (TextView) v.findViewById(R.id.surface);
                TextView nbrDeChambres = (TextView) v.findViewById(R.id.nbrDeChambres);
                TextView typeLog = (TextView) v.findViewById(R.id.typeLog);
                TextView region = (TextView) v.findViewById(R.id.region);

                //gradeImage.setImageResource(annonce.getGrandeImage());
                titre.setText(getString(R.string.titre) + " : " + titre_);
                description.setText(getString(R.string.description) + " " + annonce.getDescription() );
                prix.setText(getString(R.string.prix) + " : " + price + " " + getString(R.string.dzd));
                surface.setText(getString(R.string.surface) + " : " + annonce.getSurface() + " " + getString(R.string.metreCarre));
                nbrDeChambres.setText(getString(R.string.nombre_de_chambre)+ " " + annonce.getNbrChambres());
                typeLog.setText(getString(R.string.type_de_logement) + " " + annonce.getTypelogm());
                region.setText(getString(R.string.region) + " : " + annonce.getRegion());


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"erreur detail annonce  ! Detail Fragment ", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(arrayRequest);
    }

}
