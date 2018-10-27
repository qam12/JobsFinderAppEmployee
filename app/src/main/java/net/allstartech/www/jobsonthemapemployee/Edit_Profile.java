package net.allstartech.www.jobsonthemapemployee;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.InputType;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

import static net.allstartech.www.jobsonthemapemployee.Signup.UID;

public class Edit_Profile extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE_REQUEST = 234 ;
    private static final int PICK_IMAGE_2 = 235;
    private static final int PICK_IMAGE_3 = 236;

    AlertDialog jobReference, uploadedDocs, sinDialog;
    private DatabaseReference mDatabase;
    private StorageReference mStorage;
    int counter = 0, counter2 = 0;
    private FirebaseAuth firebaseAuth;
    public final static String Storage_Path = "Employee_profile/";
    public final static String Database_Path = "Employee";



    private Uri filepath;
    String imageUrl;
    ImageView add_link,  WCB_img , CriminalImg ,DrivingImg ,AddLang, mySkills;
    Button Submit_btn;
    private LinearLayout parentLayout , Sec_parentLayout;
    private int hint=0;

    EditText Pnum,Lname, Fname, EmpAddres , EmpAbout;
    TextView Eemail,Ppass;
    CircleImageView imgPro;
    String FNAM,LNAME,PHONE,ADDRES,ABOUT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.edit_profile);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();
//        if (firebaseAuth.getCurrentUser() == null) {
//            finish();
//            startActivity(new Intent(this, Login.class));
//        }
        UID = firebaseAuth.getCurrentUser().getUid();

        DrivingImg = (ImageView) findViewById(R.id.driverLic);

        WCB_img = (ImageView) findViewById(R.id.wcb_img);
        CriminalImg = (ImageView) findViewById(R.id.crminal_back);
        Submit_btn = (Button) findViewById(R.id.edit_profile_submit);
        add_link  =(ImageView) findViewById(R.id.add_link);

        Pnum = (EditText) findViewById(R.id.phne);
        Lname = (EditText) findViewById(R.id.lnam);
        Fname  = (EditText) findViewById(R.id.fnam);
        EmpAddres  = (EditText) findViewById(R.id.addAdrees);
        Eemail  = (TextView) findViewById(R.id.Email_id);
        Ppass  = (TextView) findViewById(R.id.Password);
        EmpAbout  =(EditText) findViewById(R.id.textView42);

        //Submit_btn.setOnClickListener(this);
        parentLayout = (LinearLayout)findViewById(R.id.parentLayout);
        imgPro = (CircleImageView) findViewById(R.id.imageView10);
        TextView  back = (TextView) findViewById(R.id.backward);
        AddLang = (ImageView) findViewById(R.id.addLang);
        Sec_parentLayout = (LinearLayout)findViewById(R.id.parentLayout_lang);
        mySkills = (ImageView) findViewById(R.id.openskil);



        WCB_img.setOnClickListener(this);
        CriminalImg.setOnClickListener(this);
        DrivingImg.setOnClickListener(this);


        //drawe code
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

//        View v = getLayoutInflater().inflate(R.layout.job_reference, null);
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setView(v);
//        jobReference = builder.create();
//        Button closeReference = (Button) v.findViewById(R.id.button16);
//        closeReference.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jobReference.dismiss();
//            }
//        });
//        v = getLayoutInflater().inflate(R.layout.uploaded_documents, null);
//        builder.setView(v);
//        uploadedDocs = builder.create();
//        Button closeUploadedDocs = (Button) v.findViewById(R.id.button18);
//        closeUploadedDocs.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadedDocs.dismiss();
//            }
//        });
//        TextView workExperience = (TextView) findViewById(R.id.textView50);
//        TextView certification = (TextView) findViewById(R.id.textView61);
//        workExperience.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                jobReference.show();
//            }
//        });
//        certification.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                uploadedDocs.show();
//            }
//        });
//        Button submit = (Button) findViewById(R.id.edit_profile_submit);
//        submit.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(Edit_Profile.this, Profile.class);
//                startActivity(i);
//            }
//        });
//
//        ImageView sin = (ImageView) findViewById(R.id.imageView100);
//        v = getLayoutInflater().inflate(R.layout.sin_info, null);
//        builder.setView(v);
//        sinDialog = builder.create();
//        ImageView closeSin = (ImageView) v.findViewById(R.id.imageView21);
//        Button okSin = (Button) v.findViewById(R.id.button19);
//        closeSin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sinDialog.dismiss();
//            }
//        });
//        okSin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sinDialog.dismiss();
//            }
//        });
//        sin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sinDialog.show();
//            }
//        });

        mySkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Edit_Profile.this, EmplyeeWork.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });


        add_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                if(counter == 5){
                    Toast.makeText(Edit_Profile.this, "Only five field allow", Toast.LENGTH_SHORT).show();
                }
                else {
                    createEditTextView();
                    counter++;
                }
            }
        });

        AddLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(counter2 == 2){
                    Toast.makeText(Edit_Profile.this, "Only Three field allow", Toast.LENGTH_SHORT).show();
                }
                else {
                    createEditTextLang();
                    counter2++;
                }
            }
        });

        Submit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //Save_empData();
                getting_Data();


            }
        });


        //geting image and email or more detail

        mDatabase.child(UID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                Employee_details emp_details = dataSnapshot.getValue(Employee_details.class);

                if (emp_details != null) {

                    String FirsName = emp_details.employee_FirstName;
                    String LastName = emp_details.employee_LastName;
                    String Phonenum = emp_details.employee_Telephone;
                    String Email = emp_details.employee_Email;
                    String Password = emp_details.employee_Password;
                    String Pro_url = emp_details.employee_Profilepicture;
                    String About_us = emp_details.employee_About;
                    String Adress = emp_details.employee_Address;

                    Fname.setText(FirsName.toString());
                    Lname.setText(LastName.toString());
                    Pnum.setText(Phonenum.toString());
                    Eemail.setText(Email.toString());
                    Ppass.setText(Password.toString());
                    EmpAbout.setText(About_us.toString());
                    EmpAddres.setText(Adress.toString());

                    Log.i("ImageUrl", emp_details.employee_Profilepicture);
                    Picasso.with(Edit_Profile.this).load(Pro_url).into(imgPro);

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }



    protected void createEditTextView() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams ( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        EditText edittTxt = new EditText(this);
            edittTxt.setHint("example@facebook.com/agent"+ hint);
            edittTxt.setLayoutParams(params);
            edittTxt.setLines(1);
            edittTxt.setHintTextColor(getResources().getColor(R.color.textLog));
            edittTxt.setTextColor(getResources().getColor(R.color.textLog));
            edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
            parentLayout.addView(edittTxt);



    }

    protected void createEditTextLang() {
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams ( RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
        EditText edittTxt = new EditText(this);
        edittTxt.setHint("example@facebook.com/agent"+ hint);
        edittTxt.setLayoutParams(params);
        edittTxt.setLines(1);
        edittTxt.setHintTextColor(getResources().getColor(R.color.textLog));
        edittTxt.setTextColor(getResources().getColor(R.color.textLog));
        edittTxt.setTextSize(TypedValue.COMPLEX_UNIT_SP,11);
        Sec_parentLayout.addView(edittTxt);
    }


    private void uploadfile(){


        if (filepath != null){
            StorageReference riversRef = mStorage.child("images/"+UID+"/documents");

            riversRef.putFile(filepath)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Uri downUrl = taskSnapshot.getDownloadUrl();
                            // Log.d("downUrl" , downUrl.toString());
                            imageUrl = downUrl.toString();
                        }

                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });

        }else {

            Toast.makeText(Edit_Profile.this , " This error from upload file" , Toast.LENGTH_SHORT).show();

        }


    }

    public void seletImg_wcb(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_REQUEST);
    }

    public void seletImg_sec(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_2);
    }

    public void seletImg_th(){
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select any image") , PICK_IMAGE_3);
    }

    public void Save_empData(){

      ArrayList<String> editTextValues = new ArrayList<>();
        for (int i = 0; i < parentLayout.getChildCount(); i++)
        {
            EditText editText = (EditText) parentLayout.getChildAt(i);
            editTextValues.add(editText.getText().toString());
            editTextValues.add(editText.getText().toString());
            Log.v("yes",editText.getText().toString());
         }


        String About_text = EmpAbout.getText().toString().trim();
        String Address_txt = EmpAddres.getText().toString().trim();
        String Emp_link = "";
        String Emp_Lanuges ="";
        String Emp_Experience = "";


        Employee_detail_sec employee_detail_sec = new Employee_detail_sec(About_text,Address_txt,Emp_Lanuges,editTextValues,Emp_Experience);

        HashMap<String, Object> result = new HashMap<>();
        result.put("employee_About", About_text);
        result.put("employee_Address",Address_txt);
        result.put("employee_links",editTextValues);
        result.put("Description",Emp_Lanuges);
        result.put("Description",Emp_Experience);

        mDatabase.child(UID).updateChildren(result);

    }

    public void getting_Data(){
        FNAM = Fname.getText().toString();
        LNAME = Lname.getText().toString();
        PHONE = Pnum.getText().toString();
        ABOUT = EmpAbout.getText().toString();
        ADDRES = EmpAddres.getText().toString();

        updateData();
    }

    public void updateData() {

        mDatabase.child(Database_Path).child(UID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                try{
                  mDatabase.child(UID).child("employee_FirstName").setValue(FNAM);
                  mDatabase.child(UID).child("employee_LastName").setValue(LNAME);
                  mDatabase.child(UID).child("employee_Telephone").setValue(PHONE);
                  mDatabase.child(UID).child("employee_About").setValue(ABOUT);
                  mDatabase.child(UID).child("employee_Address").setValue(ADDRES);

                }
                catch (Exception e){

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("User", databaseError.getMessage());
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {

            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                WCB_img.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
         if (requestCode == PICK_IMAGE_2 && resultCode == RESULT_OK && data != null && data.getData() != null){
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                CriminalImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        if (requestCode == PICK_IMAGE_3 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            filepath = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filepath);
                DrivingImg.setImageBitmap(bitmap);
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){

            case R.id.wcb_img:
                seletImg_wcb();
                break;

            case R.id.crminal_back:
                seletImg_sec();
                break;
            case R.id.driverLic:
                seletImg_th();
                break;

        }

    }


}

