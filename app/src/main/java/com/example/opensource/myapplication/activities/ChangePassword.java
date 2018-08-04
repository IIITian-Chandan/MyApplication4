package com.example.opensource.myapplication.activities;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.activities.Calender.CalenderActivity;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ChangePassword extends Activity {
    TextView tv1, tv2;
    EditText ed1, ed2;
    Button btn1;
    String encrypt;
    String name;

    //------------------------------SHA-1 Encryption-------------------------------------
    private static String convertToHex(byte[] data) {
        StringBuffer buf = new StringBuffer();
        for (int i = 0; i < data.length; i++) {
            int halfbyte = (data[i] >>> 4) & 0x0F;
            int two_halfs = 0;
            do {
                if ((0 <= halfbyte) && (halfbyte <= 9))
                    buf.append((char) ('0' + halfbyte));
                else
                    buf.append((char) ('a' + (halfbyte - 10)));
                halfbyte = data[i] & 0x0F;
            } while (two_halfs++ < 1);
        }
        return buf.toString();
    }

    public static String SHA1(String text)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md;
        md = MessageDigest.getInstance("SHA-1");
        byte[] sha1hash = new byte[40];
        md.update(text.getBytes("iso-8859-1"), 0, text.length());
        sha1hash = md.digest();
        return convertToHex(sha1hash);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyProfile", 0);
        name = pref.getString("nameKey", null);


        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tvwarn);
        ed1 = findViewById(R.id.edcng);
        ed2 = findViewById(R.id.edcng_cnf);
        btn1 = findViewById(R.id.btncng);

        tv1.setText("Hi " + name + ". Change Your Password.");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String confPass = String.valueOf(ed2.getText());
                String password = String.valueOf(ed1.getText());
                if (password.equals(confPass)) {

                    try {
                        encrypt = SHA1(password);
                        getSharedPreferences("MyProfile", 0 /*FILE_MODE*/)
                                .edit()
                                .putString("passKey", encrypt)
                                .apply();

                    } catch (NoSuchAlgorithmException e) {
                        e.printStackTrace();
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    check(encrypt);

                } else {
                    tv2.setText("Enter same value");
                }
            }
        });


    }

    public void check(String x) {
        BackGround b = new BackGround();
        b.execute(x);

    }

    @SuppressLint("NewApi")
    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String pass = params[0];

            String empid;
            SharedPreferences pref = getApplicationContext().getSharedPreferences("MyProfile", 0);

            empid = pref.getString("empKey", null);

            String data = "";
            int tmp;

            try {
                URL url = new URL("https://serve360.000webhostapp.com/app/valid/update.php");
                String urlParams = "emp=" + empid + "&password=" + pass;

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
        }

        @Override
        protected void onPostExecute(String s) {
            if (s.equals("")) {
                s = "Data saved successfully.";
                tv2.setText("Password Updated");

            }
            Toast.makeText(getApplicationContext(), s, Toast.LENGTH_LONG).show();
            Intent intent = new Intent(ChangePassword.this, CalenderActivity.class);
            startActivity(intent);
        }


    }
    //--------------------------------------------------------------------------------------------------

}
