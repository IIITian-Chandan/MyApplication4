package com.example.opensource.myapplication.activities.Calender;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.classes.HttpServiceClass;
import com.wang.avi.AVLoadingIndicatorView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class CalenderActivity extends Activity {

    RecyclerView CalenderRecycleView;
    AVLoadingIndicatorView progressBar;

    String HttpURL = "https://serve360.000webhostapp.com/getCalendar.php";

    ArrayList<String> s_no, machine_id, machine_issue, machine_location, i_date, accept_status;
    Context context;
    RecyclerviewAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.activity_calender);

        CalenderRecycleView = findViewById(R.id.recycleview_calender);

        progressBar = findViewById(R.id.ProgressBar1);


        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyProfile", 0);
        String empid = pref.getString("empKey", null);
        CallMethod(empid);


    }

    public void CallMethod(String id) {
        progressBar.show();
        new ParseJSonDataClass(this).execute(id);

    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    private class ParseJSonDataClass extends AsyncTask<String, String, String> {
        public Context context;
        String FinalJSonResult;

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


                            s_no = new ArrayList<String>();
                            machine_id = new ArrayList<String>();
                            machine_issue = new ArrayList<String>();
                            machine_location = new ArrayList<String>();
                            i_date = new ArrayList<String>();
                            accept_status = new ArrayList<String>();


                            for (int i = 0; i < jsonArray.length(); i++) {


                                jsonObject = jsonArray.getJSONObject(i);

                                s_no.add(jsonObject.getString("CALENDAR_NO"));
                                machine_id.add(jsonObject.getString("MACHINE_ID"));
                                i_date.add(jsonObject.getString("DATE"));
                                machine_issue.add(jsonObject.getString("ISSUE"));
                                machine_location.add(jsonObject.getString("LOCATION"));
                                accept_status.add(jsonObject.getString("ACCEPTANCE_STATUS"));

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
            progressBar.hide();

            CalenderRecycleView.setVisibility(View.VISIBLE);


            CalenderRecycleView.setLayoutManager(new LinearLayoutManager(context));
            DividerItemDecoration itemDecorator = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
            itemDecorator.setDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.shape));
            CalenderRecycleView.addItemDecoration(itemDecorator);
            adapter = new RecyclerviewAdapter(context, s_no, machine_id, machine_issue, machine_location, i_date, accept_status);
            CalenderRecycleView.setAdapter(adapter);
            Animation animation = AnimationUtils.loadAnimation(context, R.anim.swing_up_right);
            CalenderRecycleView.setAnimation(animation);

            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setMessage("Do Long Press for Accept or Reject.");

            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //perform any action
                    //new ParseJSonDataClass(mcontext).execute(ms_no.get(position), value);
                }
            });
            //creating alert dialog
            AlertDialog alertDialog = builder.create();
            alertDialog.show();


        }
    }

}