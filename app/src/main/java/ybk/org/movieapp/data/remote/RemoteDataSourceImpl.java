package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;

public class RemoteDataSourceImpl implements RemoteDataSource {

    private final ApiService apiService;

    @Inject
    public RemoteDataSourceImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    @Override
    public Single<MovieResponse> getMovieList() {
        return apiService.getMovieList();
    }

    @Override
    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        return apiService.getDetailMovie(id);
    }

    @Override
    public Single<CommentResponse> getCommentList(final int id) {
        return apiService.getCommentList(id);
    }

    @Override
    public Completable addComment(HashMap<String, Object> comment) {
        return apiService.addComment(comment);
    }

    @Override
    public Completable addLikeDisLike(HashMap<String, Object> count) {
        return apiService.addLikeDisLike(count);
    }

    @Override
    public Completable recommendComment(HashMap<String, Object> recommend) {
        return apiService.recommendComment(recommend);
    }
}
