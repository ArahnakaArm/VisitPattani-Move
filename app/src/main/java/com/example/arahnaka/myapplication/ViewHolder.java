package com.example.arahnaka.myapplication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

public class ViewHolder extends RecyclerView.ViewHolder{
    View mView;
    public ViewHolder(View itemView){
        super(itemView);
        mView=itemView;

    }

    public void setDetails(Context ctx,String title){
        TextView mtitleView=mView.findViewById(R.id.imagenametab1);

        mtitleView.setText(title);


    }

}
