package com.example.filmapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDataBase;

public class AlteraDadosFilme extends AppCompatActivity {
    public EditText titulo, ano;
    public Button btnSalvar, btnExcluir;
    public TextView addFilme;
    public MovieDataBase movieDB;
    public Movie filmeDB;
    int movieID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alterafilme);
        titulo = findViewById(R.id.titulo);
        ano = findViewById(R.id.ano);
        addFilme = findViewById(R.id.addFilme);
        btnSalvar = findViewById(R.id.btnSalvar);
        //btnVoltar = findViewById(R.id.btnVoltar);
        btnExcluir = findViewById(R.id.btnDelete);
        movieDB = MovieDataBase.getDataBase(getApplicationContext());
        movieID = getIntent().getIntExtra("id-filme",-1);

        /*Inicializa o banco de dados
        RoomDatabase.Callback myCallBack = new RoomDatabase.Callback() {
            @Override
            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                super.onCreate(db);
            }
            @Override
            public void onOpen(@NonNull SupportSQLiteDatabase db) {
                super.onOpen(db);
            }
        };*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        /* Se o filme já existe no banco então pega os dados deste filme em específico.
           Senão quer dizer que a inserção será feita e portanto faça sumir o botão de excluir
           e troque o texto do botão de 'Alterar' para 'Salvar'
        * */
        if(movieID >= 0)
            getMovie();
        else{
            btnExcluir.setVisibility(View.GONE);
            btnSalvar.setText("Salvar");
        }
    }

    public void getMovie(){
        filmeDB = movieDB.movieDAO().getMovie(movieID);
        titulo.setText(filmeDB.getTitulo());
        ano.setText(filmeDB.getAno());
    }

    public void movieUpdate(View view){
        String title = titulo.getText().toString();
        String year = ano.getText().toString();

        //Valida se os campos nao estao vazios
        if (title.equals("") || year.equals("")) {
            Toast.makeText(AlteraDadosFilme.this, "Preencher os campos é obrigatório",
                    Toast.LENGTH_LONG).show();
            return ;
        }

        Movie movie = new Movie();
        movie.setTitulo(title);
        movie.setAno(year);

        //Se o filme já existe então será um update, caso contrário será um insert
        if (filmeDB != null){
            movie.setId(movieID);
            movieDB.movieDAO().update(movie);
            Toast.makeText(AlteraDadosFilme.this, "Dados do filme atualizado",
                    Toast.LENGTH_LONG).show();
            finish();
        }else{
            movieDB.movieDAO().insertAll(movie);
            Toast.makeText(AlteraDadosFilme.this, "Novo filme adicionado",
                    Toast.LENGTH_LONG).show();
            // Limpa os dados dos campos
            titulo.setText("");
            ano.setText("");
        }
    }

    public void movieDelete(View view){
        movieDB.movieDAO().delete(filmeDB);
        Toast.makeText(AlteraDadosFilme.this, "Filme excluído",
                Toast.LENGTH_LONG).show();
        finish();
    }
    public void voltar(View v){
        finish();
    }
}
