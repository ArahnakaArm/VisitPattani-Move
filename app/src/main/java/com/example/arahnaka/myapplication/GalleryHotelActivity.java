package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arahnaka on 6/17/2018.
 */

public class GalleryHotelActivity extends AppCompatActivity /*implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener */{

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galleryhotel);
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
        getIncomingIntent();
        setNavi();
      /*  //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.CYAN);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        toolbar.setLogo(R.mipmap.ic_launcher);
        toolbar.setBackgroundColor(0x509CEC);
        setSupportActionBar(toolbar);*/
        //
    }
    private void getIncomingIntent(){
        if(getIntent().hasExtra("image_urlHo")&& getIntent().hasExtra("image_nameHo")){
            String imageUrlHo     = getIntent().getStringExtra("image_urlHo");
            String imageNameHo    = getIntent().getStringExtra("image_nameHo");
            String imageDateHo    = getIntent().getStringExtra("image_dateHo");
            String desHo          = getIntent().getStringExtra("dessHo");
            String locationHo     = getIntent().getStringExtra("locaHo");
            String telHo          = getIntent().getStringExtra("TelHo");
            String ratingHo          = getIntent().getStringExtra("RatingHo");
            String priceHo          = getIntent().getStringExtra("PriceHo");
            setImage(imageUrlHo ,imageNameHo ,imageDateHo ,desHo ,locationHo ,telHo ,ratingHo ,priceHo);

        }
    }
    private void setImage(String imageUrlHo, String imageNameHo, String imageDateHo, String desHo, String locateHo, String telHo, String ratingHo, String priceHo){

        TextView descriHo     = findViewById(R.id.descriptionHo);
        TextView teleHo       = findViewById(R.id.telHo);
        TextView locationHo   = findViewById(R.id.locateHo);
        ImageView imageHo     = findViewById(R.id.imagegallHo);
        ImageView ratinggHo    = findViewById(R.id.ratingHo);
        TextView  priceeHo     = findViewById(R.id.priceHo);


        // name.setText(imageNameHo);
        descriHo.setText(desHo);
        teleHo.setText(telHo);
        locationHo.setText(locateHo);
        // DateHo.setText(imageDateHo);
        priceeHo.setText(priceHo);

        Glide.with(this)
                .asBitmap()
                .load(ratingHo)
                .into(ratinggHo);

        Glide.with(this)
                .asBitmap()
                .load(imageUrlHo)
                .into(imageHo);
    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(GalleryHotelActivity.this,bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
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
            ActivityCompat.requestPermissions(GalleryHotelActivity.this,
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
                    Toast.makeText(GalleryHotelActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(GalleryHotelActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(GalleryHotelActivity.this,
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
        Toast.makeText(GalleryHotelActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}
