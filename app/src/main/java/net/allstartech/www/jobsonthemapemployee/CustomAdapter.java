package net.allstartech.www.jobsonthemapemployee;

/**
 * Created by John Carter on 7/7/2017.
 */
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAdapter extends ArrayAdapter<Items> {

    Context context;
    int layoutResourceId;
    ArrayList<Items> data;


    public CustomAdapter(Context context, int resource, ArrayList<Items> list) {
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
            holder.date = (TextView)row.findViewById(R.id.textView8);
            holder.time = (TextView)row.findViewById(R.id.textView9);
            holder.imgIcon = (ImageView)row.findViewById(R.id.imageView5);

            row.setTag(holder);
        }
        else
        {
            holder = (ItemHolder)row.getTag();
        }

        final Items items = data.get(position);
        //Items items = new Items();
        holder.date.setText(items.date);
        holder.time.setText(items.time);
        holder.imgIcon.setImageResource(items.imgId);


        //holder.imgIcon.setImageURI(uri);

        return row;
    }

    static class ItemHolder
    {
        ImageView imgIcon;

        TextView date, time;
    }
}
