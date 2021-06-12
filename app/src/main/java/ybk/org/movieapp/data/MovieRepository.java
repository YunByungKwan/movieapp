package ybk.org.movieapp.data;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;

public interface MovieRepository {
    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(final int id);

    Single<CommentResponse> getCommentList(int id);

    Completable insertMovieListToRoom(List<Movie> movieList);

    Completable insertDetailMovieToRoom(List<DetailMovie> detailMovieList);

    Completable insertCommentListToRoom(List<Comment> commentList);

    Completable addComment(HashMap<String, Object> comment);

    Completable addLikeDisLike(HashMap<String, Object> count);

    Completable recommendComment(HashMap<String, Object> recommend);
}
