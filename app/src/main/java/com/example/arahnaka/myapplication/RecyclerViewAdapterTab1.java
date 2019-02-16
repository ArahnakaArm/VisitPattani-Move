package com.example.arahnaka.myapplication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by arahnaka on 6/21/2018.
 */

public class RecyclerViewAdapterTab1 extends RecyclerView.Adapter<RecyclerViewAdapterTab1.ViewHolder> {
    private ArrayList<String> mImageNames= new ArrayList<>();
    private ArrayList<String> mImages= new ArrayList<>();
    private ArrayList<String> mDesCribtion= new ArrayList();
    private Context mContext;

    public RecyclerViewAdapterTab1(Context mContext,ArrayList<String> mImageNames, ArrayList<String> mImaes ) {
        this.mImageNames = mImageNames;
        this.mImages = mImaes;
       // this.mDesCribtion=mDescription;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layoutlistitemtab1,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        /*Glide.with(mContext)
                .asBitmap()
                .load(mImages.get(position))
                .into(holder.imageTab1);*/
        //holder.
       // holder.imageNameTab1.setText(mImageNames.get(position));
        holder.parentLayoutTab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(mContext,mImageNames.get(position),Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return mImageNames.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        CircleImageView imageTab1;
        TextView imageNameTab1;
        RelativeLayout parentLayoutTab1;
        public ViewHolder(View itemView){
            super(itemView);

            imageNameTab1=itemView.findViewById(R.id.imagenametab1);
            parentLayoutTab1=itemView.findViewById(R.id.parent_layouttab1);
        }
    }
}
