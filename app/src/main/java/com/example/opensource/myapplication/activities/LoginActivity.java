package com.example.opensource.myapplication.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CALL_PHONE;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.GET_ACCOUNTS;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class LoginActivity extends AppCompatActivity {
    public static final String MyPREFERENCES = "MyProfile";
    public static final String Namel = "nameKey";
    public static final String Passwordl = "passKey";
    public static final String PhoneL = "phoneKey";
    public static final String Emaill = "emailKey";
    public static final String Addressl = "addKey";
    public static final String Empidl = "empKey";
    private static final int PERMISSION_REQUEST_CODE = 200;
    EditText ed1, ed2;
    Button btn1, btn2;
    TextView tv1;
    String Empid, Password, encrypt;
    String NAME = null, EMAIL = null, PHONENUMBER = null, ADDRESS = null, EMPID = null, PASSWORD = null;

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
        setContentView(R.layout.activity_login);
        requestPermission();
        tv1 = findViewById(R.id.tverr);
        ed1 = findViewById(R.id.edemp);
        ed2 = findViewById(R.id.edpwd);

    }

    //---------------------------------Login----------------------------------------
    @SuppressLint("NewApi")
    public void login(View v) {


        Empid = ed1.getText().toString();
        Password = ed2.getText().toString();
        try {
            encrypt = SHA1(Password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        if (Empid.matches("") || Password.matches("")) {
            tv1.setText("Fill the empty blanks");
        }
        //datatype var= (datatype)var1
        else {

            check(Empid, encrypt);
        }

    }
    //----------------------------------------------------------------------------------------------------

    public void check(String x, String y) {
        BackGround b = new BackGround();
        b.execute(x, y);

    }

    //--------------------------------------------------------------------------------------------------
    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, CAMERA, GET_ACCOUNTS, WRITE_EXTERNAL_STORAGE, CALL_PHONE}, PERMISSION_REQUEST_CODE);
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
            Log.d("key", Password);
            if (encrypt.equals(PASSWORD)) {

                Passdata(NAME, EMPID, EMAIL, PHONENUMBER, ADDRESS, PASSWORD);
                Intent intent = new Intent(LoginActivity.this, ChangePassword.class);
                startActivity(intent);
            } else {
                tv1.setText("Enter your valid credentials");
            }

        }

        public void Passdata(String name, String emp, String em, String phn, String add, String pass) {
            SharedPreferences sharedpreferences;
            sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedpreferences.edit();

            editor.putString(Namel, name);
            editor.putString(Empidl, emp);
            editor.putString(Emaill, em);
            editor.putString(PhoneL, phn);
            editor.putString(Addressl, add);
            editor.putString(Passwordl, pass);


            editor.commit();


        }
    }
}