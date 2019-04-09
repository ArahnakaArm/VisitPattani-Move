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


public class HotelActivity extends AppCompatActivity implements View.OnClickListener /*, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener*/ {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String TAG = "HotelActivity";

    private ArrayList<String> mImageUrlsHo = new ArrayList<>();
    private ArrayList<String> mNamesHo = new ArrayList<>();
    private ArrayList<String> mDateHo = new ArrayList<>();
    private ArrayList<String> mDesHo = new ArrayList<>();
    private ArrayList<String> mlocateHo = new ArrayList<>();
    private ArrayList<String> mtelHo = new ArrayList<>();
    private ArrayList<String> mRatingHo = new ArrayList<>();
    private ArrayList<String> mPriceHo = new ArrayList<>();

    private TextView find;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hotel);
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
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        setSupportActionBar(toolbar);
        setNavi();

        find = (TextView)findViewById(R.id.findHotel);
        find.setOnClickListener(this);

        getImagesHo();
    }
    public void onClick(View view){
        if (view == find){
            finish();
            Intent start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.agoda.com/th-th/pages/agoda/default/DestinationSearchResult.aspx?city=17792&checkIn=2018-11-13&los=1&rooms=1&adults=2&children=0&cid=-1&languageId=22&userId=1edba8cf-5da9-4020-9ef2-0e73250ae4ce&sessionId=hwprdzb5gtp421mdyxqrxgqg&pageTypeId=1&origin=TH&locale=th-TH&aid=130243&currencyCode=THB&htmlLanguage=th-th&cultureInfoName=th-TH&ckuid=1edba8cf-5da9-4020-9ef2-0e73250ae4ce&prid=0&checkOut=2018-11-14&priceCur=THB&textToSearch=%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5&productType=-1"));
            startActivity(start);
        }
    }

    private void getImagesHo(){

        mImageUrlsHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F105274288.jpg?alt=media&token=49a39642-11f9-4b1b-a8a3-9130d6d520d8");
        mNamesHo.add("River Living Place");
        mDateHo.add("113/3 Rongaung Road, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mDesHo.add("    River Living Place ตั้งอยู่ในจังหวัดปัตตานี ที่พักมีอินเทอร์เน็ตไร้สาย (WiFi) ฟรี แผนกต้อนรับ 24 ชม. และบริการรับฝากสัมภาระ ห้องพักทุกห้องมีระเบียง และห้องน้ำส่วนตัว ห้องพักบางห้องมีลานระเบียง ห้องพักมีตู้เย็น");
        mlocateHo.add("113/3 Rongaung Road, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mtelHo.add("073-323-333");
        mRatingHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2FStar_rating_5_of_5.png?alt=media&token=6da0e828-5a7e-4106-9419-d9688e0164a2");
        mPriceHo.add(" เริ่มต้น 824 บาท");

        mImageUrlsHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F99972858.jpg?alt=media&token=75256e79-5c32-43da-b385-97c93a30efe3");
        mNamesHo.add("Pink House");
        mDateHo.add("148/19 Samakkee sai Khor Road, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mDesHo.add("    Pink House ตั้งอยู่ในจังหวัดปัตตานี ที่พักแห่งนี้มีบริการอินเทอร์เน็ตไร้สาย (WiFi) ฟรี และพื้นที่นั่งเล่นส่วนกลางไว้ให้บริการผู้เข้าพัก ห้องพักทุกห้องของเกสต์เฮาส์แห่งนี้มีโทรทัศน์จอแบน และห้องน้ำส่วนตัวพร้อมเครื่องใช้ในห้องน้ำฟรี บางห้องยังมีระเบียง");
        mlocateHo.add("148/19 Samakkee sai Khor Road, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mtelHo.add("-");
        mRatingHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2FStar_rating_5_of_5.png?alt=media&token=6da0e828-5a7e-4106-9419-d9688e0164a2");
        mPriceHo.add(" เริ่มต้น 400 บาท");

        mImageUrlsHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F85152574.jpg?alt=media&token=0d7e0c6b-54c8-409c-8243-dbbcccd0159c");
        mNamesHo.add("The Rooms Residence");
        mDateHo.add("2/27 Sabarang, Muang Pattani Districrt , Pattani, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mDesHo.add("    The Rooms Residence ตั้งอยู่ในอำเภอเมืองปัตตานีและมีสวนหย่อมและมีอินเทอร์เน็ตไร้สาย (WiFi) ฟรี ที่พักมีลานระเบียงและห้องนั่งเล่นส่วนกลาง ห้องพักติดตั้งโทรทัศน์จอแบนพร้อมช่องรายการดาวเทียม ห้องพักทุกห้องในเกสต์เฮาส์มีกาต้มน้ำ และห้องพักแต่ละห้องมีห้องน้ำส่วนตัวพร้อมอ่างอาบน้ำหรือฝักบัวและมีเครื่องใช้ในห้องน้ำฟรี ในขณะที่บางห้องมีระเบียง ห้องมีพื้นที่นั่งเล่น ท่านสามารถผ่อนคลายด้วยการร่วมกิจกรรมต่าง ๆ ในบริเวณใกล้ The Rooms Residence เช่น ปั่นจักรยาน เป็นต้น พนักงานที่แผนกต้อนรับมีบริการข้อมูลเกี่ยวกับพื้นที่ตลอด 24 ชม.");
        mlocateHo.add("2/27 Sabarang, Muang Pattani Districrt , Pattani, เมืองปัตตานี, ปัตตานี, ประเทศไทย, 94000");
        mtelHo.add("088-441-8407");
        mRatingHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F220px-Star_rating_4_of_5.png?alt=media&token=b6e25e3c-75d9-44ff-ae88-dd6699fd54cb");
        mPriceHo.add(" เริ่มต้น 824 บาท");

        mImageUrlsHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F129224848.jpg?alt=media&token=dc140b2a-9ad9-40e6-85bf-6aa49f0e4942");
        mNamesHo.add("Bakkahland Farm and Resort");
        mDateHo.add("54 Moo 4 Tumbon Donrak Amphoe Nongchik, เทพา, ปัตตานี, ประเทศไทย, 94000");
        mDesHo.add("Bakkahland Farm and Resort ตั้งอยู่ในจังหวัดปัตตานี และให้บริการที่พักพร้อมพื้นที่นั่งเล่น และอินเทอร์เน็ตไร้สาย (WiFi) ฟรี ห้องพักทุกห้องมีระเบียงพร้อมทิวทัศน์ทะเลสาบ ที่พักมีทั้งบริการจักรยานเช่าและบริการรถยนต์เช่า");
        mlocateHo.add("54 Moo 4 Tumbon Donrak Amphoe Nongchik, เทพา, ปัตตานี, ประเทศไทย, 94000");
        mtelHo.add("081-896-2464");
        mRatingHo.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2FStar_rating_5_of_5.png?alt=media&token=6da0e828-5a7e-4106-9419-d9688e0164a2");
        mPriceHo.add(" เริ่มต้น 505 บาท");

        initRecyclerViewHL();

    }
    private void initRecyclerViewHL(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHo);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterHotel adapterHo = new RecyclerViewAdapterHotel(this, mImageUrlsHo, mNamesHo, mDateHo, mDesHo, mlocateHo, mtelHo, mRatingHo, mPriceHo);
        recyclerView.setAdapter(adapterHo);
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(HotelActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(com.example.arahnaka.myapplication.HotelActivity.this,bottomNavigationViewEx);
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
            ActivityCompat.requestPermissions(HotelActivity.this,
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
                    Toast.makeText(HotelActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(HotelActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(HotelActivity.this,
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
        Toast.makeText(HotelActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}
