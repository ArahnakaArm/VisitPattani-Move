package com.example.arahnaka.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder> {
    private List<UserModel> list;

    public UserAdapter(List<UserModel> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new UserViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem,parent,false));

    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        UserModel user = list.get(position);
        holder.name.setText(user.name);
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class UserViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        public  UserViewHolder(View itemView){
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
        }
    }
}
