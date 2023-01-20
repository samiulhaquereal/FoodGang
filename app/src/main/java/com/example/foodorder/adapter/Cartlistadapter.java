package com.example.foodorder.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodorder.Interface.ChangeNumberItemsListener;
import com.example.foodorder.R;
import com.example.foodorder.cart;
import com.example.foodorder.halper.ManagementCart;
import com.example.foodorder.model.popularmodel;

import java.util.ArrayList;

public class Cartlistadapter extends RecyclerView.Adapter<Cartlistadapter.ViewHolder> {

    private ArrayList<popularmodel> fooddomain;
    private ManagementCart managementCart;
    private ChangeNumberItemsListener changeNumberItemsListener;

    public Cartlistadapter(ArrayList<popularmodel> fooddomain, cart context, ChangeNumberItemsListener changeNumberItemsListener) {
        this.fooddomain = fooddomain;
        this.managementCart = new ManagementCart(context);
        this.changeNumberItemsListener = changeNumberItemsListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewholder_cart,parent,false);
        return new ViewHolder(inflate);
    }

    @Override
    public void onBindViewHolder(@NonNull Cartlistadapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.title.setText(fooddomain.get(position).getTitle());
        holder.feeEachitem.setText(String.valueOf(fooddomain.get(position).getFee()));
        holder.totalEachItem.setText(String.valueOf(Math.round((fooddomain.get(position).getNumberIncart() * fooddomain.get(position).getFee()) * 100) / 100));
        holder.num.setText(String.valueOf(fooddomain.get(position).getNumberIncart()));

        int drawableResourceID = holder.itemView.getContext().getResources().getIdentifier(fooddomain.get(position).getPic(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(holder.itemView.getContext()).load(drawableResourceID).into(holder.pic);
        holder.plusitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                managementCart.plusNumberFood(fooddomain, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });

            }
        });

        holder.miunsitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                managementCart.minusNumberFood(fooddomain, position, new ChangeNumberItemsListener() {
                    @Override
                    public void changed() {
                        notifyDataSetChanged();
                        changeNumberItemsListener.changed();
                    }
                });
            }
        });

    }

    @Override
    public int getItemCount() {
        return fooddomain.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title,feeEachitem;
        ImageView pic,plusitem,miunsitem;
        TextView totalEachItem , num;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.titletxtid);
            feeEachitem = itemView.findViewById(R.id.feeEachitemid);
            pic = itemView.findViewById(R.id.piccartid);
            totalEachItem = itemView.findViewById(R.id.totalEachitemid);
            num = itemView.findViewById(R.id.numberitemtxt);
            plusitem = itemView.findViewById(R.id.plustbtn);
            miunsitem = itemView.findViewById(R.id.minuscartbtn);

        }
    }
}
