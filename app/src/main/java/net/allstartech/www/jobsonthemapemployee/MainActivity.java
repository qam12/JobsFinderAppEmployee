package net.allstartech.www.jobsonthemapemployee;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.widget.ListView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_splash);


//        try {
//            PackageInfo info = getPackageManager().getPackageInfo(getPackageName(), PackageManager.GET_SIGNATURES);
//            for (Signature signature : info.signatures) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update(signature.toByteArray());
//                String hashKey = new String(Base64.encode(md.digest(), 0));
//                Log.i("", "printHashKey() Hash Key: " + hashKey);
//            }
//        } catch (NoSuchAlgorithmException e) {
//            Log.e("", "printHashKey()", e);
//        } catch (Exception e) {
//            Log.e("2", "printHashKey()", e);
//        }
       // Intent i = new Intent(this, Login.class);
       // startActivity(i);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {

                Intent mainIntent = new Intent(MainActivity.this,Login.class);
                MainActivity.this.startActivity(mainIntent);
                MainActivity.this.finish();
            }
        }, 2000);

/*
        ListView lv = (ListView) findViewById(R.id.emplyee_work_items);

        ArrayList<Work_Items> list = new ArrayList<>();

        Work_Items i1 = new Work_Items("Tutor","Elementary | High School", "11 hour tutioning");
        Work_Items i2 = new Work_Items("Grocery", "Dairy | Fruits | Vegetables | Meat", "Live for free home-delivery");
        Work_Items i3 = new Work_Items("Dry Cleaner", "Dresses | Shirts | Pants | Laundry", "Dry Cleaner");
        Work_Items i4 = new Work_Items("Gardener", "Lawn Mowling | Yard Work", "Very lowrates");

        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);
        list.add(i1);
        list.add(i2);
        list.add(i3);
        list.add(i4);


        CustomAdapter_work adapter = new CustomAdapter_work(this, R.layout.employee_work_list, list);

        lv.setAdapter(adapter);
        lv.setDivider(null);
*/
/*
        ListView lv = (ListView) findViewById(R.id.date_time);

        ArrayList<Items> list = new ArrayList<>();

        Items i1 = new Items("Date","14/06/2017");
        Items i2 = new Items("Time", "12 hours");

        list.add(i1);
        list.add(i2);

        CustomAdapter adapter = new CustomAdapter(this, R.layout.date_time_list, list);

        lv.setAdapter(adapter);
        lv.setDivider(null);
*/
    }
}
