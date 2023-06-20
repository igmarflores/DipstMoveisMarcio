package com.example.filmapp.roomdatabase;
import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.filmapp.roomdatabase.Movie;
import com.example.filmapp.roomdatabase.MovieDAO;
@Database(entities = Movie.class, version = 1)
public abstract class MovieDataBase extends RoomDatabase{

    private static MovieDataBase INSTANCE;
    public MovieDAO movieDAO;

    public static MovieDataBase getDataBase(Context context){
        if(INSTANCE == null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    MovieDataBase.class,"Catalogo de filmes").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

}
