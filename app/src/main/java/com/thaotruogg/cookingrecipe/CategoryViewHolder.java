package com.thaotruogg.cookingrecipe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    TextView mfName;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        mfName = itemView.findViewById(R.id.tv_category);
    }
}
