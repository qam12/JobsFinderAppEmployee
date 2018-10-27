package net.allstartech.www.jobsonthemapemployee;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;


public class JobList_ComingJobs extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.job_list_coming_jobs_list, null);

        ListView lv = (ListView) rootView.findViewById(R.id.coming_job_list);
        //ImageView chatAc= (ImageView) rootView.findViewById(R.id.imageView32);



        ArrayList<Coming_Jobs_Items> list = new ArrayList<>();

        Coming_Jobs_Items items1 = new Coming_Jobs_Items("Electrician", "Industrial | Commercial", "04:26:12");
        Coming_Jobs_Items items2 = new Coming_Jobs_Items("Snow Removal", "Driveway | Sidewalk", "08:12:12");
        Coming_Jobs_Items items3 = new Coming_Jobs_Items("Floor Installer", "Laminated | Vinyl | Carpet", "06:14:05");

        list.add(items1);
        list.add(items2);
        list.add(items3);



        CustomAdapterComingJobsList adapter = new CustomAdapterComingJobsList(getContext(), R.layout.jobs_list_coming_jobs_list_items, list);



        lv.setAdapter(adapter);
        lv.setDivider(null);



        return rootView;
    }

}
