package ybk.org.movieapp.data.remote;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Single;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.MovieResult;

public interface ApiService {

    @GET("/movie/readMovieList")
    Call<MovieResult> getMovieList();

    @GET("/movie/readMovie")
    Call<DetailMovieResult> getDetailMovie(@Query("id") int id);

    @GET("/movie/readCommentList")
    Call<CommentResult> getCommentList(@Query("id") int id);

    @FormUrlEncoded
    @POST("/movie/createComment")
    Call<ResponseBody> addComment(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseLikeDisLike")
    Call<ResponseBody> addLikeDisLike(@FieldMap HashMap<String, Object> param);

    @FormUrlEncoded
    @POST("/movie/increaseRecommend")
    Call<ResponseBody> recommendComment(@FieldMap HashMap<String, Object> param);
}
