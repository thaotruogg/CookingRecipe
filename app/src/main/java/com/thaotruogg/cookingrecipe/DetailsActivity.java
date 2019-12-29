package com.thaotruogg.cookingrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DetailsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<NguyenLieu> options;
    FirebaseRecyclerAdapter<NguyenLieu, NguyenLieuViewHolder> adapter;
    ArrayList<NguyenLieu> arrayList;

    TextView tvTitle, tvTime, tvLevel, tvKhauPhan, menuTitle;
    Button btnCooking;
    Toolbar toolbar;
    ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        tvTime = findViewById(R.id.tv_timeFood);
        tvTitle = findViewById(R.id.tv_nameFood);
        tvLevel = findViewById(R.id.tv_levelFood);
        tvKhauPhan = findViewById(R.id.tv_khauPhan);
        btnCooking = findViewById(R.id.btn_action);

        final String title = getIntent().getStringExtra("title");
        String time = getIntent().getStringExtra("time");
        String level = getIntent().getStringExtra("level");
        final String idFood = getIntent().getStringExtra("idFood");
        String khauPhan = getIntent().getStringExtra("khauPhan");


        btnCooking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailsActivity.this, CookingActivity.class);
//                intent.putExtra("title", title);
                intent.putExtra("idFoodDetail", idFood);
                startActivity(intent);
            }
        });

//        this.setTitle(title);
        toolbar = findViewById(R.id.toolbar_details);
        menuTitle = findViewById(R.id.menu_title);
        menuTitle.setText(title);
        imageButton = findViewById(R.id.btn_back_details);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //tvTitle.setText(title);
        tvTime.setText(String.format("%s phút", time));
        tvLevel.setText(level);
        tvKhauPhan.setText(String.format("%s người", khauPhan));

        recyclerView = findViewById(R.id.recyclerView_details);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        arrayList = new ArrayList<>();

        assert idFood != null;
        databaseReference = FirebaseDatabase.getInstance().getReference("NguyenLieu").child(idFood);
        databaseReference.keepSynced(true);
        options = new FirebaseRecyclerOptions.Builder<NguyenLieu>().setQuery(databaseReference, NguyenLieu.class).build();
        adapter = new FirebaseRecyclerAdapter<NguyenLieu, NguyenLieuViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull NguyenLieuViewHolder holder, int position, @NonNull NguyenLieu model) {
                holder.mfTenNgl.setText(model.getTen());
                holder.mfSoLuong.setText(model.getSoluong());
                holder.mfDonVi.setText(model.getDonvi());
            }
            @NonNull
            @Override
            public NguyenLieuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new NguyenLieuViewHolder(LayoutInflater.from(DetailsActivity.this).inflate(R.layout.layout_items_nglieu, parent, false));
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
