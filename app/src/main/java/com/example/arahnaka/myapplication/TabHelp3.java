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

public class TabHelp3 extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    private static final int ACTIVITY_NUM = 1;
    FirebaseDatabase mFirebaseDatabase;
    Query Q;
    private FirebaseRecyclerAdapter<Helps, TabHelp3.NewsViewHolder> RVAdapter;
    DatabaseReference mRef;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_help_tab1, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRef = mFirebaseDatabase.getReference("Helps").child("AllHelp");
        Q = mRef.orderByChild("type").equalTo("ศูนย์นักท่องเที่ยว");

        mRef.keepSynced(true);
        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
        mRecyclerView.hasFixedSize();
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        FirebaseRecyclerOptions HelpOptions = new FirebaseRecyclerOptions.Builder<Helps>().setQuery(Q, Helps.class).build();
        RVAdapter = new FirebaseRecyclerAdapter<Helps, TabHelp3.NewsViewHolder>(HelpOptions) {
            @Override
            protected void onBindViewHolder(@NonNull TabHelp3.NewsViewHolder holder, int position, final Helps model) {
                holder.setTitle(model.getTitle());
                holder.setDesc(model.getDes());
                holder.setNumber(model.getNumber());
                holder.setImage(getContext(), model.getUrl());
                holder.mView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String url = model.getUrl();
                        Intent intent = new Intent(getContext(), ActivityHelpDetail.class);
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
            public TabHelp3.NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.listitem_help, parent, false);

                return new TabHelp3.NewsViewHolder(view);
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
            TextView post_title = (TextView) mView.findViewById(R.id.name_help);
            post_title.setText(title);
        }
        public void setDesc(String des){
            TextView post_desc = (TextView)mView.findViewById(R.id.post_desc);
            post_desc.setText(des);
        }
        public void setNumber(String number){
            TextView post_number = (TextView)mView.findViewById(R.id.post_number);
            post_number.setText(number);
        }

        public void setImage(Context ctx, String image) {
            ImageView post_image = (ImageView) mView.findViewById(R.id.image_view_help);
            Picasso.get().load(image).into(post_image);
        }
    }
}
