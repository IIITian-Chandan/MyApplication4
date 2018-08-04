package com.example.opensource.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.activities.Calender.CalenderActivity;
import com.example.opensource.myapplication.classes.GPSTracker;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class Serve360 extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyLoc";


    //------for map
    public static final String Latl = "latKey";
    public static final String Longl = "longKey";
    String PASSWORD = null, NAME = null, EMPID = null, EMAIL = null, PHONENUMBER = null, ADDRESS = null;
    // GPSTracker class
    GPSTracker gps;

    //===================================
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serve360);


        //============================check internet connection
        if (isNetworkConnectionAvailable() == false) {
            Serve360.this.startActivity(new Intent(Settings.ACTION_WIRELESS_SETTINGS));
        } else {
            //================================================

            //Gps
            gps = new GPSTracker(Serve360.this);

            // check if GPS enabled
            if (gps.canGetLocation()) {

                double latitude = gps.getLatitude();
                double longitude = gps.getLongitude();
                SharedPreferences sharedpreferences;
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Latl, String.valueOf(latitude));
                editor.putString(Longl, String.valueOf(longitude));
                editor.commit();

                // \n is for new line
                Toast.makeText(getApplicationContext(), "Your Location is - \nLat: "
                        + latitude + "\nLong: " + longitude, Toast.LENGTH_LONG).show();
            } else {
                // can't get location
                // GPS or Network is not enabled
                // Ask user to enable GPS/network in settings
                gps.showSettingsAlert();
            }


            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyProfile", 0);
            String x = pref.getString("empKey", null);
            String y = pref.getString("passKey", null);

            File f = new File(
                    "/data/data/com.example.opensource.myapplication/shared_prefs/MyProfile.xml");
            if (f.exists()) {
                Log.d("TAG", "SharedPreferences Name_of_your_preference : exist");
                check(x, y);
            } else {
                Log.d("TAG", "Setup default preferences");
                Toast.makeText(getApplicationContext(), "No Saved Data:Redirecting to Login", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Serve360.this, LoginActivity.class);
                startActivity(intent);
            }
        }
    }

    //-----------------------------------------Auto Validate account---------------------------
    public void check(String x, String y) {
        BackGround b = new BackGround();
        b.execute(x, y);

    }

    public void checkNetworkConnection() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("No internet Connection");
        builder.setMessage("Please turn on internet connection to continue");
        builder.setNegativeButton("close", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

//===========================================method to check internet======

    public boolean isNetworkConnectionAvailable() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnected();
        if (isConnected) {
            Log.d("Network", "Connected");
            return true;
        } else {
            checkNetworkConnection();
            Log.d("Network", "Not Connected");
            return false;
        }
    }

    @SuppressLint("NewApi")
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String empid = params[0];
            String password = params[1];
            String data = "";
            int tmp;

            try {
                try {
                    URL url = new URL("https://serve360.000webhostapp.com/app/valid/login.php");
                    String urlParams = "empid=" + empid + "&password=" + password;

                    HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                    httpURLConnection.setDoOutput(true);
                    OutputStream os = httpURLConnection.getOutputStream();
                    os.write(urlParams.getBytes());
                    os.flush();
                    os.close();

                    InputStream is = httpURLConnection.getInputStream();
                    while ((tmp = is.read()) != -1) {
                        data += (char) tmp;
                    }

                    is.close();
                    httpURLConnection.disconnect();

                    return data;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                    return "Exception: " + e.getMessage();
                }

            } catch (
                    Exception e) {
                e.printStackTrace();
                return "Exception: " + e.getMessage();
            }
        }

        @Override
        protected void onPostExecute(String s) {
            String err = null;
            try {
                JSONObject root = new JSONObject(s);
                JSONObject user_data = root.getJSONObject("user_data");
                NAME = user_data.getString("name");
                EMPID = user_data.getString("emp_id");
                EMAIL = user_data.getString("email");
                PHONENUMBER = user_data.getString("phoneno");
                ADDRESS = user_data.getString("address");
                PASSWORD = user_data.getString("password");
            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }

            if (EMPID.equals("null")) {

            } else {
                Intent i = new Intent(Serve360.this, CalenderActivity.class);
                startActivity(i);
            }

        }

//-------------------------------------------------------------------------------------------------------------------
    }
    //================================

}
