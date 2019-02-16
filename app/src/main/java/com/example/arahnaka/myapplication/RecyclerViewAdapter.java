package com.example.arahnaka.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by arahnaka on 6/15/2018.
 */

public class RecyclerViewAdapter extends  RecyclerView.Adapter<RecyclerViewAdapter.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapter";
    private ArrayList<String> mNames = new ArrayList<>();
    private ArrayList<String> mImageUrls = new ArrayList<>();
    private ArrayList<String> mDes = new ArrayList<>();
    private ArrayList<String> mLoca = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(Context mContext,ArrayList<String> mNames, ArrayList<String> mImageUrls,ArrayList<String>mDes,ArrayList<String> mLoca ) {
        this.mNames = mNames;
        this.mImageUrls = mImageUrls;
        this.mDes=mDes;
        this.mContext = mContext;
        this.mLoca=mLoca;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContext)
                .asBitmap()
                .load(mImageUrls.get(position))
               .into(holder.image);
        holder.name.setText(mNames.get(position));
        holder.image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // Toast.makeText(mContext,mNames.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext,ActivityGallery.class);
                intent.putExtra("image_url",mImageUrls.get(position));
                intent.putExtra("image_name",mNames.get(position));
                intent.putExtra("dess",mDes.get(position));
                intent.putExtra("loca",mLoca.get(position));
                mContext.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView image;
        TextView name;

        public ViewHoler(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image_view);
            name = itemView.findViewById(R.id.name);

        }
    }
}
