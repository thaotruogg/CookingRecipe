package com.thaotruogg.cookingrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class CookingActivity extends AppCompatActivity {

    RecyclerView recyclerView, recyclerViewGroup;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<ThucHien> options;
    FirebaseRecyclerAdapter<ThucHien, ThucHienViewHolder> adapter;
    ArrayList<NguyenLieu> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cooking);

        String id = getIntent().getStringExtra("idFoodDetail");
        setTitle("Thực hiện");

        recyclerView = findViewById(R.id.result_thuc_hien);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();

        assert id != null;
        databaseReference = FirebaseDatabase.getInstance().getReference("ThucHien").child(id);
        options = new FirebaseRecyclerOptions.Builder<ThucHien>().setQuery(databaseReference, ThucHien.class).build();
        adapter = new FirebaseRecyclerAdapter<ThucHien, ThucHienViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ThucHienViewHolder holder, int position, @NonNull ThucHien model) {
                holder.mfStep.setText(String.valueOf(model.getIdThuchien()));
                holder.mfAction.setText(model.getAcThuchien());
            }

            @NonNull
            @Override
            public ThucHienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new ThucHienViewHolder(LayoutInflater.from(CookingActivity.this).inflate(R.layout.thuc_hien_list, parent, false));
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
