package com.example.opensource.myapplication.activities.home.ServiceOrder;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

import java.util.ArrayList;

/**
 * Created by OpenSource on 4/28/2018.
 */


/**
 * Created by OpenSource on 4/28/2018.
 */

public class ComponentList extends ArrayAdapter<String> {

    private final Activity context;
    private final ArrayList web;
    private final ArrayList quant;
    private final ArrayList price;

    public ComponentList(Activity context,
                         ArrayList web, ArrayList quant, ArrayList price) {
        super(context, R.layout.component_list, web);
        this.context = context;
        this.web = web;
        this.quant = quant;
        this.price = price;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.component_list, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        TextView txtQuant = rowView.findViewById(R.id.txt2);
        TextView txtPrice = rowView.findViewById(R.id.txt1);
        txtTitle.setText(String.valueOf(web.get(position)));
        txtQuant.setText("Qty:" + String.valueOf(quant.get(position)));
        txtPrice.setText("Rs." + String.valueOf(price.get(position)));
        return rowView;
    }
}
