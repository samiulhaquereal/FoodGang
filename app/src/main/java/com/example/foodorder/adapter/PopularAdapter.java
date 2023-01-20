package com.example.foodorder.adapter;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.R;
import com.example.foodorder.model.popularmodel;
import com.example.foodorder.showdetails;

import java.io.Serializable;
import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.ViewHolder> {
    ArrayList<popularmodel> popularFood;

    public PopularAdapter(ArrayList<popularmodel> categoryFood) {
        this.popularFood = categoryFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholderpopular,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.ViewHolder holderr, @SuppressLint("RecyclerView") int position) {

        holderr.title.setText(popularFood.get(position).getTitle());
        holderr.fee.setText(String.valueOf(popularFood.get(position).getFee()));


        int drawablerescourcid = holderr.itemView.getContext().getResources().getIdentifier(popularFood.get(position).getPic(),"drawable", holderr.itemView.getContext().getPackageName());
        Glide.with(holderr.itemView.getContext()).load(drawablerescourcid).into(holderr.pic);

        holderr.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holderr.itemView.getContext(), showdetails.class);
                intent.putExtra("object",popularFood.get(position));
                holderr.itemView.getContext().startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return popularFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,fee;
        TextView add;
        ImageView pic;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleid);
            fee=itemView.findViewById(R.id.feeid);
            pic=itemView.findViewById(R.id.picid);
            add=itemView.findViewById(R.id.addidpr);
        }
    }
}
