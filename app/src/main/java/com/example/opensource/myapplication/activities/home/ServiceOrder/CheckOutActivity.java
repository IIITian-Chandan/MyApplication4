package com.example.opensource.myapplication.activities.home.ServiceOrder;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

import java.util.ArrayList;

public class CheckOutActivity extends AppCompatActivity {
    ListView list;
    TextView tot;
    float y = 0.0f;

    LinearLayout ll1, ll2;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        list = findViewById(R.id.lv1);
        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        submit = findViewById(R.id.btn1);
        ll2.setVisibility(View.GONE);
        ll1.setVisibility(View.VISIBLE);

        Intent intent2 = getIntent();

        Bundle bundle = intent2.getExtras();

        ArrayList comp = bundle.getStringArrayList("arr");
        ArrayList price = bundle.getStringArrayList("c_price");
        ArrayList quant = bundle.getStringArrayList("c_quant");
        Log.d("Tag", String.valueOf(comp));
        ComponentList adapter = new ComponentList(CheckOutActivity.this, comp, quant, price);

        list.setAdapter(adapter);
        tot = findViewById(R.id.tv1);
        for (int i = 0; i < price.size(); i++) {
            y = y + (Float) price.get(i);
            tot.setText("Total= Rs." + y);

        }

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll1.setVisibility(View.GONE);
                ll2.setVisibility(View.VISIBLE);
            }
        });
    }

}
