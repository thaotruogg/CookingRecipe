package com.thaotruogg.cookingrecipe;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.widget.SearchView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView mResultList;
    DatabaseReference databaseReference;
    ArrayList<Foods> foodsArrayList;
    FirebaseRecyclerOptions<Foods> options;
    FirebaseRecyclerAdapter<Foods, FoodViewHolder> adapter;
    FirebaseRecyclerAdapter<Foods, FoodViewHolder> searchAdapter;
    SwipeRefreshLayout refreshLayout;
    ProgressBar progressBar;
    FloatingActionButton actionButton;

    Toolbar toolbar;

    @Override
    protected void onStart() {
        super.onStart();
        loadingData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);

        actionButton = findViewById(R.id.btn_category);
        progressBar = findViewById(R.id.progress_circular);
        mResultList = findViewById(R.id.result_list);
        mResultList.setHasFixedSize(true);
        mResultList.setLayoutManager(new LinearLayoutManager(this));
        progressBar.setVisibility(View.VISIBLE);
        foodsArrayList = new ArrayList<>();

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Foods");

        refreshLayout = findViewById(R.id.refresh_data);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                loadingData();
                refreshLayout.setRefreshing(false);
            }
        });

        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CategoryActivity.class);
                startActivity(intent);
            }
        });
    }

    public void searchFood(String searchText){
        Query query = databaseReference.orderByChild("keyword").startAt(searchText).endAt(searchText + "\uf8ff");
        searchAdapter = new FirebaseRecyclerAdapter<Foods, FoodViewHolder>(
                new FirebaseRecyclerOptions.Builder<Foods>().setQuery(query, Foods.class).build()
        ) {
            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FoodViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.list_layout, parent, false));
            }

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
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("time", String.valueOf(model.getTime()));
                        intent.putExtra("level", model.getLevel());
                        intent.putExtra("idFood", model.getId());
                        intent.putExtra("khauPhan", String.valueOf(model.getKhauphan()));
                        startActivity(intent);
                    }
                });
            }
        };
        searchAdapter.startListening();
        searchAdapter.notifyDataSetChanged();
        mResultList.setAdapter(searchAdapter);
    }

    public void loadingData(){
        options = new FirebaseRecyclerOptions.Builder<Foods>().setQuery(databaseReference, Foods.class).build();

        adapter = new FirebaseRecyclerAdapter<Foods, FoodViewHolder>(options) {

            @NonNull
            @Override
            public FoodViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                return new FoodViewHolder(LayoutInflater.from(MainActivity.this).inflate(R.layout.list_layout, parent, false));
            }

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
                        Intent intent = new Intent(MainActivity.this, DetailsActivity.class);
                        intent.putExtra("title", model.getTitle());
                        intent.putExtra("time", String.valueOf(model.getTime()));
                        intent.putExtra("level", model.getLevel());
                        intent.putExtra("idFood", model.getId());
                        intent.putExtra("khauPhan", String.valueOf(model.getKhauphan()));
                        startActivity(intent);
                    }
                });
            }
        };
        adapter.startListening();
        adapter.notifyDataSetChanged();
        mResultList.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menuItem) {
        getMenuInflater().inflate(R.menu.menu_search, menuItem);
        MenuItem menu = menuItem.findItem(R.id.search);

        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menu);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchFood(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchFood(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menuItem);
    }
}
