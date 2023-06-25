package com.example.filmapp.roomdatabase;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AnoDAO {
    @Insert
    void insert(Ano ano);

    @Query("SELECT * FROM ano WHERE id = :anoId")
    Ano getAno(int anoId);
}
