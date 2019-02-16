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

public class RecyclerViewAdapterMarket extends  RecyclerView.Adapter<RecyclerViewAdapterMarket.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterMarket";
    private ArrayList<String> mImageUrlsMK = new ArrayList<>();
    private ArrayList<String> mNamesMK = new ArrayList<>();
    private ArrayList<String> mDateMK = new ArrayList<>();
    private ArrayList<String> mDesMK = new ArrayList<>();
    private ArrayList<String> mlocateMK = new ArrayList<>();
    private ArrayList<String> mtelMK = new ArrayList<>();
    private ArrayList<String> mRatingMK = new ArrayList<>();
    private ArrayList<String> mPriceMK = new ArrayList<>();
    private Context mContextMK;

    public RecyclerViewAdapterMarket(Context mContextMK, ArrayList<String> mImageUrlsMK, ArrayList<String> mNamesMK, ArrayList<String> mDateMK
            ,  ArrayList<String> mDesMK, ArrayList<String> mlocateMK, ArrayList<String> mtelMK, ArrayList<String> mRatingMK, ArrayList<String> mPriceMK) {
        this.mContextMK     = mContextMK;
        this.mImageUrlsMK   = mImageUrlsMK;
        this.mNamesMK       = mNamesMK;
        this.mDateMK        = mDateMK;
        this.mDesMK         = mDesMK;
        this.mtelMK         = mtelMK;
        this.mlocateMK      = mlocateMK;
        this.mRatingMK      = mRatingMK;
        this.mPriceMK       = mPriceMK;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_market,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContextMK)
                .asBitmap()
                .load(mImageUrlsMK.get(position))
                .into(holder.imageMK);
        holder.nameMK.setText(mNamesMK.get(position));
        Glide.with(mContextMK)
                .asBitmap()
                .load(mRatingMK.get(position))
                .into(holder.ratingMK);
        holder.priceMK.setText(mPriceMK.get(position));
        holder.telMK.setText(mtelMK.get(position));
        holder.locaMK.setText(mDateMK.get(position));

        holder.imageMK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContextMK,GalleryMarketActivity.class);
                intent.putExtra("image_urlMK",mImageUrlsMK.get(position));
                intent.putExtra("image_nameMK",mNamesMK.get(position));
                intent.putExtra("image_dateMK",mDateMK.get(position));
                intent.putExtra("dessMK",mDesMK.get(position));
                intent.putExtra("locaMK",mlocateMK.get(position));
                intent.putExtra("TelMK",mtelMK.get(position));
                intent.putExtra("RatingMK",mRatingMK.get(position));
                intent.putExtra("PriceMK",mPriceMK.get(position));

                mContextMK.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrlsMK.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView   imageMK;
        ImageView   ratingMK;
        TextView    priceMK;
        TextView    nameMK;
        TextView    telMK;
        TextView    locaMK;

        public ViewHoler(View itemView) {
            super(itemView);
            imageMK     = itemView.findViewById(R.id.image_viewMK);
            nameMK      = itemView.findViewById(R.id.nameMK);
            ratingMK    = itemView.findViewById(R.id.ratingMK);
            priceMK     = itemView.findViewById(R.id.priceMK);
            telMK       = itemView.findViewById(R.id.telMK);
            locaMK      = itemView.findViewById(R.id.locaMK);

        }
    }
}
