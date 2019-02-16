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

public class PlanningList extends AppCompatActivity {

    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;
    static final int MY_PERMISSIONS_REQUEST_ACCESS_FINE_LOCATION = 1;

    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private FirebaseRecyclerAdapter<PlanningModel, PlanningList.NewsViewHolder> RVAdapter;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    private static final int ACTIVITY_NUM = 1;
    String testt="adsa.";
    private TextView find;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.planninglist);
        String Information = getIntent().getStringExtra("information");


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




        mFirebaseDatabase = FirebaseDatabase.getInstance();
        setNavi();
        mRef = mFirebaseDatabase.getReference("Planning").child("AllPlan").child(Information);
        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_viewtab1);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));


        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<PlanningModel>().setQuery(mRef, PlanningModel.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<PlanningModel, PlanningList.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull PlanningList.NewsViewHolder holder, int position, final PlanningModel model) {
                holder.setTitle(model.getName());
                // holder.setDes(model.getDescription());
                holder.setImage(getApplicationContext(), model.getImage());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getImage();
                        Intent intent = new Intent(PlanningList.this, PlanningGallery.class);
                        intent.putExtra("name", model.getName());
                        intent.putExtra("image", model.getImage());
                        intent.putExtra("des", model.getDes());
                        intent.putExtra("des2", model.getDes2());
                        intent.putExtra("level", model.getLevel());
                        intent.putExtra("cost", model.getCost());
                        intent.putExtra("duration", model.getDuration());
                        intent.putExtra("number", model.getNumber());
                        intent.putExtra("vechicle", model.getVechicle());
                      //  Toast.makeText(getApplicationContext(),model.getName(),Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public PlanningList.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_food, parent, false);

                return new PlanningList.NewsViewHolder(view);
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

        RVAdapter.startListening();
        super.onStart();
    }

    @Override
    public void onStop() {

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
        BottomNavigationViewHelper.enableNavigation(PlanningList.this, bottomNavigationViewEx);
        Menu menu = bottomNavigationViewEx.getMenu();
        MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }
    @Override
    public void onBackPressed() {
        Intent goBackplan = new Intent(getApplicationContext(),ActivityPlan2.class);
        startActivity(goBackplan);
        return;
    }


}
