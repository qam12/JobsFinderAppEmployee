package net.allstartech.www.jobsonthemapemployee;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.AccessTokenTracker;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.ProfileTracker;
import com.facebook.appevents.AppEventsLogger;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import net.allstartech.www.jobsonthemapemployee.Edit_Profile;
import net.allstartech.www.jobsonthemapemployee.Employee_details;
import net.allstartech.www.jobsonthemapemployee.Home;
import net.allstartech.www.jobsonthemapemployee.Login;
import net.allstartech.www.jobsonthemapemployee.OnSwipeTouchListener;
import net.allstartech.www.jobsonthemapemployee.R;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.R.attr.data;
import static android.R.attr.displayOptions;
import static android.R.attr.id;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    //public final static String Storage_Path = "Employee_profile/"; // Folder path for Firebase Storage.
    public final static String Database_Path = "Employee"; // Root Database Name for Firebase Database.
    EditText ImageName ;                // Creating EditText.
    ImageView SelectImage;
    private Uri FilePathUri;                // Creating URI.
    int Image_Request_Code = 7;    // Image request code for onActivityResult()
    static String UID;
    String imageUrl;

    ProgressDialog progressDialog ;
    private EditText Eemail;
    private EditText Epass;
    private Button Reg_coch;
    private CircleImageView uploadimg;
    EditText fname,lname,phone,con_pass;
    private DatabaseReference mDatabase;
    private FirebaseAuth firebaseAuth;
    private StorageReference mStorage;
    private FirebaseAuth.AuthStateListener firebaseAuthlist;
    TextView tit_head, arg;
    AlertDialog loginAlert, callAlert;
    LoginButton regButton_fb;
    Button fb;
    CallbackManager callbackManager;
    private AccessTokenTracker accessTokenTracker;
    private ProfileTracker profileTracker;
    String frstname , lstname  , pass , ephone  , eemail, about, addres;
    ImageView Imgupload;



    @TargetApi(Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setTheme(R.style.AppTheme_NoActionBar);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.signup);

        mStorage = FirebaseStorage.getInstance().getReference();
        mDatabase = FirebaseDatabase.getInstance().getReference(Database_Path);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() != null) {
            //profile ativity
            finish();
            startActivity(new Intent(getApplicationContext(), Home.class));
        }

        progressDialog = new ProgressDialog(this);

        final TextView login = (TextView) findViewById(R.id.textView4);
        TextView text = (TextView) findViewById(R.id.textView258);
        Eemail = (EditText) findViewById(R.id.editText9);
        Epass = (EditText) findViewById(R.id.editText10);
        Reg_coch = (Button) findViewById(R.id.button3);
        fname = (EditText) findViewById(R.id.editText3);
        lname = (EditText) findViewById(R.id.editText4);
        phone = (EditText) findViewById(R.id.editText8);
        con_pass = (EditText) findViewById(R.id.editText11);
        uploadimg = (CircleImageView) findViewById(R.id.profilePic);
        tit_head = (TextView) findViewById(R.id.textView2);
        Imgupload = (ImageView) findViewById(R.id.uplobtn);
        arg = (TextView) findViewById(R.id.textView3);
        fb = (Button) findViewById(R.id.button4);
        regButton_fb = (LoginButton) findViewById(R.id.fb_reg_id);

        callbackManager = CallbackManager.Factory.create();

        //defin font-family
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/Aileron-SemiBold.otf");
        tit_head.setTypeface(typeface3);
        fname.setTypeface(typeface3);
        lname.setTypeface(typeface3);
        phone.setTypeface(typeface3);
        con_pass.setTypeface(typeface3);
        Eemail.setTypeface(typeface3);
        Epass.setTypeface(typeface3);
        text.setTypeface(typeface3);
        login.setTypeface(typeface3);

//        Typeface typeBold = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Bold.otf");
//        login.setTypeface(typeBold);
//        txtdg.setTypeface(typeBold);
//        txtSign.setTypeface(typeBold);
//        fb_btn.setTypeface(typeBold);

        //define Html style
        String first = "By signing up, you agree to our  ";
        String second = "Terms of Service";
        String third = " and confirm that you have read our ";
        String fourth = "Privacy Policy";
        String fifth = ". You may receive e-mail notification from Jobs On The Map and can opt out at any time";
        //Spanned spanned = fromHtml(first + second + third + fourth + fifth);

        Spanned spanned = fromHtml(first + second + third + fourth + fifth);
        text.setText(first + second + third + fourth + fifth, TextView.BufferType.SPANNABLE);
        Spannable s = (Spannable) text.getText();
        int start = first.length() + second.length() + third.length();
        int end = start + fourth.length();
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

        int start2 = first.length();
        int end2 = start2 + second.length();
        s.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.colorPrimary)), start2, end2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);


        View v = getLayoutInflater().inflate(R.layout.login_alert, null);
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(v);
        loginAlert = builder.create();
        Button okLoginAlert = (Button) v.findViewById(R.id.button13);
        okLoginAlert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginAlert.dismiss();

                Intent i = new Intent(Signup.this, Edit_Profile.class);
                startActivity(i);

            }
        });

        ImageView closeLoginAlert = (ImageView) v.findViewById(R.id.imageView16);
        closeLoginAlert.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onTouch() {
                loginAlert.dismiss();
            }
        });
        v = getLayoutInflater().inflate(R.layout.call_us, null);
        builder.setView(v);
        callAlert = builder.create();
        Button cancelCall = (Button) v.findViewById(R.id.button23);
        cancelCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAlert.dismiss();
            }
        });
        ImageView closeCallAlert = (ImageView) v.findViewById(R.id.imageView25);
        closeCallAlert.setOnTouchListener(new OnSwipeTouchListener(this) {
            @Override
            public void onTouch() {
                callAlert.dismiss();
            }
        });







        //uploadimage calling
        Imgupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Please Select Image"), Image_Request_Code);
            }
        });


        //fb registration
        firebaseAuthlist = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user=firebaseAuth.getCurrentUser();
                if (user != null){
//                    Intent intent = new Intent(Signup.this,Home.class);
//                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
//                    startActivity(intent);
                }

            }
        };

        //updating access Token
        accessTokenTracker= new AccessTokenTracker() {
            @Override
            protected void onCurrentAccessTokenChanged(AccessToken oldToken, AccessToken newToken) {

            }
        };

        //checking profile update
        profileTracker = new ProfileTracker() {
            @Override
            protected void onCurrentProfileChanged(com.facebook.Profile oldProfile, com.facebook.Profile currentProfile) {

            }
        };

        accessTokenTracker.startTracking();
        profileTracker.startTracking();
        //callback event method
        callbackManager = CallbackManager.Factory.create();
        regButton_fb.setReadPermissions(Arrays.asList("public_profile", "email", "user_birthday", "user_friends"));
        regButton_fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {

                System.out.println("onSuccess");
                Toast.makeText(Signup.this, "Success Login Success", Toast.LENGTH_SHORT).show();
                handleFacebookAccessToken(loginResult.getAccessToken(),loginResult);
            }

            @Override
            public void onCancel() {
                System.out.println("onCancel");
            }

            @Override
            public void onError(FacebookException exception) {
                System.out.println("onError");
                Log.v("LoginActivity", exception.getCause().toString());
            }
        });

        Reg_coch.setOnClickListener(this);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Signup.this, Login.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
            }
        });

        TextView talkToRepr = (TextView) findViewById(R.id.textView7);
        talkToRepr.setTypeface(typeface3);

        talkToRepr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callAlert.show();
            }
        });

    }



    //html creating method
    @SuppressWarnings("deprecation")
    public static Spanned fromHtml(String html){
        Spanned result;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            result = Html.fromHtml(html,Html.FROM_HTML_MODE_LEGACY);
        } else {
            result = Html.fromHtml(html);
        }
        return result;
    }

    //provide access token
    private void handleFacebookAccessToken(AccessToken token, final LoginResult loginResult) {

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("TAG", "signInWithCredential:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            //declare fb user method
                            getUserDetails(loginResult);

                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("TAG", "signInWithCredential:failure", task.getException());
                            Toast.makeText(Signup.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
    }

    //Registration func()
    private void Authemployee(){

        progressDialog.setCancelable(false);

        progressDialog.setMessage("Please Wait");
        progressDialog.show();


        frstname = fname.getText().toString().trim();
        lstname = lname.getText().toString().trim();
        ephone = phone.getText().toString().trim();
        eemail = Eemail.getText().toString().trim();
        pass = Epass.getText().toString().trim();



        firebaseAuth.createUserWithEmailAndPassword(eemail, pass)
                   .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            UID = firebaseAuth.getCurrentUser().getUid();
                            UploadImageFileToFirebaseStorage();

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Signup.this, "Error in Auth", Toast.LENGTH_SHORT).show();
                        }
        });

    }

    //open camera activty func and fb app
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == Image_Request_Code && resultCode == RESULT_OK && data != null && data.getData() != null) {
            FilePathUri = data.getData();
            try {
                // Getting selected image into Bitmap.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), FilePathUri);
                // Setting up bitmap selected image into ImageView.
                uploadimg.setImageBitmap(bitmap);
                // After selecting image change choose button above text.
                Toast.makeText(this, "Image Selected", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
        //open fb intent
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    //getting fb user data and upload
   protected void getUserDetails(LoginResult loginResult) {
       GraphRequest request = GraphRequest.newMeRequest(
               loginResult.getAccessToken(),
               new GraphRequest.GraphJSONObjectCallback() {
                   @Override
                   public void onCompleted(JSONObject object, GraphResponse response) {
                       Log.v("LoginActivity", response.toString());

                       try {
                           Log.d("tttttt",object.getString("id"));
                           //String birthday="";
                           if(object.has("birthday")){
                              // birthday = object.getString("birthday");
                           }
//                           TextView dummy1 = (TextView) findViewById(R.id.UserName);
//                           ImageView dummy2 = (ImageView) findViewById(R.id.imagPro);
                           String fid = object.getString("id");
                           String fnm = object.getString("first_name");
                           String lnm = object.getString("last_name");
                           String mail = object.getString("email");
                           String ImgUrl = ("https://graph.facebook.com/"+fid+"/picture?type=large");
                           //String gender = object.getString("gender");
                           //dummy1.setText("Name: "+fnm+" " +
                             //      ""+lnm+" \n"+"Email: " +
                              //     ""+mail+" \n" +"ID:");
                           //dummy1.id(ivpic).image();
                          // Picasso.with(Signup.this).load("https://graph.facebook.com/" + fid + "/picture?type=large").into(dummy2);
                           Log.d("aswwww","https://graph.facebook.com/"+fid+"/picture?type=large");


                           fb_user face_user = new fb_user(fnm,lnm,mail,ImgUrl);
                           UID =firebaseAuth.getCurrentUser().getUid();
                           mDatabase.child("Employee_Database");
                           mDatabase.child(UID).setValue(face_user, new DatabaseReference.CompletionListener() {
                               @Override
                               public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                                   if (databaseError != null) {
                                       System.out.println("Data could not be saved " + databaseError.getMessage());

                                   }
                                   else {

                                       finish();
                                       startActivity(new Intent(getApplicationContext(),Home.class));
                                       Toast.makeText(Signup.this,"Successfully Registered",Toast.LENGTH_LONG).show();
                                       System.out.println("Data saved successfully.");
                                   }

                               }
                           });

                       } catch (JSONException e) {
                           e.printStackTrace();
                       }

                   }
               });

       Bundle parameters = new Bundle();
       parameters.putString("fields", "id, first_name, last_name, email");
       request.setParameters(parameters);
       request.executeAsync();

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        firebaseAuth.addAuthStateListener(firebaseAuthlist);

    }
    @Override
    protected void onStop() {
        super.onStop();
        firebaseAuth.addAuthStateListener(firebaseAuthlist);
    }



    // Creating Method to get the selected image file Extension from File Path URI.
    public String GetFileExtension(Uri uri) {
        ContentResolver contentResolver = getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        // Returning the file Extension.
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ;
    }

    //upload image and data in firebase
   public void UploadImageFileToFirebaseStorage() {

           if (FilePathUri != null){
               StorageReference riversRef = mStorage.child("images/"+UID+"/profile.jpg");
               /// MAIN FOLDER MAIN JA K UID KA FOLDER HOGA THEN IMAGE HOGI:
               riversRef.putFile(FilePathUri)
                       .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                           @Override
                           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                               Uri downUrl = taskSnapshot.getDownloadUrl();
                               // Log.d("downUrl" , downUrl.toString());
                               imageUrl = downUrl.toString();
                               Validation();

                           }

                       })
                       .addOnFailureListener(new OnFailureListener() {
                           @Override
                           public void onFailure(@NonNull Exception e) {

                               Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();

                           }
                       });

           }else {

               Toast.makeText(Signup.this , " This error from upload file" , Toast.LENGTH_SHORT).show();

           }

   }




   //validation method

    public void Validation(){

        progressDialog.setCancelable(false);

        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        frstname = fname.getText().toString().trim();
        lstname = lname.getText().toString().trim();
        ephone = phone.getText().toString().trim();
        eemail = Eemail.getText().toString().trim();
        pass = Epass.getText().toString().trim();
        about = "Add aboutus";
        addres = "Add Adress";
        SaveData();
    }


    public void SaveData(){

        Employee_details  emp_details  = new Employee_details(frstname , lstname , ephone , eemail , pass , imageUrl, about, addres ,UID);
        mDatabase.child(UID).setValue(emp_details, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                if (databaseReference.equals(databaseError)){
                    progressDialog.dismiss();
                    Toast.makeText(Signup.this , "Error in Saving" , Toast.LENGTH_SHORT).show();
                }else {
                    progressDialog.dismiss();
                    Intent i = new Intent(Signup.this , Home.class);
                    startActivity(i);
                }

            }
        });

    }


    @Override
    public void onClick(View v) {
        if(v == Reg_coch){

            Authemployee();

        }

        if (v == fb){
            regButton_fb.performClick();
        }
    }
}

