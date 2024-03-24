package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Movie> items = new ArrayList<>(Arrays.asList(
            new Movie("Kung Fu Panda 4", "Comedy", "2024", "1235fsd", "https://preview.redd.it/i-thought-id-try-to-modify-a-probable-kung-fu-panda-4-v0-1ptxcb2rjw7c1.jpeg?width=640&crop=smart&auto=webp&s=f3c0f58edaba130a4b2776acec90da28781d9c59"),
            new Movie("Black Adam", "Action", "2022", "adsdas4567", "https://m.media-amazon.com/images/I/71uRkV8UAhL._AC_SY879_.jpg"),
            new Movie("The Notebook", "Drama", "2004", "dasd22dsa", "https://m.media-amazon.com/images/I/813QbaPYqUL._AC_SY879_.jpg"),
            new Movie("Insidious", "Horror", "2010", "kjk312", "https://m.media-amazon.com/images/I/81mNqhqnxcL._AC_SY500_.jpg")
    ));

    private RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar override
        getSupportActionBar().hide();

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Movies");

        // listener for add click
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                Intent intent = new Intent(getApplicationContext(), AddMovie.class);
                startActivity(intent);

                return true;
            }
        });

        // recycler view

        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        adapter = new RecyclerAdapter(items);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

    }

    ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

            int position = viewHolder.getAdapterPosition();

            if(direction == ItemTouchHelper.LEFT) {
                items.remove(position);
                adapter.notifyItemRemoved(position);
//                for (int i = 0; i < items.size(); i++) {
//                    if(position != i) {
////                        tmp_mov[tmp_mov.length] = items[i];
//                    }
//                }
            }
        }
    };

}