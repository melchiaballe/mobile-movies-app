package com.example.movies;


import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


import com.squareup.picasso.Picasso;

public class ViewMovie extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_view_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Actionbar override
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6750A4")));
        getSupportActionBar().setTitle("Home");


        // Intent contents
        Intent intent = getIntent();

        String title = intent.getStringExtra("title");
        String type = intent.getStringExtra("type");
        String year = intent.getStringExtra("year");
        String imdb = intent.getStringExtra("imdb");
        String poster = intent.getStringExtra("poster");


        // TextViews override
        TextView tViewMovieTitle = findViewById(R.id.movie_title);
        tViewMovieTitle.setText(title);

        TextView tViewMovieType = findViewById(R.id.movie_type);
        tViewMovieType.setText(type);

        TextView tViewMovieYear = findViewById(R.id.movie_year);
        tViewMovieYear.setText(year);

        TextView tViewMovieImdb = findViewById(R.id.movie_imdb);
        tViewMovieImdb.setText(imdb);

        // Image override
        ImageView poster_image = findViewById(R.id.main_image);

        // Temp img = "https://www.toronto.ca/wp-content/uploads/2022/11/8e7c-garden-contest-2022-res-traditional.jpg"
        Picasso.get()
                .load(poster)
                .into(poster_image);

    }



}