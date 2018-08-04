package com.example.opensource.myapplication.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.GridLayout;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.activities.home.Attendence;
import com.example.opensource.myapplication.activities.home.DashboardActivity;
import com.example.opensource.myapplication.activities.home.FormActivity;
import com.example.opensource.myapplication.activities.home.ServiceOrder.ServiceOrderActivity;
import com.example.opensource.myapplication.activities.home.TrackMachineActivity;
import com.example.opensource.myapplication.activities.home.TravelTrackerActivity;
import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class HomeActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 200;
    GridLayout mainGrid;
    FloatingActionMenu materialDesignFAM;
    FloatingActionButton f_call, f_loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        requestPermission();
        materialDesignFAM = findViewById(R.id.f_menu);
        f_call = findViewById(R.id.f_call);
        f_loc = findViewById(R.id.f_loc);

        f_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dial = "tel:" + "7996283813";
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.parse(dial)));
            }
        });

        f_loc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ShortestPath.class);
                startActivity(intent);
            }
        });


        mainGrid = findViewById(R.id.mainGrid);

        setSingleEvent(mainGrid);
    }

    private void setSingleEvent(GridLayout mainGrid) {
        //Loop all child item of Main Grid
        for (int i = 0; i < mainGrid.getChildCount(); i++) {
            //You can see , all child item is CardView , so we just cast object to CardView
            CardView cardView = (CardView) mainGrid.getChildAt(i);
            final int finalI = i;
            if (i == 0) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, TravelTrackerActivity.class);
                        startActivity(intent);
                    }
                });
            } else if (i == 1) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(HomeActivity.this, Attendence.class);
                        startActivity(intent);
                    }
                });
            } else if (i == 2) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent intent = new Intent(HomeActivity.this, FormActivity.class);
                        startActivity(intent);
                    }
                });
            } else if (i == 3) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, ServiceOrderActivity.class);
                        startActivity(intent);

                    }
                });
            } else if (i == 4) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, TrackMachineActivity.class);
                        startActivity(intent);

                    }
                });
            } else if (i == 5) {
                cardView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(HomeActivity.this, DashboardActivity.class);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, GET_ACCOUNTS, WRITE_EXTERNAL_STORAGE, CALL_PHONE}, PERMISSION_REQUEST_CODE);
    }


}
