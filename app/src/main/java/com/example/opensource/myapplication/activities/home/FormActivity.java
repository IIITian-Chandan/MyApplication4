package com.example.opensource.myapplication.activities.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.activities.home.Forms.Installation_Form;

public class FormActivity extends AppCompatActivity implements View.OnClickListener {
    Button btn_form1, btn_form2, btn_form3, btn_form4, btn_form5, btn_form6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        btn_form1 = findViewById(R.id.btn1);
        btn_form2 = findViewById(R.id.btn2);
        btn_form3 = findViewById(R.id.btn3);
        btn_form4 = findViewById(R.id.btn4);
        btn_form5 = findViewById(R.id.btn5);
        btn_form6 = findViewById(R.id.btn6);


        btn_form1.setOnClickListener(this);
        btn_form2.setOnClickListener(this);
        btn_form3.setOnClickListener(this);
        btn_form4.setOnClickListener(this);
        btn_form5.setOnClickListener(this);
        btn_form6.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_form1) {
            Intent intent = new Intent(FormActivity.this, Installation_Form.class);
            startActivity(intent);
        }

    }
}
