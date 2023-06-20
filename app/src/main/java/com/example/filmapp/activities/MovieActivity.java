package com.example.filmapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.filmapp.R;
import com.example.filmapp.roomdatabase.MovieDataBase;

public class MovieActivity extends AppCompatActivity {
    public EditText titulo, ano;
    public Button btnAdicionar;
    public TextView addFilme;
    public MovieDataBase movieDB; // nao eh ctz
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        titulo = findViewById(R.id.titulo);
        ano = findViewById(R.id.ano);
        addFilme = findViewById(R.id.addFilme);
        btnAdicionar = findViewById(R.id.btnAdicionar);
    }
}