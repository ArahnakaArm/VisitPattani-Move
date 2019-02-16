package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
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

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener /*, com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener*/ {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    private static final String TAG = "NewsActivity";

    private ArrayList<String> mNamesNew = new ArrayList<>();
    private ArrayList<String> mImageUrlsNew = new ArrayList<>();

    private ArrayList<String> mImageUrls4 = new ArrayList<>();
    private ArrayList<String> mNames4 = new ArrayList<>();
    private ArrayList<String> mDate4 = new ArrayList<>();
    private ArrayList<String> mDes4 = new ArrayList<>();
    private ArrayList<String> mlocate4 = new ArrayList<>();
    private ArrayList<String> mtel4 = new ArrayList<>();

    private TextView findMore;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
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
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        setNavi();

        findMore = (TextView)findViewById(R.id.findMore);
        findMore.setOnClickListener(this);



        getImages4();
    }

    private void getImages4(){

        mImageUrls4.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B9%80%E0%B8%97%E0%B8%A8%E0%B8%81%E0%B8%B2%E0%B8%A51.jpg?alt=media&token=02bebbe9-c7f4-4865-b55c-0220f0841afc");
        mNames4.add("Walk & Run วันรูฯ ดูปลาตีน");
        mDate4.add("9 พ.ย.");
        mDes4.add("ม.อ.ปัตตานี จัดกิจกรรมรำลึกถึงการมาอยู่ที่ตำบลรูสะมิแลเมื่อ 50 ปี ที่ผ่านมา\n" +
                "\n" +
                "ประชาชนในจังหวัดปัตตานี ศิษย์เก่า และนักเรียน นักศึกษา ร่วมวิ่งเพื่อรำลึกถึงวันแรกที่ประชาคมม.อ.ปัตตานีมาอยู่ที่ตำบลรูสะมิแล เมื่อ 50 ปีที่แล้ว ในกิจกรรม“Rusamilae Nature Walk & Run วันรูฯ ดูปลาตีน” และกิจกรรมสาธารณประโยชน์ที่มหาวิทยาลัยจัดให้แก่สังคม ในโอกาสครบรอบ 50 ปี ม.อ.\n" +
                "\n" +
                "พรุ่งนี้เปิดรับสมัครวันแรก 19 ตุลาคม 2561\n" +
                "เวลา 09.00 น. (รีบมาตอน 9 โมงเลยนร้า มาช้าบัตรวิ่งหมด อดเข้าร่วมกิจกรรมด้วย บัตรวิ่งมีจำนวนจำกัดนะจร้า)\n" +
                "\n" +
                "สถานที่รับสมัคร แบ่งเป็น 2 ช่วง ดังนี้ค่ะ \n" +
                "เวลา 09.00-10.00 น. สมัครได้ที่ห้องโถงกองกิจฯ \n" +
                "เวลา 10.00-16.30 น. สมัครได้ที่ หน่วย กยศ. กองกิจฯชั้น 2 \n" +
                "หรือจุดรับสมัคร 1-12 ตามความสะดวกในการสมัครเข้าร่วมกิจกรรม");
        mlocate4.add("ถนนหลังมหาวิทยาลัย เข้าสู่เส้นชัย ณ สถานีโคกโพธิ์ ณ ตึกเรือ");
        mtel4.add("082-4310495");

        mImageUrls4.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B9%80%E0%B8%97%E0%B8%A8%E0%B8%81%E0%B8%B2%E0%B8%A52.jpg?alt=media&token=245ae373-4318-472e-85a9-77e9ea3660ac");
        mNames4.add("ม.อ.วิชาการ 2561 คณะวิทยาศาสตร์และเทคโนโลยี");
        mDate4.add("6 ก.ย. – 7 ก.ย.");
        mDes4.add("คณะวิทยาศาสตร์และเทคโนโลยี ขอเชิญนักเรียน นักศึกษา และผู้สนใจ เข้าร่วมงาน ม.อ.วิชาการ ประจำปี 2561 ภายใต้หัวข้อหลัก 50 ปี ม.อ. สืบต่อพระปณิธาน สานประโยชน์เพื่อเพื่อนมนุษย์ “2561 ม.อ.วิชาการ นวัตกรรมเพื่อการพัฒนาที่ยั่งยืน ระหว่างวันที่ 6-7 กันยายน 2561\n" +
                "ณ อาคารพรีคลินิก คณะวิทยาศาสตร์และเทคโนโลยี ม.อ.ปัตตานี\n" +
                "\n" +
                "วันที่ 6 กันยายน 2561 พบกับ \n" +
                "-การประกวดโครงงานวิทยาศาสตร์\n" +
                "-การประกวดสิ่งประดิษฐ์\n" +
                "-การแข่งขันตอบปัญหาทางวิทยาศาสตร์ คณิตศาสตร์\n" +
                "-การแข่งขันยิงจรวดขวดน้ำ\n" +
                "-การแข่งขัน Electronics Fighter ครั้งที่ 1\n" +
                "-กิจกรรมบนเวที 100 วินาทีมีรางวัล , IQ 108\n" +
                "-ชมนิทรรศการผลงานทางวิชาการของภาควิชาต่างๆ\n" +
                "-ชมสาธิตการทดลองทางวิทยาศาสตร์\n" +
                "\n" +
                "วันที่ 6-7 กันยายน 2561 \n" +
                "เปิดให้เข้าชมโดมดาราศาสตร์ท้องฟ้าจำลอง ให้เข้าชมรอบละ 15 นาที รอบละ 35 คน \n" +
                "ช่วงเช้าเริ่มเวลา 09.00 -12.00 น. \n" +
                "และช่วงบ่ายเวลา 13.00 -15.00 น.\n" +
                "ชมฟรี\n");
        mlocate4.add("อาคารพรีคลินิก คณะวิทยาศาสตร์และเทคโนโลยี ม.อ.ปัตตานี");
        mtel4.add("-");

        mImageUrls4.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/News%2F%E0%B9%80%E0%B8%97%E0%B8%A8%E0%B8%81%E0%B8%B2%E0%B8%A53.jpg?alt=media&token=d4cdcdcf-db38-4661-810d-89115af85f4f");
        mNames4.add("มหาสมโภชเจ้าแม่ลิ้มกอเหนี่ยว 2561");
        mDate4.add("25 ก.พ. – 5 มี.ค.");
        mDes4.add("ขอเชิญเที่ยวงาน มหกรรมท่องเที่ยวปัตตานีอาเซียน กตัญญูคู่ฟ้า มหาสมโภช เจ้าแม่ลิ้มกอเหนี่ยวปัตตานี 2561 ระหว่างวันที่ 25 กุมภาพันธ์ - 5 มีนาคม 2561 ณ ศาลเจ้าเล่งจูเกียง (ศาลเจ้าแม่ลิ้มกอเหนี่ยวปัตตานี)\n" +
                "\n" +
                "Lim Ko Niao Goddess Celebration 2018  25 February 2018 - 5 March 2018 at Leng Chu Kiang Shrine\n" +
                "(Chao Mae Lim Ko Niao Chinese Shrine) Pattani, Thailand\n" +
                "--------------------\n" +
                "1st March 2018 at 01.00 PM\n" +
                "Ceremony of invoking Chao Mae Lim Ko Niao imprinted on the palankeen and Carrying parade around the town and Lim Ko Niao Goddess Celebration 2018 opening ceremony.\n" +
                "\n" +
                "2nd March 2018 at 04.00 AM\n" +
                "Ceremony of carrying Chinese gods palankeen across the pattani river and fire-walking.\n" +
                "\n" +
                "4th March - 5th March 2018 at 06.00 PM \n" +
                "1st Asia Lion dance Championships 2018");
        mlocate4.add("ถนน โรงเรียนเทศบาล 6  เทศบาลเมืองปัตตานี 94000");
        mtel4.add("073 311 860");

        initRecyclerView3();

    }
    private void initRecyclerView3(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterNew adapter = new RecyclerViewAdapterNew(this, mImageUrls4, mNames4, mDate4, mDes4, mlocate4, mtel4);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        if (v == findMore){
            finish();
            Intent start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.thairath.co.th/tags/%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5"));
            startActivity(start);
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(NewsActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(com.example.arahnaka.myapplication.NewsActivity.this,bottomNavigationViewEx);
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
            ActivityCompat.requestPermissions(NewsActivity.this,
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
                    Toast.makeText(NewsActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(NewsActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NewsActivity.this,
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
        Toast.makeText(NewsActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}

