package com.example.arahnaka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by arahnaka on 6/20/2018.
 */

public class Tab1 extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private static final int ACTIVITY_NUM = 1;
    FirebaseDatabase mFirebaseDatabase;
    Query Q;
    private FirebaseRecyclerAdapter<Atraction, Tab1.NewsViewHolder> RVAdapter;
    DatabaseReference mRef;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_plan_tab1, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRef = mFirebaseDatabase.getReference("Places-Home").child("Attractions");
        Q = mRef.orderByChild("type").equalTo("โบราณสถาน");

        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions foodOptions = new FirebaseRecyclerOptions.Builder<Atraction>().setQuery(Q, Atraction.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<Atraction, Tab1.NewsViewHolder>(foodOptions) {
            @Override
            protected void onBindViewHolder(@NonNull Tab1.NewsViewHolder holder, int position, final Atraction model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                holder.setImage(getContext(), model.getUrl());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getContext(), ActivityGallery.class);
                        intent.putExtra("image_name", model.getTitle());
                        intent.putExtra("image_url", model.getUrl());
                        intent.putExtra("dess", model.getDes());
                        intent.putExtra("loca", model.getLocation());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public Tab1.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_food, parent, false);

                return new Tab1.NewsViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(RVAdapter);

/*



       FirebaseRecyclerAdapter<Atraction, ViewHolder_Food> firebaseRecyclerAdapter =
               new FirebaseRecyclerAdapter<Atraction, ViewHolder_Food>(
                        Atraction.class, R.layout.listitem_food, ViewHolder_Food.class, Q
                ) {
                    @Override
                    protected  void populateViewHolder (ViewHolder_Food viewHolder, final Atraction model, final int position) {
                        viewHolder.setDetails(getContext(), model.getTitle(),model.getImage(),model.getDescription(),
                                model.getTel(),model.getType(),model.getLocation());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent=new Intent(getContext(),ActivityGallery.class);
                                intent.putExtra("image_url",model.getImage());
                                intent.putExtra("image_name",model.getTitle());
                                intent.putExtra("dess",model.getDescription());
                                intent.putExtra("Tel",model.getTel());
                                intent.putExtra("type",model.getType());
                                intent.putExtra("loca",model.getLocation());
                                Tab1.this.startActivity(intent);
                            }
                        });

                    }
                };
*/
/*
        BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) rootView.findViewById(R.id.bottomNavViewBar);
        BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
        BottomNavigationViewHelper.enableNavigation(getContext(),bottomNavigationViewEx);
        Menu menu =bottomNavigationViewEx.getMenu();
        MenuItem menuItem=menu.getItem(ACTIVITY_NUM);
        menuItem.setChecked(true);

        //RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
       // rv.setAdapter(firebaseRecyclerAdapter);
        //  RecyclerViewAdapterTab1 adapter = new RecyclerViewAdapterTab1(getContext(), mNames, mImageUrls);
        // rv.setAdapter(adapter);
       // rv.setHasFixedSize(true);
        //rv.setLayoutManager(new LinearLayoutManager(getContext()));*/
        return rootView;
    }
    @Override
    public void onStart() {
        super.onStart();
        RVAdapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        RVAdapter.stopListening();


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
}