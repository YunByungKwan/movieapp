package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import io.reactivex.Single;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.MovieResult;
import ybk.org.movieapp.data.local.entity.Response;

public interface ApiService {

    @GET("/movie/readMovieList")
    Single<MovieResult> getMovieList();

    @GET("/movie/readMovie")
    Single<DetailMovieResult> getDetailMovie(@Query("id") int id);

    @GET("/movie/readCommentList")
    Single<CommentResult> getCommentList(@Query("id") int id);

    @FormUrlEncoded
    @POST("/movie/createComment")
    Single<Response> addComment(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseLikeDisLike")
    Single<Response> addLikeDisLike(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseRecommend")
    Single<Response> recommendComment(@FieldMap HashMap<String, Object> param);
}
