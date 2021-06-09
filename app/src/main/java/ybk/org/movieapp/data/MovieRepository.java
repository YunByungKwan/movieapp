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
    public Single<MovieResponse> getMovieList();

    public Single<DetailMovieResponse> getDetailMovie(final int id);

    public Single<CommentResponse> getCommentList(int id);

    public void insertMovieListToRoom(List<Movie> movieList);

    public void insertDetailMovieToRoom(List<DetailMovie> detailMovieList);

    public void insertCommentListToRoom(List<Comment> commentList);

    public Single<Response> addComment(HashMap<String, Object> comment);

    public Single<Response> addLikeDisLike(HashMap<String, Object> count);

    public Single<Response> recommendComment(HashMap<String, Object> recommend);
}
