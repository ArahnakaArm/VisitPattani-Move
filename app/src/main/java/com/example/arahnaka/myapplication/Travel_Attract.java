package com.example.arahnaka.myapplication;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arahnaka.myapplication.ActivityMap;
import com.example.arahnaka.myapplication.ActivityTravel;
import com.example.arahnaka.myapplication.Atraction;
import com.example.arahnaka.myapplication.BottomNavigationViewHelper;
import com.example.arahnaka.myapplication.MainActivity;
import com.example.arahnaka.myapplication.MapActivity;
import com.example.arahnaka.myapplication.R;
import com.example.arahnaka.myapplication.ViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by arahnaka on 6/20/2018.
 */

public class Travel_Attract extends AppCompatActivity implements View.OnClickListener , com.google.android.gms.location.LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<Atraction, Travel_Attract.NewsViewHolder> RVAdapter;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private static final int ACTIVITY_NUM = 1;

    private TextView find;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.travel_attract);

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
        // setNavi();
        //old
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
       // toolbar.setLogo(R.mipmap.ic_launcher);
        //toolbar.setBackgroundColor(0x009688);
        setSupportActionBar(toolbar);

        find = (TextView)findViewById(R.id.findFood);
        find.setOnClickListener(this);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        setNavi();
        mRef = mFirebaseDatabase.getReference("Places-Home").child("Attractions");
        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_viewtab1);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<Atraction>().setQuery(mRef, Atraction.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<Atraction, Travel_Attract.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Travel_Attract.NewsViewHolder holder, int position, final Atraction model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                holder.setImage(getApplicationContext(), model.getImage());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(Travel_Attract.this, ActivityGallery.class);
                        intent.putExtra("image_name", model.getTitle());
                        intent.putExtra("image_url", model.getUrl());
                        intent.putExtra("dess", model.getDes());
                        intent.putExtra("loca", model.getLocation());
                        intent.putExtra("Tel", model.getTel());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public Travel_Attract.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_food, parent, false);

                return new Travel_Attract.NewsViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(RVAdapter);
/*


        FirebaseRecyclerAdapter<Atraction, ViewHolder_Food> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Atraction, ViewHolder_Food>(
                        Atraction.class, R.layout.listitem_food, ViewHolder_Food.class, mRef
                ) {
                    @Override
                    protected  void populateViewHolder (ViewHolder_Food viewHolder, final Atraction model, final int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle(),model.getImage(),model.getDescription(),model.getTel()
                        ,model.getType(),model.getLocation());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent=new Intent(Travel_Accom.this,ActivityGalleryFood.class);
                                intent.putExtra("image_url",model.getImage());
                                intent.putExtra("image_name",model.getTitle());
                                intent.putExtra("loca",model.getLocation());
                                intent.putExtra("Tel",model.getTel());
                                Travel_Accom.this.startActivity(intent);
                            }
                        });

                    }
                };

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_viewtab1);
        rv.setAdapter(firebaseRecyclerAdapter);


        // RecyclerViewAdapterTab1 adapter = new RecyclerViewAdapterTab1(getContext(), mNames, mImageUrls);
        //rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

    }
    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Travel_Accom.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);*/
    }
    @Override
    public void onStart() {
        mGoogleApiClient.connect();
        RVAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {
        mGoogleApiClient.disconnect();
        RVAdapter.stopListening();
        super.onStop();


    }
    public static class NewsViewHolder extends RecyclerView.ViewHolder {
        View mView;

        public NewsViewHolder(View itemView) {
            super(itemView);
            mView = itemView;
        }

        public void setTitle(String title) {
            TextView post_title = (TextView) mView.findViewById(R.id.name_food);
            post_title.setText(title);
        }

        /* public void setDes(String desc){
             TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
             post_desc.setText(desc);
         }*/
        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_view_food);
            Picasso.get().load(image).into(post_image);
        }
    }
    public void setNavi () {

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Travel_Attract.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    public void onClick(View view){
        if (view == find){
            finish();
            Intent start = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.google.co.th/search?rlz=1C1CHZL_enTH716TH753&tbm=lcl&ei=8g4-XN3YKYa99QO5hbfoDw&q=%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5+%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B8%97%E0%B8%B5%E0%B9%88%E0%B8%97%E0%B9%88%E0%B8%AD%E0%B8%87%E0%B9%80%E0%B8%97%E0%B8%B5%E0%B9%88%E0%B8%A2%E0%B8%A7&oq=%E0%B8%9B%E0%B8%B1%E0%B8%95%E0%B8%95%E0%B8%B2%E0%B8%99%E0%B8%B5+%E0%B8%AA%E0%B8%96%E0%B8%B2%E0%B8%99%E0%B8%97%E0%B8%B5%E0%B9%88&gs_l=psy-ab.3.0.0.5258.10673.0.11529.21.14.5.2.2.0.118.1453.6j8.14.0....0...1c.1.64.psy-ab..0.12.908...38j0i22i30k1j33i160k1j35i39k1.0.BRaOzdG1k58"));
            startActivity(start);
        }
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
            ActivityCompat.requestPermissions(Travel_Attract.this,
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
                    Toast.makeText(Travel_Attract.this,
                            "permission was granted, :)",
                            Toast.LENGTH_LONG).show();

                    try{
                        LocationServices.FusedLocationApi.requestLocationUpdates(
                                mGoogleApiClient, mLocationRequest, this);
                    }catch(SecurityException e){
                        Toast.makeText(Travel_Attract.this,
                                "SecurityException:\n" + e.toString(),
                                Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(Travel_Attract.this,
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
        Toast.makeText(Travel_Attract.this,
                "onConnectionFailed: \n" + connectionResult.toString(),
                Toast.LENGTH_LONG).show();
    }
}
