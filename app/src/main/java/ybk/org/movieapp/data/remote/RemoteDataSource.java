package ybk.org.movieapp.data.remote;

import java.util.HashMap;
import io.reactivex.Single;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.MovieResult;
import ybk.org.movieapp.data.local.entity.Response;

public class RemoteDataSource {

    private volatile static RemoteDataSource instance;

    private RemoteDataSource() {}

    public static RemoteDataSource getInstance() {
        if(instance == null) {
            synchronized (RemoteDataSource.class) {
                if(instance == null) {
                    instance = new RemoteDataSource();
                }
            }
        }
        return instance;
    }

    private ApiService apiService = RetrofitClient.cteateService(ApiService.class);

    public Single<MovieResult> getMovieList() {
        return apiService.getMovieList();
    }

    public Single<DetailMovieResult> getDetailMovie(final int id) {
        return apiService.getDetailMovie(id);
    }

    public Single<CommentResult> getCommentList(final int id) {
        return apiService.getCommentList(id);
    }

    public Single<Response> addComment(HashMap<String, Object> comment) {
        return apiService.addComment(comment);
    }

    public Single<Response> addLikeDisLike(HashMap<String, Object> count) {
        return apiService.addLikeDisLike(count);
    }

    public Single<Response> recommendComment(HashMap<String, Object> recommend) {
        return apiService.recommendComment(recommend);
    }
}
