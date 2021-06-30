package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import io.reactivex.Single;
import io.reactivex.Completable;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;

public interface ApiService {

    @GET("/movie/readMovieList")
    Single<MovieResponse> getMovieList();

    @GET("/movie/readMovie")
    Single<DetailMovieResponse> getDetailMovie(@Query("id") int id);

    @GET("/movie/readCommentList")
    Single<CommentResponse> getCommentList(@Query("id") int id);

    @FormUrlEncoded
    @POST("/movie/createComment")
    Completable addComment(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseLikeDisLike")
    Completable addLikeDisLike(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseRecommend")
    Completable recommendComment(@FieldMap HashMap<String, Object> param);
}
