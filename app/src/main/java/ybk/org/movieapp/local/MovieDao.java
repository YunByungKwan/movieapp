package ybk.org.movieapp.local;

import androidx.room.Dao;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<MovieEntity> getMovieList();

}
