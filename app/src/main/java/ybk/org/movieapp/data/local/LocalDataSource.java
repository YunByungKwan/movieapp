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

    public Single<MovieResponse> getMovieList();

    public Single<DetailMovieResponse> getDetailMovie(int id);

    public Single<CommentResponse> getCommentList(int id);

    public Single<Response> addComment(HashMap<String, Object> comment);

    public Single<Response> addLikeDisLike(HashMap<String, Object> count);

    public Single<Response> recommendComment(HashMap<String, Object> recommend);

    public void insertMovieList(List<Movie> movieList);

    public void insertDetailMovie(List<DetailMovie> detailMovie);

    public void insertCommentList(List<Comment> commentList);
}
