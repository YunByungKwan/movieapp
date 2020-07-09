package ybk.org.movieapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ybk.org.movieapp.data.DetailMovieResult;
import ybk.org.movieapp.data.MovieListResult;

public interface ApiService {

    @GET("/movie/readMovieList")
    Call<MovieListResult> getMovieList();

    @GET("/movie/readMovie")
    Call<DetailMovieResult> getDetailMovie(@Query("id") int id);
}
