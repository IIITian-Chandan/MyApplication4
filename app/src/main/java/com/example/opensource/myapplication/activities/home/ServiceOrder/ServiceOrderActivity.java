package com.example.opensource.myapplication.activities.home.ServiceOrder;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.myapplication.R;

import java.util.ArrayList;

public class ServiceOrderActivity extends Activity {
    static ArrayList component = new ArrayList();
    static ArrayList c_quant = new ArrayList();
    static ArrayList c_price = new ArrayList();

    static int pos;
    static float total = 0.0f;
    ListView list;
    String[] web = {
            "Google Plus",
            "Twitter",
            "Windows",
            "Bing",
            "Itunes",
            "Wordpress",
            "Drupal"
    };
    Integer[] price = {
            15, 56, 55, 45, 44, 7, 45

    };
    LinearLayout linearLayout, linearLayout1;
    Button btn1, btn2, btn3;
    TextView tv1, tv2, tv3, tot;
    Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_order);
        linearLayout = findViewById(R.id.ll1);
        linearLayout1 = findViewById(R.id.ll2);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);


        tot = findViewById(R.id.total);
        sp = findViewById(R.id.sp1);
        linearLayout.setVisibility(View.GONE);
        linearLayout1.setVisibility(View.GONE);
        CustomList adapter = new
                CustomList(ServiceOrderActivity.this, web, price);
        list = findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(ServiceOrderActivity.this, "You Clicked at " + String.valueOf(price[+position]), Toast.LENGTH_SHORT).show();
                tv1.setText(web[+position]);
                tv2.setText(String.valueOf(price[+position]));
                linearLayout.setVisibility(View.VISIBLE);
                linearLayout1.setVisibility(View.GONE);
                float x = Float.valueOf(price[+position]).floatValue();
                int y = Integer.valueOf(sp.getSelectedItem().toString());
                pos = position;


            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float x = Float.valueOf(tv2.getText().toString()).floatValue();
                int y = Integer.valueOf(sp.getSelectedItem().toString());
                total = total + (x * y);
                tv3.setText("Total: " + "Rs." + String.valueOf(total));
                linearLayout.setVisibility(View.GONE);
                linearLayout1.setVisibility(View.VISIBLE);

                //Inserting list of components to Array
                component.add(web[pos]);
                c_quant.add(y);
                c_price.add(x * y);


            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total = 0;
                tv3.setText("Total: " + "Rs." + String.valueOf(total));
                component = new ArrayList();
                c_price = new ArrayList();
                c_quant = new ArrayList();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(ServiceOrderActivity.this, CheckOutActivity.class);
                intent.putExtra("arr", component);
                intent.putExtra("c_price", c_price);
                intent.putExtra("c_quant", c_quant);
                startActivity(intent);
            }
        });
    }


}
