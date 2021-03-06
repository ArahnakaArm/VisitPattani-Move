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

public class TabPlan3 extends Fragment{
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private static final int ACTIVITY_NUM = 1;
    FirebaseDatabase mFirebaseDatabase;
    Query Q;

    private FirebaseRecyclerAdapter<Plans, TabPlan2.NewsViewHolder> RVAdapter;
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

        mRef = mFirebaseDatabase.getReference("Plantrip").child("AllPlan");
        Q = mRef.orderByChild("type").equalTo("3day");

        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions PlanOptions = new FirebaseRecyclerOptions.Builder<Plans>().setQuery(Q, Plans.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<Plans, TabPlan2.NewsViewHolder>(PlanOptions) {
            @Override
            protected void onBindViewHolder(@NonNull TabPlan2.NewsViewHolder holder, int position, final Plans model) {
                holder.setTitle(model.getTitle());
                // holder.setDes(model.getDescription());
                holder.setImage(getContext(), model.getUrl());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getContext(), ActivityPlanDetail.class);
                        intent.putExtra("image_name", model.getTitle());
                        intent.putExtra("image_url", model.getUrl());
                        intent.putExtra("dess", model.getDes());
                        intent.putExtra("loca", model.getLocation());
                        intent.putExtra("cost", model.getCost());
                        intent.putExtra("tel", model.getTel());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public TabPlan2.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_plan, parent, false);

                return new TabPlan2.NewsViewHolder(view);
            }
        };
        mRecyclerView.setAdapter(RVAdapter);

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
            TextView post_title = (TextView) mView.findViewById(R.id.name_plan);
            post_title.setText(title);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_view_plan);
            Picasso.get().load(image).into(post_image);
        }
    }
}
