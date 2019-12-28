package com.thaotruogg.cookingrecipe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class NguyenLieuViewHolder extends RecyclerView.ViewHolder {

    TextView mfTenNgl, mfDonVi, mfSoLuong;

    public NguyenLieuViewHolder(@NonNull View itemView) {
        super(itemView);
        mfTenNgl = itemView.findViewById(R.id.tv_tenNglieu);
        mfSoLuong = itemView.findViewById(R.id.tv_soLuong);
        mfDonVi = itemView.findViewById(R.id.tv_donVi);
    }
}
