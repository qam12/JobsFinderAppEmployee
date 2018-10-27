package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

public class FindAMission extends AppCompatActivity {

    String names[];
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_mission);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFindmiss);
        setSupportActionBar(toolbar);

        ImageView drawer = (ImageView) findViewById(R.id.nav1);

        ImageView iv = (ImageView) findViewById(R.id.selectWork);

        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FindAMission.this, FindMissionList.class);
                startActivity(i);
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


        drawer.setOnTouchListener(new OnSwipeTouchListener(this) {
                @Override
            public void onTouch() {
                if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
                    mDrawerLayout.closeDrawer(mDrawerList);
                    // getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
                    // getSupportActionBar().setCustomView(R.layout.menu_title);
                    // getSupportActionBar().show();
                } else {
                    mDrawerLayout.openDrawer(Gravity.LEFT);
                    //getSupportActionBar().hide();
                    // requestWindowFeature(Window.FEATURE_NO_TITLE);
                }
            }
        });

        String list = getIntent().getStringExtra("list");

        Bundle args = new Bundle();
        if (list != null) {
            args.putString("list", list);
        }
        Fragment fragment = new FindAMissionFragment();
        fragment.setArguments(args);
        FragmentManager fragmentManager3 = getSupportFragmentManager();
        fragmentManager3.beginTransaction()
                .replace(R.id.flcontainer, fragment)
                .commit();


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
                i = new Intent(FindAMission.this, Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 2:
                i = new Intent(FindAMission.this, Statements.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 3:
                i = new Intent(FindAMission.this, Payment_InfoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 4:
                i = new Intent(FindAMission.this, Job_Offer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 5:
                i = new Intent(FindAMission.this, Rate_Employer_list.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 6:
                i = new Intent(FindAMission.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }
}
