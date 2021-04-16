package ybk.org.movieapp.data.local;

import androidx.lifecycle.MutableLiveData;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.util.App;

/**
 * Singleton class networking with Room
 */
public class LocalDataSource {

    private LocalDataSource() {}

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

    public void getMovieList(MutableLiveData<List<Movie>> movieList) {
        executor.execute(
                () -> movieList.postValue(database.movieDao().getMovieList())
        );
    }

    public void insertMovieList(List<Movie> movieList) {
        executor.execute(
                () -> {
                    for(Movie movie : movieList) {
                        database.movieDao().insert(movie);
                    }
                }
        );
    }

    public void getDetailMovie(
            MutableLiveData<List<DetailMovie>> detailMovieInfo, int id
    ) {
        executor.execute(
                () -> {
                    detailMovieInfo.postValue(database.detailMovieDao().getMovieInfo(id));
                }
        );
    }

    public void insertDetailMovie(List<DetailMovie> detailMovie) {
        executor.execute(
                () -> {
                    database.detailMovieDao().insert(detailMovie);
                }
        );
    }

    public void getCommentList(
            MutableLiveData<List<Comment>> commentList, final int id
    ) {
        executor.execute(
                () -> commentList.postValue(database.commentDao().getCommentList(id))
        );
    }

    public void insertCommentList(List<Comment> commentList) {
        executor.execute(
                () -> {
                    for(Comment comment: commentList) {
                        database.commentDao().insert(comment);
                    }

                }
        );
    }
}
