package com.example.movies;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

//    private String[][] data;

    private ArrayList<Movie> movies;

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder)
     */
    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView title;
        public TextView type;
        public TextView year;
        public TextView imdb;

        public ImageView poster;


        public ViewHolder(View view) {
            super(view);
            title = view.findViewById(R.id.title);
            type = view.findViewById(R.id.type);
            year = view.findViewById(R.id.year);
            imdb = view.findViewById(R.id.imdb);
            poster = view.findViewById(R.id.poster);

        }
    }

    /**
     * Initialize the dataset of the Adapter
     *
     * @param data String[] containing the data to populate views to be used
     * by RecyclerView
     */
    public RecyclerAdapter(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    // Create new views (invoked by the layout manager)
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);


        return new ViewHolder(view);
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Movie current_movie = movies.get(position);
        holder.title.setText(current_movie.getM_title());
        holder.type.setText(current_movie.getM_type());
        holder.year.setText(current_movie.getM_year());
        holder.imdb.setText(current_movie.getM_imdb());
        Picasso.get()
                .load(current_movie.getM_poster())
                .into(holder.poster);

        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewMovie.class);
                intent.putExtra("title", current_movie.getM_title());
                intent.putExtra("type", current_movie.getM_type());
                intent.putExtra("year", current_movie.getM_year());
                intent.putExtra("imdb", current_movie.getM_imdb());
                intent.putExtra("poster", current_movie.getM_poster());
                v.getContext().startActivity(intent);
            }
        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return movies.size();
    }
}



