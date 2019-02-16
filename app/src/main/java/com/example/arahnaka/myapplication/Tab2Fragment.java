package com.example.arahnaka.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

/**
 * Created by arahnaka on 6/20/2018.
 */

public class Tab2Fragment extends Fragment {
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    RecyclerView mRecyclerView;
    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mRef;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.travel_food, container, false);
        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mRef = mFirebaseDatabase.getReference("Places-Home").child("Restaurants");
      /*  FirebaseRecyclerAdapter<Atraction, ViewHolder_Food> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Atraction, ViewHolder_Food>(
                        Atraction.class, R.layout.listitem_food, ViewHolder_Food.class, mRef
                ) {
                    @Override
                    protected  void populateViewHolder (ViewHolder_Food viewHolder, final Atraction model, final int position) {
                        viewHolder.setDetails(getContext(), model.getTitle(),model.getImage(),model.getDescription(),
                                model.getTel(),model.getType(),model.getLocation());
                        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {

                                Intent intent=new Intent(getContext(),ActivityGalleryFood.class);
                                intent.putExtra("image_url",model.getImage());
                                intent.putExtra("image_name",model.getTitle());
                                intent.putExtra("dess",model.getDescription());
                                intent.putExtra("Tel",model.getTel());
                                intent.putExtra("type",model.getType());
                                intent.putExtra("loca",model.getLocation());
                                Tab2Fragment.this.startActivity(intent);
                            }
                        });

                    }
                };




        RecyclerView rv = (RecyclerView) rootView.findViewById(R.id.recycler_viewtab1);
        rv.setAdapter(firebaseRecyclerAdapter);
      //  RecyclerViewAdapterTab1 adapter = new RecyclerViewAdapterTab1(getContext(), mNames, mImageUrls);
       // rv.setAdapter(adapter);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getContext()));*/
        return rootView;
    }
}