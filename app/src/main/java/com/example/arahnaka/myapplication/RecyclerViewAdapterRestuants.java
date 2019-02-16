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

public class RecyclerViewAdapterRestuants extends  RecyclerView.Adapter<RecyclerViewAdapterRestuants.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterTravel";
    private ArrayList<String> mNames3 = new ArrayList<>();
    private ArrayList<String> mImageUrls3 = new ArrayList<>();
    private ArrayList<String> mDes3 = new ArrayList<>();
    private ArrayList<String> mtel3 = new ArrayList<>();
    private ArrayList<String> mlocate3 = new ArrayList<>();
    private Context mContext3;

    public RecyclerViewAdapterRestuants(Context mContext, ArrayList<String> mNames, ArrayList<String> mImageUrls, ArrayList<String> mDes,ArrayList<String> mtel,ArrayList<String> mlocate) {
        this.mNames3 = mNames;
        this.mImageUrls3 = mImageUrls;
        this.mContext3 = mContext;
        this.mDes3=mDes;
        this.mtel3=mtel;
        this.mlocate3=mlocate;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem3,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContext3)
                .asBitmap()
                .load(mImageUrls3.get(position))
               .into(holder.image3);
        holder.name3.setText(mNames3.get(position));
        holder.image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(mContext3,mNames3.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext3,ActivityGalleryFood.class);
                intent.putExtra("image_url",mImageUrls3.get(position));
                intent.putExtra("image_name",mNames3.get(position));
                intent.putExtra("dess",mDes3.get(position));
                intent.putExtra("loca",mlocate3.get(position));
                intent.putExtra("Tel",mtel3.get(position));


                mContext3.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls3.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView image3;
        TextView name3;

        public ViewHoler(View itemView) {
            super(itemView);
            image3 = itemView.findViewById(R.id.image_view3);
            name3 = itemView.findViewById(R.id.name3);

        }
    }
}
