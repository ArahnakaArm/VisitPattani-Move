package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arahnaka on 6/18/2018.
 */


public class Setting extends AppCompatActivity implements View.OnClickListener , com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private TextView textTitle1, textTitle2, textTitle3, textTitle4 ,textTitle5;
    private TextView imageView1, imageView2, imageView3, imageView4;

    private CardView Card1, Card2, Card3, Card4, Card5;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

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

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        //toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(0xff509CEC);
        setSupportActionBar(toolbar);

        textTitle1 = (TextView)findViewById(R.id.textTitle1);
        imageView1 = (TextView)findViewById(R.id.imageView1);
        textTitle1.setOnClickListener(this);
        imageView1.setOnClickListener(this);

        Card1 = (CardView)findViewById(R.id.Card1);
        Card1.setOnClickListener(this);

        textTitle2 = (TextView)findViewById(R.id.textTitle2);
        imageView2 = (TextView)findViewById(R.id.imageView2);
        textTitle2.setOnClickListener(this);
        imageView2.setOnClickListener(this);

        Card2 = (CardView)findViewById(R.id.Card2);
        Card2.setOnClickListener(this);

        textTitle3 = (TextView)findViewById(R.id.textTitle3);
        imageView3 = (TextView)findViewById(R.id.imageView3);
        textTitle3.setOnClickListener(this);
        imageView3.setOnClickListener(this);

        Card3 = (CardView)findViewById(R.id.Card3);
        Card3.setOnClickListener(this);

        textTitle4 = (TextView)findViewById(R.id.textTitle4);
        imageView4 = (TextView)findViewById(R.id.imageView4);
        textTitle4.setOnClickListener(this);
        imageView4.setOnClickListener(this);

        Card4 = (CardView)findViewById(R.id.Card4);
        Card4.setOnClickListener(this);

        textTitle5 = (TextView)findViewById(R.id.textTitle5);
        textTitle5.setOnClickListener(this);
        Card5 = (CardView)findViewById(R.id.Card5);
        Card5.setOnClickListener(this);


    }
    public void onClick(View view) {
        if (view == textTitle1) {
            startActivity(new Intent(this, LanguageActivity.class));
        }
        if (view == imageView1) {
            startActivity(new Intent(this, LanguageActivity.class));
        }
        if (view == Card1) {
            startActivity(new Intent(this, LanguageActivity.class));
        }

        if (view == textTitle2) {
            startActivity(new Intent(this, PattaniActivity.class));
        }
        if (view == imageView2) {
            startActivity(new Intent(this, PattaniActivity.class));
        }
        if (view == Card2) {
            startActivity(new Intent(this, PattaniActivity.class));
        }

        if (view == textTitle3) {
            startActivity(new Intent(this, Agree2Activity.class));
        }
        if (view == imageView3) {
            startActivity(new Intent(this, Agree2Activity.class));
        }
        if (view == Card3) {
            startActivity(new Intent(this, Agree2Activity.class));
        }

        if (view == textTitle4) {
            startActivity(new Intent(this, VersionActivity.class));
        }
        if (view == imageView4) {
            startActivity(new Intent(this, VersionActivity.class));
        }
        if (view == Card4) {
            startActivity(new Intent(this, VersionActivity.class));
        }

        if (view == textTitle5) {
            StatusCheck.nologin();
            startActivity(new Intent(this, LoginActivity.class));
        }
        if (view == Card5) {
            StatusCheck.nologin();
            startActivity(new Intent(this, LoginActivity.class));
        }
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
            ActivityCompat.requestPermissions(Setting.this,
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
                    Toast.makeText(Setting.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(Setting.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Setting.this,
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
        Toast.makeText(Setting.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}
