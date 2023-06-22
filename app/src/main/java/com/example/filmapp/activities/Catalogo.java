package com.example.filmapp.activities;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;

import java.util.ArrayList;
import java.util.List;

public class Catalogo extends AppCompatActivity {
    public TextView txtCatalogo;
    private ListView listaFilme;
    private List<Movie> movieList = new ArrayList<>();
    private ArrayAdapter<Movie> movieArrayAdapter;
    MovieDataBase movieDB;
    int movieID;
    Movie filme;
    private Intent it;


    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalogo);
        txtCatalogo = findViewById(R.id.txtCatalogo);
        listaFilme = findViewById(R.id.listaFilme);
        movieDB = MovieDataBase.getDataBase(getApplicationContext());
        movieID = getIntent().getIntExtra("filme-id", -1);
    }

    @Override
    protected void onResume(){
        super.onResume();
        if(movieID >=0){
            getMovieID();
        }
        else{
            Toast.makeText(Catalogo.this, "Filme inválido ou inexistente", Toast.LENGTH_LONG).show();;
        }
        insereFilmesLista();
    }
    public void getMovieID(){
        filme = movieDB.movieDAO().getMovie(movieID);
    }
    public void insereFilmesLista(){
        movieList = movieDB.movieDAO().getAll();
        movieArrayAdapter = new ArrayAdapter<>(Catalogo.this, android.R.layout.simple_list_item_1,
                movieList);
        listaFilme.setAdapter(movieArrayAdapter);

        listaFilme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Movie filme = movieList.get(i);
                it = new Intent(Catalogo.this, MovieActivity.class);
                it.putExtra("id-filme", filme.getId());
                it.putExtra("titulo-filme", filme.getTitulo());
                it.putExtra("ano-filme",filme.getAno());
                startActivity(it);
            }
        });
    }

    /*public void voltar(View view){
        finish();
    }*/
}