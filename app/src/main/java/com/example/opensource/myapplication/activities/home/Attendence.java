package com.example.opensource.myapplication.activities.home;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.classes.MyService;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MapStyleOptions;
import com.google.android.gms.maps.model.MarkerOptions;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Attendence extends FragmentActivity implements OnMapReadyCallback {
    static String cur_date, cur_time;
    Button btn1, btn2, btn3;
    TextView tv1, tv2, tv3, tv4;
    long timeInMilliseconds = 0L;
    long timeSwapBuff = 0L;
    long updatedTime = 0L;
    private GoogleMap mMap;
    //======================timer in notification==============
    private long startTime = 0L;
    private Handler customHandler = new Handler();
    private Runnable updateTimerThread = new Runnable() {
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updatedTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updatedTime / 1000);
            int mins = secs / 60;
            secs = secs % 60;
            int milliseconds = (int) (updatedTime % 1000);
            String x = "" + mins + ":"
                    + String.format("%02d", secs) + ":"
                    + String.format("%03d", milliseconds);
            customHandler.postDelayed(this, 0);
            addNotification(x);
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attendence);
        btn1 = findViewById(R.id.btn_chkin);
        btn2 = findViewById(R.id.btn_chkout);
        btn3 = findViewById(R.id.btn3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        date();
        tv1.setText(cur_date);
        tv2.setText(cur_time);
        tv3.setVisibility(View.GONE);
        tv4.setVisibility(View.GONE);
        btn3.setVisibility(View.GONE);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startService(new Intent(Attendence.this, MyService.class));
                tv3.setText("Time starts");
                tv3.setVisibility(View.VISIBLE);

                //=====================
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimerThread, 0);


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopService(new Intent(Attendence.this, MyService.class));

                tv3.setText("Time ends");
                tv3.setVisibility(View.VISIBLE);

                tv4.setText(MyService.x);
                tv4.setText(MyService.x);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.VISIBLE);


                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimerThread);


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv4.setText(MyService.x);
                tv3.setText("Work Duration:");
                tv3.setVisibility(View.VISIBLE);
                tv4.setVisibility(View.VISIBLE);
                btn1.setVisibility(View.GONE);
                btn2.setVisibility(View.GONE);
                btn3.setVisibility(View.GONE);
            }
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    //================================================Notification==========================================

    // Toast.makeText(Attendence.this,"HAI",Toast.LENGTH_LONG).show();

    //================================================showing current date and time======================
    public void date() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        cur_date = simpleDateFormat.format(calendar.getTime());
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("HH:mm:ss");
        cur_time = simpleTimeFormat.format(calendar.getTime());

    }

    private void addNotification(String x) {
        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle("Nanopix")
                        .setContentTitle("Hey Your Timer is Running")
                        .setContentText("Done with Work.Click Here.")
                        .setContentText(x);

        Intent notificationIntent = new Intent(this, Attendence.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);

        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }

    //========================================================for Map=======================================
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
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));
    }
}
