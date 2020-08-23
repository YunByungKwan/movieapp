package ybk.org.movieapp.data;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.remote.ApiService;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResult;
import ybk.org.movieapp.data.remote.RetrofitClient;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class Repository {

    private static Repository repo;
    private ApiService apiService;
    private MovieDatabase database;
    private ExecutorService executor;

    public static Repository getInstance() {
        if (repo == null){
            repo = new Repository();
        }

        return repo;
    }

    private Repository() {
        database = MovieDatabase.getInstance(App.getInstance());
        apiService = RetrofitClient.cteateService(ApiService.class);
        executor = Executors.newFixedThreadPool(4);
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        final MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

        apiService.getMovieList().enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(Call<MovieResult> call, Response<MovieResult> response) {
                if (response.isSuccessful()){
                    movieList.setValue(response.body().getResult());

                    if(movieList != null) {
                        executor.execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        for(Movie movie : movieList.getValue()) {
                                            database.movieDao().insert(movie);
                                        }
                                    }
                                }
                        );
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResult> call, Throwable t) {
                if(database != null){
                    executor.execute(
                            new Runnable() {
                                @Override
                                public void run() {
                                    movieList.postValue(database.movieDao().getMovieList());
                                }
                            }
                    );

                }
            }
        });

        return movieList;
    }

    public MutableLiveData<List<DetailMovie>> getDetailMovie(final int id) {
        final MutableLiveData<List<DetailMovie>> movie = new MutableLiveData<>();

        apiService.getDetailMovie(id).enqueue(new Callback<DetailMovieResult>() {
            @Override
            public void onResponse(Call<DetailMovieResult> call, Response<DetailMovieResult> response) {
                if (response.isSuccessful()){
                    movie.setValue(response.body().getResult());

                    executor.execute(
                            new Runnable() {
                                @Override
                                public void run() {
                                    database.detailMovieDao().insert(movie.getValue());
                                }
                            }
                    );
                }
            }

            @Override
            public void onFailure(Call<DetailMovieResult> call, Throwable t) {
                executor.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                               movie.postValue(database.detailMovieDao().getMovieInfo(id));
                            }
                        }
                );

            }
        });
        return movie;
    }

    public MutableLiveData<List<Comment>> getCommentList(final int id) {
        final MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();

        apiService.getCommentList(id).enqueue(new Callback<CommentResult>() {
            @Override
            public void onResponse(Call<CommentResult> call, Response<CommentResult> response) {
                if (response.isSuccessful()){
                    commentList.setValue(response.body().getResult());

                    executor.execute(
                            new Runnable() {
                                @Override
                                public void run() {
                                    for(Comment comment: commentList.getValue()) {
                                        database.commentDao().insert(comment);
                                    }
                                }
                            }
                    );
                }
            }

            @Override
            public void onFailure(Call<CommentResult> call, Throwable t) {
                executor.execute(
                        new Runnable() {
                            @Override
                            public void run() {
                                commentList.postValue(database.commentDao().getCommentList(id));
                            }
                        }
                );
            }
        });
        return commentList;
    }

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
