package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class JobOfferFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.job_offers, null);

        ListView lv = (ListView) rootView.findViewById(R.id.job_offers_list);

        ArrayList<Coming_Jobs_Items> list = new ArrayList<>();

        Coming_Jobs_Items items1 = new Coming_Jobs_Items("XYZ Company", "The House Store", "June 17,2017");
        Coming_Jobs_Items items2 = new Coming_Jobs_Items("Michael", "The FLoor Installer", "July 14,2017");
        Coming_Jobs_Items items3 = new Coming_Jobs_Items("XY Enterprise", "George Grocery", "June 15,2017");
        Coming_Jobs_Items items4 = new Coming_Jobs_Items("Oakswood", "Snow Removal", "June 10,2017");
        Coming_Jobs_Items items5 = new Coming_Jobs_Items("Alexander", "The House Store", "June 09,2017");
        Coming_Jobs_Items items6 = new Coming_Jobs_Items("Benjamin", "Snow Removal", "Feb 10,2017");
        Coming_Jobs_Items items7 = new Coming_Jobs_Items("Secuvic house", "The House Store", "Jan 10,2017");

        list.add(items1);
        list.add(items2);
        list.add(items3);
        list.add(items4);
        list.add(items5);
        list.add(items6);
        list.add(items7);

        CustomAdapterJobOffers adapter = new CustomAdapterJobOffers(getContext(), R.layout.job_offers_list_items, list, getLayoutInflater(savedInstanceState));

        lv.setAdapter(adapter);
        lv.setDivider(null);


        return rootView;
    }


}
