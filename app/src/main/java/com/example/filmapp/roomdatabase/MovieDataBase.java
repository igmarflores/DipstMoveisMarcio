package com.example.filmapp.roomdatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Movie.class, Genre.class}, version = 1)
public abstract class MovieDataBase extends RoomDatabase {

    private static MovieDataBase INSTANCE;

    public abstract MovieDAO movieDAO();
    public abstract GenreDAO genreDAO();

    public static MovieDataBase getDataBase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MovieDataBase.class, "Catalogo de filmes")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}
