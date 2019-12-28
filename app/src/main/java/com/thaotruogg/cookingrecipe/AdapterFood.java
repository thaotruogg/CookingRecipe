package com.thaotruogg.cookingrecipe;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AdapterFood extends RecyclerView.Adapter<AdapterFood.MyViewHolder> {

    public ArrayList<Foods> list;
    public AdapterFood(ArrayList<Foods> list){
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mfName.setText(list.get(position).getTitle());
        holder.mfTime.setText(list.get(position).getTime());
        holder.mfLevel.setText(list.get(position).getLevel());
        holder.mfNglieuchinh.setText(list.get(position).getNglieuchinh());
        Picasso.get().load(list.get(position).getImage()).into(holder.mfImg);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mfName, mfTime, mfLevel, mfNglieuchinh;
        ImageView mfImg;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mfName = itemView.findViewById(R.id.tv_title);
            mfTime = itemView.findViewById(R.id.tv_timeComplete);
            mfLevel = itemView.findViewById(R.id.tv_Level);
            mfNglieuchinh = itemView.findViewById(R.id.tv_nglieuchinh);
            mfImg = itemView.findViewById(R.id.img_food);
        }
    }
}
