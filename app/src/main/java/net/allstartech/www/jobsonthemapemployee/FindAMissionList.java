package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class FindAMissionList extends Fragment {


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View v = inflater.inflate(R.layout.find_mission_list, null);


        ListView lv = (ListView) v.findViewById(R.id.find_mission_list_items);
        final ListView lv2 = (ListView) v.findViewById(R.id.find_mission_list_itemsExpand);
        TextView tv = (TextView) v.findViewById(R.id.textView183);

        ArrayList<MissionItems> list = new ArrayList<>();

        MissionItems i1 = new MissionItems("The House Store","Stocking", "25km" , "11 min");
        MissionItems i2 = new MissionItems("George Grocery","Warmhousing", "32km" , "1 min");
        MissionItems i3 = new MissionItems("Post-Construction","Construction", "36km" , "2 min");
        MissionItems i4 = new MissionItems("Floor Installer","Laminated", "37" , "5 min");

        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);

        lv2.setVisibility(View.INVISIBLE);

        CustomAdapter_MissionItems adapter = new CustomAdapter_MissionItems(this.getActivity(), R.layout.find_mission_list_items, list);

        lv.setAdapter(adapter);
        lv.setDivider(null);
        lv2.setAdapter(adapter);
        lv2.setDivider(null);


        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(lv2.getVisibility() == View.INVISIBLE)
                {
                    lv2.setVisibility(View.VISIBLE);
                }
                else
                {
                    lv2.setVisibility(View.INVISIBLE);
                }
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Intent intent = new Intent(FindAMissionList.this.getActivity(), FindAMissionDetail.class);
                startActivity(intent);

            }
        });



        return v;
    }

}
