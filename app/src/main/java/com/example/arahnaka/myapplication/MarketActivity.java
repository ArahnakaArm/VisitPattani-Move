package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MarketActivity extends AppCompatActivity implements View.OnClickListener /*, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener */{

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    private static final String TAG = "MarketActivity";

    private ArrayList<String> mImageUrlsMK  = new ArrayList<>();
    private ArrayList<String> mNamesMK      = new ArrayList<>();
    private ArrayList<String> mDateMK       = new ArrayList<>();
    private ArrayList<String> mDesMK     = new ArrayList<>();
    private ArrayList<String> mlocateMK  = new ArrayList<>();
    private ArrayList<String> mtelMK     = new ArrayList<>();
    private ArrayList<String> mRatingMK  = new ArrayList<>();
    private ArrayList<String> mPriceMK   = new ArrayList<>();

    private TextView find;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_market);

     /*   mLocationRequest = new LocationRequest();
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
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        setSupportActionBar(toolbar);
        setNavi();

        find = (TextView)findViewById(R.id.findMarket);
        find.setOnClickListener(this);

        getImagesHo();
    }
    public void onClick(View view){
        if (view == find){
            finish();
            Intent start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.th/search?q=%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5+%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%94&rlz=1C1CHZL_enTH716TH753&oq=%E0%B8%9B%E0%B8%B1&aqs=chrome.3.69i59j69i61j69i57j69i59l2j69i61.2870j0j7&sourceid=chrome&ie=UTF-8"));
            startActivity(start);
        }
    }

    private void getImagesHo(){

        mImageUrlsMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%941.jpg?alt=media&token=6f5fc106-95e4-41be-b2d8-9d10a27ead22");
        mNamesMK.add("ตลาดเปิดท้าย ปัตตานี (ตลาดสุวรรณมงคล)");
        mDateMK.add("25/1 ถนนสันติสุข ตำบล จะบังติกอ อำเภอเมืองปัตตานี ปัตตานี 94000");
        mDesMK.add("วันจันทร์\t9:00–12:00\n" +
                "วันอังคาร\tปิดทำการ\n" +
                "วันพุธ\tปิดทำการ\n" +
                "วันพฤหัสบดี\t9:00–12:00\n" +
                "วันศุกร์\t16:00–21:00\n" +
                "วันเสาร์\t16:00–21:00\n" +
                "วันอาทิตย์\t16:00–21:00");
        mlocateMK.add("25/1 ถนนสันติสุข ตำบล จะบังติกอ อำเภอเมืองปัตตานี ปัตตานี 94000");
        mtelMK.add("อาหาร  เครื่องใช้");
        mRatingMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F220px-Star_rating_4_of_5.png?alt=media&token=b6e25e3c-75d9-44ff-ae88-dd6699fd54cb");
        mPriceMK.add(" เริ่มต้น 20 บาท");

        mImageUrlsMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%942.jpg?alt=media&token=7539aed2-926e-43e4-904d-1e8747ff90cc");
        mNamesMK.add("ตลาดเทศวิวัฒน์ 1");
        mDateMK.add("ตลาดเทศวิวัฒน์ 1, ถนนฤดี, ตำบลอาเนาะรู อำเภอเมืองปัตตานี จังหวัดปัตตานี, 94000");
        mDesMK.add("วันจันทร์\tปิดทำการ\n" +
                "วันอังคาร\t5:00–17:00\n" +
                "วันพุธ\t5:00–17:00\n" +
                "วันพฤหัสบดี\tปิดทำการ\n" +
                "วันศุกร์\t5:00–17:00\n" +
                "วันเสาร์\tปิดทำการ\n" +
                "วันอาทิตย์\t5:00–17:00");
        mlocateMK.add("ตลาดเทศวิวัฒน์ 1, ถนนฤดี, ตำบลอาเนาะรู อำเภอเมืองปัตตานี จังหวัดปัตตานี, 94000");
        mtelMK.add("อารหาร");
        mRatingMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F220px-Star_rating_4_of_5.png?alt=media&token=b6e25e3c-75d9-44ff-ae88-dd6699fd54cb");
        mPriceMK.add(" เริ่มต้น 20 บาท");

        mImageUrlsMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B8%95%E0%B8%A5%E0%B8%B2%E0%B8%943.jpg?alt=media&token=ffc9d253-c618-4a42-be12-61b674bbd9dd");
        mNamesMK.add("ตลาดหมอวิทย์");
        mDateMK.add("104/31 ถนนสามัคคีสาย ข ตำบล สะบารัง อำเภอเมืองปัตตานี ปัตตานี 94000");
        mDesMK.add("วันจันทร์\t10:00–20:00\n" +
                "วันอังคาร\t10:00–20:00\n" +
                "วันพุธ\t10:00–20:00\n" +
                "วันพฤหัสบดี\t10:00–20:00\n" +
                "วันศุกร์\t10:00–20:00\n" +
                "วันเสาร์\t10:00–20:00\n" +
                "วันอาทิตย์\t10:00–20:00");
        mlocateMK.add("104/31 ถนนสามัคคีสาย ข ตำบล สะบารัง อำเภอเมืองปัตตานี ปัตตานี 94000");
        mtelMK.add("เครื่องใช้");
        mRatingMK.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2FStar_rating_5_of_5.png?alt=media&token=6da0e828-5a7e-4106-9419-d9688e0164a2");
        mPriceMK.add(" เริ่มต้น 50 บาท");


        initRecyclerViewHL();

    }
    private void initRecyclerViewHL(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewMK);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterMarket adapterMK = new RecyclerViewAdapterMarket(this, mImageUrlsMK, mNamesMK, mDateMK, mDesMK, mlocateMK, mtelMK, mRatingMK, mPriceMK);
        recyclerView.setAdapter(adapterMK);
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(MarketActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(com.example.arahnaka.myapplication.MarketActivity.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
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
            ActivityCompat.requestPermissions(MarketActivity.this,
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
                    Toast.makeText(MarketActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(MarketActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MarketActivity.this,
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
        Toast.makeText(MarketActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}

