package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Edit_Profile_Other extends AppCompatActivity {

    EditText firAid, Airbrak, whms, H2s, fallA, forkLi, Scal, barten, foodSaf, foodH, pros, secur, wait,other;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.edit_profile_other_title);

        TextView back = (TextView) findViewById(R.id.textView250);

        setContentView(R.layout.edit_profile_other);

        firAid  = (EditText) findViewById(R.id.editText12);
        Airbrak  = (EditText) findViewById(R.id.editText13);
        whms  = (EditText) findViewById(R.id.editText14);
        H2s  = (EditText) findViewById(R.id.editText16);
        fallA  = (EditText) findViewById(R.id.editText17);
        Scal  = (EditText) findViewById(R.id.editText18);
        forkLi = (EditText)findViewById(R.id.editText19);
        barten  = (EditText) findViewById(R.id.editText20);
        foodSaf  = (EditText) findViewById(R.id.editText21);
        foodH  = (EditText) findViewById(R.id.editText22);
        pros  = (EditText) findViewById(R.id.editText23);
        secur  = (EditText) findViewById(R.id.editText24);
        wait  = (EditText) findViewById(R.id.editText25);
        other  = (EditText) findViewById(R.id.editText26);



        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/Aileron-SemiBold.otf");
        firAid.setTypeface(typeface3);
        Airbrak.setTypeface(typeface3);
        whms.setTypeface(typeface3);
        H2s.setTypeface(typeface3);
        fallA.setTypeface(typeface3);
        Scal.setTypeface(typeface3);
        forkLi.setTypeface(typeface3);
        barten.setTypeface(typeface3);
        foodSaf.setTypeface(typeface3);
        foodH.setTypeface(typeface3);
        pros.setTypeface(typeface3);
        secur.setTypeface(typeface3);
        wait.setTypeface(typeface3);
        other.setTypeface(typeface3);


        Typeface aliReg = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Regular.otf");



//        Typeface alibold = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Bold.otf");
//        About.setTypeface(alibold);
//        myLin.setTypeface(alibold);
//        Lang.setTypeface(alibold);
//        cert.setTypeface(alibold);
//        myAch.setTypeface(alibold);
//        mySk.setTypeface(alibold);
//        woExp.setTypeface(alibold);




        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Edit_Profile_Other.this, Edit_Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

    }
}
