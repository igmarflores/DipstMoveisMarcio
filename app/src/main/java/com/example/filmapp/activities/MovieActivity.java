package com.example.filmapp.activities;

import androidx.appcompat.app.AppCompatActivity;
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

public class MovieActivity extends AppCompatActivity {
    MovieDataBase movieDB;
    Movie filme;
    private Intent it;
    public TextView txtCatalogo;
    private ListView listaFilme;
    private List<Movie> movieList = new ArrayList<>();
    private ArrayAdapter<Movie> movieArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        txtCatalogo = findViewById(R.id.txtCatalogo);
        listaFilme = findViewById(R.id.listaFilme);
        movieDB = MovieDataBase.getDataBase(getApplicationContext());
    }

    @Override
    protected void onResume(){
        super.onResume();
        it = new Intent(MovieActivity.this, AlteraDadosFilme.class);
        insereFilmesLista();
    }

    // Insere o que o usuário digitou no banco de dados e também no ArrayList de filmes
    public void insereFilmesLista() {
        movieList = movieDB.movieDAO().getAll();
        movieArrayAdapter = new ArrayAdapter<>(MovieActivity.this, android.R.layout.simple_list_item_1,
                movieList);
        listaFilme.setAdapter(movieArrayAdapter);
        listaFilme.setOnItemClickListener((adapterView, view, i, l) -> {
            filme = movieList.get(i);
            it.putExtra("id-filme", filme.getId());
            startActivity(it);
        });
    }
    public void addMovie(View v) {
        startActivity(it);
    }
}