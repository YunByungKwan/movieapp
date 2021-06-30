package ybk.org.movieapp.data.local;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Maybe;
import io.reactivex.Single;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;

public interface LocalDataSource {

    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(int id);

    Single<CommentResponse> getCommentList(int id);

    Maybe addComment(HashMap<String, Object> comment);

    Maybe addLikeDisLike(HashMap<String, Object> count);

    Maybe recommendComment(HashMap<String, Object> recommend);

    void insertMovieList(List<Movie> movieList);

    void insertDetailMovie(List<DetailMovie> detailMovie);

    void insertCommentList(List<Comment> commentList);
}
