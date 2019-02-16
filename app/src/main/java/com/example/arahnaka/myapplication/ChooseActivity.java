package com.example.arahnaka.myapplication;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChooseActivity extends AppCompatActivity
        implements View.OnClickListener  {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private Button buttonchoose, buttonth, buttonen, buttonmu;
    private static String key="KEY";

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
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
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
        }

        key = getIntent().getStringExtra("KEY");
        buttonchoose = (Button) findViewById(R.id.buttonchoose);
        buttonth = (Button) findViewById(R.id.buttonth);
        buttonen = (Button) findViewById(R.id.buttonen);
        buttonmu = (Button) findViewById(R.id.buttonmu);

        buttonchoose.setOnClickListener(this);
        buttonth.setOnClickListener(this);
        buttonen.setOnClickListener(this);
        buttonmu.setOnClickListener(this);
       /* if(key!=null) {
            Toast.makeText(ChooseActivity.this, key, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(ChooseActivity.this, "Fail", Toast.LENGTH_SHORT).show();
        }*/
    }

    @Override
    public void onClick(View v) {
        if(v == buttonchoose){
            startActivity(new Intent(this, LoginActivity.class));
        }
        if(v == buttonth){
            //Toast.makeText(this, "ภาษา : ไทย", Toast.LENGTH_SHORT).show();
            setLocate("th");
            recreate();
        }
        if(v == buttonen){
            //Toast.makeText(this, "ภาษา : อังกฤษ", Toast.LENGTH_SHORT).show();
            setLocate("en");
            recreate();
        }
        if(v == buttonmu){
            //Toast.makeText(this, "ภาษา : มลายู", Toast.LENGTH_SHORT).show();
            setLocate("ms");
            recreate();
        }
    }
    private void setLocate(String lang){
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //Save Lan//
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }
    //load Lan //
    public  void loadLocale(){
        SharedPreferences preferences = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = preferences.getString("My_Lang","");
        setLocate(language);
    }
    /*
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
            ActivityCompat.requestPermissions(ChooseActivity.this,
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
                    Toast.makeText(ChooseActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(ChooseActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(ChooseActivity.this,
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
        Toast.makeText(ChooseActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}
