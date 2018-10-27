package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FacebookAuthProvider;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
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

public class Home extends AppCompatActivity implements View.OnClickListener {

    String names[];
    DrawerLayout mDrawerLayout;
    ListView mDrawerList;
    ImageView toggle;
    TextView Map_, currJob_, wel, ageTt, toWork, itemList ,Emp_name ,textViewUserEmail , FindMis;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    private FirebaseAuth firebaseAuth;
    public final static String Storage_Path = "Employee_profile/";
    public final static String Database_Path = "Employee";
    CircleImageView ProfileImg;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(this, Login.class));
        }
       UID = firebaseAuth.getCurrentUser().getUid();
//        progressDialog = new ProgressDialog(this);

        //custom tool bar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toobarhome);
        setSupportActionBar(toolbar);

        ImageView drawer = (ImageView) findViewById(R.id.imageView77);
        ImageView iv2 = (ImageView) findViewById(R.id.imageView79);
        ImageView iv3 = (ImageView) findViewById(R.id.imageView80);
//        Map_ = (TextView) findViewById(R.id.mapLoc);
//        currJob_ = (TextView) findViewById(R.id.mapLoc);
        toggle = (ImageView) findViewById(R.id.toggleImageView);
        wel = (TextView) findViewById(R.id.welTxt);
        ageTt = (TextView) findViewById(R.id.ageTxt);
        toWork = (TextView) findViewById(R.id.reTxt);
        FindMis = (TextView) findViewById(R.id.findMis);




        //FONT FAMILY
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/Aileron-SemiBold.otf");
        toWork.setTypeface(typeface3);



//        Typeface aliReg = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Regular.otf");

        Typeface openSans = Typeface.createFromAsset(getAssets(), "fonts/OpenSans-Regular.ttf");
        wel.setTypeface(openSans);
        ageTt.setTypeface(openSans);

        iv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, MissionActivity.class);
                i.putExtra("mission", "1");
                startActivity(i);
            }
        });

        iv3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, FindAMission.class);
                startActivity(i);
            }
        });

        toggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Drawable drawable = toggle.getDrawable();
                Drawable d = getResources().getDrawable(R.mipmap.switch_on);

                if (drawable.getConstantState() == d.getConstantState()) {
                    toggle.setImageResource(R.mipmap.switch_off);
                } else {
                    toggle.setImageResource(R.mipmap.switch_on);
                }
            }
        });

        names = new String[]{"Home" , "Profile", "Statements", "Payment information", "Job offers", "Rate employers", "Logout"};
        int img[] = { R.mipmap.profile_icon, R.mipmap.profile_icon, R.mipmap.statements_icon, R.mipmap.payment_icon, R.mipmap.joboffer_icon, R.mipmap.rate_icon, R.mipmap.logout_icon};
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setFitsSystemWindows(true);

        // Set the adapter for the list view
        //mDrawerList.setAdapter(new ArrayAdapter<String>(this, R.layout.drawer_items, mPlanetTitles));
        mDrawerList.setAdapter(new CustomAdapter2(this, img, names));

        ViewGroup header = (ViewGroup) getLayoutInflater().inflate(R.layout.nav_header, mDrawerList, false);


        //geting email
//        textViewUserEmail = (TextView) findViewById(R.id.view_emp_email);
//        FirebaseUser employeEmail = firebaseAuth.getCurrentUser();
//        textViewUserEmail.setText(employeEmail.getEmail());
           // textViewUserEmail.setTypeface(aliReg);

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
                Intent i = new Intent(Home.this, Settings.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        ImageView email = (ImageView) footer.findViewById(R.id.imageView85);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Home.this, Email.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

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

        //int dialog and Drawer Activity
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

        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        //map and mission
//        Map_.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(Home.this, FindAMissionFragment.class);
////                startActivity(i);
//
//            }
//        });
//
//        currJob_.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent i = new Intent(Home.this, CurrentMissionDetailFragment.class);
////                startActivity(i);
//
//            }
//        });

        FindMis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home.this, "hellow", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Home.this,FindAMission.class);
                startActivity(i);

            }
        });

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


                    Emp_name.setText(Fname.toString() + " " + Lname.toString());
                    textViewUserEmail.setText(Email.toString());

                    Log.i("ImageUrl", Pro_url);
                    Picasso.with(Home.this).load(Pro_url).into(ProfileImg);

                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }




    //METHODS START HERE
    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        Intent i;

        switch (position) {

            case 1:
                i = new Intent(Home.this, Home.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 2:
                i = new Intent(Home.this, Profile.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 3:
                i = new Intent(Home.this, Statements.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                break;

            case 4:
//                i = new Intent(Home.this, Payment_InfoActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
                break;

            case 5:
//                i = new Intent(Home.this, Job_Offer.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
                break;

            case 6:
//                i = new Intent(Home.this, Rate_Employer_list.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                startActivity(i);
                break;

            case 7:
                firebaseAuth.getInstance().signOut();
                LoginManager.getInstance().logOut();

                finish();
                i = new Intent(Home.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(i);
                Log.i("test", "check");
                break;
        }


    }


    @Override
    public void onClick(View v) {

    }




}