package com.example.kerkouche.tp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private LocationManager locationManager;
    private LocationListener locationListener;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        //------------------------------------------------------------------------------------------
        Intent intent = getIntent();

        final double latitudeArrive = intent.getDoubleExtra("latitudeArrive",0);
        final double longitudeArrive = intent.getDoubleExtra("longitudeArrive",0);
        final String region = intent.getStringExtra("region");

        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        locationListener = new LocationListener() {

            @Override
            public void onLocationChanged(Location location) {

                String depart = String.valueOf(location.getLatitude()) +","+ String.valueOf(location.getLongitude());
                String arrivee = String.valueOf(latitudeArrive) + "," + String.valueOf(longitudeArrive);

                new ItineraireTask(MapsActivity.this, mMap, depart, arrivee).execute();
            }

            @Override
            public void onStatusChanged(String provider, int status, Bundle extras) {

            }

            @Override
            public void onProviderEnabled(String provider) {

            }

            @Override
            public void onProviderDisabled(String provider) {
                Intent i = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(i);
            }
        };
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{
                    Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION
                    ,Manifest.permission.INTERNET
            },10);

            return;
        }else {
            configurebutton();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case 10:
                if (grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    configurebutton();
                return;
        }
    }

    private void configurebutton(){
        // 2eme param rafraichir tt les 5000 cad 5 seconds
        // 3eme param rafraichir tt les 5 metres
        locationManager.requestLocationUpdates("gps", 0, 100, locationListener);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


            /*
        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(latitudeArrive, longitudeArrive);
        mMap.addMarker(new MarkerOptions().position(position).title(region));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,13)); */
    }
}
