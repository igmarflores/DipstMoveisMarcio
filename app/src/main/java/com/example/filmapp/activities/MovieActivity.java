package com.example.filmapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;


@SuppressWarnings("ALL")
public class MovieActivity extends AppCompatActivity {
    public EditText titulo, ano;
    public Button btnAdicionar;
    public Button btnCatalogo;
    public Button btnExcluir;
    public TextView addFilme;
    public MovieDataBase movieDB;
    //public Movie aux;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        titulo = findViewById(R.id.titulo);
        ano = findViewById(R.id.ano);
        addFilme = findViewById(R.id.addFilme);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        btnCatalogo = findViewById(R.id.btnCatalogo);
        btnExcluir= findViewById(R.id.btnDelete);
        Movie movie = new Movie();
        //aux = getIntent().getIntExtra("titulo",-1);

        //Inicializa o banco de dados
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

        //Botão de adicionar insere o filme no BD e vai para a próxima Activity
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String title = titulo.getText().toString();
                String year = ano.getText().toString();
                //Movie movie = new Movie();
                movie.setTitulo(title);
                movie.setAno(year);

                //Valida se os campos nao estao vazios
                if(titulo.equals("") || ano.equals("")){
                    Toast.makeText(MovieActivity.this, "Preencher os campos é obrigatório",
                            Toast.LENGTH_LONG).show();
                }

                //Se o BD nao existe/nao tem nada adicionado ainda, é impossivel atualizar, apenas inserir
                if(movieDB == null) {
                    movieDB.movieDAO().insertAll(movie);
                    Toast.makeText(MovieActivity.this, "Novo filme adicionado", Toast.LENGTH_LONG).show();
                }else{
                    // Como que faz pra pegar o id do filme pra dps usar o update(movie)?
                    movieDB.movieDAO().update(movie);
                    Toast.makeText(MovieActivity.this, "Dados do filme atualizado", Toast.LENGTH_LONG).show();
                }

                // Limpa os dados dos campos
                titulo.setText("");
                ano.setText("");
            }
        });

        btnCatalogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(MovieActivity.this, Catalogo.class);
                it.putExtra("titulo", titulo.getText());
                //it.putExtra("ano", ano.getText());
                startActivity(it);
            }
        });

        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    movieDB.movieDAO().delete(movie);
                    Toast.makeText(MovieActivity.this, "Filme excluído", Toast.LENGTH_LONG).show();
                }catch(SQLiteConstraintException e){
                    Toast.makeText(MovieActivity.this, "Não é possível excluir", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}