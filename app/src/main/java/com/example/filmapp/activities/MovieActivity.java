package com.example.filmapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;

@SuppressWarnings("ALL")
public class MovieActivity extends AppCompatActivity {
    public EditText titulo, ano;
    public Button btnAdicionar;
    public TextView addFilme;
    public MovieDataBase movieDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        titulo = findViewById(R.id.titulo);
        ano = findViewById(R.id.ano);
        addFilme = findViewById(R.id.addFilme);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        Intent intent = getIntent();

        movieDB = Room.databaseBuilder(getApplicationContext(),
                MovieDataBase.class,"Catalogo de filmes").allowMainThreadQueries().build();
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titulo.getText().toString();
                String year = ano.getText().toString();
                int id = 0;
                Movie movie = new Movie(id,title,year);
                movieDB.movieDAO().insertAll(movie);
            }
        });
    }
}