package ybk.org.movieapp;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ybk.org.movieapp.data.DetailMovieItem;
import ybk.org.movieapp.data.DetailMovieResult;
import ybk.org.movieapp.data.MovieItem;
import ybk.org.movieapp.data.MovieListResult;
import ybk.org.movieapp.util.Constants;

public class Repository {

    private static Repository repos;

    public static Repository getInstance(){
        Constants.logd("Call getInstance()");

        if (repos == null){
            Constants.logd("Repository is null");
            repos = new Repository();
        }

        return repos;
    }

    private ApiService apiService;

    public Repository() {
        apiService = RetrofitClient.cteateService(ApiService.class);
    }

    public MutableLiveData<List<MovieItem>> getMovieList() {
        final MutableLiveData<List<MovieItem>> items = new MutableLiveData<>();

        apiService.getMovieList().enqueue(new Callback<MovieListResult>() {
            @Override
            public void onResponse(Call<MovieListResult> call, Response<MovieListResult> response) {
                Constants.logd("Call onResponse()");

                if (response.isSuccessful()){
                    Constants.logd("Response is successful");

                    items.setValue(response.body().getResult());
                }
            }

            @Override
            public void onFailure(Call<MovieListResult> call, Throwable t) {
                Constants.logd("Call onFailure()");
                items.setValue(null);
            }
        });
        return items;
    }

    public MutableLiveData<List<DetailMovieItem>> getDetailMovie(int id) {
        final MutableLiveData<List<DetailMovieItem>> item = new MutableLiveData<>();

        apiService.getDetailMovie(id).enqueue(new Callback<DetailMovieResult>() {
            @Override
            public void onResponse(Call<DetailMovieResult> call, Response<DetailMovieResult> response) {
                Constants.logd("111111111111111111111111111Call onResponse()");

                if (response.isSuccessful()){
                    Constants.logd("Response is successful11");
                    item.setValue(response.body().getResult());

                } else {
                    Constants.loge("Response is fail");
                }
            }

            @Override
            public void onFailure(Call<DetailMovieResult> call, Throwable t) {
                Constants.logd("Call onFailure()");
                item.setValue(null);
            }
        });
        return item;
    }
}
