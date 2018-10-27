package net.allstartech.www.jobsonthemapemployee;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.ToggleButton;


public class RateEmployersFragment extends Fragment {

    AlertDialog blockDialog, favouriteDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.rate_employers, null);


        ImageView rb = (ImageView) v.findViewById(R.id.toggleButton2);

        AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());

        View v2 = inflater.inflate(R.layout.block_employer, null);

        builder.setView(v2);

        blockDialog = builder.create();

        Button okBlock = (Button) v2.findViewById(R.id.button49);
        ImageView closeBlock = (ImageView) v2.findViewById(R.id.imageView74);

        okBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockDialog.dismiss();
            }
        });

        closeBlock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockDialog.dismiss();
            }
        });


        rb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blockDialog.show();
            }
        });

        ImageView favourite = (ImageView) v.findViewById(R.id.imageView93);

        v2 = inflater.inflate(R.layout.rate_employers_alert, null);
        builder.setView(v2);

        favouriteDialog = builder.create();


        Button okFav = (Button) v2.findViewById(R.id.button22);
        ImageView closeFav = (ImageView) v2.findViewById(R.id.imageView94);

        okFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteDialog.dismiss();
            }
        });

        closeFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteDialog.dismiss();
            }
        });



        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favouriteDialog.show();
            }
        });

        Button submit = (Button) v.findViewById(R.id.button7);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RateEmployersFragment.this.getActivity(), Home.class);
                startActivity(i);
            }
        });

        return v;
    }

}
