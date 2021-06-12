package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public interface RemoteDataSource {
    Single<MovieResponse> getMovieList();

    Single<DetailMovieResponse> getDetailMovie(final int id);

    Single<CommentResponse> getCommentList(final int id);

    Completable addComment(HashMap<String, Object> comment);

    Completable addLikeDisLike(HashMap<String, Object> count);

    Completable recommendComment(HashMap<String, Object> recommend);
}
