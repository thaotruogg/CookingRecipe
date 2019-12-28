package com.thaotruogg.cookingrecipe;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


class FoodViewHolder extends RecyclerView.ViewHolder {

    TextView mfName, mfTime, mfLevel, mfNglieuchinh;
    ImageView mfImg;

    FoodViewHolder(@NonNull View itemView) {
        super(itemView);
//        mView = itemView;
        mfName = itemView.findViewById(R.id.tv_title);
        mfTime = itemView.findViewById(R.id.tv_timeComplete);
        mfLevel = itemView.findViewById(R.id.tv_Level);
        mfNglieuchinh = itemView.findViewById(R.id.tv_nglieuchinh);
        mfImg = itemView.findViewById(R.id.img_food);
    }
}
