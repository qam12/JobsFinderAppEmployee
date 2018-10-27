package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.content.ClipData;
import android.content.Intent;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EmplyeeWork extends AppCompatActivity {

    String names[];
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;

    Button btn;
    CheckBox c1,c2,c3,c4,c5;
    TextView skil_f,skil_s,skil_th,skil_ft,skil_fth;
    String sk1,sk2,sk3,sk4,sk5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.employee_work);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toobarskill);
        setSupportActionBar(toolbar);

        ImageView drawer = (ImageView) findViewById(R.id.navDraw);


        skil_f=(TextView)findViewById(R.id.sk_1);
        skil_s=(TextView)findViewById(R.id.sk_2);
        skil_th=(TextView)findViewById(R.id.sk_3);
        skil_ft=(TextView)findViewById(R.id.sk_4);
        skil_fth=(TextView)findViewById(R.id.sk_5);

        btn = (Button)findViewById(R.id.data);
        c1 = (CheckBox)findViewById(R.id.checkBox1);
        c2 = (CheckBox)findViewById(R.id.checkBox2);
        c3 = (CheckBox)findViewById(R.id.checkBox3);
        c4 = (CheckBox)findViewById(R.id.checkBox4);
        c5 = (CheckBox)findViewById(R.id.checkBox5);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(c1.isChecked()){
                    c1.setChecked(true);
                    sk1 = skil_f.getText().toString();
                    Toast.makeText(EmplyeeWork.this, "Value" + sk1 , Toast.LENGTH_SHORT).show();
                }

                if(c2.isChecked()){
                    c1.setChecked(true);
                    sk2 = skil_s.getText().toString();
                    Toast.makeText(EmplyeeWork.this, "Value" + sk2 , Toast.LENGTH_SHORT).show();
                }

                if(c3.isChecked()){
                    c1.setChecked(true);
                    sk3 = skil_th.getText().toString();
                    Toast.makeText(EmplyeeWork.this, "Value" + sk3 , Toast.LENGTH_SHORT).show();
                }

                if(c4.isChecked()){
                    c1.setChecked(true);
                    sk4 = skil_ft.getText().toString();
                    Toast.makeText(EmplyeeWork.this, "Value" + sk4 , Toast.LENGTH_SHORT).show();
                }

                if(c5.isChecked()){
                    c1.setChecked(true);
                    sk5 = skil_fth.getText().toString();
                    Toast.makeText(EmplyeeWork.this, "Value" + sk5 , Toast.LENGTH_SHORT).show();
                }

            }
        });




        names = new String[]{"Profile", "Statements", "Payment information", "Job offers", "Rate employers", "Logout"};
        int img[] = {R.mipmap.profile_icon, R.mipmap.statements_icon, R.mipmap.payment_icon, R.mipmap.joboffer_icon, R.mipmap.rate_icon, R.mipmap.logout_icon};
        //mPlanetTitles = getResources().getStringArray(R.array.planets_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setFitsSystemWindows(true);

        // Set the adapter for the list view
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_items, mPlanetTitles));
        mDrawerList.setAdapter(new CustomAdapter2(this, img, names));

        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.nav_header, mDrawerList, false);

        mDrawerList.addHeaderView(header, null, false);

        View footer = getLayoutInflater().inflate(R.layout.nav_footer, null);

        mDrawerList.addFooterView(footer);


        ImageView settings = (ImageView) footer.findViewById(R.id.imageView84);

        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmplyeeWork.this, Settings.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        ImageView email = (ImageView) footer.findViewById(R.id.imageView85);

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(EmplyeeWork.this, Email.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        final ImageView call = (ImageView) footer.findViewById(R.id.imageView86);

        View v2 = getLayoutInflater().inflate(R.layout.call_us, null);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v2);

        final AlertDialog callDialog = builder.create();

        ImageView closeCall = (ImageView) v2.findViewById(R.id.imageView25);

        closeCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialog.dismiss();
            }
        });

        Button cancelCall = (Button) v2.findViewById(R.id.button23);

        cancelCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialog.dismiss();
            }
        });


        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callDialog.show();
            }
        });


        drawer.setOnTouchListener(new OnSwipeTouchListener(this)
        {
            @Override
            public void onTouch() {
                if(mDrawerLayout.isDrawerOpen(Gravity.LEFT))
                {
                    mDrawerLayout.closeDrawer(mDrawerList);
                    // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    // getSupportActionBar().setCustomView(R.layout.menu_title);
                    // getSupportActionBar().show();
                }
                else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    //getSupportActionBar().hide();
                    // requestWindowFeature(Window.FEATURE_NO_TITLE);
                }
            }
        });

//        done.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(EmplyeeWork.this, FindAMission.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                startActivity(i);
//            }
//        });

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position)
    {
        Intent i;

        switch (position)
        {
            case 1:
                i = new Intent(EmplyeeWork.this, Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 2:
                i = new Intent(EmplyeeWork.this, Statements.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 3:
                i = new Intent(EmplyeeWork.this, Payment_InfoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 4:
                i = new Intent(EmplyeeWork.this, Job_Offer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 5:
                i = new Intent(EmplyeeWork.this, Rate_Employer_list.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 6:
                i = new Intent(EmplyeeWork.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }


    ///expandable list


}
