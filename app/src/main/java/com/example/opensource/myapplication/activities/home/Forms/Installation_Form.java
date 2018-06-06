package com.example.opensource.myapplication.activities.home.Forms;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.opensource.myapplication.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Installation_Form extends AppCompatActivity implements View.OnClickListener {

    static String region, customer, installation_date, company, signoff_date, person_name, contact,
            address, per1_name, per2_name, per3_name, mach, timeStared, timeEnded;
    static String s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11, s12, s14, s13;
    static String rad31, rad32, rad33, rad34, rad35, rad36;
    private final Callback<Void> callCallback = new Callback<Void>() {
        @Override
        public void onResponse(Call<Void> call, Response<Void> response) {
            Log.d("XXX", "Submitted. " + response);
        }

        @Override
        public void onFailure(Call<Void> call, Throwable t) {
            Log.e("XXX", "Failed", t);
        }
    };
    //    Form 1 Variables
    ImageButton calender2, calender1;
    TextView DOI, install_date, signoff, startTime, endTime;
    Spinner machine;
    EditText reg, cust, comp, per, perno, addr, per1, per2, per3;
    Button part1, part2, part3;
    Button sendData, sendData2;
    LinearLayout layout_1, layout_2, layout_3;
    boolean d;
    int b = 0;
    //    Form 2 Variables
    EditText ser1, ser2, ser3, ser4, ser5, ser6, ser7;
    boolean val;
    //    Form 3 Variables
    ImageButton clock1, clock2;
    CheckBox check1, check2, check3, check4;
    EditText B, C, D, E, percentinput, weight, custfeed;
    RadioButton radioButton_1, radioButton_2, radioButton_3, radioButton_4, radioButton_5, radioButton_6;
    int selected_id_1, selected_id_2, selected_id_3, selected_id_4, selected_id_5, selected_id_6;
    TextView start, end;
    Button next, submit_form;
    Button validate1;
    Button form_nav_btn1, form_nav_btn2, form_nav_btn3, form_nav_btn4, form_nav_btn5, form_nav_btn6;
    RadioGroup rad1, rad2, rad3, rad4, rad5, rad6, rad10;
    boolean result, r1, r2, r3;
    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_installation__form);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://docs.google.com/forms/d/e/")
                .build();
        final QuestionsSpreadsheetWebService spreadsheetWebService = retrofit.create(QuestionsSpreadsheetWebService.class);

        part1 = findViewById(R.id.btn1st);
        part2 = findViewById(R.id.btn2nd);
        part3 = findViewById(R.id.btn3rd);


        validate1 = findViewById(R.id.next);

        install_date = findViewById(R.id.DOI_val);
        signoff = findViewById(R.id.signoff_val);


        machine = findViewById(R.id.machine_select);
        reg = findViewById(R.id.region_val);
        cust = findViewById(R.id.cust_name_Val);
        comp = findViewById(R.id.comp_name_Val);
        per = findViewById(R.id.contact_person_val);
        perno = findViewById(R.id.cont_number_Val);
        addr = findViewById(R.id.address_val);
        per1 = findViewById(R.id.staff_list1);
        per2 = findViewById(R.id.staff_list2);
        per3 = findViewById(R.id.staff_list3);


        ser1 = findViewById(R.id.serial_1);
        ser2 = findViewById(R.id.serial_2);
        ser3 = findViewById(R.id.serial_3);
        ser4 = findViewById(R.id.serial_4);
        ser5 = findViewById(R.id.serial_5);
        ser6 = findViewById(R.id.serial_6);
        ser7 = findViewById(R.id.serial_7);


        start = findViewById(R.id.starting_input);
        end = findViewById(R.id.ending_input);
        E = findViewById(R.id.Ebin_input);
        D = findViewById(R.id.Dbin_input);
        C = findViewById(R.id.Cbin_input);
        B = findViewById(R.id.Bbin_input);
        weight = findViewById(R.id.weight_final);
        percentinput = findViewById(R.id.percentage);
        custfeed = findViewById(R.id.customerfeedback);
        check1 = findViewById(R.id.chkb1);
        check2 = findViewById(R.id.chkb2);
        check3 = findViewById(R.id.chkb3);
        check4 = findViewById(R.id.chkb4);
        rad1 = findViewById(R.id.radio1);
        rad2 = findViewById(R.id.radio2);
        rad3 = findViewById(R.id.radio3);
        rad4 = findViewById(R.id.radio4);
        rad5 = findViewById(R.id.radio5);
        rad6 = findViewById(R.id.radio6);
        rad10 = findViewById(R.id.radio10);


        sendData2 = findViewById(R.id.submit_button);
        sendData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mach = machine.getSelectedItem().toString();
                region = reg.getText().toString();
                customer = cust.getText().toString();
                installation_date = install_date.getText().toString();
                company = comp.getText().toString();
                signoff_date = signoff.getText().toString();
                person_name = per.getText().toString();
                contact = perno.getText().toString();
                address = addr.getText().toString();
                per1_name = per1.getText().toString();
                per2_name = per2.getText().toString();
                per3_name = per3.getText().toString();


                s1 = ser1.getText().toString();
                s2 = ser2.getText().toString();
                s3 = ser3.getText().toString();
                s4 = ser4.getText().toString();
                s5 = ser5.getText().toString();
                s6 = ser6.getText().toString();
                s7 = ser7.getText().toString();
                s8 = E.getText().toString();
                s9 = D.getText().toString();
                s10 = C.getText().toString();
                s11 = B.getText().toString();
                s12 = weight.getText().toString();
                s13 = percentinput.getText().toString();
                s14 = custfeed.getText().toString();

                selected_id_1 = rad1.getCheckedRadioButtonId();
                radioButton_1 = findViewById(selected_id_1);
                rad31 = radioButton_1.getText().toString();

                selected_id_2 = rad2.getCheckedRadioButtonId();
                radioButton_2 = findViewById(selected_id_2);
                rad32 = radioButton_2.getText().toString();

                selected_id_3 = rad3.getCheckedRadioButtonId();
                radioButton_3 = findViewById(selected_id_3);
                rad33 = radioButton_3.getText().toString();

                selected_id_4 = rad4.getCheckedRadioButtonId();
                radioButton_4 = findViewById(selected_id_4);
                rad34 = radioButton_4.getText().toString();

                selected_id_5 = rad5.getCheckedRadioButtonId();
                radioButton_5 = findViewById(selected_id_5);
                rad35 = radioButton_5.getText().toString();

                selected_id_6 = rad6.getCheckedRadioButtonId();
                radioButton_6 = findViewById(selected_id_6);
                rad36 = radioButton_6.getText().toString();

                timeStared = startTime.getText().toString();
                timeEnded = endTime.getText().toString();

                Call<Void> completeQuestionnaireCall = spreadsheetWebService.completeQuestionnaire(mach, region,
                        customer, installation_date, company, signoff_date, person_name, contact, address,
                        per1_name, per2_name, per3_name, s1, s2, s3, s4, s5, s6, s7, rad31, rad32, rad33,
                        rad34, rad35, rad36, s8, s9, s10, s11, s12, s13, s14, timeStared, timeEnded);
                completeQuestionnaireCall.enqueue(callCallback);

            }
        });


//                          Form 1

        calender1 = findViewById(R.id.calender1);
        calender2 = findViewById(R.id.calender2);

        clock1 = findViewById(R.id.start_clock);
        clock2 = findViewById(R.id.end_clock);


        startTime = findViewById(R.id.starting_input);
        endTime = findViewById(R.id.ending_input);

        layout_1 = findViewById(R.id.layout_1);
        layout_2 = findViewById(R.id.layout_2);
        layout_3 = findViewById(R.id.layout_3);

        machine = findViewById(R.id.machine_select);


        layout_2.setVisibility(View.GONE);
        layout_3.setVisibility(View.GONE);

        calender1.setOnClickListener(this);
        calender2.setOnClickListener(this);

        //part1 = (Button) findViewById(R.id.btn1st);
        part1.setOnClickListener(this);

        //part2 = (Button) findViewById(R.id.btn2nd);
        part2.setOnClickListener(this);

        //part3 = (Button) findViewById(R.id.btn3rd);
        part3.setOnClickListener(this);

        validate1.setOnClickListener(this);

        clock1.setOnClickListener(this);
        clock2.setOnClickListener(this);

        part1.setBackgroundColor(Color.CYAN);
        part2.setBackgroundColor(Color.BLACK);
        part3.setBackgroundColor(Color.BLACK);

        part1.setTextSize(30);
        part2.setTextSize(24);
        part3.setTextSize(24);

    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void onClick(View view) {

       /* if(view == form_nav_btn1){
            setContentView(R.layout.installation_form);
            calender1 = (ImageButton) findViewById(R.id.calender1);
            calender2 = (ImageButton) findViewById(R.id.calender2);
            install_date = (TextView) findViewById(R.id.DOI_val);
            signoff = (TextView) findViewById(R.id.signoff_val);

            layout_1 = (LinearLayout) findViewById(R.id.layout_1);
            layout_2 = (LinearLayout) findViewById(R.id.layout_2);
            layout_3 = (LinearLayout) findViewById(R.id.layout_3);

            machine = (Spinner) findViewById(R.id.machine_select);


            layout_2.setVisibility(View.GONE);
            layout_3.setVisibility(View.GONE);

            calender1.setOnClickListener(this);
            calender2.setOnClickListener(this);


            part1 = (Button) findViewById(R.id.btn1st);
            part1.setOnClickListener(this);

            part2 = (Button) findViewById(R.id.btn2nd);
            part2.setOnClickListener(this);

            part3 = (Button) findViewById(R.id.btn3rd);
            part3.setOnClickListener(this);

            part1.setBackgroundColor(Color.CYAN);
            part2.setBackgroundColor(Color.BLACK);
            part3.setBackgroundColor(Color.BLACK);

            part1.setTextSize(30);
            part2.setTextSize(24);
            part3.setTextSize(24);
        }*/


        if (view == calender1) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            install_date.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }

        if (view == calender2) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            signoff.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    }, mYear, mMonth, mDay);
            datePickerDialog.show();
        }


        if (view == part1) {
            layout_1.setVisibility(View.VISIBLE);
            layout_2.setVisibility(View.GONE);

            part1.setBackgroundColor(Color.CYAN);
            part2.setBackgroundColor(Color.BLACK);
            part3.setBackgroundColor(Color.BLACK);

            part1.setTextSize(35);
            part2.setTextSize(24);
            part3.setTextSize(24);


        }

        if (view == part2) {
            layout_2.setVisibility(View.VISIBLE);
            layout_1.setVisibility(View.GONE);

            part2.setBackgroundColor(Color.CYAN);
            part1.setBackgroundColor(Color.BLACK);
            part3.setBackgroundColor(Color.BLACK);

            part2.setTextSize(35);
            part1.setTextSize(24);
            part3.setTextSize(24);
            // check2();

        }

        if (view == part3) {
            layout_3.setVisibility(View.VISIBLE);
            layout_1.setVisibility(View.GONE);
            layout_2.setVisibility(View.GONE);

            part3.setBackgroundColor(Color.CYAN);
            part1.setBackgroundColor(Color.BLACK);
            part2.setBackgroundColor(Color.BLACK);


            part3.setTextSize(35);
            part2.setTextSize(24);
            part1.setTextSize(24);

            //();
        }

        if (view == clock1) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            startTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (view == clock2) {

            // Get Current Time
            final Calendar c = Calendar.getInstance();
            mHour = c.get(Calendar.HOUR_OF_DAY);
            mMinute = c.get(Calendar.MINUTE);

            // Launch Time Picker Dialog
            TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                    new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay,
                                              int minute) {

                            endTime.setText(hourOfDay + ":" + minute);
                        }
                    }, mHour, mMinute, false);
            timePickerDialog.show();
        }

        if (view == validate1) {
            Toast.makeText(Installation_Form.this, "Button clicked", Toast.LENGTH_SHORT).show();
        }


        custfeed.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (v.getId() == R.id.customerfeedback) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (event.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            v.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                }
                return false;
            }
        });
    }


//    Methods Defined

/*
    private void check2() {


        reg = (EditText) findViewById(R.id.region_val);
        cust = (EditText) findViewById(R.id.cust_name_Val);
        comp = (EditText) findViewById(R.id.comp_name_Val);
        per = (EditText) findViewById(R.id.contact_person_val);
        perno = (EditText) findViewById(R.id.cont_number_Val);
        addr = (EditText) findViewById(R.id.address_val);
        per1 = (EditText) findViewById(R.id.staff_list1);
        per2 = (EditText) findViewById(R.id.staff_list2);
        per3 = (EditText) findViewById(R.id.staff_list3);

        region = reg.getText().toString();
        customer = cust.getText().toString();
        installation_date = install_date.getText().toString();
        company = comp.getText().toString();
        signoff_date = signoff.getText().toString();
        person_name = per.getText().toString();
        contact = perno.getText().toString();
        address = addr.getText().toString();
        per1_name = per1.getText().toString();
        per2_name = per2.getText().toString();
        per3_name = per3.getText().toString();

        String fileName = "Vishal";
        String textToWrite = region;
        FileOutputStream outputStream;

        try {
            outputStream = openFileOutput(fileName , Context.MODE_PRIVATE);
            outputStream.write(textToWrite.getBytes());
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }*/

    /*public void check() {

        if (region.isEmpty() || customer.isEmpty() || installation_date.isEmpty() || company.isEmpty() ||
                signoff_date.isEmpty() || person_name.isEmpty() || chkno(contact) || address.isEmpty() ||
                per1_name.isEmpty() || per2_name.isEmpty() || per3_name.isEmpty() == true) {
            Toast.makeText(MainActivity.this, "All fields are mandatory", Toast.LENGTH_SHORT).show();


        } else {
            //senddata();
            if (machine.getSelectedItem().toString().trim().equals("Select")) {
                Toast.makeText(MainActivity.this, "Please select a machine type", Toast.LENGTH_SHORT).show();
            } else {
                part2.setEnabled(true);
                /*part1.setBackgroundResource(R.drawable.button_style);
                part2.setBackgroundResource(R.drawable.gradient);
                part3.setBackgroundResource(R.drawable.button_style);*//*
            }

        }

    }

    public boolean chkno(String no) {
        if ((no.length() < 10 || no.length() > 10)) {
            perno.setError("Wrong input");
            d = true;
        } else {
            d = false;
        }
        return d;
    }*/
}


