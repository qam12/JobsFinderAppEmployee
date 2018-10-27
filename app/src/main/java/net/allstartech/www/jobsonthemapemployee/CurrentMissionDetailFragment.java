package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class CurrentMissionDetailFragment extends Fragment {


    AlertDialog abortAlert, notifyAlert;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.current_mission_detail, null);

        ImageView iv = (ImageView) v.findViewById(R.id.imageView3);

        ListView lv = (ListView) v.findViewById(R.id.currentmission_listview);

        ArrayList<Items> list = new ArrayList<>();

        Items i1 = new Items("Date","14/06/2017", R.mipmap.calendar);
        Items i2 = new Items("Time", "12 hours", R.mipmap.time);

        list.add(i1);
        list.add(i2);

        CustomAdapter adapter = new CustomAdapter(this.getActivity(), R.layout.date_time_list, list);

        lv.setAdapter(adapter);
        lv.setDivider(null);

        TextView name = (TextView) v.findViewById(R.id.textView19);
        TextView name1 = (TextView) v.findViewById(R.id.textView20);
        TextView seeLocation = (TextView) v.findViewById(R.id.textView25);


        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CurrentMissionDetailFragment.this.getActivity(), RateProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CurrentMissionDetailFragment.this.getActivity(), RateProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        seeLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(CurrentMissionDetailFragment.this.getActivity(), FindAMission.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        View v2 = inflater.inflate(R.layout.current_mission_alert, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getActivity());

        builder.setView(v2);

        abortAlert = builder.create();

        Button noAbort = (Button) v2.findViewById(R.id.button30);
        Button yesAbort = (Button) v2.findViewById(R.id.button31);

        ImageView closeAbort = (ImageView) v2.findViewById(R.id.imageView34);

        noAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abortAlert.dismiss();
            }
        });


        closeAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abortAlert.dismiss();
            }
        });



        v2 = inflater.inflate(R.layout.current_mission_abort_alert, null);
        builder.setView(v2);

        notifyAlert = builder.create();

        Button okNotify = (Button) v2.findViewById(R.id.button29);
        ImageView closeNotify = (ImageView) v2.findViewById(R.id.imageView33);


        yesAbort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                abortAlert.dismiss();
                notifyAlert.show();
            }
        });

        okNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyAlert.dismiss();
            }
        });

        closeNotify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                notifyAlert.dismiss();
            }
        });

        return v;
    }


}
