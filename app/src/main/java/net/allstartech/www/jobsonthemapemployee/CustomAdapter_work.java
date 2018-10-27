package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import net.allstartech.www.jobsonthemapemployee.R;
import net.allstartech.www.jobsonthemapemployee.Work_Items;

import java.util.ArrayList;

/**
 * Created by John Carter on 7/7/2017.
 */

//public class CustomAdapter_work  extends ArrayAdapter<Work_Items> {

//    Context context;
//    int layoutResourceId;
//    ArrayList<Work_Items> data;
//
//
//    public CustomAdapter_work(Context context, int resource, ArrayList<Work_Items> list) {
//        super(context, resource, list);
//        this.context = context;
//        this.layoutResourceId = resource;
//        this.data = list;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//
//        View row = convertView;
//        ItemHolder holder = null;
//
//        if(row == null)
//        {
//
//            LayoutInflater inflater = LayoutInflater.from(context);
//            row = inflater.inflate(layoutResourceId, parent, false);
//
//            holder = new CustomAdapter_work.ItemHolder();
//            // holder.imgIcon = (ImageView)row.findViewById(R.id.imgIcon);
//            holder.profession = (TextView)row.findViewById(R.id.textView10);
//            holder.level = (TextView)row.findViewById(R.id.textView26);
//            holder.time = (TextView)row.findViewById(R.id.textView27);
//            holder.professionImg = (ImageView) row.findViewById(R.id.imageView6);
//
//            row.setTag(holder);
//        }
//        else
//        {
//            holder = (CustomAdapter_work.ItemHolder)row.getTag();
//        }
//
//        final Work_Items items = data.get(position);
//        //Items items = new Items();
//        holder.profession.setText(items.profession);
//        holder.level.setText(items.level);
//        holder.time.setText(items.time);
//        holder.professionImg.setImageResource(items.img);
//
//        final ImageView iv = (ImageView) row.findViewById(R.id.imageView102);
//        final LinearLayout rv = (LinearLayout) row.findViewById(R.id.linear);
//
//        iv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                if(rv.getVisibility() == View.GONE)
//                {
//                    rv.setVisibility(View.VISIBLE);
//                    iv.setImageResource(R.mipmap.up_arrow);
//                }
//                else
//                {
//                    rv.setVisibility(View.GONE);
//                    iv.setImageResource(R.mipmap.down_arrow);
//
//                }
//
//
//            }
//        });
//
//        //holder.imgIcon.setImageURI(uri);
//
//        return row;
//    }
//
//    static class ItemHolder
//    {
//        //ImageView imgIcon;
//
//        TextView profession, level, time;
//        ImageView  professionImg;
//    }
//}