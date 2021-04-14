package ybk.org.movieapp.data.remote;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ybk.org.movieapp.data.DataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResult;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Utils;

public class RemoteDataSource implements DataSource {

    private ApiService apiService = RetrofitClient.cteateService(ApiService.class);

    @Override
    public MutableLiveData<List<Movie>> getMovieList() {
        Dlog.d(">>>>>>>>>>>>>>> getMovieList()");
        final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

        apiService.getMovieList().enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                Dlog.d(">>>>>>>>>>>>>>> onResponse()");

                if (response.isSuccessful()){
                    Dlog.d(">>>>>>>>>>>>>>> isSuccessful");
                    movieList.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                Dlog.d(">>>>>>>>>>>>>>> onFailure()");
            }
        });

        Dlog.d(">>>>>>>>>>>>>>> return 직전");
        return movieList;
    }

    @Override
    public MutableLiveData<List<DetailMovie>> getDetailMovie(final int id) {
        final MutableLiveData<List<DetailMovie>> movie = new MutableLiveData<>();

        apiService.getDetailMovie(id).enqueue(new Callback<DetailMovieResult>() {
            @Override
            public void onResponse(Call<DetailMovieResult> call, Response<DetailMovieResult> response) {
                if (response.isSuccessful()){
                    movie.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<DetailMovieResult> call, Throwable t) {

            }
        });
        return movie;
    }

    @Override
    public MutableLiveData<List<Comment>> getCommentList(final int id) {
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
            }
        });
        return commentList;
    }

    @Override
    public void addComment(HashMap<String, Object> comment) {
        apiService.addComment(comment).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Utils.loge("StatusCode: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Utils.loge(t.getMessage());
            }
        });
    }

    @Override
    public void addLikeDisLike(HashMap<String, Object> count) {
        apiService.addLikeDisLike(count).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Utils.loge("StatusCode: " + response.code());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Utils.loge(t.getMessage());
            }
        });
    }

    @Override
    public void recommendComment(HashMap<String, Object> recommend) {
        apiService.recommendComment(recommend).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call, @NonNull Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    Utils.loge("StatusCode: " + response.code());
                } else {
                    Utils.loge("StatusCode: " + response.code());
                    Utils.loge(response.message());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Utils.loge(t.getMessage());
            }
        });
    }
}
