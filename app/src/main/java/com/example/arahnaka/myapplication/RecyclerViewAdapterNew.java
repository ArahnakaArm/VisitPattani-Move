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

public class RecyclerViewAdapterNew extends  RecyclerView.Adapter<RecyclerViewAdapterNew.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterNew";
    private ArrayList<String> mImageUrls4 = new ArrayList<>();
    private ArrayList<String> mNames4 = new ArrayList<>();
    private ArrayList<String> mDate4 = new ArrayList<>();
    private ArrayList<String> mDes4 = new ArrayList<>();
    private ArrayList<String> mlocate4 = new ArrayList<>();
    private ArrayList<String> mtel4 = new ArrayList<>();
    private Context mContext4;

    public RecyclerViewAdapterNew(Context mContext, ArrayList<String> mImageUrls, ArrayList<String> mNames, ArrayList<String> mDate4,  ArrayList<String> mDes, ArrayList<String> mlocate, ArrayList<String> mtel) {
        this.mContext4 = mContext;
        this.mImageUrls4 = mImageUrls;
        this.mNames4 = mNames;
        this.mDate4 = mDate4;
        this.mDes4 = mDes;
        this.mtel4 = mtel;
        this.mlocate4 = mlocate;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem4,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContext4)
                .asBitmap()
                .load(mImageUrls4.get(position))
                .into(holder.image4);
        holder.name4.setText(mNames4.get(position));
        holder.tel4.setText(mDate4.get(position));
        holder.image4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContext4,GalleryNewActivity.class);
                intent.putExtra("image_url",mImageUrls4.get(position));
                intent.putExtra("image_name",mNames4.get(position));
                intent.putExtra("image_date",mDate4.get(position));
                intent.putExtra("dess",mDes4.get(position));
                intent.putExtra("loca",mlocate4.get(position));
                intent.putExtra("Tel",mtel4.get(position));


                mContext4.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls4.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView image4;
        TextView name4;
        TextView tel4;

        public ViewHoler(View itemView) {
            super(itemView);
            image4 = itemView.findViewById(R.id.image_view4);
            name4 = itemView.findViewById(R.id.name4);
            tel4 = itemView.findViewById(R.id.tel4);

        }
    }
}
