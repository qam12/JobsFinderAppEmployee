package net.allstartech.www.jobsonthemapemployee;

import android.app.ProgressDialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static net.allstartech.www.jobsonthemapemployee.R.id.textView;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextView tv,forgetpas,txtdg, txtSign;

    private EditText Email_emp;
    private CustomEditText password_emp;
    private Button login,fb_btn;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    static String UID;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //requestWindowFeature(Window.FEATURE_NO_TITLE);
        //setTheme(R.style.AppTheme_NoActionBar);
        setContentView(R.layout.login);




        //firebase Auth

        progressDialog = new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null){
            //profile ativity
            UID =firebaseAuth.getCurrentUser().getUid();
            finish();
            startActivity(new Intent(getApplicationContext(),Home.class));
            //Toast.makeText(this, "Profile Activity", Toast.LENGTH_SHORT).show();
        }

        Email_emp = (EditText) findViewById(R.id.editText);
        login = (Button) findViewById(R.id.button);
        password_emp = (CustomEditText) findViewById(R.id.editText2);
        forgetpas = (TextView)findViewById(R.id.textView);
        txtdg  =(TextView) findViewById(R.id.textView5);
        txtSign  =(TextView) findViewById(R.id.textView6);
        fb_btn = (Button) findViewById(R.id.button2);

        //fonts
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/Aileron-SemiBold.otf");
        Email_emp.setTypeface(typeface3);
        password_emp.setTypeface(typeface3);
        forgetpas.setTypeface(typeface3);

        Typeface typeBold = Typeface.createFromAsset(getAssets(), "fonts/Aileron-Bold.otf");
        login.setTypeface(typeBold);
        txtdg.setTypeface(typeBold);
        txtSign.setTypeface(typeBold);
        fb_btn.setTypeface(typeBold);





        login.setOnClickListener(this);


        tv = (TextView) findViewById(R.id.textView6);

        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });




        password_emp.setTag("hide");

        password_emp.setDrawableClickListener(new DrawableClickListener() {


            public void onClick(DrawablePosition target) {
                switch (target) {
                    case RIGHT:
                        //Do something here
                        if(password_emp.getTag().equals("hide"))
                        {
                            password_emp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_PASSWORD);
                            password_emp.setTransformationMethod(PasswordTransformationMethod.getInstance());
                            password_emp.setSelection(password_emp.getText().length());
                            password_emp.setTag("show");
                            //Toast.makeText(Login.this, "Show", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            password_emp.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                            password_emp.setTransformationMethod(null);
                            password_emp.setSelection(password_emp.getText().length());
                            password_emp.setTag("hide");
                            //Toast.makeText(Login.this, "Hide", Toast.LENGTH_SHORT).show();
                        }
                        break;

                    default:
                        break;
                }
            }

        });


    }



    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i == null) {
            return false;
            //throw new PackageManager.NameNotFoundException();
        }
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        context.startActivity(i);
        return true;
    }

    private void Employee_Login(){
        String email = Email_emp.getText().toString().trim();
        String password = password_emp.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            //Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            Email_emp.setError("Please enter email");
            return;
        }

        if(TextUtils.isEmpty(password)){
            //Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            password_emp.setError("Please enter password");
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog
        //progressDialog.setCancelable(false);
        progressDialog.setMessage("Login Please Wait...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                //progressDialog.dismiss();
                if (task.isSuccessful()){
                    //profile ativity
                    finish();
                    UID =firebaseAuth.getCurrentUser().getUid();
                    startActivity(new Intent(getApplicationContext(),Home.class));
                    //Toast.makeText(Login.this, "Profile Activity", Toast.LENGTH_SHORT).show();
                }
                else{
                    //display some message here
                   // Toast.makeText(Signup.this,"Registration Error",Toast.LENGTH_LONG).show();
                    Toast.makeText(Login.this, "Invalid Email and Password", Toast.LENGTH_SHORT).show();
                }
                 progressDialog.dismiss();
            }
        });
    }


    @Override
    public void onClick(View v) {

        if(v == login){
            Employee_Login();
        }

    }
}
