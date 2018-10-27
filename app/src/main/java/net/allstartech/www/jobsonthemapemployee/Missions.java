package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


public class Missions extends Fragment {

    public static FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.missions_fragment, container, false);

        String mission = getArguments().getString("mission");

        mTabHost = (FragmentTabHost)rootView.findViewById(android.R.id.tabhost);
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.realtabcontent);



        mTabHost.addTab(mTabHost.newTabSpec("current mission").setIndicator("Current Mission"),
                Current_Jobs.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("coming mission").setIndicator("Coming Mission"),
                JobList_ComingJobs.class, null);

        TextView tv = (TextView) mTabHost.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        TextView tv2 = (TextView) mTabHost.getTabWidget().getChildAt(1).findViewById(android.R.id.title);

        tv.setTextColor(getResources().getColor(R.color.white));
        tv2.setTextColor(getResources().getColor(R.color.white));

        mTabHost.getTabWidget().setBackgroundColor(getResources().getColor(R.color.colorPrimary));


        if(mission.equals("2"))
        {
            mTabHost.setCurrentTab(1);
        }

        mTabHost.setOnTouchListener(new OnSwipeTouchListener(this.getContext())
        {

            public void onTouch() {
                // Toast.makeText(HelpCenterFragment.this.getContext(), "Touch!", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                int current = mTabHost.getCurrentTab();

                if(mTabHost.getChildAt(current) != null)
                {
                    mTabHost.setCurrentTab(current-1);
                }
                else if(mTabHost.getChildAt(current-1) != null)
                {
                    mTabHost.setCurrentTab(current-1);
                }
                else
                {
                    //Toast.makeText(HelpCenterFragment.this.getContext(), "RIGHT", Toast.LENGTH_SHORT).show();
                    mTabHost.setCurrentTab(current);
                }
            }


            public void onSwipeLeft() {
                int current = mTabHost.getCurrentTab();

                if(mTabHost.getChildAt(current) != null)
                {
                    mTabHost.setCurrentTab(current+1);
                }
                else if(mTabHost.getChildAt(current+1) != null)
                {
                    mTabHost.setCurrentTab(current+1);
                }
                else
                {
                    // Toast.makeText(HelpCenterFragment.this.getContext(), "LEFT", Toast.LENGTH_SHORT).show();
                    mTabHost.setCurrentTab(current);
                }
            }
        });


        return rootView;
    }


}
