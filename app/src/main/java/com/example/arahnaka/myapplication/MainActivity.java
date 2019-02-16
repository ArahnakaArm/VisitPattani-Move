package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.AttributeSet;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import android.util.Log;

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
import com.google.firebase.database.ValueEventListener;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private static final String TAG = "MainActivity";

    boolean loginStatus =true;
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    DatabaseReference myRef;
    DatabaseReference myRef2;

    private static final int ACTIVITY_NUM = 0;
    //vars
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDes = new ArrayList<>();
    private ArrayList<String> mDes2 = new ArrayList<>();
    private ArrayList<String> mDes3 = new ArrayList<>();
    private ArrayList<String> mlocate3 = new ArrayList<>();
    private ArrayList<String> mtel3 = new ArrayList<>();
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private ArrayList<String> mNames3 = new ArrayList<>();
    private ArrayList<String> mImageUrls3 = new ArrayList<>();
    private ArrayList<String> mNames4 = new ArrayList<>();
    private ArrayList<String> mImageUrls4 = new ArrayList<>();
    private ArrayList<String> mLoca = new ArrayList<>();
    private ArrayList<String> mLoca2 = new ArrayList<>();
    private ArrayList<String> mLoca3 = new ArrayList<>();
    private TextView txt3;
    private TextView txt1;
    private TextView txt2;
    private static String Email;
    private  static String as;
    private static String key;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

       Atraction att = new Atraction();
       att.setTime("asdasd");
        Email = getIntent().getStringExtra("Email");
       /* if(Email!=null) {
            Toast.makeText(MainActivity.this, Email, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(MainActivity.this, "FAIL", Toast.LENGTH_SHORT).show();
        }
       /*
       key=LoginActivity.getKey();
        Toast.makeText(MainActivity.this,key,Toast.LENGTH_SHORT).show();
*/
       //KEY
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        myRef = mFirebaseDatabase.getReference("UserAccount");


        myRef.orderByChild("Email").equalTo(Email).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child: dataSnapshot.getChildren()) {
                    Log.d("User key", child.getKey());
                    key = child.getKey();
                    Log.d("User ref", child.getRef().toString());
                    Log.d("User val", child.getValue().toString());

                }

               // Toast.makeText(MainActivity.this,key,Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value

            }
        });

        //

       //old
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);

        //toolbar.setBackgroundColor(0x009688);
        setSupportActionBar(toolbar);
        //
        getImages();
        getImages2();
        getImages3();
        txt1 = (TextView)findViewById(R.id.plus1);
        txt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFood = new Intent(MainActivity.this,Travel_Attract.class);
                startActivity(goFood);
            }
        });
        txt3 = (TextView)findViewById(R.id.plus3);
        txt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFood = new Intent(MainActivity.this,Travel_Food.class);
                startActivity(goFood);
            }
        });
        txt2 = (TextView)findViewById(R.id.plus2);
        txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goFood = new Intent(MainActivity.this,ActivityPlan.class);
                startActivity(goFood);
            }
        });

       // getImages4();
        //

        setNavi();

        //

        if(!isConnected(MainActivity.this)) buildDialog(MainActivity.this).show();
        else {
            //Toast.makeText(MainActivity.this,"Welcome", Toast.LENGTH_SHORT).show();


        }
        //firebase test

/*
            mFirebaseDatabase = FirebaseDatabase.getInstance();
            mRef=mFirebaseDatabase.getReference("Home_atraction");
           mRecyclerView=findViewById(R.id.recyclerView);
            mRecyclerView.setHasFixedSize(true);
        FirebaseRecyclerAdapter<Atraction,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Atraction,ViewHolder>(
                        Atraction.class,R.layout.layout_listitem,ViewHolder.class,mRef
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Atraction model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getImage());
                    }
                };


        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(layoutManager);


        firebaseRecyclerAdapter.notifyDataSetChanged();


*/

        //
        //navi


    }
    public void  setNavi(){
        Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(MainActivity.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }

    public boolean isConnected(Context context) {

        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netinfo = cm.getActiveNetworkInfo();

        if (netinfo != null && netinfo.isConnectedOrConnecting()) {
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            if((mobile != null && mobile.isConnectedOrConnecting()) || (wifi != null && wifi.isConnectedOrConnecting())) return true;
        else return false;
        } else
        return false;
    }

    public AlertDialog.Builder buildDialog(Context c) {

        AlertDialog.Builder builder = new AlertDialog.Builder(c);
        builder.setTitle("No Internet Connection");
        builder.setMessage("You need to have Mobile Data or wifi to access this. Press ok to Exit");

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
            }
        });

        return builder;
    }


/*
    public void onResume(){
        super.onResume();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("Home_atraction");
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        FirebaseRecyclerAdapter<Atraction,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Atraction,ViewHolder>(
                        Atraction.class,R.layout.layout_listitem,ViewHolder.class,mRef
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Atraction model, int position) {
                        viewHolder.setDetails(getApplicationContext(),model.getTitle(),model.getImage());
                    }
                };


        mRecyclerView.setAdapter(firebaseRecyclerAdapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        mRecyclerView.setLayoutManager(layoutManager);
        firebaseRecyclerAdapter.notifyDataSetChanged();
    }

*/

    private void getImages(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A1%E0%B8%B1%E0%B8%AA%E0%B8%A2%E0%B8%B4%E0%B8%94%E0%B8%81%E0%B8%A3%E0%B8%B7%E0%B8%AD%E0%B9%80%E0%B8%8B%E0%B8%B0-1.jpg?alt=media&token=2c150a41-fa8a-45dd-bce0-5de0ca8aa313");
        mNames.add("มัสยิดกรือเซะ");
        mDes.add("      เป็นมัสยิดโบราณอายุกว่า 300 ปี มีความเกี่ยวข้องกับประวัติศาสตร์สำคัญของจังหวัดปัตตานี มีลักษณะโดดเด่นในสถาปัตยกรรมเชิงช่างผสมผสานศิลปะอาหรับสร้างขึ้นในสมัย สุลต่านมุฎอลฟัร ซาห์ ซึ่งเป็นพระราชโอรสคนโตของพญาอินทิรา (เจ้านครที่รับอิสลามองค์แรก) และเป็นมัสยิดแห่งแรกในเอเชียอาคเนย์ที่ได้ก่อสร้างด้วย");
        mLoca.add("     มัสยิดกรือเซะ ตั้งอยู่ที่ ตำบลตันหยงลุโละ อำเภอเมืองปัตตานี ห่างจากศาลากลางจังหวัดปัตตานีประมาณ 8.5 กิโลเมตรไปตามเส้นทางหลักหมายเลข 42");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A1%E0%B8%B1%E0%B8%AA%E0%B8%A2%E0%B8%B4%E0%B8%94%E0%B8%81%E0%B8%A5%E0%B8%B2%E0%B8%87%E0%B8%88%E0%B8%B1%E0%B8%87%E0%B8%AB%E0%B8%A7%E0%B8%B1%E0%B8%94%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5-3.jpg?alt=media&token=01f849be-6061-42f7-aaef-ad203bf7bdb3");
        mNames.add("มัสยิดกลางจังหวัด");
        mDes.add("      เป็นมัสยิดที่สวยที่สุดของไทย สร้างในปี พ.ศ. 2497 เป็นศูนย์กลางในการประกอบศาสนกิจและเป็นศูนย์รวมจิตใจของชาวไทยมุสลิมในภาคใต้รูปแบบสถาปัตยกรรมมีรูปทรงคล้ายคลึงกับ ทัชมาฮาล ประเทศอินเดีย ตรงกลางเป็นอาคารมียอดโดมขนาดใหญ่มีโดมบริวารสี่ทิศ มีหอคอยอยู่สองข้างสูงเด่นเป็นสง่า บริเวณด้านหน้ามัสยิดมีสระน้ำสี่เหลี่ยมขนาดใหญ่ ภายในมัสยิดมีลักษณะเป็นห้องโถงมีระเบียงสองข้าง ภายในห้องโถงมีบังลังค์ทรงสูงและแคบเป็นที่สำหรับ “คอเต็บ” ยืนอ่านคุฎบะฮ์ในการละหมาดวันศุกร์หอคอยสองข้างนี้เดิมใช้เป็นหอกลางสำหรับตีกลอง เป็นสัญญาเรียกให้มุสลิมมาร่วมปฏิบัติศาสนกิจ ต่อมาใช้เป็นที่ติดตั้งลำโพง เครื่องขยายเสียงแทนเสียงกลอง ปัจจุบันขยายด้านข้างออกไปทั้งสองข้างและสร้างหอบัง(อะซาน) พร้อมขยายสระน้ำและทีอาบน้ำละหมาดไห้ดูสง่างามยิ่งขึ้น ภายในมัสยิดประดับด้วยหินอ่อนอย่างสวยงาม");
        mLoca.add("     ตั้งอยู่ที่ ถนนยะรัง เส้นทางยะรัง-ปัตตานี ในเขตเทศบาลเมืองปัตตานี");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A7%E0%B8%B1%E0%B8%87%E0%B8%A2%E0%B8%B0%E0%B8%AB%E0%B8%A3%E0%B8%B4%E0%B9%88%E0%B8%87.jpg?alt=media&token=54aed153-b5d7-4573-a233-f956fb9ac2de");
        mNames.add("วังยะหริ่ง");
        mDes.add("      วังยะหริ่งเป็นอาคาร 2 ชั้น ครึ่งปูนครึ่งไม้ แบบเรือนไทยมุสลิมผสมกับแบบบ้านแถบยุโรป ตัววังเป็นรูปตัวยูชั้นบนภายในอาคารจัดเป็นห้องโถงขนาดใหญ่ มีบันไดโค้งทอดขึ้นไปสู่ระเบียงทั้งสองด้าน จากระเบียงมีประตูเปิดเข้าสู่ห้องโถงใหญ่ ลักษณะคล้ายกับท้องพระโรง ด้านข้างของตัวอาคารทั้ง 2 ด้าน เป็นห้องสำหรับพักผ่อนของเจ้าเมือง และบุตรธิดาข้างละ 4 ห้อง ส่วนชั้นล่างนั้นเป็นลานโล่งแบบใต้ถุนบ้าน ลักษณะเด่น คือ บันไดบ้านโค้งแบบยุโรป มีช่องแสงประดับด้วยกระจกสีเขียว แดง และน้ำเงิน ช่องระบายอากาศ และหน้าจั่ว ทำด้วยไม้ ฉลุเป็นลวดลาย พรรณพฤกษา ตามแบบศิลปะชวา และตะวันตก ทำให้ตัววังสง่างาม นอกจากความงามสง่าของอาคารทรงโปร่ง ที่แซมลวดลายฉลุประดับประดาอย่างอ่อนหวาน ผนวกกับประโยชน์ใช้สอยที่รายรอบด้วยห้องหับนานา เป็นมนต์เสน่ห์ดึงดูดสายตาของนักท่องเที่ยวแล้ว..ตำนานของวังแห่งนี้ก็ท้าทายให้มาค้นหาเรื่องราวได้ไม่แพ้กัน ... เรือนไม้กึ่งปูน สร้างขึ้นแบบสไตล์ยุโรป ผสมผสานศิลปกรรมพื้นเมือง และชวา ตั้งตระหง่านอยู่ใจกลางเมืองยะหริ่งในปัจจุบัน นับเป็นความภาคภูมิใจของชาวยะหริ่งยิ่งนัก เพราะสถาปัตยกรรมทรงคลาสสิคหลังนี้ ได้ทำหน้าที่ต้อนรับนักท่องเที่ยว ผู้มาเยือนจากต่างแดนอยู่เป็นกิจวัตร ปัจจุบันนี้วังยะหริ่งยังมีให้เห็นถึงความสมบูรณ์ แม้จะมีอายุการสร้างวังของเจ้าเมืองยะหริ่งมา");
        mLoca.add("     ทางรถยนต์ เดินทางตามถนนเพชรเกษม ด้วยทางหลวงหมายเลข 41 ผ่านอำเภอทุ่งสง จังหวัดพัทลุง อำเภอหาดใหญ่ อำเภอเมืองยะลา ต่อด้วยถนนสายปัตตานี-นราธิวาส ถึงหมู่ที่ 2 ตำบลยามู อำเภอยะหริ่ง จังหวัดปัตตานี ทั้งหมดตั้งอยู่ในพื้นที่วังประมาณ 16ไร่ ห่างจากตัวจังหวัดประมาร 13 กม.");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B8%A7%E0%B8%B1%E0%B8%87%E0%B8%AB%E0%B8%99%E0%B8%AD%E0%B8%87%E0%B8%88%E0%B8%B4%E0%B8%81-4.jpg?alt=media&token=18e96573-1b37-4090-892c-3b86678a8358");
        mNames.add("วังหนองจิก");
        mDes.add("      วังหนองจิก เป็นบ้านพักของเจ้าเมืองหนองจิกในสมัยเจ็ดหัวเมืองซึ่งมีความเป็นมาทางประวัติศาสตร์ มีเอกลักษณ์ทางวัฒนธรรมซึ่งคงเหลือหลักฐานทางประวัติศาสตร์ สันนิษฐานว่าสร้างมาก่อนปีพ.ศ. 2437 สมัยเจ้าเมืองหนองจิก (ทัด ณ สงขลา) จนถึงเจ้าเมืองคนสุดท้าย คือ พระยาเพชราภิบาลนฤเบศร์วาปีเขตมุจลินทร์นฤบดินทร์ สวามิภักดิ์ (พวง ณ สงขลา) เป็นผู้อยู่อาศัยในวังนี้ วังหนองจิกล้อมรอบด้วยกำแพงวังแถบซุ้มประตูแบบสถาปัตยกรรมจีน ภายในบริเวณวังมีบ่อน้ำแผ่นอิฐที่เหลืออยู่เป็นแหล่งศึกษาประวัติศาสตร์และท่องเที่ยวเชิงวัฒนธรรม");
        mLoca.add("     การเดินทาง วังหนองจิก ตั้งอยู่ หมู่ที่ 1 ตำบลตุยง อำเภอหนองจิก จังหวัดปัตตานี อยู่ห่างจากศาลากลางจังหวัดปัตตานีประมาณ 9 กิโลเมตร ไปตามเส้นทางหลวงหมายเลข 42 เลี้ยวขวาไปประมาณ 100 เมตร\n");

        mImageUrls.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Attract%2F%E0%B9%80%E0%B8%A1%E0%B8%B7%E0%B8%AD%E0%B8%87%E0%B9%82%E0%B8%9A%E0%B8%A3%E0%B8%B2%E0%B8%93%E0%B8%A2%E0%B8%B0%E0%B8%A3%E0%B8%B1%E0%B8%87-4.jpg?alt=media&token=27068f3e-bec6-4b2f-8815-88807238515d");
        mNames.add(" เมืองโบราณยะรัง");
        mDes.add("      เปิดประตูกาลเวลาย้อนกลับไปสู่ชุมชนสมัยแรกเริ่มประวัติศาสตร์ที่ใหญ่ที่สุดแห่งหนึ่งในภาคใต้ของประเทศไทย และเชื่อว่าที่นี่คือที่ตั้งอาณาจักรโบราณ “ลังกาสุกะ” หรือ “ลังยาเสียว” ตามที่มีหลักฐานปรากฏในเอกสารของจีน ชวา มลายู และอาหรับ ทั้งนี้ ลักษณะของเมืองโบราณยะรัง สันนิษฐานว่า มีผังเมืองเป็นรูปวงรีขนาดใหญ่ในพื้นที่ประมาณ 9 ตารางกิโลเมตร และเป็นเมืองที่มีการสร้างทับซ้อนกันถึง 3 เมือง โดยขยายตัวเชื่อมต่อกันซึ่งประกอบไปด้วย\n" +
                "• เมืองโบราณบ้านวัด  มีศูนย์กลางเป็นลานจัตุรัสกลางเมือง ล้อมรอบด้วยคูน้ำ และมีซากเนินดินโบราณสถาน กระจายอยู่โดยรอบกว่า 25 แห่ง โดยเฉพาะอย่างยิ่งทางทิศตะวันตกและทางทิศเหนือ ในบริเวณพื้นที่บ้านจาเละ\n" +
                "• เมืองโบราณบ้านจาเละ มีศูนย์กลางอยู่ที่สระน้ำ ที่โอบล้อมด้วยคูเมืองรูปสี่เหลี่ยม ถัดจากกลุ่มโบราณสถานบ้านวัดขึ้นไปทางทิศเหนือประมาณ 1 กิโลเมตร\n" +
                "• เมืองโบราณบ้านปราแว เป็นเมืองคูน้ำคันดินขนาดเล็กที่มีผังเมืองเป็นรูปสี่เหลี่ยมด้านไม่เท่ามีป้อมดินทั้ง 4 มุมเมือง และมีคลองส่งน้ำต่อเชื่อมกับคูเมืองโบราณบ้านจาเละสี่มุมเมืองด้านทิศเหนือทั้ง 2 ด้าน นอกจากร่องรอยของคูน้ำ คันดินคูเมืองโบราณทั้ง 3 แห่งแล้ว ภายในกลุ่มเมืองโบราณนี้ ยังพบซากโบราณสถานเนินดินกระจัดกระจายอยู่ทั่วไปไม่น้อยกว่า 30 แห่ง");
        mLoca.add("     จากตัวเมืองปัตตานี ไปทางทิศใต้ตามถนนสายปัตตานี - ยะลา ประมาณ กิโลเมตร ถึงอำเภอยะรัง เลี้ยวซ้ายสู่เมืองโบราณ ระยะทาง ๑.๕ กิโลเมตร");

        initRecyclerView();

    }
    private void initRecyclerView(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames, mImageUrls,mDes,mLoca);
        recyclerView.setAdapter(adapter);
    }
    private void getImages2(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2F%E0%B8%AD%E0%B8%B8%E0%B8%97%E0%B8%A2%E0%B8%B2%E0%B8%99%E0%B9%81%E0%B8%AB%E0%B9%88%E0%B8%87%E0%B8%8A%E0%B8%B2%E0%B8%95%E0%B8%B4%E0%B8%99%E0%B9%89%E0%B8%B3%E0%B8%95%E0%B8%81%E0%B8%97%E0%B8%A3%E0%B8%B2%E0%B8%A2%E0%B8%82%E0%B8%B2%E0%B8%A7-5.jpg?alt=media&token=24669e98-ee6e-4ba1-bcba-16d678160db6");
       // mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2FBaan-suan-rim-nam1.jpg?alt=media&token=6f45d525-1e6f-470d-ac5b-512ccdea70e4");
        mNames2.add("อุทยานแห่งชาติน้ำตกทรายขาว");
        mDes2.add("     เป็นน้ำตกที่ตกมาจากหน้าผาสูงประมาณ 30 เมตร สองข้างลำธารมีต้นไม้ขึ้นปกคลุมตลอด ให้ความร่มรื่นและสวยงามเป็นอย่างยิ่ง เป็นสถานที่พักผ่อนหย่อนใจของชุมชนและนักท่องเที่ยว โดยเฉพาะช่วงสุดสัปดาห์ ที่นี่จะเต็มไปด้วยผู้คนจากพื้นที่ต่างๆ นั่งล้อมวงรับประทานอาหารและลงเล่นน้ำร่วมกันอย่างมีความสุข");
        mLoca2.add("        เดินทางมาโดยใช้เส้นทางหลวงแผ่นดิน หมายเลข 409 (ปัตตานี-ยะลา) ถึง สามแยกนาประดู่ อำเภอโคกโพธิ์ จังหวัดปัตตานี (ระยะทางประมาณ 28 กิโลเมตร) จากนั้น เดินทางต่อโดยใช้เส้นทาง (สายนาประดู่-ทรายขาว)เข้าสู่อุทยานแห่งชาติน้ำตกทรายขาว (ระยะทาง 7 กิโลเมตร)");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2F%E0%B8%8A%E0%B8%B8%E0%B8%A1%E0%B8%8A%E0%B8%99%E0%B8%9A%E0%B8%B2%E0%B8%87%E0%B8%9B%E0%B8%B9-1.JPG?alt=media&token=63d96196-d993-43d9-8723-153692ccbf2f");
        mNames2.add("ชุมชนบางปู");
        mDes2.add("     “บางปู” ตำบลเล็กๆ ริมอ่าวปัตตานี ในพื้นที่อำเภอยะหริ่ง จังหวัดปัตตานี รายล้อมไปด้วยธรรมชาติที่สวยงาม และความอุดมสมบูรณ์ของป่าชายเลน ซึ่งถือเป็นแหล่งทำมาหากินสำคัญของชาวประมงพื้นบ้านในพื้นที่แห่งนี้ นอกจากนี้ยังมีนกน้ำอีกนับหมื่นแสนตัว ทางชุมชนจึงได้จัดให้มีกิจกรรมท่องเที่ยวในพื้นที่ โดยจัดตั้งเป็น ชุมชนท่องเที่ยวบางปู บ้านบาลาดูวอ ต.บางปู อ.ยะหริ่ง จ.ปัตตานี โดยมีกิจกรรมให้เลือกมากมาย ทั้งล่องเรือชมความสมบูรณ์ของป่าชายเลน ชมอุโมงค์ป่าโกงกาง เพลิดเพลินกับวิถีชาวเล ดูฝูงนกน้อยบินกลับเข้ารัง เฝ้ารอแสงสุดท้ายยามพระอาทิตย์อัสดง…และไฮไลต์สำคัญอีกอย่างหนึ่งก็คือ การล่องเรือชมหิ่งห้อยยามค่ำคืน ซึ่งจะเปล่งแสงเล่นเวฟกันให้ได้เห็นกันอย่างสวยงาม\n" +
                "ความงดงามทางสายตาที่ยิ่งดูยิ่งมีเสน่ห์ บวกกับความมีน้ำใจของคนในชุมชนที่มีวิถีชีวิตที่เรียบง่ายด้วยแล้ว ทำให้ “บางปู” ยังคงเป็นสถานที่ที่ยังคงมีเสน่ห์อยู่เสมอ");
        mLoca2.add("        เดินทางจากตัวเมืองปัตตานีใช้เส้นทางสาย 42 (ปัตตานี-นราธิวาส)ระยะทาง 42 กิโลเมตร เลี้ยวซ้ายเข้าตำบลบางปูจะถึงชุมชนท่องเที่ยวบางปู");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2F%E0%B8%9E%E0%B8%A5%E0%B8%B1%E0%B8%9A%E0%B8%9E%E0%B8%A5%E0%B8%B2%E0%B8%97%E0%B8%B5%E0%B9%88%E0%B8%9B%E0%B8%A3%E0%B8%B0%E0%B8%97%E0%B8%B1%E0%B8%9A%E0%B8%A3%E0%B8%B1%E0%B8%8A%E0%B8%81%E0%B8%B2%E0%B8%A5%E0%B8%97%E0%B8%B5%E0%B9%88%207-3.jpg?alt=media&token=02150d9b-9f78-4815-9034-58f129e88c84");
        mNames2.add("พลับพลาที่ประทับ");
        mDes2.add("     พลับพลาที่ประทับของรัชกาลที่ 7 คณะดาราศาสตร์ชาวอังกฤษและเยอรมัน คำนวณว่า พื้นที่จังหวัดปัตตานีสามารถมองเห็นสุริยุปราคาได้ชัดเจนกว่าที่จังหวัดอื่นๆ จึงกราบบังคมทูลเชิญรัชกาลที่ 7 เสด็จพระราชดำเนินทอดพระเนตร นักดาราศาสตร์ชาวอังกฤษเตรียมการติดตั้งกล้องและอุปกรณ์ บริเวณสนามหญ้าใกล้ศาลารัฐบาลมณฑลปัตตานี นักดาราศาสตร์ชาวเยอรมันเตรียมการติดตั้งกล้องและอุปกรณ์บนภูเขาหลังที่ว่าการอำเภอโคกโพธิ์ โดยมณฑลปัตตานีได้สร้างพลับพลาไว้เพื่อเป็นที่ปรับทับของรัชกาลที่ 7 ทั้งที่อำเภอโคกโพธิ์และอำเภอเมืองปัตตานี และพระองค์ได้เสด็จทอดพระเนตรสุริยุปราคาทั้งสองแห่ง เมื่อวันที่ 9 พฤศจิกายน พ.ศ. 2472 แต่อำเภอโคกโพธิ์วันนั้นท้องฟ้ามืดครึ้ม ไม่สามารถมองเห็นสุริยุปราคาได้ พระองค์จึงได้เสด็จมาทอดพระเนตรที่เมืองปัตตานี\n");
        mLoca2.add("        อยู่ห่างจากตัวเมืองปัตตานีประมาณ 26 กิโลเมตร ตามทางหลวงหมายเลข 42 ตั้งอยู่บริเวณที่ว่าการอำเภอโคกโพธิ์");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2F%E0%B8%A7%E0%B8%B1%E0%B8%87%E0%B8%9E%E0%B8%B4%E0%B8%9E%E0%B8%B4%E0%B8%98%E0%B8%A0%E0%B8%B1%E0%B8%81%E0%B8%94%E0%B8%B5-1.jpg?alt=media&token=c4bbd810-69e7-43e4-971a-31557c1b5456");
        mNames2.add("วังพิพิธภักดี");
        mDes2.add("ตั้งอยู่ตรงข้ามกับวังสายบุรี สืบเนื่องจากพระพิพิธภักดี ได้มาหลงรักหลานสาวพระยาสุริยสุนทรบวรภักดีบุตรชายของเจ้าเมืองยะหริ่ง ต่อมาเมื่อพิพิธภักดีได้แต่งงานกับหลานสาวพระยาสุริยสุนทรบวรภักดี จึงได้สร้างวังพิพิธภักดีเป็นเรือนหออยู่ใกล้ๆกับวังสายบุรีนั่นเอง วังพิพิธภักดีเป็นอาคารไม้ 2 ชั้น ช่างท้องถิ่นเป็นผู้สร้างโดยนำศิลปะแบบตะวันตกและศิลปะของท้องถิ่นมาผสมผสานกันคือ มีหน้ามุขแบบตะวันตก ลูกกรงบันไดเป็นหลายปูนปั้นรูปดอกไม ");
        mLoca2.add("        ตั้งอยู่ใกล้กับที่ว่าการอำเภอสายบุรี ถนนลูกเสือ ตำบลตะลุบัน อำเภอสายบุรี จังหวัดปัตตานี");

        mImageUrls2.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Theme%2F%E0%B8%AB%E0%B8%A1%E0%B8%B9%E0%B9%88%E0%B8%9A%E0%B9%89%E0%B8%B2%E0%B8%99%E0%B8%97%E0%B8%B3%E0%B9%80%E0%B8%A3%E0%B8%B7%E0%B8%AD%E0%B8%81%E0%B8%AD%E0%B9%81%E0%B8%A5%E0%B8%B0-3.jpg?alt=media&token=2c01ee85-e30f-4804-8a72-57ca8361867b");
        mNames2.add("หมู่บ้านทำเรือกอและ");
        mDes2.add("     บ้านปะเสยะวอ อำเภอปะนาเระ เป็นหมู่บ้านที่มีชื่อเสียงในการต่อเรือประมงของชาวปัตตานี และนราธิวาสที่มีลักษณะเป็นเรือหัวแหลมท้ายแหลม ระบายสีสันงดงาม เรือกอและมีทั้งขนาดใหญ่ที่เป็นเรือประมงจริงๆ และขนาดเล็กที่จำลองขึ้นเพื่อเป็นของที่ระลึก ฝีมือการต่อเรือกอและ ของที่นี่ได้รับการยอมรับว่าประณีตงดงามด้วยลวดลายที่ผสมกลมกลืนระหว่างศิลปะไทยและมุสลิม ในระยะแรกเลียนแบบเทคนิคการตกแต่งเรือพระราชพิธี คือ การแกะสลัก ซึ่งต้องใช้ฝีมือและความประณีตเป็นอย่างสูง แต่ในระยะหลังใช้การวาดลวดลายจิตรกรรมแล้วระบายสี ทำให้สะดุดตาและสะดวกกว่า\n" +
                "เรือกอและ เป็นเรือประมงที่ใช้ในแถบภาคใต้ตอนล่างของประเทศไทย ต่อด้วยไม้กระดาน โดยทำส่วนหัวและส่วนท้ายสูงขึ้นจากลำเรือ หรืออีกแบบจะเป็นแบบหัวสั้นและท้ายตัด นิยมทาสีพื้นตลอดลำเรือ แล้วเขียนลวดลายด้วยสีฉูดฉาด ซึ่งลวดลายอันวิจิตรนี้เองที่เป็นเอกลักษณ์ของเรือกอและ ที่ผสมผสานระหว่างศิลปะไทย อิสลาม จีน และศิลปะอื่น ๆ เนื่องจากอิทธิพลที่ได้รับมาจากสภาพแวดล้อมอันได้แก่ภูมิประเทศ ภูมิอากาศ สังคมความเป็นอยู่ ซึ่งสังคมความเป็นอยู่ในจังหวัดปัตตานีนั้น ประกอบไปด้วยชนชาติ 3 ชนชาติที่อาศัยอยู่ร่วมกันนั่นคือ ชาวไทยพุทธ ชาวไทยมุสลิมและชาวจีน จิตรกรรมที่ศิลปินไทยมุสลิมวาดตกแต่งเรือกอและมีข้อจำกัดในด้านหลักความเชื่อทางศาสนาอิสลาม จึงไม่มีภาพคนร้องรำทำเพลง หรือลักษณะที่ยั่วยุกามารมณ์ ภาพส่วนใหญ่เป็นภาพสัตว์น้ำ สัตว์ในจินตนาการจากประเพณี ศาสนา วรรณคดี ศิลปะการแสดงต่าง ๆ สัตว์หิมพานต์และภาพทิวทัศน์\n");
        mLoca2.add("        หมายเลข 42 ถึง อำเภอสายบุรี ระยะทาง 50 กิโลเมตร แล้วต่อด้วยทางหลวงหมายเลข 4157 (สายบุรี-ปะนาเระ) อีกราว 2 กิโลเมตร มีทางแยกเข้าหมู่บ้านปะเสยะวอ");


        initRecyclerView2();

    }

    private void initRecyclerView2(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView2);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, mNames2, mImageUrls2,mDes2,mLoca2);
        recyclerView.setAdapter(adapter);
    }
    private void getImages3(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%84%E0%B8%A3%E0%B8%B1%E0%B8%A7%E0%B8%9A%E0%B8%B1%E0%B8%87%E0%B9%82%E0%B8%8B%E0%B9%8A%E0%B8%B0.jfif?alt=media&token=ab686e47-e4f3-419e-b36c-4cc839788c28");
        mNames3.add("ครัวบังโซ๊ะ");
        mDes3.add("     รายการอาหารแกงส้ม ปลาอินทรีผัดพริกไทยดำ\n" +
                "เปิดบริการ ช่วงเย็น - ค่ำ");
        mtel3.add("073-311022");
        mlocate3.add("ถ.นาเกลือ ซ. 2/2 อ.เมืองปัตตานี จ.ปัตตานี");


        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%95%E0%B9%89%E0%B8%99%E0%B9%81%E0%B8%95%E0%B8%87%E0%B8%81%E0%B8%A7%E0%B8%B2.jpg?alt=media&token=0edb957a-b3e3-4428-a1cf-206d1b3854df");
        mNames3.add("ต้นแตงกวา");
        mDes3.add("     รายการอาหาร เค้กและขนมปังต่างๆ\n" +
                "เปิดบริการ 12:00 - 17:30 น.");
        mtel3.add("073-348749");
        mlocate3.add("15 ซ.รามา ถ.ปัตตานีภิรมย์ ต.อาเนาะรู อ.เมืองปัตตานี จ.ปัตตานี ");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%A0%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%84%E0%B8%B2%E0%B8%A3%E0%B8%A5%E0%B8%AD%E0%B8%99%E0%B8%94%E0%B8%AD%E0%B8%99.JPG?alt=media&token=69154a99-74a6-41e0-aecc-13466de60f18");
        mNames3.add("ภัตตาคารลอนดอน");
        mDes3.add("     รายการอาหาร แห่กึ้นสด , ปลาจะละเม็ดนึ่งเกี่ยมบ๊วย\n" +
                "เปิดบริการ 10:00 - 21:00 น. ");
        mtel3.add("086-4887682");
        mlocate3.add("89/15 ถ.ยะรัง\n" +
                "ต.จะบังติกอ อ.เมืองปัตตานี\n" +
                "จ.ปัตตานี ");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%A1%E0%B8%B4%E0%B8%87%E0%B8%99%E0%B8%B1%E0%B8%A1%E0%B9%80%E0%B8%9A%E0%B8%AD%E0%B8%A3%E0%B9%8C%E0%B8%A7%E0%B8%B1%E0%B8%99.jpg?alt=media&token=8b620db6-300d-4e6f-9938-885f414fa527");
        mNames3.add("มิงนัมเบอร์วัน");
        mDes3.add("     บะหมี่ ก๋วยเตี๋ยวยาจีน\n" +
                "เปิดบริการ 09.00-16.30 น.");
        mlocate3.add("จะบังติกอ อ.เมืองปัตตานี จ.ปัตตานี");
        mtel3.add("086-6869545");


        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%99%E0%B8%82%E0%B9%89%E0%B8%B2%E0%B8%A7%E0%B8%A1%E0%B8%B1%E0%B8%99%E0%B9%84%E0%B8%81%E0%B9%88%E0%B9%82%E0%B8%81%E0%B8%88%E0%B8%B4%E0%B8%A7.jpg?alt=media&token=15bfab7c-39b1-49ec-859d-bf5f9713a1cb");
        mNames3.add(" ร้านข้าวมันไก่โกจิว");
        mDes3.add("     รายการอาหารข้าวหมูอบ ข้าวมันไก่บ้าน\n" +
                "เปิดบริการ 08:00 - 16:00 น.");
        mtel3.add("073-323161");
        mlocate3.add("4/124 ถ.ปัตตานีภิรมณ์ ต.รูสะมิแล อ.เมืองปัตตานี");

        mImageUrls3.add("https://firebasestorage.googleapis.com/v0/b/projectfi-635a2.appspot.com/o/Restaurants%2F%E0%B8%A3%E0%B9%89%E0%B8%B2%E0%B8%99%E0%B8%AA%E0%B8%B5%E0%B9%88%E0%B8%81%E0%B8%B1%E0%B9%8A%E0%B8%81.jpg?alt=media&token=1b9eba84-0195-4d71-973d-4893c982bbd9");
        mNames3.add("ร้านสี่กั๊ก");
        mtel3.add("073-431148");
        mlocate3.add("8 ม.หมู่ 3 ถ.เพชรเกษม ต.มะกรูด อ.โคกโพ จ.ปัตตานี");
        mDes3.add("     รายการอาหาร ผัดเผ็ดปลากะพงทอดกรอบ ปีกไก่ยัดใส้\n" +
                "เปิดบริการ 10:00 - 22:00 น.");




        initRecyclerView3();

    }
    private void initRecyclerView3(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView3);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterRestuants adapter = new RecyclerViewAdapterRestuants(this, mNames3, mImageUrls3,mDes3,mtel3,mlocate3);
        recyclerView.setAdapter(adapter);
    }
    /*
    private void getImages4(){
        Log.d(TAG, "initImageBitmaps: preparing bitmaps.");

        mImageUrls4.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames4.add("Havasu Falls");

        mImageUrls4.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames4.add("Trondheim");

        mImageUrls4.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames4.add("Portugal");

        mImageUrls4.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames4.add("Rocky Mountain National Park");


        mImageUrls4.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames4.add("Mahahual");

        mImageUrls4.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames4.add("Frozen Lake");


        mImageUrls4.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames4.add("White Sands Desert");

        mImageUrls4.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames4.add("Austrailia");

        mImageUrls4.add("https://i.imgur.com/ZcLLrkY.jpg");
        mNames4.add("Washington");

        initRecyclerView4();

    }
    private void initRecyclerView4(){
        Log.d(TAG, "initRecyclerView: init recyclerview");

        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView recyclerView = findViewById(R.id.recyclerView4);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerViewAdapterETC adapter = new RecyclerViewAdapterETC(this, mNames4, mImageUrls4);
        recyclerView.setAdapter(adapter);
    }
    */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            Intent goSet = new Intent(MainActivity.this,Setting.class);
            startActivity(goSet);
            return true;
        }


        return super.onOptionsItemSelected(item);
    }

    public static String getKey() {
        return key;
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
            ActivityCompat.requestPermissions(MainActivity.this,
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
                    Toast.makeText(MainActivity.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(MainActivity.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this,
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
        Toast.makeText(MainActivity.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}

