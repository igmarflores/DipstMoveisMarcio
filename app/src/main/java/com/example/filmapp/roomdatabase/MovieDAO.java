package com.example.filmapp.roomdatabase;
import androidx.room.Query;
import androidx.room.Insert;
import androidx.room.Update;
import androidx.room.Delete;
import androidx.room.Dao;

import java.util.List;

@Dao
public interface MovieDAO {
    @Query("SELECT * FROM Movie WHERE id = :id LIMIT 1")
    Movie getMovie(int id);

    @Query("SELECT * FROM Movie")
    List<Movie> getAll();

    @Insert
    void insertAll(Movie... movies);

    @Update
    void update(Movie movie);

    @Delete
    void delete(Movie movie);
}
