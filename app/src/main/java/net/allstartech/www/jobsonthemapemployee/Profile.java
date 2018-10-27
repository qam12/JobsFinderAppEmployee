package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

import static net.allstartech.www.jobsonthemapemployee.Signup.UID;

public class Profile extends AppCompatActivity {

    String names[];
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    AlertDialog lastMinuteDialog;
    TextView EmpName,EmpDes,About,myLin,Lang,cert,myAch,mySk,woExp , Addres ,phnum, EmAdd;
    TextView textViewUserEmail, Emp_name , phoF,AdF ,aboutS ,EmF;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private FirebaseAuth firebaseAuth;
    public final static String Storage_Path = "Employee_profile/";
    public final static String Database_Path = "Employee";
    CircleImageView ProfileImg , Act_ProfileImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = this.getWindow();
        window.setBackgroundDrawableResource(R.mipmap.profile_header_img);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        window.setStatusBarColor(getResources().getColor(android.R.color.transparent));

        setContentView(R.layout.profile);

        //database refrence
        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(this,Login.class));
        }
        UID =firebaseAuth.getCurrentUser().getUid();

        //drawe activities define here
        ImageView drawer = (ImageView) findViewById(R.id.nav);
        ImageView iv = (ImageView) findViewById(R.id.imageView11);
        TextView chatbtn = (TextView) findViewById(R.id.chatBtn);
        chatbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Profile.this, Chat.class);
                startActivity(i);            }
        });
        iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Edit_Profile.class);
                startActivity(i);
            }
        });

        EmpName = (TextView) findViewById(R.id.textView38);
        EmpDes = (TextView) findViewById(R.id.textView39);
        About = (TextView) findViewById(R.id.chatBtn);
        myLin = (TextView) findViewById(R.id.textView43);
        Lang = (TextView) findViewById(R.id.textView46);
        cert = (TextView) findViewById(R.id.textView50);
        myAch = (TextView) findViewById(R.id.textView55);
        mySk = (TextView) findViewById(R.id.textView60);
        woExp = (TextView) findViewById(R.id.textView61);
        aboutS = (TextView) findViewById(R.id.textView42);
        phnum = (TextView) findViewById(R.id.phoneNum);
        Addres  =(TextView)findViewById(R.id.Addr);
        EmAdd = (TextView) findViewById(R.id.Em);
        phoF = (TextView) findViewById(R.id.phfrm);
        AdF = (TextView) findViewById(R.id.addFrm);
        EmF = (TextView) findViewById(R.id.emFrm);
        Act_ProfileImg = (CircleImageView)findViewById(R.id.imageView10);

       //font family
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/Aileron-SemiBold.otf");
        phnum.setTypeface(typeface3);
        Addres.setTypeface(typeface3);
        EmAdd.setTypeface(typeface3);
        phoF.setTypeface(typeface3);
        AdF.setTypeface(typeface3);
        EmF.setTypeface(typeface3);

        Typeface aliReg = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Regular.otf");
        EmpName.setTypeface(aliReg);
        aboutS.setTypeface(aliReg);

        Typeface alibold = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Bold.otf");
        About.setTypeface(alibold);
        myLin.setTypeface(alibold);
        Lang.setTypeface(alibold);
        cert.setTypeface(alibold);
        myAch.setTypeface(alibold);
        mySk.setTypeface(alibold);
        woExp.setTypeface(alibold);

        names = new String[]{"Profile", "Statements", "Payment information", "Job offers", "Rate employers", "Logout"};
        int img[] = {R.mipmap.profile_icon, R.mipmap.statements_icon, R.mipmap.payment_icon, R.mipmap.joboffer_icon, R.mipmap.rate_icon, R.mipmap.logout_icon};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerList.setFitsSystemWindows(true);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new CustomAdapter2(this, img, names));
        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.nav_header, mDrawerList, false);


        //define drawe item
        Emp_name = (TextView) header.findViewById(R.id.Emp_Name);
        textViewUserEmail = (TextView) header.findViewById(R.id.view_emp_email);
        ProfileImg = (CircleImageView) header.findViewById(R.id.proImg);
        mDrawerList.addHeaderView(header, null, false);

        View footer = getLayoutInflater().inflate(R.layout.nav_footer, null);
        mDrawerList.addFooterView(footer);

        ImageView settings = (ImageView) footer.findViewById(R.id.imageView84);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Settings.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        ImageView email = (ImageView) footer.findViewById(R.id.imageView85);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Profile.this, Email.class);
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

        View v = getLayoutInflater().inflate(R.layout.last_minute, null);
        builder.setView(v);
        lastMinuteDialog = builder.create();
        Button closeLastMinute = (Button) v.findViewById(R.id.button50);
        closeLastMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastMinuteDialog.dismiss();
            }
        });
        TextView lastMinute = (TextView) findViewById(R.id.textView57);
        lastMinute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lastMinuteDialog.show();
            }
        });
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());


        //Downloading data
        //Display username and email

        mDatabase.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Employee_details emp_details = dataSnapshot.getValue(Employee_details.class);

                if (emp_details != null) {

                    String Fname = emp_details.employee_FirstName;
                    String Lname = emp_details.employee_LastName;
                    String Email = emp_details.employee_Email;
                    String Pro_url = emp_details.employee_Profilepicture;
                    String Abouttext  = emp_details.employee_About;
                    String Addre_e  = emp_details.employee_Address;
                    String phone_num = emp_details.employee_Telephone;


                    //Download Drawer Data
                    Emp_name.setText(Fname.toString() + " " + Lname.toString());
                    textViewUserEmail.setText(Email.toString());

                    //personal data
                    EmF.setText(Email.toString());
                    AdF.setText(Addre_e.toString());
                    aboutS.setText(Abouttext.toString());
                    phoF.setText(phone_num.toString());

                    //download drawer image
                    Log.i("ImageUrl", Pro_url);
                    Picasso.with(Profile.this).load(Pro_url).into(ProfileImg);

                    //Download Activity Data

                    Log.i("ImageUrl", Pro_url);
                    Picasso.with(Profile.this).load(Pro_url).into(Act_ProfileImg);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }



    //method deine here

    //drawer click listener
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //drawer selecting method
    private void selectItem(int position)
    {
        Intent i;

        switch (position)
        {
            case 1:
                i = new Intent(Profile.this, Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 3:
                i = new Intent(Profile.this, Payment_InfoActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 4:
                i = new Intent(Profile.this, Job_Offer.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 5:
                i = new Intent(Profile.this, Rate_Employer_list.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 6:
                i = new Intent(Profile.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;
        }
    }
}
