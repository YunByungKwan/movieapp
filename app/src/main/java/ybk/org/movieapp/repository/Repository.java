package ybk.org.movieapp.repository;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ybk.org.movieapp.api.ApiService;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.data.CommentResult;
import ybk.org.movieapp.data.DetailMovie;
import ybk.org.movieapp.data.DetailMovieResult;
import ybk.org.movieapp.data.Movie;
import ybk.org.movieapp.data.MovieResult;
import ybk.org.movieapp.util.Constants;

public class Repository {

    private static Repository repo;

    public static Repository getInstance(){
        Constants.logd("Call getInstance()");

        if (repo == null){
            Constants.logd("Repository is null");
            repo = new Repository();
        }

        return repo;
    }

    private ApiService apiService;

    private Repository() {
        apiService = RetrofitClient.cteateService(ApiService.class);
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

        apiService.getMovieList().enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()){
                    movieList.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                movieList.setValue(null);
            }
        });
        return movieList;
    }

    public MutableLiveData<List<DetailMovie>> getDetailMovie(int id) {
        final MutableLiveData<List<DetailMovie>> movie = new MutableLiveData<>();

        apiService.getDetailMovie(id).enqueue(new Callback<DetailMovieResult>() {
            @Override
            public void onResponse(Call<DetailMovieResult> call, Response<DetailMovieResult> response) {
                if (response.isSuccessful()){
                    Constants.logd("repo getDetailMovie: Success");
                    movie.setValue(response.body().getResult());

                }
            }

            @Override
            public void onFailure(Call<DetailMovieResult> call, Throwable t) {
                movie.setValue(null);
            }
        });
        return movie;
    }

    public MutableLiveData<List<Comment>> getCommentList(int id) {
        final MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();

        apiService.getCommentList(id).enqueue(new Callback<CommentResult>() {
            @Override
            public void onResponse(Call<CommentResult> call, Response<CommentResult> response) {
                if (response.isSuccessful()){
                    commentList.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<CommentResult> call, Throwable t) {
                commentList.setValue(null);
            }
        });
        return commentList;
    }

    public MutableLiveData<List<Comment>> getCommentListByLimit(int id, int limit) {
        final MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();

        apiService.getCommentListByLimit(id, limit).enqueue(new Callback<CommentResult>() {
            @Override
            public void onResponse(Call<CommentResult> call, Response<CommentResult> response) {
                if (response.isSuccessful()){
                    commentList.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<CommentResult> call, Throwable t) {
                commentList.setValue(null);
            }
        });
        return commentList;
    }

    public void addComment(HashMap<String, Object> comment) {
        apiService.addComment(comment).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Constants.loge("StatusCode: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Constants.loge(t.getMessage());
            }
        });
    }

    public void addLikeDisLike(HashMap<String, Object> count) {
        apiService.addLikeDisLike(count).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Constants.loge("StatusCode: " + response.code());
                } else {
                    Constants.loge("StatusCode: " + response.code());
                    Constants.loge(response.errorBody().toString());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Constants.loge(t.getMessage());
            }
        });
    }

    public void recommendComment(HashMap<String, Object> recommend) {
        apiService.recommendComment(recommend).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Constants.loge("StatusCode: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Constants.loge(t.getMessage());
            }
        });
    }
}
