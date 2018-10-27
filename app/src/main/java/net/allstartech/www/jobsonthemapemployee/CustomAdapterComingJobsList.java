package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John Carter on 8/5/2017.
 */

public class CustomAdapterComingJobsList extends ArrayAdapter<Coming_Jobs_Items> implements View.OnClickListener {

    Context context;
    int layoutResourceId;
    ArrayList<Coming_Jobs_Items> data;


    public CustomAdapterComingJobsList(Context context, int resource, ArrayList<Coming_Jobs_Items> list) {
        super(context, resource, list);
        this.context = context;
        this.layoutResourceId = resource;
        this.data = list;
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
            holder.cost = (TextView)row.findViewById(R.id.textView161);
            holder.costSign = (TextView)row.findViewById(R.id.textView160);
            holder.job = (TextView)row.findViewById(R.id.textView162);
            holder.type = (TextView)row.findViewById(R.id.textView163);
            holder.time = (TextView)row.findViewById(R.id.textView165);

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

        if(items.job.equals("Electrician"))
        {
            holder.costSign.setTextColor(context.getResources().getColor(R.color.lblue));
            holder.cost.setTextColor(context.getResources().getColor(R.color.lblue));
            holder.cost.setText("42");
        }
        else if(items.job.equals("Snow Removal"))
        {
            holder.costSign.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.cost.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.cost.setText("105");
        }
        else if(items.job.equals("Floor Installer"))
        {
            holder.costSign.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.cost.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.cost.setText("75");
        }


        //holder.imgIcon.setImageURI(uri);

        return row;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.textView162)
        {
            Intent i = new Intent(context, MissionDetailActivity.class);
            context.startActivity(i);
        }

    }

    static class ItemHolder
    {
        //ImageView imgIcon;

        TextView job, type, time, cost, costSign;
    }
}
