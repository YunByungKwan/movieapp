package ybk.org.movieapp.local;

import androidx.room.Dao;
import androidx.room.Query;

import ybk.org.movieapp.data.DetailMovie;

@Dao
public interface DetailMovieDao {

    @Query("SELECT * FROM detail_movie WHERE id = (:id)")
    DetailMovie getMovieInfo(int id);

}
