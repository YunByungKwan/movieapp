package ybk.org.movieapp.data;

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

public interface MovieRepository {
    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(final int id);

    Single<CommentResponse> getCommentList(int id);

    void insertMovieListToRoom(List<Movie> movieList);

    void insertDetailMovieToRoom(List<DetailMovie> detailMovieList);

    void insertCommentListToRoom(List<Comment> commentList);

    Single<Response> addComment(HashMap<String, Object> comment);

    Single<Response> addLikeDisLike(HashMap<String, Object> count);

    Single<Response> recommendComment(HashMap<String, Object> recommend);
}
