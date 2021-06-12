package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public class RemoteDataSourceImpl implements RemoteDataSource {

    private final String CLASS_NAME = this.getClass().getName();
    private final ApiService apiService;

    @Inject
    public RemoteDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<MovieResponse> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        return apiService.getMovieList();
    }

    @Override
    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getDetailMovie()");
        return apiService.getDetailMovie(id);
    }

    @Override
    public Single<CommentResponse> getCommentList(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        return apiService.getCommentList(id);
    }

    @Override
    public Completable addComment(HashMap<String, Object> comment) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addComment()");
        return apiService.addComment(comment);
    }

    @Override
    public Completable addLikeDisLike(HashMap<String, Object> count) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addLikeDisLike()");
        return apiService.addLikeDisLike(count);
    }

    @Override
    public Completable recommendComment(HashMap<String, Object> recommend) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call recommendComment()");
        return apiService.recommendComment(recommend);
    }
}
