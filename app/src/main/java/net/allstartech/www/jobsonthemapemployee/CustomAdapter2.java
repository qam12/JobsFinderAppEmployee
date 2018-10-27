package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by John Carter on 6/15/2017.
 */

public class CustomAdapter2 extends BaseAdapter {

    private LayoutInflater lInflater;
    //private List<ItemObject> listStorage;
    private String name[];
    private int img[];

    public CustomAdapter2(Context context, int img[], String name[]) {
        lInflater =(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //listStorage = customizedListView;
        this.img = img;
        this.name = name;
    }

    @Override
    public int getCount() {
        //return listStorage.size();
        return img.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder listViewHolder;
        if(convertView == null){
            listViewHolder = new ViewHolder();
            convertView = lInflater.inflate(R.layout.drawer_items, parent, false);

            listViewHolder.textInListView = (TextView)convertView.findViewById(R.id.textView62);
            listViewHolder.imageInListView = (ImageView)convertView.findViewById(R.id.drawerImage);


            convertView.setTag(listViewHolder);
        }else{
            listViewHolder = (ViewHolder)convertView.getTag();
        }
        listViewHolder.textInListView.setText(name[position]);
        listViewHolder.imageInListView.setImageResource(img[position]);

        return convertView;
    }

    static class ViewHolder{

        TextView textInListView;
        ImageView imageInListView;
    }
}