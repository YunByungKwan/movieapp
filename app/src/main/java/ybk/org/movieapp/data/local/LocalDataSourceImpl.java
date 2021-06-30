package ybk.org.movieapp.data.local;

import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;

import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;

/**
 * Singleton class networking with Room
 */
public class LocalDataSourceImpl implements LocalDataSource {

    private MovieDatabase database;
    private ExecutorService executor;

    @Inject
    public LocalDataSourceImpl(
            MovieDatabase database,
            ExecutorService executor) {
        this.database = database;
        this.executor = executor;
    }

    public Single<MovieResponse> getMovieList() {
        MovieResponse res = new MovieResponse();
        res.setResult(database.movieDao().getMovieList());
        return Single.just(res);
    }

    public Single<DetailMovieResponse> getDetailMovie(int id) {
        DetailMovieResponse res = new DetailMovieResponse();
        res.setResult(database.detailMovieDao().getMovieInfo(id));
        return Single.just(res);
    }

    public Single<CommentResponse> getCommentList(int id) {
        CommentResponse res = new CommentResponse();
        res.setResult(database.commentDao().getCommentList(id));
        return Single.just(res);
    }

    @Override
    public Maybe addComment(HashMap<String, Object> comment) {
        return null;
    }

    @Override
    public Maybe addLikeDisLike(HashMap<String, Object> count) {
        return null;
    }

    @Override
    public Maybe recommendComment(HashMap<String, Object> recommend) {
        return null;
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

    public void insertDetailMovie(List<DetailMovie> detailMovie) {
        executor.execute(() -> database.detailMovieDao().insert(detailMovie)
        );
    }

    public void insertCommentList(List<Comment> commentList) {
        executor.execute(() -> {
                    for(Comment comment: commentList) {
                        database.commentDao().insert(comment);
                    }
                }
        );
    }
}
