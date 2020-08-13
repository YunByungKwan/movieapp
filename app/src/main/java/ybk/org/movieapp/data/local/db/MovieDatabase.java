package ybk.org.movieapp.data.local.db;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import ybk.org.movieapp.data.local.dao.CommentDao;
import ybk.org.movieapp.data.local.dao.DetailMovieDao;
import ybk.org.movieapp.data.local.dao.MovieDao;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.util.Constants;

@Database(entities = {Movie.class, DetailMovie.class, Comment.class}, version = 1)
abstract public class MovieDatabase extends RoomDatabase {

    abstract public MovieDao movieDao();
    abstract public DetailMovieDao detailMovieDao();
    abstract public CommentDao commentDao();
    private static MovieDatabase INSTANCE;

    public static MovieDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            Constants.loge("Database instance is created!!");

            INSTANCE = Room.databaseBuilder(context, MovieDatabase.class, "movie-db")
                    .build();
        }

        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}