package com.example.filmapp.roomdatabase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface GenreDAO {
    @Query("SELECT * FROM Genre")
    List<Genre> getAllGenres();

    @Query("SELECT * FROM Genre WHERE id = :genreId")
    Genre getGenreById(int genreId);

    @Insert
    void insertGenres(Genre... genres);

    @Update
    void updateGenre(Genre genre);

    @Delete
    void deleteGenre(Genre genre);
}

