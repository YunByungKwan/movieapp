package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.DataSource;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public class RemoteDataSource implements DataSource {

    private final String CLASS_NAME = this.getClass().getName();
    private ApiService apiService = RetrofitClient.cteateService(ApiService.class);

    @Inject
    public RemoteDataSource() {}

    public Single<MovieResponse> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        return apiService.getMovieList();
    }

    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getDetailMovie()");
        return apiService.getDetailMovie(id);
    }

    public Single<CommentResponse> getCommentList(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        return apiService.getCommentList(id);
    }

    public Single<Response> addComment(HashMap<String, Object> comment) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addComment()");
        return apiService.addComment(comment);
    }

    public Single<Response> addLikeDisLike(HashMap<String, Object> count) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addLikeDisLike()");
        return apiService.addLikeDisLike(count);
    }

    public Single<Response> recommendComment(HashMap<String, Object> recommend) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call recommendComment()");
        return apiService.recommendComment(recommend);
    }
}
