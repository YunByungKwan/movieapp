package ybk.org.movieapp.api;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import ybk.org.movieapp.data.CommentResult;
import ybk.org.movieapp.data.DetailMovieResult;
import ybk.org.movieapp.data.MovieResult;
import ybk.org.movieapp.ui.comment.NewComment;

public interface ApiService {

    @GET("/movie/readMovieList")
    Call<MovieResult> getMovieList();

    @GET("/movie/readMovie")
    Call<DetailMovieResult> getDetailMovie(@Query("id") int id);

    @GET("/movie/readCommentList")
    Call<CommentResult> getCommentListByLimit(@Query("id") int id, @Query("limit") int limit);

    @GET("/movie/readCommentList")
    Call<CommentResult> getCommentList(@Query("id") int id);

    @FormUrlEncoded
    @POST("/movie/createComment")
    Call<NewComment> postComment(@FieldMap HashMap<String, Object> param);
}
