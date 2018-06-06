package com.example.opensource.myapplication.activities.home.ServiceOrder;

/**
 * Created by OpenSource on 4/28/2018.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

/**
 * Created by OpenSource on 4/27/2018.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;

    private final Integer[] price;

    public CustomList(Activity context,
                      String[] web, Integer[] price) {
        super(context, R.layout.item_list, web);
        this.context = context;
        this.web = web;
        this.price = price;

    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.item_list, null, true);
        TextView txtTitle = rowView.findViewById(R.id.txt);
        TextView txtPrice = rowView.findViewById(R.id.txt1);
        txtTitle.setText(web[position]);
        txtPrice.setText("Rs." + String.valueOf(price[position]));
        return rowView;
    }
}
