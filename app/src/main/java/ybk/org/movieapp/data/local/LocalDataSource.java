package ybk.org.movieapp.data.local;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Dlog;

/**
 * Singleton class networking with Room
 */
public class LocalDataSource {

    private LocalDataSource() {}

    private final String CLASS_NAME = this.getClass().getName();
    private volatile static LocalDataSource instance;
    private MovieDatabase database = MovieDatabase.getInstance(App.getInstance());
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    public static LocalDataSource getInstance() {
        if(instance == null) {
            synchronized (LocalDataSource.class) {
                if(instance == null) {
                    instance = new LocalDataSource();
                }
            }
        }
        return instance;
    }

    public List<Movie> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        return database.movieDao().getMovieList();
    }

    public List<DetailMovie> getDetailMovie(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getDetailMovie()");
        return database.detailMovieDao().getMovieInfo(id);
    }

    public List<Comment> getCommentList(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        return database.commentDao().getCommentList(id);
    }

    public void insertMovieList(List<Movie> movieList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertMovieList()");
        executor.execute(
                () -> {
                    for(Movie movie : movieList) {
                        database.movieDao().insert(movie);
                    }
                }
        );
    }

    public void insertDetailMovie(List<DetailMovie> detailMovie) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertDetailMovie()");
        executor.execute(() -> database.detailMovieDao().insert(detailMovie)
        );
    }

    public void insertCommentList(List<Comment> commentList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertMovieList()");
        executor.execute(() -> {
                    for(Comment comment: commentList) {
                        database.commentDao().insert(comment);
                    }
                }
        );
    }
}
