package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
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
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Highlight2Activity extends AppCompatActivity/* implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener*/ {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String TAG = "Highlight2Activity";

    private ArrayList<String> mNamesNew = new ArrayList<>();
    private ArrayList<String> mImageUrlsNew = new ArrayList<>();

    private ArrayList<String> mImageUrlsHL = new ArrayList<>();
    private ArrayList<String> mNamesHL = new ArrayList<>();
    private ArrayList<String> mDateHL = new ArrayList<>();
    private ArrayList<String> mDesHL = new ArrayList<>();
    private ArrayList<String> mlocateHL = new ArrayList<>();
    private ArrayList<String> mtelHL = new ArrayList<>();

    private TextView findMore;
    private static final int ACTIVITY_NUM = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highlight2);
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
        findMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go = new Intent(Highlight2Activity.this,ActivityPlan.class);
                startActivity(go);
            }
        });

        getImages5();
    }

    private void getImages5(){

        mImageUrlsHL.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A1%E0%B8%B1%E0%B8%AA%E0%B8%A2%E0%B8%B4%E0%B8%94%E0%B8%81%E0%B8%A5%E0%B8%B2%E0%B8%87%E0%B8%88%E0%B8%B1%E0%B8%87%E0%B8%AB%E0%B8%A7%E0%B8%B1%E0%B8%94%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5-3.jpg?alt=media&token=01f849be-6061-42f7-aaef-ad203bf7bdb3");
        mNamesHL.add("มัสยิดกลางจังหวัดปัตตานี");
        mDateHL.add("2018-10-29");
        mDesHL.add("    เป็นมัสยิดที่สวยที่สุดของไทย สร้างในปี พ.ศ. 2497 เป็นศูนย์กลางในการประกอบศาสนกิจและเป็นศูนย์รวมจิตใจของชาวไทยมุสลิมในภาคใต้รูปแบบสถาปัตยกรรมมีรูปทรงคล้ายคลึงกับ ทัชมาฮาล ประเทศอินเดีย ตรงกลางเป็นอาคารมียอดโดมขนาดใหญ่มีโดมบริวารสี่ทิศ มีหอคอยอยู่สองข้างสูงเด่นเป็นสง่า บริเวณด้านหน้ามัสยิดมีสระน้ำสี่เหลี่ยมขนาดใหญ่ ภายในมัสยิดมีลักษณะเป็นห้องโถงมีระเบียงสองข้าง ภายในห้องโถงมีบังลังค์ทรงสูงและแคบเป็นที่สำหรับ “คอเต็บ” ยืนอ่านคุฎบะฮ์ในการละหมาดวันศุกร์หอคอยสองข้างนี้เดิมใช้เป็นหอกลางสำหรับตีกลอง เป็นสัญญาเรียกให้มุสลิมมาร่วมปฏิบัติศาสนกิจ ต่อมาใช้เป็นที่ติดตั้งลำโพง เครื่องขยายเสียงแทนเสียงกลอง ปัจจุบันขยายด้านข้างออกไปทั้งสองข้างและสร้างหอบัง(อะซาน) พร้อมขยายสระน้ำและทีอาบน้ำละหมาดไห้ดูสง่างามยิ่งขึ้น ภายในมัสยิดประดับด้วยหินอ่อนอย่างสวยงาม");
        mlocateHL.add("ตั้งอยู่ที่ ถนนยะรัง เส้นทางยะรัง-ปัตตานี ในเขตเทศบาลเมืองปัตตานี");
        mtelHL.add("10,000 - 20,000 บาท");

        mImageUrlsHL.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A1%E0%B8%B1%E0%B8%AA%E0%B8%A2%E0%B8%B4%E0%B8%94%E0%B8%81%E0%B8%A3%E0%B8%B7%E0%B8%AD%E0%B9%80%E0%B8%8B%E0%B8%B0-1.jpg?alt=media&token=2c150a41-fa8a-45dd-bce0-5de0ca8aa313");
        mNamesHL.add("มัสยิดกรือเซะ");
        mDateHL.add("2018-10-29");
        mDesHL.add("    เป็นมัสยิดโบราณอายุกว่า 300 ปี มีความเกี่ยวข้องกับประวัติศาสตร์สำคัญของจังหวัดปัตตานี มีลักษณะโดดเด่นในสถาปัตยกรรมเชิงช่างผสมผสานศิลปะอาหรับสร้างขึ้นในสมัย สุลต่านมุฎอลฟัร ซาห์ ซึ่งเป็นพระราชโอรสคนโตของพญาอินทิรา (เจ้านครที่รับอิสลามองค์แรก) และเป็นมัสยิดแห่งแรกในเอเชียอาคเนย์ที่ได้ก่อสร้างด้วยอิฐโดยใช้ฐานรากในรูปแบบพานเป็นที่ตั้งตัวมัสยิด");
        mlocateHL.add("การเดินทางมัสยิดกรือเซะ ตั้งอยู่ที่ ตำบลตันหยงลุโละ อำเภอเมืองปัตตานี ห่างจากศาลากลางจังหวัดปัตตานีประมาณ 8.5 กิโลเมตรไปตามเส้นทางหลักหมายเลข 42");
        mtelHL.add("084-999999");

        mImageUrlsHL.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A7%E0%B8%B1%E0%B8%87%E0%B8%AB%E0%B8%99%E0%B8%AD%E0%B8%87%E0%B8%88%E0%B8%B4%E0%B8%81-4.jpg?alt=media&token=18e96573-1b37-4090-892c-3b86678a8358");
        mNamesHL.add("วังเจ้าเมืองหนอกจิก");
        mDateHL.add("2018-10-29");
        mDesHL.add("    วังหนองจิก เป็นบ้านพักของเจ้าเมืองหนองจิกในสมัยเจ็ดหัวเมืองซึ่งมีความเป็นมาทางประวัติศาสตร์ มีเอกลักษณ์ทางวัฒนธรรมซึ่งคงเหลือหลักฐานทางประวัติศาสตร์ สันนิษฐานว่าสร้างมาก่อนปีพ.ศ. 2437 สมัยเจ้าเมืองหนองจิก (ทัด ณ สงขลา) จนถึงเจ้าเมืองคนสุดท้าย คือ พระยาเพชราภิบาลนฤเบศร์วาปีเขตมุจลินทร์นฤบดินทร์ สวามิภักดิ์ (พวง ณ สงขลา) เป็นผู้อยู่อาศัยในวังนี้ วังหนองจิกล้อมรอบด้วยกำแพงวังแถบซุ้มประตูแบบสถาปัตยกรรมจีน ภายในบริเวณวังมีบ่อน้ำแผ่นอิฐที่เหลืออยู่เป็นแหล่งศึกษาประวัติศาสตร์และท่องเที่ยวเชิงวัฒนธรรม");
        mlocateHL.add("วังหนองจิก ตั้งอยู่ หมู่ที่ 1 ตำบลตุยง อำเภอหนองจิก จังหวัดปัตตานี อยู่ห่างจากศาลากลางจังหวัดปัตตานีประมาณ 9 กิโลเมตร ไปตามเส้นทางหลวงหมายเลข 42 เลี้ยวขวาไปประมาณ 100 เมตร");
        mtelHL.add("084-999999");

        mImageUrlsHL.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A7%E0%B8%B1%E0%B8%87%E0%B8%A2%E0%B8%B0%E0%B8%AB%E0%B8%A3%E0%B8%B4%E0%B9%88%E0%B8%87.jpg?alt=media&token=54aed153-b5d7-4573-a233-f956fb9ac2de");
        mNamesHL.add("วังยะหริ่ง");
        mDateHL.add("2018-10-29");
        mDesHL.add("    ว ังยะหริ่งเป็นอาคาร 2 ชั้น แบบเรือนไทยมุสลิมผสมกับแบบบ้านแถบยุโรป ตัววังเป็นรูปตัวยูชั้นบนภายในอาคารจัดเป็นห้องโถงขนาดใหญ่ มีบันไดทอดขึ้นไปสู่ระเบียงทั้งสองด้าน จากระเบียงมีประตูเปิดเข้าสู่ห้องโถงใหญ่ คล้ายกับท้องพระโรง ด้านข้างของตัวอาคารทั้ง 2 ด้าน เป็นห้องสำหรับพักผ่อนของเจ้าเมือง และบุตรธิดาข้างละ 4 ห้อง ส่วนชั้นล่างนั้นเป็นลานโล่งแบบใต้ถุนบ้าน ลักษณะเด่น คือ บันไดบ้านโค้งแบบยุโรป มีช่องแสงประดับด้วยกระจกสีเขียว แดง และน้ำเงิน ช่องระบายอากาศ และหน้าจั่ว ทำด้วยไม้ ฉลุเป็นลวดลาย พรรณพฤกษา ตามแบบศิลปะชวา และตะวันตก ทำให้ตัววังสง่างาม นอกจากความงามสง่าของอาคาร ที่แซมลวดลายฉลุประดับประดา ผนวกกับประโยชน์ใช้สอยที่รายรอบด้วยห้องหับนานา เป็นมนต์เสน่ห์ดึงดูดสายตาของนักท่องเที่ยให้มาค้นหาเรื่องราว การผสมผสานศิลปกรรมพื้นเมือง และชวา ตั้งตระหง่านอยู่ใจกลางเมืองยะหริ่งในปัจจุบัน นับเป็นความภาคภูมิใจของชาวยะหริ่ง เพราะสถาปัตยกรรมหลังนี้ ได้ทำหน้าที่ต้อนรับนักท่องเที่ยว ผู้มาเยือนจากต่างแดนอยู่เป็นกิจวัตร ปัจจุบันนี้วังยะหริ่งยังมีให้เห็นถึงความสมบูรณ์ แม้จะมีอายุการสร้างวังของเจ้าเมืองยะหริ่งมานานถึง 100 กว่าปี");
        mlocateHL.add("หมายเลข 41 ผ่านอำเภอทุ่งสง จังหวัดพัทลุง อำเภอหาดใหญ่ อำเภอเมืองยะลา ต่อด้วยถนนสายปัตตานี-นราธิวาส ถึงหมู่ที่ 2 ตำบลยามู อำเภอยะหริ่ง จังหวัดปัตตานี");
        mtelHL.add("084-999999");

        mImageUrlsHL.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%82%E0%B8%9A%E0%B8%A3%E0%B8%B2%E0%B8%93%E0%B8%A2%E0%B8%B0%E0%B8%A3%E0%B8%B1%E0%B8%87-4.jpg?alt=media&token=27068f3e-bec6-4b2f-8815-88807238515d");
        mNamesHL.add("เมืองโบราณยะรัง");
        mDateHL.add("2018-10-29");
        mDesHL.add("    ย้อนกลับไปสู่ชุมชนสมัยแรกเริ่มประวัติศาสตร์ในภาคใต้ของประเทศไทย และเชื่อว่าที่นี่คือที่ตั้งอาณาจักรโบราณ “ลังกาสุกะ” หรือ “ลังยาเสียว” ตามที่มีหลักฐานปรากฏในเอกสารของจีน ชวา มลายู และอาหรับ ทั้งนี้ ลักษณะของเมืองโบราณยะรัง สันนิษฐานว่า มีผังเมืองเป็นรูปวงรีขนาดใหญ่ในพื้นที่ประมาณ 9 ตารางกิโลเมตร และเป็นเมืองที่มีการสร้างทับซ้อนกันถึง 3 เมือง โดยขยายตัวเชื่อมต่อกันซึ่งประกอบไปด้วย • เมืองโบราณบ้านวัด  มีศูนย์กลางเป็นลานจัตุรัสกลางเมือง ล้อมรอบด้วยคูน้ำ และมีซากเนินดินโบราณสถาน กระจายอยู่โดยรอบกว่า 25 แห่ง โดยเฉพาะอย่างยิ่งทางทิศตะวันตกและทางทิศเหนือ ในบริเวณพื้นที่บ้านจาเละ • เมืองโบราณบ้านจาเละ มีศูนย์กลางอยู่ที่สระน้ำ ที่โอบล้อมด้วยคูเมืองรูปสี่เหลี่ยม ถัดจากกลุ่มโบราณสถานบ้านวัดขึ้นไปทางทิศเหนือประมาณ 1 กิโลเมตร • เมืองโบราณบ้านปราแว เป็นเมืองคูน้ำคันดินขนาดเล็กที่มีผังเมืองเป็นรูปสี่เหลี่ยมด้านไม่เท่ามีป้อมดินทั้ง 4 มุมเมือง และมีคลองส่งน้ำต่อเชื่อมกับคูเมืองโบราณบ้านจาเละสี่มุมเมืองด้านทิศเหนือทั้ง 2 ด้าน นอกจากร่องรอยของคูน้ำ คันดินคูเมืองโบราณทั้ง 3 แห่งแล้ว ภายในกลุ่มเมืองโบราณนี้ ยังพบซากโบราณสถานเนินดินกระจัดกระจายอยู่ทั่วไปไม่น้อยกว่า 30 แห่ง");
        mlocateHL.add("จากตัวเมืองปัตตานี ไปทางทิศใต้ตามถนนสายปัตตานี - ยะลา ประมาณ กิโลเมตร ถึงอำเภอยะรัง เลี้ยวซ้ายสู่เมืองโบราณ ระยะทาง ๑.๕ กิโลเมตร");
        mtelHL.add("084-999999");

        initRecyclerViewHL();

    }
    private void initRecyclerViewHL(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerViewHL);
        recyclerView.setLayoutManager(layoutManager);

        RecyclerViewAdapterHighlight adapterHL = new RecyclerViewAdapterHighlight(this, mImageUrlsHL, mNamesHL, mDateHL, mDesHL, mlocateHL, mtelHL);
        recyclerView.setAdapter(adapterHL);
    }
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(Highlight2Activity.this,Setting.class);
            startActivity(goSet);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(com.example.arahnaka.myapplication.Highlight2Activity.this,bottomNavigationViewEx);
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
            ActivityCompat.requestPermissions(Highlight2Activity.this,
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
                    Toast.makeText(Highlight2Activity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(Highlight2Activity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Highlight2Activity.this,
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
        Toast.makeText(Highlight2Activity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }*/
}
