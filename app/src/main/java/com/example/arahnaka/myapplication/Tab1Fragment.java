package com.example.arahnaka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Path;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by arahnaka on 6/20/2018.
 */

public class Tab1Fragment extends AppCompatActivity {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
   static private RecyclerView mRecyclerView;
   static private RecyclerView mRecyclerView2;
    static private RecyclerView mRecyclerView3;
    static FirebaseDatabase mFirebaseDatabase;
    static FirebaseDatabase mFirebaseDatabase2;
    static FirebaseDatabase mFirebaseDatabase3;
    static DatabaseReference mRef;
    static DatabaseReference mRef2;
    static DatabaseReference mRef3;
    LinearLayoutManager mq;
    LinearLayoutManager mq2;
    LinearLayoutManager mq3;
    static Query mQ;
    static Query mQ2;
    static Query mQ3;
    static int cDay;
    static int cSum;
    static int cYesterday;
    static int cMonth;
    static int cYear;
    static int cBeforeYesterday;
    private static String KEY;
    private static final int ACTIVITY_NUM=1;
    static private FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder> RVAdapter;
    static private FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder> RVAdapter2;
    static private FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder> RVAdapter3;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerfordiary);
        setNavi();
        KEY = MainActivity.getKey();
        //old
        //toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("VisitPattani");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitle("");
        toolbar.setSubtitleTextColor(Color.MAGENTA);
        //toolbar.setLogo(R.mipmap.ic_launcher);
        //toolbar.setBackgroundColor(0x009688);

        setSupportActionBar(toolbar);
        //date



        Calendar calander = Calendar.getInstance();
        cDay = calander.get(Calendar.DAY_OF_MONTH);

        cBeforeYesterday= calander.get(Calendar.DAY_OF_MONTH)-2;


        final String Day = Integer.toString(cDay);
        cMonth = calander.get(Calendar.MONTH);
        final String Month = Integer.toString(cMonth);
        cYear = calander.get(Calendar.YEAR);
        final String Year = Integer.toString(cYear);
        cSum=cDay+cMonth+cYear;
        final String Sum = Integer.toString(cSum);
        cYesterday = cSum-1;
        cBeforeYesterday =  cSum-3;

        final String Yesterday = Integer.toString(cYesterday);

        final String BeforeYesterday = Integer.toString(cBeforeYesterday);
        //
        //  1
        mFirebaseDatabase = FirebaseDatabase.getInstance();
     /*   mRef = mFirebaseDatabase.getReference("UserAccount").child(KEY).child("PlacesName");
        mQ = mRef.orderByChild("Day").equalTo(Sum).limitToLast(4);
        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerViewTab1);
        mRecyclerView.hasFixedSize();
        mq = new LinearLayoutManager(Tab1Fragment.this);
        mq.setReverseLayout(true);
        ;
        mRecyclerView.setLayoutManager(mq);

        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<Atraction>().setQuery(mQ, Atraction.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Tab1Fragment.NewsViewHolder holder, int position, @NonNull Atraction model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                //holder.setImage(getBaseContext(), model.getImage());

            }

            @NonNull
            @Override
            public Tab1Fragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layoutlistitemtab1, parent, false);

                return new Tab1Fragment.NewsViewHolder(view);
            }
        };

        mRecyclerView.setAdapter(RVAdapter);
        //
        //  2


        mFirebaseDatabase2 = FirebaseDatabase.getInstance();
        mRef2 = mFirebaseDatabase2.getReference("UserAccount").child(KEY).child("PlacesName");
        mQ2 = mRef2.orderByChild("Day").startAt(BeforeYesterday).endAt(Yesterday);
        mRef2.keepSynced(true);
        mRecyclerView2 = (RecyclerView)findViewById(R.id.recyclerViewTab2);
        mRecyclerView2.hasFixedSize();

        mq2 = new LinearLayoutManager(Tab1Fragment.this);
        mq2.setReverseLayout(true);
        ;
        mRecyclerView2.setLayoutManager(mq2);

        FirebaseRecyclerOptions foodOptions2 = new FirebaseRecyclerOptions.Builder<Atraction>().setQuery(mQ2, Atraction.class).build();
        RVAdapter2 = new FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder>(foodOptions2) {
            @Override
            protected void onBindViewHolder(@NonNull Tab1Fragment.NewsViewHolder holder, int position, @NonNull Atraction model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                //holder.setImage(getBaseContext(), model.getImage());

            }

            @NonNull
            @Override
            public Tab1Fragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layoutlistitemtab1, parent, false);

                return new Tab1Fragment.NewsViewHolder(view);
            }
        };
        mRecyclerView2.setAdapter(RVAdapter2);
        //



        //   3

/*
        mFirebaseDatabase3 = FirebaseDatabase.getInstance();
        mRef3= mFirebaseDatabase3.getReference("UserAccount").child(KEY).child("PlacesName");
        mQ3 = mRef3.orderByChild("Day").equalTo(BeforeYesterday).limitToLast(4);
        mRef3.keepSynced(true);
       // mRecyclerView3 = (RecyclerView)findViewById(R.id.recyclerViewTab3);
        mRecyclerView3.hasFixedSize();

        mq3 = new LinearLayoutManager(Tab1Fragment.this);
        mq3.setReverseLayout(true);
        ;
        mRecyclerView3.setLayoutManager(mq3);

        FirebaseRecyclerOptions foodOptions3 = new FirebaseRecyclerOptions.Builder<Atraction>().setQuery(mQ3, Atraction.class).build();
        RVAdapter3 = new FirebaseRecyclerAdapter<Atraction, Tab1Fragment.NewsViewHolder>(foodOptions3) {
            @Override
            protected void onBindViewHolder(@NonNull Tab1Fragment.NewsViewHolder holder, int position, @NonNull Atraction model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                //holder.setImage(getBaseContext(), model.getImage());

            }

            @NonNull
            @Override
            public Tab1Fragment.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.layoutlistitemtab1, parent, false);

                return new Tab1Fragment.NewsViewHolder(view);
            }
        };
        mRecyclerView3.setAdapter(RVAdapter3);

*/


        //


    }



    public static class NewsViewHolder extends RecyclerView.ViewHolder{
        View mView;
        public NewsViewHolder(View itemView){
            super(itemView);
            mView = itemView;
        }
        public void setTitle(String title){
            TextView post_title = (TextView)mView.findViewById(R.id.imagenametab1);
            post_title.setText(title);
        }





        /*
        public void setDes(String desc){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(desc);
        }*/
       /* public void setImage(Context ctx, String image){
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_view_food);
            Picasso.get().load(image).into(post_image);
        }*/
    }




/*

        FirebaseRecyclerAdapter<Atraction, ViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Atraction, ViewHolder>(
                        Atraction.class, R.layout.layoutlistitemtab1, ViewHolder.class, mRef
                ) {
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Atraction model, int position) {
                        viewHolder.setDetails(getApplicationContext(), model.getTitle());
                    }
                };

        RecyclerView rv = (RecyclerView) findViewById(R.id.recycler_viewtab1);
        rv.setAdapter(firebaseRecyclerAdapter);


        // RecyclerViewAdapterTab1 adapter = new RecyclerViewAdapterTab1(getContext(), mNames, mImageUrls);
        //rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));*/



    public void  setNavi(){

        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(Tab1Fragment.this,bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);
    }


        @Override
        public void onStart() {
            super.onStart();
            /*
            RVAdapter.startListening();
            RVAdapter2.startListening();*/
         //   RVAdapter3.startListening();
        }

        @Override
        public void onStop() {
            super.onStop();
           /* RVAdapter.stopListening();
            RVAdapter2.stopListening();*/
           // RVAdapter3.stopListening();
        }
    }


    /*@Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_plan_tab1, container, false);
       /* mImageUrls.add("https://c1.staticflickr.com/5/4636/25316407448_de5fbf183d_o.jpg");
        mNames.add("Havasu Falls");

        mImageUrls.add("https://i.redd.it/tpsnoz5bzo501.jpg");
        mNames.add("Trondheim");

        mImageUrls.add("https://i.redd.it/qn7f9oqu7o501.jpg");
        mNames.add("Portugal");

        mImageUrls.add("https://i.redd.it/j6myfqglup501.jpg");
        mNames.add("Rocky Mountain National Park");


        mImageUrls.add("https://i.redd.it/0h2gm1ix6p501.jpg");
        mNames.add("Mahahual");

        mImageUrls.add("https://i.redd.it/k98uzl68eh501.jpg");
        mNames.add("Frozen Lake");


        mImageUrls.add("https://i.redd.it/glin0nwndo501.jpg");
        mNames.add("White Sands Desert");

        mImageUrls.add("https://i.redd.it/obx4zydshg601.jpg");
        mNames.add("Austrailia");


      / mFirebaseDatabase = FirebaseDatabase.getInstance();
        mRef=mFirebaseDatabase.getReference("User").child("TestUser").child("Diary").child("PlacesName");


        FirebaseRecyclerAdapter<Atraction,ViewHolder> firebaseRecyclerAdapter=
                new FirebaseRecyclerAdapter<Atraction,ViewHolder>(
                        Atraction.class,R.layout.layoutlistitemtab1,ViewHolder.class,mRef
                ){
                    @Override
                    protected void populateViewHolder(ViewHolder viewHolder, Atraction model, int position) {
                        viewHolder.setDetails(getContext(),model.getTitle());
                    }
                };

        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
        rv.setAdapter(firebaseRecyclerAdapter);




       // RecyclerViewAdapterTab1 adapter = new RecyclerViewAdapterTab1(getContext(), mNames, mImageUrls);
        //rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));
        return rootView;
    }
}*/