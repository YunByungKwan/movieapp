package ybk.org.movieapp.data.local;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Dlog;

/**
 * Singleton class networking with Room
 */
public class LocalDataSource implements DataSource {

    private final String CLASS_NAME = this.getClass().getName();
    private MovieDatabase database = MovieDatabase.getInstance(App.getInstance());
    private ExecutorService executor = Executors.newFixedThreadPool(4);

    @Inject
    public LocalDataSource() {}

    public Single<MovieResponse> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        MovieResponse res = new MovieResponse();
        res.setResult(database.movieDao().getMovieList());
        return Single.just(res);
    }

    public Single<DetailMovieResponse> getDetailMovie(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getDetailMovie()");
        DetailMovieResponse res = new DetailMovieResponse();
        res.setResult(database.detailMovieDao().getMovieInfo(id));
        return Single.just(res);
    }

    public Single<CommentResponse> getCommentList(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        CommentResponse res = new CommentResponse();
        res.setResult(database.commentDao().getCommentList(id));
        return Single.just(res);
    }

    @Override
    public Single<Response> addComment(HashMap<String, Object> comment) {
        return null;
    }

    @Override
    public Single<Response> addLikeDisLike(HashMap<String, Object> count) {
        return null;
    }

    @Override
    public Single<Response> recommendComment(HashMap<String, Object> recommend) {
        return null;
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
