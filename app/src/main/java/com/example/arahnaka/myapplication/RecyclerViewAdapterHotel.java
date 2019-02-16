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

public class RecyclerViewAdapterHotel extends  RecyclerView.Adapter<RecyclerViewAdapterHotel.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterHotel";
    private ArrayList<String> mImageUrlsHo = new ArrayList<>();
    private ArrayList<String> mNamesHo = new ArrayList<>();
    private ArrayList<String> mDateHo = new ArrayList<>();
    private ArrayList<String> mDesHo = new ArrayList<>();
    private ArrayList<String> mlocateHo = new ArrayList<>();
    private ArrayList<String> mtelHo = new ArrayList<>();
    private ArrayList<String> mRatingHo = new ArrayList<>();
    private ArrayList<String> mPriceHo = new ArrayList<>();
    private Context mContextHo;

    public RecyclerViewAdapterHotel(Context mContextHo, ArrayList<String> mImageUrlsHo, ArrayList<String> mNamesHo, ArrayList<String> mDateHo
            ,  ArrayList<String> mDesHo, ArrayList<String> mlocateHo, ArrayList<String> mtelHo, ArrayList<String> mRatingHo, ArrayList<String> mPriceHo) {
        this.mContextHo     = mContextHo;
        this.mImageUrlsHo   = mImageUrlsHo;
        this.mNamesHo       = mNamesHo;
        this.mDateHo        = mDateHo;
        this.mDesHo         = mDesHo;
        this.mtelHo         = mtelHo;
        this.mlocateHo      = mlocateHo;
        this.mRatingHo      = mRatingHo;
        this.mPriceHo       = mPriceHo;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_hotel,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContextHo)
                .asBitmap()
                .load(mImageUrlsHo.get(position))
                .into(holder.imageHo);
        holder.nameHo.setText(mNamesHo.get(position));
        Glide.with(mContextHo)
                .asBitmap()
                .load(mRatingHo.get(position))
                .into(holder.ratingHo);
        holder.priceHo.setText(mPriceHo.get(position));
        holder.telHo.setText(mtelHo.get(position));
        holder.locaHo.setText(mDateHo.get(position));

        holder.imageHo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(mContextHo,GalleryHotelActivity.class);
                intent.putExtra("image_urlHo",mImageUrlsHo.get(position));
                intent.putExtra("image_nameHo",mNamesHo.get(position));
                intent.putExtra("image_dateHo",mDateHo.get(position));
                intent.putExtra("dessHo",mDesHo.get(position));
                intent.putExtra("locaHo",mlocateHo.get(position));
                intent.putExtra("TelHo",mtelHo.get(position));
                intent.putExtra("RatingHo",mRatingHo.get(position));
                intent.putExtra("PriceHo",mPriceHo.get(position));


                mContextHo.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrlsHo.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView   imageHo;
        ImageView   ratingHo;
        TextView    priceHo;
        TextView    nameHo;
        TextView    telHo;
        TextView    locaHo;

        public ViewHoler(View itemView) {
            super(itemView);
            imageHo     = itemView.findViewById(R.id.image_viewHo);
            nameHo      = itemView.findViewById(R.id.nameHo);
            ratingHo    = itemView.findViewById(R.id.ratingHo);
            priceHo     = itemView.findViewById(R.id.priceHo);
            telHo       = itemView.findViewById(R.id.telHo);
            locaHo      = itemView.findViewById(R.id.locaHo);

        }
    }
}
