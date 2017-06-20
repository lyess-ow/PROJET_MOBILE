package com.example.kerkouche.tp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public List<Annonce> listAnnonce = new ArrayList<Annonce>();
    public  List<User> listUser = new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(getString(R.string.string27));

        //shared preferences user
        SharedPreferences sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        String Id_user = sharedPreferences.getString("Id_user","");



        //******************************************************************************************
        if (isTwoPane()){

            listFragment listFragment = new listFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame1,listFragment);

            DetailFragment detailFragment = new DetailFragment();

            Bundle bundle = new Bundle();
            bundle.putInt("annonce", 0);

            detailFragment.setArguments(bundle);
            ft.replace(R.id.frame2,detailFragment);

            ft.commit();

        } else {

            listFragment listFragment = new listFragment();
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.frame1,listFragment);
            ft.commit();
        }

        //******************************************************************************************

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }


    public boolean isTwoPane(){
        View view = findViewById(R.id.frame2);
        return (view!=null);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        //******************************************************************************************
        if (id == R.id.accueil) {
            if (isTwoPane()){

                listFragment listFragment = new listFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,listFragment);

                DetailFragment detailFragment = new DetailFragment();

                Bundle bundle = new Bundle();
                bundle.putInt("annonce", 0);

                detailFragment.setArguments(bundle);
                ft.replace(R.id.frame2,detailFragment);

                ft.commit();
            } else {

                listFragment listFragment = new listFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,listFragment);
                ft.commit();
            }
            //**************************************************************************************
        } else if (id == R.id.voirAnnoncesProprio) {
            if (isTwoPane()){

                Annonce_proprietaireFragment annonce_proprietaireFragment = new Annonce_proprietaireFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,annonce_proprietaireFragment);

                VoirDemandeRDVFragment voirDemandeRDVFragment = new VoirDemandeRDVFragment();
                ft.replace(R.id.frame2,voirDemandeRDVFragment);

                ft.commit();
            } else {
                Annonce_proprietaireFragment annonce_proprietaireFragment = new Annonce_proprietaireFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,annonce_proprietaireFragment);
                ft.commit();
            }
        } //****************************************************************************************
        else if (id == R.id.voirRDVProprio) {
            if (isTwoPane()){

                Annonce_proprietaireFragment annonce_proprietaireFragment = new Annonce_proprietaireFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,annonce_proprietaireFragment);

                VoirDemandeRDVFragment voirDemandeRDVFragment = new VoirDemandeRDVFragment();
                ft.replace(R.id.frame2,voirDemandeRDVFragment);

                ft.commit();

            } else {
                VoirDemandeRDVFragment voirDemandeRDVFragment = new VoirDemandeRDVFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,voirDemandeRDVFragment);
                ft.commit();
            }
        } //****************************************************************************************
        else if (id == R.id.modifierDisponibilite) {
            if (isTwoPane()){

                listAnnonceProprioFragment listFragment = new listAnnonceProprioFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,listFragment);

                ModifierDisponibiliteFragment modifierDisponibiliteFragment = new ModifierDisponibiliteFragment();
                ft.replace(R.id.frame2,modifierDisponibiliteFragment);

                ft.commit();
            } else {

                listAnnonceProprioFragment listFragment = new listAnnonceProprioFragment();
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frame1,listFragment);
                ft.commit();
            }

        } //****************************************************************************************
        else if (id == R.id.connexion) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.creation) {
            Intent intent = new Intent(MainActivity.this, CreationCompteActivity.class);
            startActivity(intent);
        } else if (id == R.id.deconnexion) {
            SharedPreferences sharedPref = getSharedPreferences("user", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.remove("Id_user");
            editor.commit();

        } else if (id == R.id.startEtStop ){

            Switch switcher;
            switcher = (Switch) findViewById(R.id.drawer_switch);
            switcher.setChecked(true);
            switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                    if (isChecked){
                        Intent intent = new Intent(MainActivity.this, ServiceNotification.class);
                        ServiceNotification.ServiceIsRun=true;
                        startService(intent);
                    } else {
                        ServiceNotification.ServiceIsRun=false;
                    }
                }
            });
        }



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
