package com.example.opensource.myapplication.activities.home.Calender;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.opensource.myapplication.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class CalenderActivity extends Activity {

    ListView SubjectFullFormListView;
    ProgressBar progressBar;

    String HttpURL = "https://serve360.000webhostapp.com/getCalendar.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_calender);

        SubjectFullFormListView = findViewById(R.id.SubjectFullFormListView);

        progressBar = findViewById(R.id.ProgressBar1);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyProfile", 0);
        String empid = pref.getString("empKey", null);
        CallMethod(empid);


    }

    public void CallMethod(String id) {
        progressBar.setVisibility(View.VISIBLE);
        new ParseJSonDataClass(this).execute(id);

    }


    private class ParseJSonDataClass extends AsyncTask<String, String, String> {
        public Context context;
        String FinalJSonResult;
        List<Subject> SubjectFullFormList;

        public ParseJSonDataClass(Context context) {

            this.context = context;
        }


        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... params) {
            String emp = params[0];
            HttpServiceClass httpServiceClass = new HttpServiceClass("https://serve360.000webhostapp.com/getCalendar.php/?name=" + emp);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();

                    if (FinalJSonResult != null) {

                        JSONArray jsonArray = null;
                        try {

                            jsonArray = new JSONArray(FinalJSonResult);
                            JSONObject jsonObject;
                            Subject subject;

                            SubjectFullFormList = new ArrayList<Subject>();


                            for (int i = 0; i < jsonArray.length(); i++) {


                                subject = new Subject();

                                jsonObject = jsonArray.getJSONObject(i);

                                subject.s_no = jsonObject.getString("CALENDAR_NO");
                                subject.machine_id = jsonObject.getString("MACHINE_ID");
                                subject.date = jsonObject.getString("DATE");
                                subject.machine_issue = jsonObject.getString("ISSUE");
                                subject.machine_location = jsonObject.getString("LOCATION");
                                subject.accept_status = jsonObject.getString("ACCEPTANCE_STATUS");
                                SubjectFullFormList.add(subject);
                            }

                        } catch (JSONException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                } else {

                    Toast.makeText(context, httpServiceClass.getErrorMessage(), Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result)

        {
            progressBar.setVisibility(View.GONE);

            SubjectFullFormListView.setVisibility(View.VISIBLE);

            if (SubjectFullFormList != null) {


                com.example.opensource.myapplication.activities.home.Calender.ListAdapter adapter = new com.example.opensource.myapplication.activities.home.Calender.ListAdapter(SubjectFullFormList, context);

                SubjectFullFormListView.setAdapter(adapter);
            }
        }
    }

}