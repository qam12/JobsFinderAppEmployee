package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class FindAMissionDetail extends AppCompatActivity {

    AlertDialog detailAlert, thanksAlert, knowSomeone, knowSomeoneName, sortAlert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.find_a_mission_detail_title);

        TextView back = (TextView) findViewById(R.id.textView256);
        final ImageView sort = (ImageView) findViewById(R.id.imageView107);

        setContentView(R.layout.find_mission_detail);


        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindAMissionDetail.this, FindAMission.class);
                i.putExtra("list", "list");
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


        TextView tv = (TextView) findViewById(R.id.textView37);

        String s = getResources().getString(R.string.test);
        //tv.setText(Html.fromHtml(s));

        TextView name = (TextView) findViewById(R.id.textView184);
        TextView name1 = (TextView) findViewById(R.id.textView185);

        name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindAMissionDetail.this, RateProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        name1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindAMissionDetail.this, RateProfile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        ListView lv = (ListView) findViewById(R.id.listView);

        Items i1 = new Items("Date", "June 14,2017", R.mipmap.calendar);
        Items i2 = new Items("Time", "4 PM to 6 PM", R.mipmap.time);

        ArrayList<Items> list = new ArrayList<>();

        list.add(i1);
        list.add(i2);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.date_time_list, list);

        lv.setAdapter(adapter);


        View v = getLayoutInflater().inflate(R.layout.find_a_mission_detail_alert, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);

        detailAlert = builder.create();

        Button acceptDetail = (Button) v.findViewById(R.id.button33);
        Button cancelDetail = (Button) v.findViewById(R.id.button32);
        ImageView closeDetail = (ImageView) v.findViewById(R.id.imageView58);

        v = getLayoutInflater().inflate(R.layout.find_a_mission_thanks_alert, null);
        builder.setView(v);

        thanksAlert = builder.create();

        Button okThanks = (Button) v.findViewById(R.id.button34);
        ImageView closeThanks = (ImageView) v.findViewById(R.id.imageView59);

        okThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanksAlert.dismiss();
            }
        });

        closeThanks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                thanksAlert.dismiss();
            }
        });

        acceptDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailAlert.dismiss();
                thanksAlert.show();

            }
        });

        cancelDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailAlert.dismiss();
            }
        });

        closeDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailAlert.dismiss();
            }
        });


        v = getLayoutInflater().inflate(R.layout.popup_iknow_someone, null);
        builder.setView(v);

        knowSomeone = builder.create();

        Button emailButton = (Button) v.findViewById(R.id.button36);
        ImageView closeKnowSomeone = (ImageView) v.findViewById(R.id.imageView60);

        v = getLayoutInflater().inflate(R.layout.popup_iknow_someone_name, null);
        builder.setView(v);

        knowSomeoneName = builder.create();

        Button okKnowSomeoneName = (Button) v.findViewById(R.id.button37);
        ImageView closeKnowSomeoneName = (ImageView) v.findViewById(R.id.imageView61);


        emailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowSomeone.dismiss();
                knowSomeoneName.show();
            }
        });

        closeKnowSomeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowSomeone.dismiss();
            }
        });

        okKnowSomeoneName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowSomeoneName.dismiss();
            }
        });

        closeKnowSomeone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowSomeoneName.dismiss();
            }
        });


        v = getLayoutInflater().inflate(R.layout.find_a_mission_detail_sort, null);
        builder.setView(v);
        sortAlert = builder.create();

        //Window window = sortAlert.getWindow();

        //window.setLayout(200, 70);
        //window.setAttributes(wlp);

        sort.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortAlert.show();
                /*WindowManager.LayoutParams wlp = new WindowManager.LayoutParams();

                wlp.copyFrom(sortAlert.getWindow().getAttributes());
                wlp.width = 400;
                wlp.height = 200;
                wlp.x = 100;
                wlp.y = 50;
                sortAlert.getWindow().setAttributes(wlp);*/
                sortAlert.getWindow().setLayout(600, 300);
                sortAlert.getWindow().setGravity(Gravity.TOP | Gravity.RIGHT);
            }
        });

        Button b = (Button) findViewById(R.id.button6);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                detailAlert.show();
            }
        });

        Button b2 = (Button) findViewById(R.id.button9);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                knowSomeone.show();
            }
        });
    }
}
