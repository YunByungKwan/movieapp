package ybk.org.movieapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import ybk.org.movieapp.data.local.entity.DetailMovie;

@Dao
public interface DetailMovieDao {

    @Query("SELECT * FROM detail_movie WHERE id = (:id)")
    List<DetailMovie> getMovieInfo(int id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<DetailMovie> detailMovie);
}
