package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public interface RemoteDataSource {
    public Single<MovieResponse> getMovieList();

    public Single<DetailMovieResponse> getDetailMovie(final int id);

    public Single<CommentResponse> getCommentList(final int id);

    public Single<Response> addComment(HashMap<String, Object> comment);

    public Single<Response> addLikeDisLike(HashMap<String, Object> count);

    public Single<Response> recommendComment(HashMap<String, Object> recommend);
}
