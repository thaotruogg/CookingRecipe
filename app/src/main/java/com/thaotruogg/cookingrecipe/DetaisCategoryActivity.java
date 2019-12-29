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
import android.widget.ImageButton;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetaisCategoryActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    ArrayList<Foods> foodsArrayList;
    FirebaseRecyclerOptions<Foods> options;
    FirebaseRecyclerAdapter<Foods, FoodViewHolder> adapter;

    ImageButton imageButton;
    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detais_category);

        String id = getIntent().getStringExtra("idCategory");
        String name = getIntent().getStringExtra("nameCategory");

        textView = findViewById(R.id.menu_details_category);
        textView.setText(name);
        imageButton = findViewById(R.id.btn_back_details_category);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = findViewById(R.id.result_detais_category);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Foods");
        Query query = databaseReference.orderByChild("category").equalTo(id);

        options = new FirebaseRecyclerOptions.Builder<Foods>().setQuery(query, Foods.class).build();

        adapter = new FirebaseRecyclerAdapter<Foods, FoodViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull FoodViewHolder holder, int position, @NonNull final Foods model) {
                holder.mfName.setText(model.getTitle());
                holder.mfTime.setText(String.valueOf(model.getTime()));
                holder.mfLevel.setText(model.getLevel());
                holder.mfNglieuchinh.setText(model.getNglieuchinh());
                Picasso.get().load(model.getImage()).into(holder.mfImg);

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(DetaisCategoryActivity.this, DetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("time", String.valueOf(model.getTime()));
                        intent.putExtra("level", model.getLevel());
                        intent.putExtra("idFood", model.getId());
                        intent.putExtra("khauPhan", String.valueOf(model.getKhauphan()));
                        startActivity(intent);
                    }
                });
            }

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FoodViewHolder(LayoutInflater.from(DetaisCategoryActivity.this).inflate(R.layout.list_layout, parent, false));
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);
    }
}
