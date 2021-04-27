package ybk.org.movieapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ybk.org.movieapp.data.local.entity.Movie;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM movie")
    List<Movie> getMovieList();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Movie movie);

}
