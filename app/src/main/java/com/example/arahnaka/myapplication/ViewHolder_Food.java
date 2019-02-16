package com.example.arahnaka.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ViewHolder_Food extends RecyclerView.ViewHolder{
    View mView;
    public ViewHolder_Food(View itemView){
        super(itemView);
        mView=itemView;

    }

    public void setDetails(Context ctx,String title,String image,String Des,String tel,String type,String location){
        TextView mtitleView=mView.findViewById(R.id.name_food);
        mtitleView.setText(title);

        ImageView mImageView=mView.findViewById(R.id.image_view_food);
        Glide.with(ctx).load(image).into(mImageView);



    }


}