package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by John Carter on 7/8/2017.
 */

public class CustomAdapter_MissionItems extends ArrayAdapter<MissionItems> {

    Context context;
    int layoutResourceId;
    ArrayList<MissionItems> data;


    public CustomAdapter_MissionItems(Context context, int resource, ArrayList<MissionItems> list) {
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
            holder.profession = (TextView)row.findViewById(R.id.textView10);
            holder.level = (TextView)row.findViewById(R.id.textView26);
            holder.distance = (TextView) row.findViewById(R.id.textView27);
            holder.time = (TextView)row.findViewById(R.id.textView72);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        final MissionItems items = data.get(position);
        //Items items = new Items();
        holder.profession.setText(items.profession);
        holder.level.setText(items.level);
        holder.time.setText(items.time);
        holder.distance.setText(items.distance);

        if(items.profession.equals("The House Store") || items.profession.equals("Post-Construction"))
        {
            holder.profession.setTextColor(context.getResources().getColor(R.color.lblue));
        }
        else
        {
            holder.profession.setTextColor(Color.BLACK);
        }

        //holder.imgIcon.setImageURI(uri);

        return row;
    }

    static class ItemHolder
    {
        //ImageView imgIcon;

        TextView profession, level, time, distance;
    }
}
