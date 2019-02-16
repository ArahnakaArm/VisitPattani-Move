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

public class RecyclerViewAdapterTravel extends  RecyclerView.Adapter<RecyclerViewAdapterTravel.ViewHoler> {
    private static  final String TAG = "RecyclerViewAdapterTravel";
    private ArrayList<String> mNames2 = new ArrayList<>();
    private ArrayList<String> mImageUrls2 = new ArrayList<>();
    private Context mContext2;

    public RecyclerViewAdapterTravel(Context mContext, ArrayList<String> mNames, ArrayList<String> mImageUrls ) {
        this.mNames2 = mNames;
        this.mImageUrls2 = mImageUrls;
        this.mContext2 = mContext;
    }

    @Override
    public ViewHoler onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem2,parent,false);
        return new ViewHoler(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHoler holder, final int position) {
        Glide.with(mContext2)
                .asBitmap()
                .load(mImageUrls2.get(position))
               .into(holder.image2);
        holder.name2.setText(mNames2.get(position));
        holder.image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext2,mNames2.get(position),Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(mContext2,ActivityGallery.class);
                intent.putExtra("image_url",mImageUrls2.get(position));
                intent.putExtra("image_name",mNames2.get(position));
                mContext2.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImageUrls2.size();
    }

    public class ViewHoler extends RecyclerView.ViewHolder{
        ImageView image2;
        TextView name2;

        public ViewHoler(View itemView) {
            super(itemView);
            image2 = itemView.findViewById(R.id.image_view2);
            name2 = itemView.findViewById(R.id.name2);

        }
    }
}
