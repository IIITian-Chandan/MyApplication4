package com.example.opensource.myapplication.activities;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.opensource.myapplication.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

public class ShortestPath extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shortest_path);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        try {
            // Customise the styling of the base map using a JSON object defined
            // in a raw resource file.
            boolean success = googleMap.setMapStyle(
                    MapStyleOptions.loadRawResourceStyle(
                            this, R.raw.style_json));

            if (!success) {
                Log.e("TAG", "Style parsing failed.");
            }
        } catch (Resources.NotFoundException e) {
            Log.e("TAG", "Can't find style. Error: ", e);
        }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyLoc", 0);
        double x = Double.parseDouble(pref.getString("latKey", null));
        double y = Double.parseDouble(pref.getString("longKey", null));
        // Add a marker in Sydney and move the camera
        SharedPreferences pref1 = getApplicationContext().getSharedPreferences("MyProfile", 0);
        String name = pref1.getString("nameKey", null);
        LatLng myloc = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(myloc).title("Hi " + name + "!You are Here"));

        String[] Lat_arr = {"15.3495285", "15.3515408", "15.3515408", "15.3514167"};
        String[] Long_arr = {"75.0896899", "75.0789276", "75.0789276", "75.0666967"};
        for (int i = 0; i < Lat_arr.length; i++) {
            double lat_near = Double.parseDouble(Lat_arr[i]);
            double lon_near = Double.parseDouble(Long_arr[i]);
            // mMap.addMarker(new MarkerOptions().position(new LatLng(lat_near, lon_near)).title("Repair me").icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_repair)));
            MarkerOptions marker = new MarkerOptions().position(new LatLng(lat_near, lon_near)).title("Repair me");
            marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
            googleMap.addMarker(marker);
        }

        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc,zoom));

        CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(myloc, 10);
        mMap.animateCamera(cameraUpdate);
    }
}
