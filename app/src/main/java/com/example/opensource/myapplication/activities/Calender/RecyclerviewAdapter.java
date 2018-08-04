package com.example.opensource.myapplication.activities.Calender;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.opensource.myapplication.R;
import com.example.opensource.myapplication.activities.HomeActivity;
import com.example.opensource.myapplication.classes.HttpServiceClass;

import java.util.List;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder> {
    public Context mcontext;
    String id;
    int position;
    private List<String> ms_no, mmachine_id, mmachine_issue, mmachine_location, mi_date, maccept_status;
    private LayoutInflater mInflater;
    private RecyclerviewAdapter.ItemClickListener mClickListener;

    // data is passed into the constructor
    public RecyclerviewAdapter(Context context, List<String> s_no, List<String> machine_id, List<String> machine_issue, List<String> machine_location, List<String> i_date, List<String> accept_status) {
        this.mInflater = LayoutInflater.from(context);
        this.ms_no = s_no;
        this.mmachine_id = machine_id;
        this.mmachine_issue = machine_issue;
        this.mmachine_location = machine_location;
        this.mi_date = i_date;
        this.maccept_status = accept_status;
        mcontext = context;
    }

    // inflates the row layout from xml when needed
    @Override
    public RecyclerviewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.calender_list, parent, false);
        return new RecyclerviewAdapter.ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @RequiresApi(api = Build.VERSION_CODES.M)
    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(final RecyclerviewAdapter.ViewHolder holder, final int position) {
        id = ms_no.get(position);

        holder.SubNameTextView.setText(mmachine_id.get(position));
        holder.SubFullFormTextView.setText(mi_date.get(position));
        holder.UNameTextView.setText(mmachine_issue.get(position));
        holder.LocNameTextView.setText(mmachine_location.get(position));
        holder.acceptTextView.setText(maccept_status.get(position));
        if ((maccept_status.get(position)).equals("accepted")) {
            holder.rl1.setBackgroundResource(R.drawable.gradient_accept);
        } else if ((maccept_status.get(position)).equals("rejected")) {
            holder.rl1.setBackgroundResource(R.drawable.gradient_reject);
        } else {
            holder.rl1.setBackgroundResource(R.drawable.gradient_pending);
        }


    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mmachine_id.size();
    }

    public void callMethod(String pos, String value) {
        new ParseJSonDataClass(mcontext).execute(pos, value);
    }

    // convenience method for getting data at click position
    String getItem(int id) {

        return mmachine_id.get(id);

    }

    // allows clicks events to be caught
    public void setClickListener(RecyclerviewAdapter.ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;

    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);

    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {
        TextView SubNameTextView;
        TextView SubFullFormTextView;
        TextView UNameTextView;
        TextView LocNameTextView;
        TextView acceptTextView;

        RelativeLayout rl1;

        ViewHolder(View itemView) {
            super(itemView);
            SubNameTextView = itemView.findViewById(R.id.SubjectNameTextView);
            SubFullFormTextView = itemView.findViewById(R.id.SubjectFullFormTextView);
            UNameTextView = itemView.findViewById(R.id.UserNameTextView);
            LocNameTextView = itemView.findViewById(R.id.LocationNameTextView);
            acceptTextView = itemView.findViewById(R.id.acceptstatusTextView);
            rl1 = itemView.findViewById(R.id.rl1);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
            if (maccept_status.get(getAdapterPosition()).equals("accepted")) {
                Intent intent = new Intent(mcontext, HomeActivity.class);
                mcontext.startActivity(intent);

            } else {
                Toast.makeText(mcontext, "Make sure you have Accepted  service Request for " + mmachine_id.get(position) + ".", Toast.LENGTH_LONG).show();
            }
        }

        @Override
        public boolean onLongClick(View v) {

            if (mClickListener != null) mClickListener.onItemClick(v, getAdapterPosition());
            position = getAdapterPosition();

            if (maccept_status.get(position).equals("accepted")) {
                Toast.makeText(mcontext, "Hey! You cant edit status of " + mmachine_id.get(position) + ".Contact Admin for any changes.", Toast.LENGTH_LONG).show();


            } else if (maccept_status.get(position).equals("rejected")) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setMessage("Hey are you excited to take this chance for " + mmachine_id.get(position) + " ?");

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //perform any action
                        Toast.makeText(mcontext, "Yes clicked", Toast.LENGTH_SHORT).show();
                        String value = "accepted";
                        String pos = ms_no.get(position);
                        callMethod(pos, value);
                        //new ParseJSonDataClass(mcontext).execute(ms_no.get(position), value);
                    }
                });
                //creating alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            } else {
                AlertDialog.Builder builder = new AlertDialog.Builder(mcontext);
                builder.setMessage("Hey are you excited to take this chance for " + mmachine_id.get(position) + " ?");

                builder.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //perform any action
                        Toast.makeText(mcontext, "Yes clicked", Toast.LENGTH_SHORT).show();
                        String value = "accepted";
                        String pos = ms_no.get(position);
                        callMethod(pos, value);
                        //new ParseJSonDataClass(mcontext).execute(ms_no.get(position), value);
                    }
                });

                builder.setNegativeButton("Reject", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //perform any action
                        Toast.makeText(mcontext, "No clicked", Toast.LENGTH_SHORT).show();
                        String value = "rejected";
                        String pos = ms_no.get(position);
                        callMethod(pos, value);
                    }
                });

                //creating alert dialog
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
            return false;
        }

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
            int c_no = Integer.parseInt(params[0]);
            String val = params[1];


            HttpServiceClass httpServiceClass = new HttpServiceClass("https://serve360.000webhostapp.com/updateAcceptanceStatus.php/?cn=" + c_no + "&status=" + val);

            try {
                httpServiceClass.ExecutePostRequest();

                if (httpServiceClass.getResponseCode() == 200) {

                    FinalJSonResult = httpServiceClass.getResponse();
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
            Toast.makeText(mcontext, "Value updated", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(mcontext, CalenderActivity.class);

            mcontext.startActivity(intent);

        }
    }
}



