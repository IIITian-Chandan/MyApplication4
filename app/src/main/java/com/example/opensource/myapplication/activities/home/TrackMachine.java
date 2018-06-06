package com.example.opensource.myapplication.activities.home;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class TrackMachine extends AppCompatActivity {
    EditText ed1;
    Button btn1;
    TextView tverr, tv1, tv2, tv3, tv4, tv5, tv6, tv7, tv8, tv9, tv10, tv11, tv12, tv13, tv14, tv15, tv16, tv17, tv18, tv19, tv20;
    String mcnid;
    LinearLayout ll;
    String Machine_No = null, Company_Name, AMC_Warranty_Status, Address, Region, Customer_Mail_Id, GST_Number, Customer_Name, Owner_Phone_No, Date_of_Shipping, Date_of_Installation, Operator_Name, Operator_Phone_No, Installation_Team, Installation_Sign_off_date, Training_given_by, Trainer_Sign_off_date, Hamsa_UPG_Date, General_Upgrade, New_Owner, Warranty_completion_Date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_machine);
        ed1 = findViewById(R.id.edmid);
        btn1 = findViewById(R.id.btnsmt);
        tv1 = findViewById(R.id.tv2);
        tv2 = findViewById(R.id.tv3);
        tv3 = findViewById(R.id.tv4);
        tv4 = findViewById(R.id.tv5);
        tv5 = findViewById(R.id.tv6);
        tv6 = findViewById(R.id.tv7);
        tv7 = findViewById(R.id.tv8);
        tv8 = findViewById(R.id.tv9);
        tv9 = findViewById(R.id.tv10);
        tv10 = findViewById(R.id.tv11);
        tv11 = findViewById(R.id.tv12);
        tv12 = findViewById(R.id.tv13);
        tv13 = findViewById(R.id.tv14);
        tv14 = findViewById(R.id.tv15);
        tv15 = findViewById(R.id.tv16);
        tv16 = findViewById(R.id.tv17);
        tv17 = findViewById(R.id.tv18);
        tv18 = findViewById(R.id.tv19);
        tv19 = findViewById(R.id.tv20);
        tv20 = findViewById(R.id.tv21);
        tverr = findViewById(R.id.tverr);
        ll = findViewById(R.id.ll2);

    }

    public void machine(View v) {
        mcnid = String.valueOf(ed1.getText());
        check(mcnid);
    }

    public void check(String x) {
        BackGround b = new BackGround();
        b.execute(x);

    }

    class BackGround extends AsyncTask<String, String, String> {

        @Override
        protected String doInBackground(String... params) {
            String mcnid = params[0];

            String data = "";
            int tmp;

            try {
                try {
                    URL url = new URL("https://serve360.000webhostapp.com/app/valid/trackmachine.php");
                    String urlParams = "mcnid=" + mcnid;

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

                Machine_No = user_data.getString("Machine_No");
                Company_Name = user_data.getString("Company_Name");
                AMC_Warranty_Status = user_data.getString("AMC_Warranty_Status");
                Address = user_data.getString("Address");
                Region = user_data.getString("Region");
                Customer_Mail_Id = user_data.getString("Customer_Mail_Id");
                GST_Number = user_data.getString("GST_Number");
                Customer_Name = user_data.getString("Customer_Name");
                Owner_Phone_No = user_data.getString("Owner_Phone_No");
                Date_of_Shipping = user_data.getString("Date_of_Shipping");
                Date_of_Installation = user_data.getString("Date_of_Installation");
                Operator_Name = user_data.getString("Operator_Name");
                Operator_Phone_No = user_data.getString("Operator_Phone_No");
                Installation_Team = user_data.getString("Installation_Team");
                Installation_Sign_off_date = user_data.getString("Installation_Sign_off_date");
                Training_given_by = user_data.getString("Training_given_by");
                Trainer_Sign_off_date = user_data.getString("Trainer_Sign_off_date");
                Hamsa_UPG_Date = user_data.getString("Hamsa_UPG_Date");
                General_Upgrade = user_data.getString("General_Upgrade");
                New_Owner = user_data.getString("New_Owner");
                Warranty_completion_Date = user_data.getString("Warranty_completion_Date");

            } catch (JSONException e) {
                e.printStackTrace();
                err = "Exception: " + e.getMessage();
            }

            if (mcnid.equals(Machine_No) || !mcnid.equals(null)) {

                tv1.setText(Company_Name);
                tv2.setText(AMC_Warranty_Status);
                tv3.setText(Address);
                tv4.setText(Region);
                tv5.setText(Customer_Mail_Id);
                tv6.setText(GST_Number);
                tv7.setText(Customer_Name);
                tv8.setText(Owner_Phone_No);
                tv9.setText(Date_of_Shipping);
                tv10.setText(Date_of_Installation);
                tv11.setText(Operator_Name);
                tv12.setText(Operator_Phone_No);
                tv13.setText(Installation_Team);
                tv14.setText(Installation_Sign_off_date);
                tv15.setText(Training_given_by);
                tv16.setText(Trainer_Sign_off_date);
                tv17.setText(Hamsa_UPG_Date);
                tv18.setText(General_Upgrade);
                tv19.setText(New_Owner);
                tv20.setText(Warranty_completion_Date);
                ll.setVisibility(View.VISIBLE);

            } else {
                tverr.setText("Enter your valid credentials");
            }

        }

    }
}