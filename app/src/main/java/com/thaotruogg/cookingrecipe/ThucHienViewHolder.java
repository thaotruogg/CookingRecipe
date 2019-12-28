package com.thaotruogg.cookingrecipe;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class ThucHienViewHolder extends RecyclerView.ViewHolder {


    TextView mfStep, mfAction;

    public ThucHienViewHolder(@NonNull View itemView) {
        super(itemView);

        mfStep = itemView.findViewById(R.id.tv_step);
        mfAction = itemView.findViewById(R.id.tv_action);
    }
}
