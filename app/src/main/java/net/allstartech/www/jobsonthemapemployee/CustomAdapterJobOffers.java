package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John Carter on 8/8/2017.
 */

public class CustomAdapterJobOffers extends ArrayAdapter<Coming_Jobs_Items> implements View.OnClickListener {

    Context context;
    int layoutResourceId;
    ArrayList<Coming_Jobs_Items> data;
    LayoutInflater inflater;


    public CustomAdapterJobOffers(Context context, int resource, ArrayList<Coming_Jobs_Items> list, LayoutInflater inflater) {
        super(context, resource, list);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = list;
        this.inflater = inflater;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View row = convertView;
        ItemHolder holder = null;

        if(row == null)
        {

            LayoutInflater inflater = LayoutInflater.from(context);
            row = inflater.inflate(layoutResourceId, parent, false);

            holder = new ItemHolder();
            // holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
            holder.job = (TextView)row.findViewById(R.id.textView89);
            holder.type = (TextView)row.findViewById(R.id.textView90);
            holder.time = (TextView)row.findViewById(R.id.textView91);

            holder.job.setOnClickListener(this);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        final Coming_Jobs_Items items = data.get(position);
        //Items items = new Items();
        holder.job.setText(items.job);
        holder.type.setText(items.type);
        holder.time.setText(items.time);

        TextView textView = (TextView) row.findViewById(R.id.textView92);

        textView.setOnClickListener(this);

        //holder.imgIcon.setImageURI(uri);

        return row;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.textView92)
        {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            View v = inflater.inflate(R.layout.outstanding_performance, null);

            builder.setView(v);

            ImageView iv = (ImageView) v.findViewById(R.id.imageView23);

            final AlertDialog performanceAlert = builder.create();

            performanceAlert.show();

            iv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    performanceAlert.dismiss();
                }
            });

        }

    }

    static class ItemHolder
    {
        //ImageView imgIcon;

        TextView job, type, time;
    }
}
