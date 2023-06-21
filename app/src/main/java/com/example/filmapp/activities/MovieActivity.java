package com.example.filmapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;

import java.util.List;

@SuppressWarnings("ALL")
public class MovieActivity extends AppCompatActivity {
    public EditText titulo, ano;
    public Button btnAdicionar;
    public TextView addFilme;
    public MovieDataBase movieDB;
    public List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        titulo = findViewById(R.id.titulo);
        ano = findViewById(R.id.ano);
        addFilme = findViewById(R.id.addFilme);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        Intent intent = getIntent();

        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };

        movieDB = Room.databaseBuilder(getApplicationContext(),
                MovieDataBase.class,"Catalogo de filmes").allowMainThreadQueries().build();
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titulo.getText().toString();
                String year = ano.getText().toString();
                //Movie movie = new Movie(id,title,year);
                Movie movie = new Movie();
                movie.setTitulo(title);
                movie.setAno(year);
                movieDB.movieDAO().insertAll(movie);

                movieList = movieDB.movieDAO().getAll();
                StringBuilder sb = new StringBuilder();
                for(Movie m: movieList){
                    sb.append(m.getTitulo() + ": "+m.getAno());
                    sb.append("\n");
                }
                String mensagem = sb.toString();
                Toast.makeText(MovieActivity.this, ""+mensagem, Toast.LENGTH_LONG).show();
            }
        });
    }
}