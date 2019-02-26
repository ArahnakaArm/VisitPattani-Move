package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.FirebaseError;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by arahnaka on 6/18/2018.
 */

public class Start extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    FirebaseDatabase mFirebaseDatabase;
    Timer timer;
    Query Q;
    private FirebaseRecyclerAdapter<Atraction, Tab1.NewsViewHolder> RVAdapter;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    DatabaseReference refid;
    static String key;
    static int cDay;
    static int cMonth;
    static int cYear;
    static int cMonthh;
    String asd;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitystart);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference DeviceidRef = database.getReference("UserAccount");
        final String id = Settings.Secure.getString(getContentResolver(),Settings.Secure.ANDROID_ID);
       // DeviceidRef.push(id);
/*
        String key = Userdiary.push().getKey();
        HashMap<String, Object> postValues = new HashMap<>();

        postValues.put("title", Placesname);
        postValues.put("Day", Sum);
        postValues.put("Month", Month);
        postValues.put("Year", Year);



        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/PlacesName/" + key, postValues);

        // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

        Userdiary.updateChildren(childUpdates);


*//*
        DeviceidRef
                .addListenerForSingleValueEvent(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            //bus number exists in Database
                        } else {
                            DeviceidRef.push(id);
                        }
                    }

                        @Override
                        public void abonCancelled (FirebaseError firebaseError){

                        }

                     });
*/

        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        );


        Button bt = (Button) findViewById(R.id.start_text);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        //


        Calendar calander = Calendar.getInstance();
        cDay = calander.get(Calendar.DAY_OF_MONTH);
        String Day = Integer.toString(cDay);
        cMonth = calander.get(Calendar.MONTH);
        cMonthh = calander.get(Calendar.MONTH)-1;
        String Monthh = Integer.toString(cMonthh);
        String Month = Integer.toString(cMonth);
        cYear = calander.get(Calendar.YEAR);
        String Year = Integer.toString(cYear);
        //Toast.makeText(Start.this,Day+"/"+Monthh+"/"+Year,Toast.LENGTH_LONG).show();

        //
        refid = mFirebaseDatabase.getReference("UserAccount").child("undefine");
        refid.push();

        myRef = mFirebaseDatabase.getReference("UserAccount");


        myRef.orderByChild("Email").equalTo("t@gmail.com").addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.d("User key", child.getKey());
                    key = child.getKey();
                    Log.d("User ref", child.getRef().toString());
                    Log.d("User val", child.getValue().toString());

                }
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
               // String value = dataSnapshot.getValue().toString();


/*

                String key2 = myRef.child(key).push().getKey();
                HashMap<String, Object> postValues = new HashMap<>();
                postValues.put("title", "asdads");


                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/PlacesName/" + key2, postValues);
                // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                myRef.updateChildren(childUpdates);
*/
            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent goHome = new Intent(Start.this,ChooseActivity.class);
                startActivity(goHome);
            }
        },4000);



        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
/*
                myRef2 = mFirebaseDatabase.getReference("UserAccount").child(key);
                String key2 = myRef2.child(key).push().getKey();
                HashMap<String, Object> postValues = new HashMap<>();
                postValues.put("title", "asdads");


                Map<String, Object> childUpdates = new HashMap<>();
                childUpdates.put("/PlacesName/" + key2, postValues);
                // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

                myRef2.updateChildren(childUpdates);*/

                    //Toast.makeText(Start.this, key, Toast.LENGTH_SHORT).show();
                    Intent intent1 = new Intent(Start.this, ChooseActivity.class);
                    intent1.putExtra("KEY", key);
                    startActivity(intent1);


                   // Toast.makeText(Start.this, "Try Again", Toast.LENGTH_SHORT).show();


            }
        });


    }


}



