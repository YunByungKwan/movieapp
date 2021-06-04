package ybk.org.movieapp.data.local;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;

public interface DataSource {

    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(final int id);

    Single<CommentResponse> getCommentList(final int id);

    Single<Response> addComment(HashMap<String, Object> comment);

    Single<Response> addLikeDisLike(HashMap<String, Object> count);

    Single<Response> recommendComment(HashMap<String, Object> recommend);

}
