package com.example.filmapp.roomdatabase;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Filme")
public class Movie {
    @PrimaryKey(autoGenerate = true)
    int id;
    @ColumnInfo(name = "titulo")
    String titulo;
    @ColumnInfo(name = "ano")
    String ano;
    public Movie(){}
    public Movie(int id, String titulo, String ano) {
        this.id = id;
        this.titulo = titulo;
        this.ano = ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    @Override
    public String toString(){
        return "id:"+this.id+" "+ this.titulo+"-"+this.ano;
    }
}
