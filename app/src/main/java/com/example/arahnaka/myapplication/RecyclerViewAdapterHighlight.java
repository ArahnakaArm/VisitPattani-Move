package com.example.arahnaka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by arahnaka on 6/15/2018.
 */

public class RecyclerViewAdapterHighlight extends  RecyclerView.Adapter<RecyclerViewAdapterHighlight.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterHighlight";
    private ArrayList<String> mImageUrlsHL = new ArrayList<>();
    private ArrayList<String> mNamesHL = new ArrayList<>();
    private ArrayList<String> mDateHL = new ArrayList<>();
    private ArrayList<String> mDesHL = new ArrayList<>();
    private ArrayList<String> mlocateHL = new ArrayList<>();
    private ArrayList<String> mtelHL = new ArrayList<>();
    private Context mContextHL;

    public RecyclerViewAdapterHighlight(Context mContextHL, ArrayList<String> mImageUrlsHL, ArrayList<String> mNamesHL, ArrayList<String> mDateHL
            ,  ArrayList<String> mDesHL, ArrayList<String> mlocateHL, ArrayList<String> mtelHL) {
        this.mContextHL     = mContextHL;
        this.mImageUrlsHL   = mImageUrlsHL;
        this.mNamesHL       = mNamesHL;
        this.mDateHL        = mDateHL;
        this.mDesHL         = mDesHL;
        this.mtelHL         = mtelHL;
        this.mlocateHL      = mlocateHL;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_highlight,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContextHL)
                .asBitmap()
                .load(mImageUrlsHL.get(position))
                .into(holder.imageHL);
        holder.nameHL.setText(mNamesHL.get(position));
        //holder.telHL.setText(mDateHL.get(position));
        holder.imageHL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContextHL,GalleryHighlightActivity.class);
                intent.putExtra("image_urlHL",mImageUrlsHL.get(position));
                intent.putExtra("image_nameHL",mNamesHL.get(position));
                intent.putExtra("image_dateHL",mDateHL.get(position));
                intent.putExtra("dessHL",mDesHL.get(position));
                intent.putExtra("locaHL",mlocateHL.get(position));
                intent.putExtra("TelHL",mtelHL.get(position));


                mContextHL.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrlsHL.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView   imageHL;
        TextView    nameHL;
        //TextView    telHL;

        public ViewHoler(View itemView) {
            super(itemView);
            imageHL  = itemView.findViewById(R.id.image_viewHL);
            nameHL   = itemView.findViewById(R.id.nameHL);
            //telHL    = itemView.findViewById(R.id.telHL);

        }
    }
}
