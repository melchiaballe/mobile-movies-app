package com.example.movies;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import androidx.appcompat.app.AlertDialog;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

public class AddMovie extends AppCompatActivity {

    private AlertDialog dialog;

    private Spinner SpinnerMovieTypes;
    private Spinner SpinnerYears;


    private String m_title;
    private String m_type;
    private String m_year;
    private String m_imdb;
    private String m_poster;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_movie);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Change action bar color and Title
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF6750A4")));
        getSupportActionBar().setTitle("Home");


        // Set movie types Spinner
        SpinnerMovieTypes = findViewById(R.id.type_spinner);

        ArrayAdapter<CharSequence> mTypesAdapter = ArrayAdapter.createFromResource(this, R.array.movie_types,
                android.R.layout.simple_spinner_item);

        mTypesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinnerMovieTypes.setAdapter(mTypesAdapter);

        SpinnerMovieTypes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                m_type = selectedItem;
//                Toast.makeText(AddMovie.this, selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing now
            }
        });


        // Set year spinner
        SpinnerYears = findViewById(R.id.year_spinner);

        ArrayList<String> years = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            years.add(Integer.toString(Calendar.getInstance().get(Calendar.YEAR) - i));
        }


        ArrayAdapter<String> yearsAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, years);

        SpinnerYears.setAdapter(yearsAdapter);

        SpinnerYears.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = parent.getItemAtPosition(position).toString();
                m_year = selectedItem;
//                Toast.makeText(AddMovie.this, selectedItem, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing now
            }
        });

    }


    public void onSubmitMovie(View v) {
        EditText editTxtMovieTitle = findViewById(R.id.mvie_title);
        EditText editTxtMovieImdb = findViewById(R.id.imdb_id);
        EditText editTxtMoviePoster = findViewById(R.id.poster_link);

        m_title = editTxtMovieTitle.getText().toString();
        m_imdb = editTxtMovieImdb.getText().toString();
        m_poster = editTxtMoviePoster.getText().toString();

        Movie tmp_mov = new Movie(m_title, m_type, m_year, m_imdb, m_poster);
//        Movie[] tmp_arr = Arrays.copyOf(MainActivity.items, MainActivity.items.length + 1);
//        tmp_arr[tmp_arr.length - 1] = tmp_mov;
//        MainActivity.items = tmp_arr;

        MainActivity.items.add(tmp_mov);

        dialog = new AlertDialog.Builder(this)
                .setTitle("Success!")
                .setMessage("Successfully Added a new Movie")
                .setPositiveButton("Ok", (dialogInterface, which) -> onYesClicked())
                .setCancelable(false)
                .create();

        dialog.show();

    }

    private void onYesClicked() {
        dialog.hide();

        EditText editTxtMovieTitle = findViewById(R.id.mvie_title);
        EditText editTxtMovieImdb = findViewById(R.id.imdb_id);
        EditText editTxtMoviePoster = findViewById(R.id.poster_link);

        editTxtMovieTitle.setText("");
        editTxtMoviePoster.setText("");
        editTxtMovieImdb.setText("");
        SpinnerYears.setSelection(0);
        SpinnerMovieTypes.setSelection(0);
    }

}