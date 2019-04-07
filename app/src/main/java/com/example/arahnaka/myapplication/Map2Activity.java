package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arahnaka.myapplication.model.PlaceInfo;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.AutocompletePrediction;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.PlaceBuffer;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Map2Activity extends AppCompatActivity implements OnMapReadyCallback,
        GoogleApiClient.OnConnectionFailedListener,LocationListener,PopupMenu.OnMenuItemClickListener
        ,com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks
        {

    GoogleApiClient mGoogleApiClient2;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;


    /////////////////////// LIKE //////////////////////////
    private static final LatLng pattani   = new LatLng(6.761963, 101.323222);
    private static final LatLng TOP1BEST   = new LatLng(6.862097, 101.255107);
    private static final LatLng TOP2BEST   = new LatLng(6.873028, 101.302838);
    private static final LatLng TOP3BEST   = new LatLng(6.844450, 101.177111);
    private static final LatLng TOP4BEST   = new LatLng(6.864339, 101.371326);
    private static final LatLng TOP5BEST   = new LatLng(6.765369, 101.300827);
    private static final LatLng TOP6BEST   = new LatLng(6.935942, 101.240532);
    private static final LatLng TOP7BEST   = new LatLng(6.884098, 101.428091);
    private static final LatLng TOP8BEST   = new LatLng(6.871397, 101.258156);
    ////////////////////// TEMPLE /////////////////////////
    private static final LatLng TEMPLE1 = new LatLng(6.873028, 101.302838);
    private static final LatLng TEMPLE2 = new LatLng(6.871397, 101.258156);
    private static final LatLng TEMPLE3 = new LatLng(6.862097, 101.255107);
    private static final LatLng TEMPLE4 = new LatLng(6.668852, 101.168884);
    private static final LatLng TEMPLE5 = new LatLng(6.864339, 101.371326);
    private static final LatLng TEMPLE6 = new LatLng(6.912683, 101.331615);
    private static final LatLng TEMPLE7 = new LatLng(6.873379, 101.301794);
    private static final LatLng TEMPLE8 = new LatLng(6.763663, 101.491513);
    private static final LatLng TEMPLE9 = new LatLng(6.740042, 101.133188);
    private static final LatLng TEMPLE10 = new LatLng(6.847990, 101.180563);
    private static final LatLng TEMPLE11 = new LatLng(6.870446, 101.218201);
    private static final LatLng TEMPLE12 = new LatLng(6.844450, 101.177111);
    private static final LatLng TEMPLE13 = new LatLng(6.847057, 101.180507);
    ////////////////////// BEACH  /////////////////////////
    private static final LatLng BEACH1 = new LatLng(6.884098, 101.428091);
    private static final LatLng BEACH2 = new LatLng(6.709306, 101.643420);
    private static final LatLng BEACH3 = new LatLng(6.931199, 101.321994);
    private static final LatLng BEACH4 = new LatLng(6.869929, 101.493035);
    private static final LatLng BEACH5 = new LatLng(6.822951, 101.419221);
    private static final LatLng BEACH6 = new LatLng(6.902689, 101.239183);
    private static final LatLng BEACH7 = new LatLng(6.863670, 101.524335);
    private static final LatLng BEACH8 = new LatLng(6.935942, 101.240532);
    ///////////////////// FOREST //////////////////////////
    private static final LatLng FOREST1 = new LatLng(6.656444, 101.095715);
    private static final LatLng FOREST2 = new LatLng(6.892058, 101.244367);
    private static final LatLng FOREST3 = new LatLng(6.894051, 101.243907);
    private static final LatLng FOREST4 = new LatLng(6.871032, 101.351639);
    private static final LatLng FOREST5 = new LatLng(6.881361, 101.231626);
    private static final LatLng FOREST6 = new LatLng(6.872393, 101.349886);
    private static final LatLng FOREST7 = new LatLng(6.693966, 101.495706);
    private static final LatLng FOREST8 = new LatLng(6.861561, 101.244550);
    ///////////////////// MUSEUM //////////////////////////
    private static final LatLng MUSEUM1 = new LatLng(6.882056, 101.240100);
    private static final LatLng MUSEUM2 = new LatLng(6.730613, 101.095101);
    private static final LatLng MUSEUM3 = new LatLng(6.880638, 101.238958);
    private static final LatLng MUSEUM4 = new LatLng(66.703061, 101.618727);
    private static final LatLng MUSEUM5 = new LatLng(6.875638, 101.315796);
    private static final LatLng MUSEUM6 = new LatLng(6.765369, 101.300827);
    private static final LatLng MUSEUM7 = new LatLng(6.850909, 101.212471);
    private static final LatLng MUSEUM8 = new LatLng(6.881985, 101.239791);
    /////////////////////  FOOD   /////////////////////////
    private static final LatLng FOOD1 = new LatLng(6.876491, 101.233060);
    private static final LatLng FOOD2 = new LatLng(6.863061, 101.234707);
    private static final LatLng FOOD3 = new LatLng(6.876249, 101.234964);
    private static final LatLng FOOD4 = new LatLng(6.865603, 101.252898);
    private static final LatLng FOOD5 = new LatLng(6.856399, 101.221507);
    private static final LatLng FOOD6 = new LatLng(6.869188, 101.254339);
    private static final LatLng FOOD7 = new LatLng(6.865495, 101.241039);
    private static final LatLng FOOD8 = new LatLng(6.855860, 101.253125);
    private static final LatLng FOOD9 = new LatLng(6.859104, 101.228617);
    private static final LatLng FOOD10 = new LatLng(6.874502, 101.233814);
    private static final LatLng FOOD11 = new LatLng(6.859698, 101.231017);
    private static final LatLng FOOD12 = new LatLng(6.870901, 101.249545);
    private static final LatLng FOOD13 = new LatLng(6.862565, 101.256759);
    /////////////////////  SHOP   /////////////////////////
    private static final LatLng SHOP1 = new LatLng(6.757266, 101.480154);
    private static final LatLng SHOP2 = new LatLng(6.863420, 101.253191);
    private static final LatLng SHOP3 = new LatLng(6.866044, 101.249987);
    private static final LatLng SHOP4 = new LatLng(6.866439, 101.241681);
    private static final LatLng SHOP5 = new LatLng(6.867509, 101.257026);
    private static final LatLng SHOP6 = new LatLng(6.861017, 101.255668);
    private static final LatLng SHOP7 = new LatLng(6.891877, 101.256694);
    private static final LatLng SHOP8 = new LatLng(6.876816, 101.233730);
    private static final LatLng SHOP9 = new LatLng(6.862659, 101.218173);
    /////////////////////  GAS   /////////////////////////
    private static final LatLng GAS1 = new LatLng(6.858047, 101.226717);
    private static final LatLng GAS2 = new LatLng(6.850253, 101.197814);
    private static final LatLng GAS3 = new LatLng(6.804570, 101.133312);
    private static final LatLng GAS4 = new LatLng(6.879591, 101.252543);
    private static final LatLng GAS5 = new LatLng(6.803324, 101.140395);
    ///LIKE//////
    private Marker mpattani,TOP1,TOP2,TOP3,TOP4,TOP5,TOP6,TOP7,TOP8;
    ///TENPLE////
    private Marker TP1, TP2, TP3, TP4, TP5, TP6, TP7, TP8, TP9, TP10, TP11, TP12, TP13;
    ///BEACH/////
    private Marker BE1, BE2, BE3, BE4, BE5, BE6, BE7, BE8;
    ///FOREST////
    private Marker FOR1, FOR2, FOR3, FOR4, FOR5, FOR6, FOR7, FOR8;
    ///MUSEUM////
    private Marker MU1, MU2, MU3, MU4, MU5, MU6, MU7, MU8;
    ///FOOD////
    private Marker FO1, FO2, FO3, FO4, FO5, FO6, FO7, FO8, FO9, FO10, FO11, FO12, FO13;
    ///SHOP////
    private Marker  SH1, SH2, SH3, SH4, SH5, SH6, SH7, SH8, SH9;
    ///SHOP////
    private Marker  GA1, GA2, GA3, GA4, GA5;
    //private Marker


    public void setMarkLocation(){

    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        //Toast.makeText(this, "เเผนที่พร้อมใช้งาน", Toast.LENGTH_LONG).show();
        Log.d(TAG, "onMapReady: map is ready");
        mMap = googleMap;

        mMap.setMapType(GoogleMap.MAP_TYPE_TERRAIN);

        //mStar.performClick();

        if (mLocationPermissionsGranted) {
            //getDeviceLocation();
            mMap.moveCamera(CameraUpdateFactory.newLatLng(pattani));
            mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
            if(ClickOpenMark == true) {
                mMap.clear();
                ShowMarkLike(mMap);
                ClickOpenMark = false;
            }


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                return;
            }
            mMap.setMyLocationEnabled(true);
            mMap.getUiSettings().setMyLocationButtonEnabled(false);

            init();

        }

    }


    private static final String TAG = "Map2Activity";

    private static final String FINE_LOCATION = Manifest.permission.ACCESS_FINE_LOCATION;
    private static final String COURSE_LOCATION = Manifest.permission.ACCESS_COARSE_LOCATION;
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1234;
    private static final float DEFAULT_ZOOM = 15f;
    private static final int PLACE_PICKER_REQUEST = 1;
    private static final LatLngBounds LAT_LNG_BOUNDS = new LatLngBounds(
            new LatLng(-40, -168), new LatLng(71, 136));


    private AutoCompleteTextView mSearchText;
    private ImageView mGps, mPlacePicker, mStar, mDelect;
    private ImageView TempleM1, BeachM1, ForestM1, MuseumM1, FoodM1, ShopM1, GasM1;

    double latitude,longitude;
    public static final int REQUEST_LOCATION_CODE = 99;
    int PROXIMITY_RADIUS = 10000;

    private static final int ACTIVITY_NUM = 3;

    private Boolean mLocationPermissionsGranted = false;
    private GoogleMap mMap;
    private FusedLocationProviderClient mFusedLocationProviderClient;
    private PlaceAutocompleteAdapter mPlaceAutocompleteAdapter;
    private GoogleApiClient mGoogleApiClient;
    private PlaceInfo mPlace;
    private Marker mMarker;

    public boolean ClickOpenMark = true;
    public boolean ClickOpenMarkTP = true;
    public boolean ClickOpenMarkBE = true;
    public boolean ClickOpenMarkFO = true;
    public boolean ClickOpenMarkSH = true;
    public boolean ClickOpenMarkFOR = true;
    public boolean ClickOpenMarkMU = true;
    public boolean ClickOpenMarkGA = true;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map2);
        setNavi();


        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(5000);
        mLocationRequest.setFastestInterval(5000);
        mLocationRequest.setNumUpdates(1);
        mLocationRequest.setMaxWaitTime(10000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mGoogleApiClient2 = new GoogleApiClient.Builder(this)
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

        mSearchText = (AutoCompleteTextView) findViewById(R.id.input_search2);
        mGps = (ImageView) findViewById(R.id.ic_gps2);
        mPlacePicker = (ImageView) findViewById(R.id.place_picker);
        mStar = (ImageView) findViewById(R.id.star);
        mDelect = (ImageView) findViewById(R.id.ic_delect);

        TempleM1 = (ImageView)findViewById(R.id.TempleM);
        BeachM1 = (ImageView)findViewById(R.id.BeachM);
        ForestM1 = (ImageView)findViewById(R.id.ForestM);
        MuseumM1 = (ImageView)findViewById(R.id.MuseumM);
        FoodM1 = (ImageView)findViewById(R.id.FoodM);
        ShopM1 = (ImageView)findViewById(R.id.ShopM);
        GasM1 = (ImageView)findViewById(R.id.GasM);
        getLocationPermission();

    }

    private void init(){
        Log.d(TAG, "init: initializing");

        mGoogleApiClient = new GoogleApiClient
                .Builder(this)
                .addApi(Places.GEO_DATA_API)
                .addApi(Places.PLACE_DETECTION_API)
                .enableAutoManage(this, this)
                .build();

        mSearchText.setOnItemClickListener(mAutocompleteClickListener);

        mPlaceAutocompleteAdapter = new PlaceAutocompleteAdapter(this, mGoogleApiClient,
                LAT_LNG_BOUNDS, null);

        mSearchText.setAdapter(mPlaceAutocompleteAdapter);

        mSearchText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if(actionId == EditorInfo.IME_ACTION_SEARCH
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || event.getAction() == KeyEvent.ACTION_DOWN
                        || event.getAction() == KeyEvent.KEYCODE_ENTER){
                    geoLocate();
                }

                return false;
            }
        });

        mGps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick:  clicked gps icon");
                getDeviceLocation();
            }
        });


        mPlacePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

                try {
                    startActivityForResult(builder.build(Map2Activity.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    Log.e(TAG, "onClick: GooglePlayServicesRepairableException: " + e.getMessage() );
                } catch (GooglePlayServicesNotAvailableException e) {
                    Log.e(TAG, "onClick: GooglePlayServicesNotAvailableException: " + e.getMessage() );
                }
            }
        });


        mStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMark == true) {
                    ShowMarkLike(mMap);
                    ClickOpenMark = false;

                }
                else if (ClickOpenMark == false){
                    ClickOpenMark = true;
                    mpattani.remove();
                    TOP1.remove();
                    TOP2.remove();
                    TOP3.remove();
                    TOP4.remove();
                    TOP5.remove();
                    TOP6.remove();
                    TOP7.remove();
                    TOP8.remove();
                }
            }
        });

        mDelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mMap.clear();
                ClickOpenMark = true;
                ClickOpenMarkTP = true;
                ClickOpenMarkBE = true;
                ClickOpenMarkFO = true;
                ClickOpenMarkSH = true;
                ClickOpenMarkFOR = true;
                ClickOpenMarkMU = true;
                ClickOpenMarkGA = true;
            }
        });

        TempleM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkTP == true) {
                    ShowMarkTemple(mMap);
                    ClickOpenMarkTP = false;

                }
                else if (ClickOpenMarkTP == false){
                    ClickOpenMarkTP = true;
                    TP1.remove();
                    TP2.remove();
                    TP3.remove();
                    TP4.remove();
                    TP5.remove();
                    TP6.remove();
                    TP7.remove();
                    TP8.remove();
                    TP9.remove();
                    TP10.remove();
                    TP11.remove();
                    TP12.remove();
                    TP13.remove();
                }
            }
        });
        BeachM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkBE == true) {
                    ShowMarkBeach(mMap);
                    ClickOpenMarkBE = false;
                }
                else if (ClickOpenMarkBE == false){
                    ClickOpenMarkBE = true;
                    BE1.remove();
                    BE2.remove();
                    BE3.remove();
                    BE4.remove();
                    BE5.remove();
                    BE6.remove();
                    BE7.remove();
                    BE8.remove();
                }
            }
        });
        ForestM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkFOR == true) {
                    ShowMarkForest(mMap);
                    ClickOpenMarkFOR = false;
                }
                else if (ClickOpenMarkFOR == false){
                    ClickOpenMarkFOR = true;
                    FOR1.remove();
                    FOR2.remove();
                    FOR3.remove();
                    FOR4.remove();
                    FOR5.remove();
                    FOR6.remove();
                    FOR7.remove();
                    FOR8.remove();
                }
            }
        });
        MuseumM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkMU == true) {
                    ShowMarkMuseum(mMap);
                    ClickOpenMarkMU = false;
                }
                else if (ClickOpenMarkMU == false){
                    ClickOpenMarkMU = true;
                    MU1.remove();
                    MU2.remove();
                    MU3.remove();
                    MU4.remove();
                    MU5.remove();
                    MU6.remove();
                    MU7.remove();
                    MU8.remove();
                }
            }
        });
        FoodM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkFO == true) {
                    ShowMarkFood(mMap);
                    ClickOpenMarkFO = false;
                }
                else if (ClickOpenMarkFO == false){
                    ClickOpenMarkFO = true;
                    FO1.remove();
                    FO2.remove();
                    FO3.remove();
                    FO4.remove();
                    FO5.remove();
                    FO6.remove();
                    FO7.remove();
                    FO8.remove();
                    FO9.remove();
                    FO10.remove();
                    FO11.remove();
                    FO12.remove();
                    FO13.remove();
                }
            }
        });
        ShopM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkSH == true) {
                    ShowMarkShop(mMap);
                    ClickOpenMarkSH = false;
                }
                else if (ClickOpenMarkSH == false){
                    ClickOpenMarkSH = true;
                    SH1.remove();
                    SH2.remove();
                    SH3.remove();
                    SH4.remove();
                    SH5.remove();
                    SH6.remove();
                    SH7.remove();
                    SH8.remove();
                    SH9.remove();
                }
            }
        });
        GasM1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ClickOpenMarkGA == true) {
                    ShowMarkGas(mMap);
                    ClickOpenMarkGA = false;
                }
                else if (ClickOpenMarkGA == false){
                    ClickOpenMarkGA = true;
                    GA1.remove();
                    GA2.remove();
                    GA3.remove();
                    GA4.remove();
                    GA5.remove();
                }
            }
        });



        hideSoftKeyboard();
    }
    public void onZoom(View view){
        if(view.getId() == R.id.zoomin){
            mMap.animateCamera(CameraUpdateFactory.zoomIn());
        }
        if(view.getId() == R.id.zoomout){
            mMap.animateCamera(CameraUpdateFactory.zoomOut());
        }
    }
    public void ShowPopupMenu(View v){
        /*PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener(this);
        popup.inflate(R.menu.popup_menu);
        popup.show();
        <LinearLayout
                android:layout_width="70dp"
                android:layout_height="85dp"
                android:orientation="vertical"
                android:layout_marginLeft="5dp"
                        >
                        <ImageView
                android:id="@+id/ic_menu"
                android:layout_width="45dp"
                android:layout_height="45dp"
                android:layout_alignParentTop="true"
                android:layout_toEndOf="@+id/delect"
                android:layout_marginStart="10dp"
                android:layout_marginTop="10dp"
                android:background="@drawable/map_iconbgmenu"
                android:onClick="ShowPopupMenu"
                android:scaleType="centerCrop"
                android:src="@drawable/newme5" />

                        <TextView
                android:id="@+id/tx5"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
                android:layout_alignParentStart="true"
                android:layout_alignParentTop="true"
                android:layout_marginStart="20dp"
                android:layout_marginTop="2dp"
                android:scaleType="centerCrop"
                android:text="เมนู"
                android:textSize="15dp"
                android:textStyle="bold"
                android:fontFamily="@font/mitr_extralight"
                        />*/
        PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.popup_menu, popup.getMenu());
        popup.setOnMenuItemClickListener(this);

        Object menuHelper;
        Class[] argTypes;
        try {
            Field fMenuHelper = PopupMenu.class.getDeclaredField("mPopup");
            fMenuHelper.setAccessible(true);
            menuHelper = fMenuHelper.get(popup);
            argTypes = new Class[]{boolean.class};
            menuHelper.getClass().getDeclaredMethod("setForceShowIcon", argTypes).invoke(menuHelper, true);
        } catch (Exception e) {

        }
        popup.show();
    }
    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.TempleM:
                if(ClickOpenMarkTP == true) {
                    ShowMarkTemple(mMap);
                    ClickOpenMarkTP = false;

                }
                else if (ClickOpenMarkTP == false){
                    ClickOpenMarkTP = true;
                    TP1.remove();
                    TP2.remove();
                    TP3.remove();
                    TP4.remove();
                    TP5.remove();
                    TP6.remove();
                    TP7.remove();
                    TP8.remove();
                    TP9.remove();
                    TP10.remove();
                    TP11.remove();
                    TP12.remove();
                    TP13.remove();
                }
                return true;
            case R.id.BeachM:
                if(ClickOpenMarkBE == true) {
                    ShowMarkBeach(mMap);
                    ClickOpenMarkBE = false;

                }
                else if (ClickOpenMarkBE == false){
                    ClickOpenMarkBE = true;
                    BE1.remove();
                    BE2.remove();
                    BE3.remove();
                    BE4.remove();
                    BE5.remove();
                    BE6.remove();
                    BE7.remove();
                    BE8.remove();
                }
                return true;
            case R.id.ForestM:
                if(ClickOpenMarkFOR == true) {
                    ShowMarkForest(mMap);
                    ClickOpenMarkFOR = false;

                }
                else if (ClickOpenMarkFOR == false){
                    ClickOpenMarkFOR = true;
                    FOR1.remove();
                    FOR2.remove();
                    FOR3.remove();
                    FOR4.remove();
                    FOR5.remove();
                    FOR6.remove();
                }
                return true;
            case R.id.MuseumM:
                if(ClickOpenMarkMU == true) {
                    ShowMarkMuseum(mMap);
                    ClickOpenMarkMU = false;

                }
                else if (ClickOpenMarkMU == false){
                    ClickOpenMarkMU = true;
                    MU1.remove();
                    MU2.remove();
                    MU3.remove();
                    MU4.remove();
                    MU5.remove();
                    MU6.remove();
                }
                return true;
            case R.id.FoodM:
                if(ClickOpenMarkFO == true) {
                    ShowMarkFood(mMap);
                    ClickOpenMarkFO = false;

                }
                else if (ClickOpenMarkFO == false){
                    ClickOpenMarkFO = true;
                    FO1.remove();
                    FO2.remove();
                    FO3.remove();
                    FO4.remove();
                    FO5.remove();
                    FO6.remove();
                    FO7.remove();
                    FO8.remove();
                    FO9.remove();
                    FO10.remove();
                    FO11.remove();
                }
                return true;
            case R.id.ShopM:
                if(ClickOpenMarkSH == true) {
                    ShowMarkShop(mMap);
                    ClickOpenMarkSH = false;

                }
                else if (ClickOpenMarkSH == false){
                    ClickOpenMarkSH = true;
                    SH1.remove();
                    SH2.remove();
                    SH3.remove();
                    SH4.remove();
                    SH5.remove();
                    SH6.remove();
                    SH7.remove();
                    SH8.remove();
                    SH9.remove();
                }
                return true;
            case R.id.GasM:
                if(ClickOpenMarkGA == true) {
                    ShowMarkGas(mMap);
                    ClickOpenMarkGA = false;

                }
                else if (ClickOpenMarkGA == false){
                    ClickOpenMarkGA = true;
                    GA1.remove();
                    GA2.remove();
                    GA3.remove();
                    GA4.remove();
                    GA5.remove();
                }
                return true;
            default:
                return false;
        }
    }

    public  void ShowMarkLike(GoogleMap mMap){
        mpattani = mMap.addMarker(new MarkerOptions().position(pattani).title("จังหวัดปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.po1)));;
        mpattani.setTag(0);
        TOP1 = mMap.addMarker(new MarkerOptions().position(TOP1BEST).title("มัสยิดกลางจังหวัดปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP1.setTag(0);
        TOP2 = mMap.addMarker(new MarkerOptions().position(TOP2BEST).title("มัสยิดกรือเซะ").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP2.setTag(0);
        TOP3 = mMap.addMarker(new MarkerOptions().position(TOP3BEST).title("วังหนองจิก").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP3.setTag(0);
        TOP4 = mMap.addMarker(new MarkerOptions().position(TOP4BEST).title("วังยะหริ่ง").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP4.setTag(0);
        TOP5 = mMap.addMarker(new MarkerOptions().position(TOP5BEST).title("เมืองโบราณยะรัง").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP5.setTag(0);
        TOP6 = mMap.addMarker(new MarkerOptions().position(TOP6BEST).title("แหลมตาชี").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP6.setTag(0);
        TOP7 = mMap.addMarker(new MarkerOptions().position(TOP7BEST).title("หาดตะโละกาโปร์").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP7.setTag(0);
        TOP8 = mMap.addMarker(new MarkerOptions().position(TOP8BEST).title("ศาลเจ้าแม่ลิ้มกอเหนี่ยว").icon(BitmapDescriptorFactory.fromResource(R.drawable.star1)));
        TOP8.setTag(0);

    }
    public void ShowMarkTemple(GoogleMap mMap){
        //////////////TEMPLE///////////////////////
        TP1 = mMap.addMarker(new MarkerOptions().position(TEMPLE1).title("มัสยิดกรือเซะ").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP1.setTag(0);
        TP2 = mMap.addMarker(new MarkerOptions().position(TEMPLE2).title("ศาลเจ้าแม่ลิ้มกอเหนี่ยว").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP2.setTag(0);
        TP3 = mMap.addMarker(new MarkerOptions().position(TEMPLE3).title("มัสยิดกลางจังหวัดปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP3.setTag(0);
        TP4 = mMap.addMarker(new MarkerOptions().position(TEMPLE4).title("วัดช้างให้ราษฎร์บูรณาราม").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP4.setTag(0);
        TP5 = mMap.addMarker(new MarkerOptions().position(TEMPLE5).title("วังยะหริ่ง").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP5.setTag(0);
        TP6 = mMap.addMarker(new MarkerOptions().position(TEMPLE6).title("มัสยิดดาโต๊ะ").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP6.setTag(0);
        TP7 = mMap.addMarker(new MarkerOptions().position(TEMPLE7).title("ฮวงซุ้ยเจ้าแม่ลิ้มกอเหนี่ยว").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP7.setTag(0);
        TP8 = mMap.addMarker(new MarkerOptions().position(TEMPLE8).title("วัดควนใน").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP8.setTag(0);
        TP9 = mMap.addMarker(new MarkerOptions().position(TEMPLE9).title("วัดนาเกตุ").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP9.setTag(0);
        TP10 = mMap.addMarker(new MarkerOptions().position(TEMPLE10).title("Phiphitthaphan Phra Rat Phutthi Rang Si ").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP10.setTag(0);
        TP11 = mMap.addMarker(new MarkerOptions().position(TEMPLE11).title("วัดขจรประชาราม").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP11.setTag(0);
        TP12 = mMap.addMarker(new MarkerOptions().position(TEMPLE12).title("วังหนองจิก").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP12.setTag(0);
        TP13 = mMap.addMarker(new MarkerOptions().position(TEMPLE13).title("วัดมุจลินทวาปีวิหาร").icon(BitmapDescriptorFactory.fromResource(R.drawable.tem5)));
        TP13.setTag(0);
    }
    public void ShowMarkBeach(GoogleMap mMap){
        //////////////BEACH////////////////////////
        BE1 = mMap.addMarker(new MarkerOptions().position(BEACH1).title("หาดตะโละกาโปร์").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE1.setTag(0);
        BE2 = mMap.addMarker(new MarkerOptions().position(BEACH2).title("หาดวาสุกรี").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE2.setTag(0);
        BE3 = mMap.addMarker(new MarkerOptions().position(BEACH3).title("หาดตะโล๊ะสะมิแล").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE3.setTag(0);
        BE4 = mMap.addMarker(new MarkerOptions().position(BEACH4).title("หาดปะนาเระ").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE4.setTag(0);
        BE5 = mMap.addMarker(new MarkerOptions().position(BEACH5).title("สวนนํ้าแบลีทับทิม").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE5.setTag(0);
        BE6 = mMap.addMarker(new MarkerOptions().position(BEACH6).title("หาดรูสะมิแล").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE6.setTag(0);
        BE7 = mMap.addMarker(new MarkerOptions().position(BEACH7).title("หาดราชรักษ์").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE7.setTag(0);
        BE8 = mMap.addMarker(new MarkerOptions().position(BEACH8).title("แหลมตาชี").icon(BitmapDescriptorFactory.fromResource(R.drawable.beah7)));
        BE8.setTag(0);
    }
    public void ShowMarkForest(GoogleMap mMap){
        //////////////FOREST///////////////////////
        FOR1 = mMap.addMarker(new MarkerOptions().position(FOREST1).title("น้ำตกทรายขาว").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR1.setTag(0);
        FOR2 = mMap.addMarker(new MarkerOptions().position(FOREST2).title("สวนสมเด็จพระศรีนครินทร์ ปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR2.setTag(0);
        FOR3 = mMap.addMarker(new MarkerOptions().position(FOREST3).title("Pattani Sky Walk").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR3.setTag(0);
        FOR4 = mMap.addMarker(new MarkerOptions().position(FOREST4).title("ชุมชนท่องเที่ยวบางปู ปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR4.setTag(0);
        FOR5 = mMap.addMarker(new MarkerOptions().position(FOREST5).title("หอดูนก มอ.ปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR5.setTag(0);
        FOR6 = mMap.addMarker(new MarkerOptions().position(FOREST6).title("Bangpu Amazing Tour").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR6.setTag(0);
        FOR7 = mMap.addMarker(new MarkerOptions().position(FOREST7).title("วนอุทยานปราสาทนางผมหอม").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR7.setTag(0);
        FOR8 = mMap.addMarker(new MarkerOptions().position(FOREST8).title("สนามเด็กเล่นสวนสาธารณะโรงอ่าง").icon(BitmapDescriptorFactory.fromResource(R.drawable.for4)));
        FOR8.setTag(0);
    }
    public void ShowMarkMuseum(GoogleMap mMap){
        //////////////MUSEUM///////////////////////
        MU1 = mMap.addMarker(new MarkerOptions().position(MUSEUM1).title("สถาบันวัฒนธรรมศึกษากัลยาณิวัฒนา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU1.setTag(0);
        MU2 = mMap.addMarker(new MarkerOptions().position(MUSEUM2).title("พลับพลาที่ประทับของรัชกาลที่ 7").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU2.setTag(0);
        MU3 = mMap.addMarker(new MarkerOptions().position(MUSEUM3).title("พิพิธภัณฑ์พระเทพญาณโมลี").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU3.setTag(0);
        MU4 = mMap.addMarker(new MarkerOptions().position(MUSEUM4).title("วังพิพิธภักดี สายบุรี").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU4.setTag(0);
        MU5 = mMap.addMarker(new MarkerOptions().position(MUSEUM5).title("สุสานพญาอินทิรา").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU5.setTag(0);
        MU6 = mMap.addMarker(new MarkerOptions().position(MUSEUM6).title("เมืองโบราณยะรัง").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU6.setTag(0);
        MU7 = mMap.addMarker(new MarkerOptions().position(MUSEUM7).title("Patani Artspace").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU7.setTag(0);
        MU8 = mMap.addMarker(new MarkerOptions().position(MUSEUM8).title("หอศิลปวัฒนธรรมภาคใต้").icon(BitmapDescriptorFactory.fromResource(R.drawable.mus5)));
        MU8.setTag(0);

    }
    public void ShowMarkFood(GoogleMap mMap){
        //////////////FOOD/////////////////////////
        FO1 = mMap.addMarker(new MarkerOptions().position(FOOD1).title("ไทปัน ซีฟู๊ด ร้านอาหาร").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO1.setTag(0);
        FO2 = mMap.addMarker(new MarkerOptions().position(FOOD2).title("ร้านอาหารช่อชบา").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO2.setTag(0);
        FO3 = mMap.addMarker(new MarkerOptions().position(FOOD3).title("ปันตัย ปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO3.setTag(0);
        FO4 = mMap.addMarker(new MarkerOptions().position(FOOD4).title("ร้านอาหาร นำรส").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO4.setTag(0);
        FO5 = mMap.addMarker(new MarkerOptions().position(FOOD5).title("ร้านซุปหางวัว").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO5.setTag(0);
        FO6 = mMap.addMarker(new MarkerOptions().position(FOOD6).title("ข้าวมันไก่โกจิว").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO6.setTag(0);
        FO7 = mMap.addMarker(new MarkerOptions().position(FOOD7).title("Bagus Chicken").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO7.setTag(0);
        FO8 = mMap.addMarker(new MarkerOptions().position(FOOD8).title("ร้านระฟ้า ติ่มซำฮาลาล").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO8.setTag(0);
        FO9 = mMap.addMarker(new MarkerOptions().position(FOOD9).title("นาครัวฮาลาล บุฟเฟ่ต์เนื้อกระทะ").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO9.setTag(0);
        FO10 = mMap.addMarker(new MarkerOptions().position(FOOD10).title("บ้านเดอนารา").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO10.setTag(0);
        FO11 = mMap.addMarker(new MarkerOptions().position(FOOD11).title("GolfTar Shabu").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO11.setTag(0);
        FO12 = mMap.addMarker(new MarkerOptions().position(FOOD12).title("ร้านอาหาร อิ่มอร่อย").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO12.setTag(0);
        FO13 = mMap.addMarker(new MarkerOptions().position(FOOD13).title("ภัตตาคารลอนดอน").icon(BitmapDescriptorFactory.fromResource(R.drawable.foo2)));
        FO13.setTag(0);
    }
    public void ShowMarkShop(GoogleMap mMap){
        //////////////SHOP/////////////////////////
        SH1 = mMap.addMarker(new MarkerOptions().position(SHOP1).title("ตลาดนัดปาลัส").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH1.setTag(0);
        SH2 = mMap.addMarker(new MarkerOptions().position(SHOP2).title("ตลาดเปิดท้าย ปัตตานี (ตลาดสุวรรณมงคล)").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH2.setTag(0);
        SH3 = mMap.addMarker(new MarkerOptions().position(SHOP3).title("ตลาดมะกรูด").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH3.setTag(0);
        SH4 = mMap.addMarker(new MarkerOptions().position(SHOP4).title("ตลาดหมอวิทย์").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH4.setTag(0);
        SH5 = mMap.addMarker(new MarkerOptions().position(SHOP5).title("ตลาดเทศวิวัฒน์ 1").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH5.setTag(0);
        SH6 = mMap.addMarker(new MarkerOptions().position(SHOP6).title("ตลาดสดเทศบาลเมืองปัตตานี").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH6.setTag(0);
        SH7 = mMap.addMarker(new MarkerOptions().position(SHOP7).title("ท่าเทียบเรือประมงปัตตานี องค์การสะพานปลา").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH7.setTag(0);
        SH8 = mMap.addMarker(new MarkerOptions().position(SHOP8).title("ตลาดนัดเช้ารูสะมิแล").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH8.setTag(0);
        SH9 = mMap.addMarker(new MarkerOptions().position(SHOP9).title("ตลาดโต้รุ่ง").icon(BitmapDescriptorFactory.fromResource(R.drawable.sh4)));
        SH9.setTag(0);
    }
    public void ShowMarkGas(GoogleMap mMap){
        GA1 = mMap.addMarker(new MarkerOptions().position(GAS1).title("ปตท.บจ.เอสทีซี").icon(BitmapDescriptorFactory.fromResource(R.drawable.gas2)));
        GA1.setTag(0);
        GA2 = mMap.addMarker(new MarkerOptions().position(GAS2).title("ปตท.ตุยง").icon(BitmapDescriptorFactory.fromResource(R.drawable.gas2)));
        GA2.setTag(0);
        GA3 = mMap.addMarker(new MarkerOptions().position(GAS3).title("ปตท.ภคภูมิ").icon(BitmapDescriptorFactory.fromResource(R.drawable.gas2)));
        GA3.setTag(0);
        GA4 = mMap.addMarker(new MarkerOptions().position(GAS4).title("ปตท.สะบารัง").icon(BitmapDescriptorFactory.fromResource(R.drawable.gas2)));
        GA4.setTag(0);
        GA5 = mMap.addMarker(new MarkerOptions().position(GAS5).title("บางจาก.วัชระ").icon(BitmapDescriptorFactory.fromResource(R.drawable.gas2)));
        GA5.setTag(0);
    }


    private String getUrl(double latitube, double longitube, String nearbyPlace)
    {
        StringBuilder googlePlaceUrl = new StringBuilder("https://maps.googleapis.com/maps/api/place/nearbysearch/json?");
        googlePlaceUrl.append("location="+latitube+","+longitube);
        googlePlaceUrl.append("&radius="+PROXIMITY_RADIUS);
        googlePlaceUrl.append("&type="+nearbyPlace);
        googlePlaceUrl.append("&sensor=true");
        googlePlaceUrl.append("&key="+"AIzaSyAA1NFbVjPxDVa1YBkuROJYCsFC3QhM-54");

        Log.d("MapsActivity", "url = "+googlePlaceUrl.toString());

        return googlePlaceUrl.toString();
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(this, data);

                PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                        .getPlaceById(mGoogleApiClient, place.getId());
                placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
            }
        }

    }

    private void  geoLocate(){
        Log.d(TAG, "geoLocate: geolocating");

        String searchString = mSearchText.getText().toString();

        Geocoder geocoder = new Geocoder(Map2Activity.this);
        List<Address> list = new ArrayList<>();
        try {
            list = geocoder.getFromLocationName(searchString, 1);
        }catch (IOException e){
            Log.e(TAG, "geoLocate: IOException" + e.getMessage() );
        }

        if (list.size() > 0){
            Address address = list.get(0);

            Log.d(TAG, "geoLocate: found a location" + address.toString());
            //Toast.makeText(this, address.toString(), Toast.LENGTH_SHORT).show();

            moveCamera(new LatLng(address.getLatitude(), address.getLongitude()), DEFAULT_ZOOM,
                    address.getAddressLine(0));
        }
    }

    private void getDeviceLocation(){
        Log.d(TAG, "getDeviceLocation: getting the devices current location");

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        try {
            if(mLocationPermissionsGranted){

                final Task location = mFusedLocationProviderClient.getLastLocation();
                location.addOnCompleteListener(new OnCompleteListener() {
                    @Override
                    public void onComplete(@NonNull Task task) {
                        if(task.isSuccessful()&& task.getResult() != null){
                            Log.d(TAG, "onComplete: found location!");
                            Location currentLocation = (Location) task.getResult();

                            moveCamera(new LatLng(currentLocation.getLatitude(), currentLocation.getLongitude()),
                                    DEFAULT_ZOOM,
                                    "My Location");

                        }else {
                            Log.d(TAG, "onComplete: current location is null");
                            Toast.makeText(Map2Activity.this, "unable to get current location",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        }catch (SecurityException e){
            Log.e(TAG, "getDeviceLocation: SecurityException: " + e.getMessage() );
        }
    }

    private  void moveCamera(LatLng latLng, float zoom, PlaceInfo placeInfo){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        mMap.clear();
        ClickOpenMark = true;

        /////////////BUG//////////////////////
        //mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter(Map2Activity.this));


        if (placeInfo != null){
            try{
                String snippet = "Address: " + placeInfo.getAddress() + "\n" +
                        "Phone Number: " + placeInfo.getPhoneNumber() + "\n" +
                        "Website: " + placeInfo.getWebsiteUri() + "\n" +
                        "Price Ratind: " + placeInfo.getRating() + "\n" ;
                MarkerOptions options = new MarkerOptions()
                        .position(latLng)
                        .title(placeInfo.getName())
                        .snippet(snippet);
                mMarker = mMap.addMarker(options);

            }catch (NullPointerException e){
                Log.e(TAG, "moveCamera: NullPointerException: " + e.getMessage() );
            }
        }else{
            mMap.addMarker(new MarkerOptions().position(latLng));
        }

        hideSoftKeyboard();
    }

    private  void moveCamera(LatLng latLng, float zoom, String title){
        Log.d(TAG, "moveCamera: moving the camera to: lat: " + latLng.latitude + ", lng: " + latLng.longitude );
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoom));

        if (!title.equals("My Location")){
            MarkerOptions options = new MarkerOptions()
                    .position(latLng)
                    .title(title);
            mMap.addMarker(options);
        }

        hideSoftKeyboard();
    }

    private void initMap(){
        Log.d(TAG, "initMap: initializing map");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map2);

        mapFragment.getMapAsync(Map2Activity.this);
    }

    private void  getLocationPermission(){
        Log.d(TAG, "getLocationPermission: getting location permissions");
        String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION};

        if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.getApplicationContext(),
                    COURSE_LOCATION) == PackageManager.PERMISSION_GRANTED){
                mLocationPermissionsGranted = true;
                initMap();
            }else {
                ActivityCompat.requestPermissions(this,
                        permissions,
                        LOCATION_PERMISSION_REQUEST_CODE);
            }
        }else {
            ActivityCompat.requestPermissions(this,
                    permissions,
                    LOCATION_PERMISSION_REQUEST_CODE);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Log.d(TAG, "onRequestPermissionsResult: called.");
        mLocationPermissionsGranted = false;

        switch (requestCode){
            case LOCATION_PERMISSION_REQUEST_CODE:{
                if (grantResults.length > 0){
                    for (int i = 0; i < grantResults.length; i++){
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionsGranted = false;
                            Log.d(TAG, "onRequestPermissionsResult: permission failed");
                            return;
                        }
                    }
                    Log.d(TAG, "onRequestPermissionsResult: permission granted");
                    mLocationPermissionsGranted = true;
                    initMap();
                }
            }
        }
    }

    private void hideSoftKeyboard(){
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    private AdapterView.OnItemClickListener mAutocompleteClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            hideSoftKeyboard();

            final AutocompletePrediction item = mPlaceAutocompleteAdapter.getItem(position);
            final String placeId = item.getPlaceId();

            PendingResult<PlaceBuffer> placeResult = Places.GeoDataApi
                    .getPlaceById(mGoogleApiClient, placeId);
            placeResult.setResultCallback(mUpdatePlaceDetailsCallback);
        }
    };

    private ResultCallback<PlaceBuffer> mUpdatePlaceDetailsCallback = new ResultCallback<PlaceBuffer>() {
        @Override
        public void onResult(@NonNull PlaceBuffer places) {
            if (!places.getStatus().isSuccess()){
                Log.d(TAG, "onResult: Place query did not complete successfully: " + places.getStatus().toString());
                places.release();
                return;
            }
            Place place = places.get(0);

            try {
                mPlace = new PlaceInfo();
                mPlace.setName(place.getName().toString());
                Log.d(TAG, "onResult: name: " + place.getName());
                mPlace.setAddress(place.getAddress().toString());
                Log.d(TAG, "onResult: address: " + place.getAddress());
                //mPlace.setAttributions(place.getAttributions().toString());
                //Log.d(TAG, "onResult: Attributions: " + place.getAttributions());
                mPlace.setId(place.getId());
                Log.d(TAG, "onResult: id: " + place.getId());
                mPlace.setLatlng(place.getLatLng());
                Log.d(TAG, "onResult: Latlng: " + place.getLatLng());
                mPlace.setRating(place.getRating());
                Log.d(TAG, "onResult: rating: " + place.getRating());
                mPlace.setPhoneNumber(place.getPhoneNumber().toString());
                Log.d(TAG, "onResult: phoneNumber: " + place.getPhoneNumber());
                mPlace.setWebsiteUri(place.getWebsiteUri());
                Log.d(TAG, "onResult: Website url: " + place.getWebsiteUri());

                Log.d(TAG, "onResult: place details: " + mPlace.toString());
            }catch (NullPointerException e){
                Log.e(TAG, "onResult: NullPointerExeception: " + e.getMessage() );
            }

            moveCamera(new LatLng(place.getViewport().getCenter().latitude,
                    place.getViewport().getCenter().longitude), DEFAULT_ZOOM, mPlace);

            places.release();
        }
    };

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
                    Intent goSet = new Intent(Map2Activity.this,Setting.class);
                    startActivity(goSet);
                    return true;
                }

                return super.onOptionsItemSelected(item);
            }

            @Override
            protected void onStart() {
                mGoogleApiClient2.connect();
                super.onStart();
            }

            @Override
            protected void onStop() {
                mGoogleApiClient2.disconnect();
                super.onStop();
            }
            @Override
            public void onLocationChanged(final Location location) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
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
                    ActivityCompat.requestPermissions(Map2Activity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION);

                    return;
                }
                LocationServices.FusedLocationApi.requestLocationUpdates(
                        mGoogleApiClient2, mLocationRequest, this);

            }

            @Override
            public void onConnectionSuspended(int i) {

            }

            @Override
            public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                Toast.makeText(Map2Activity.this,
                        "onConnectionFailed: \n" + connectionResult.toString(),
                        Toast.LENGTH_LONG).show();
            }
            public void  setNavi(){
                Log.d(TAG, "setupBottomNavigationView: setting up BottomNavigationView");
                BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
                BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
                BottomNavigationViewHelper.enableNavigation(Map2Activity.this,bottomNavigationViewEx);
                Menu menu =bottomNavigationViewEx.getMenu();
                MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
                menuItem.setChecked(true);
            }
        }


