package com.example.filmapp.activities;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.filmapp.R;

import java.util.ArrayList;

public class Catalogo extends RecyclerView.Adapter<Catalogo.FilmeViewHolder> {
    private ArrayList<Object> listaFilmes;

    public Catalogo(ArrayList<Object> listaFilmes) {
        this.listaFilmes = listaFilmes;
    }

    @NonNull
    @Override
    public FilmeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_catalogo, parent, false);
        return new FilmeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmeViewHolder holder, int position) {
        // Implementação do onBind para atualizar os dados do item do RecyclerView
    }

    @Override
    public int getItemCount() {
        return listaFilmes.size();
    }

    public static class FilmeViewHolder extends RecyclerView.ViewHolder {
        public FilmeViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar os componentes do item do RecyclerView aqui
        }
    }
}
