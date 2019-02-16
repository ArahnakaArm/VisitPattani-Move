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

/**
 * Created by arahnaka on 6/18/2018.
 */

public class Start extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;
    FirebaseDatabase mFirebaseDatabase;

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


        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();

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
    @Override
    protected void onStart() {
        mGoogleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        super.onStop();
    }

    @Override
    public void onLocationChanged(final Location location) {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference myRef = database.getReference("Location");
        if (location != null) {
            new Handler().postDelayed(new Runnable() {

                @Override
                public void run() {
            String strLocation =
                    DateFormat.getTimeInstance().format(location.getTime()) + "\n" +
                            "Latitude=" + location.getLatitude() + "\n" +
                            "Longitude=" + location.getLongitude();

            String key1 = myRef.push().getKey();
            HashMap<String, Object> postValues1 = new HashMap<>();
            postValues1.put("latt", location.getLatitude());
            postValues1.put("long", location.getLongitude());

            Map<String, Object> childUpdates1 = new HashMap<>();
            childUpdates1.put( key1,postValues1);
            // childUpdates.put("/user-messages/Jirawatee/" + key, postValues);

            myRef.updateChildren(childUpdates1);
                }
            }, 10000);
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            ActivityCompat.requestPermissions(Start.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

            return;
        }
        LocationServices.FusedLocationApi.requestLocationUpdates(
                mGoogleApiClient, mLocationRequest, this);

    }

    @Override
    public void onRequestPermissionsResult(
            int requestCode,
            @NonNull String[] permissions,
            @NonNull int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                   /* Toast.makeText(Start.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();
*/
                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(Start.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Start.this,
                            "permission denied, ...:(",
                            Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(Start.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}



