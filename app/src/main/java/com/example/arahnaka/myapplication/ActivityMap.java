package com.example.arahnaka.myapplication;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.nfc.Tag;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arahnaka on 6/13/2018.
 */

public class ActivityMap extends AppCompatActivity /*implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener*/ {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String TAG = "ActivityMap";
    private static final int ERROR_DIALOG_REQUPEST = 9001;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
/*
        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setMaxWaitTime(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API)
                .build();
*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("Subtitle");
        toolbar.setSubtitleTextColor(Color.MAGENTA);

        toolbar.setBackgroundColor(0x509CEC);
        setSupportActionBar(toolbar);

        BottomNavigationView bottomNavigationView=(BottomNavigationView)findViewById(R.id.bottom_navigation);
       // BottomNavigationViewHelper.disableShiftMode(bottomNavigationView);
        if (isServiceOK()) {

            init();
        }



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.action_home:
                        Intent intent0 = new Intent(ActivityMap.this, MainActivity.class);
                        startActivity(intent0);

                        break;

                    case R.id.travelinfo:
                        Intent intent1 = new Intent(ActivityMap.this, ActivityTravel.class);
                        startActivity(intent1);
                        break;

                    case R.id.checkinnavi:
                        Intent intent2 = new Intent(ActivityMap.this, ActivityPlan.class);
                        startActivity(intent2);
                        break;

                    case R.id.map:

                        break;

                }


                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(ActivityMap.this,Setting.class);
            startActivity(goSet);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    private void init(){
        Button btn =(Button)findViewById(R.id.map);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ActivityMap.this,MapActivity.class);
                startActivity(intent);
            }
        });

    }
    public boolean isServiceOK(){
        Log.d(TAG,"isServiceOK : Checking google service version");
        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(ActivityMap.this);
        if(available==ConnectionResult.SUCCESS){
            Log.d(TAG,"isServiceOK : Google Play is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            Log.d(TAG,"isServiceOK : error can fix");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(ActivityMap.this,available,ERROR_DIALOG_REQUPEST);
            dialog.show();
        }else{
            Toast.makeText(this,"Cant make map",Toast.LENGTH_SHORT).show();
        }
        return false;


    }/*
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
            ActivityCompat.requestPermissions(ActivityMap.this,
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
                    Toast.makeText(ActivityMap.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(ActivityMap.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ActivityMap.this,
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
        Toast.makeText(ActivityMap.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}

