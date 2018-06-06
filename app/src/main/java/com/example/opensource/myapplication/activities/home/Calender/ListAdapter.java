package com.example.opensource.myapplication.activities.home.Calender;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.opensource.myapplication.R;

import java.util.List;

public class ListAdapter extends BaseAdapter {
    public static String id;
    public static int status = 0;
    public static ViewItem viewItem;
    Context context;
    List<Subject> subject_list;

    public ListAdapter(List<Subject> listValue, Context context) {
        this.context = context;
        this.subject_list = listValue;
    }

    @Override
    public int getCount() {
        return this.subject_list.size();
    }

    @Override
    public Object getItem(int position) {
        return this.subject_list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        viewItem = null;
        if (convertView == null) {
            viewItem = new ViewItem();

            LayoutInflater layoutInfiater = (LayoutInflater) this.context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);

            convertView = layoutInfiater.inflate(R.layout.calender_list, null);

            viewItem.SubNameTextView = convertView.findViewById(R.id.SubjectNameTextView);

            viewItem.SubFullFormTextView = convertView.findViewById(R.id.SubjectFullFormTextView);
            viewItem.UNameTextView = convertView.findViewById(R.id.UserNameTextView);
            viewItem.LocNameTextView = convertView.findViewById(R.id.LocationNameTextView);
            viewItem.acceptTextView = convertView.findViewById(R.id.acceptstatusTextView);
            viewItem.acceptButton = convertView.findViewById(R.id.acceptstatus);
            viewItem.rejectButton = convertView.findViewById(R.id.rejectstatus);
            viewItem.wv = convertView.findViewById(R.id.wv1);
            convertView.setTag(viewItem);
        } else {
            viewItem = (ViewItem) convertView.getTag();
        }
        id = subject_list.get(position).s_no;
        viewItem.SubNameTextView.setText(subject_list.get(position).machine_id);

        viewItem.SubFullFormTextView.setText(subject_list.get(position).date);
        viewItem.UNameTextView.setText(subject_list.get(position).machine_issue);
        viewItem.LocNameTextView.setText(subject_list.get(position).machine_location);
        viewItem.acceptTextView.setText(subject_list.get(position).accept_status);
        viewItem.acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                v.setVisibility(View.INVISIBLE);
                //viewItem.rejectButton.setVisibility(View.INVISIBLE);

                viewItem.wv.getSettings().setLoadsImagesAutomatically(true);
                viewItem.wv.getSettings().setJavaScriptEnabled(true);
                viewItem.wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                viewItem.wv.loadUrl("https://serve360.000webhostapp.com/updateAcceptanceStatus.php/?name=" + id);
                viewItem.acceptTextView.setText("Accepted");


            }
        });

        viewItem.rejectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                v.setVisibility(View.INVISIBLE);
                //viewItem.rejectButton.setVisibility(View.INVISIBLE);


                viewItem.wv.getSettings().setLoadsImagesAutomatically(true);
                viewItem.wv.getSettings().setJavaScriptEnabled(true);
                viewItem.wv.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                viewItem.wv.loadUrl("https://serve360.000webhostapp.com/updateRejectStatus.php/?name=" + id);
                viewItem.acceptTextView.setText("Rejected");


            }
        });


        return convertView;
    }

}

class ViewItem {
    TextView SubNameTextView;
    TextView SubFullFormTextView;
    TextView UNameTextView;
    TextView LocNameTextView;
    TextView acceptTextView;
    Button acceptButton;
    Button rejectButton;
    WebView wv;

}
