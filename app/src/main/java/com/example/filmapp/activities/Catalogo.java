package com.example.filmapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {
    private TextView txtCatalogo;
    private ListView listaFilme;
    private List<Movie> movieList;
    MovieDataBase movieDB;
    Movie filme;
    private Intent it;

    ArrayAdapter<Movie> movieArrayAdapter;
    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        txtCatalogo = findViewById(R.id.txtCatalogo);
        listaFilme = findViewById(R.id.listaFilme);
        //Intent it = getIntent();
    }
    @Override
    protected void onResume(){
        super.onResume();
        it = new Intent(this, MovieActivity.class);
        insereFilmesLista();
    }

    private void insereFilmesLista(){
        movieList = movieDB.movieDAO().getAll();
        ArrayAdapter<Movie> adapter = new ArrayAdapter<Movie>(Catalogo.this, android.R.layout.simple_list_item_1,
                movieList);
        listaFilme.setAdapter(adapter);

        listaFilme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                filme = movieList.get(i);
                //it = new Intent(Catalogo.this, MovieActivity.class);
                it.putExtra("titulo", filme.getId());
                startActivity(it);
            }
        });
    }
}