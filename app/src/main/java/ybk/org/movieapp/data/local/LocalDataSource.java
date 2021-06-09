package ybk.org.movieapp.data.local;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public interface LocalDataSource {

    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(int id);

    Single<CommentResponse> getCommentList(int id);

    Single<Response> addComment(HashMap<String, Object> comment);

    Single<Response> addLikeDisLike(HashMap<String, Object> count);

    Single<Response> recommendComment(HashMap<String, Object> recommend);

    void insertMovieList(List<Movie> movieList);

    void insertDetailMovie(List<DetailMovie> detailMovie);

    void insertCommentList(List<Comment> commentList);
}
