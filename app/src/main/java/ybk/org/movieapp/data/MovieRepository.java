package ybk.org.movieapp.data;

import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Network;

public class MovieRepository {

    private final String CLASS_NAME = this.getClass().getName();
    private volatile static MovieRepository instance;
    private LocalDataSource localDataSource = LocalDataSource.getInstance();
    private RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();

    private MovieRepository() {}

    public static MovieRepository getInstance() {
        if(instance == null) {
            synchronized (MovieRepository.class) {
                if(instance == null) {
                    instance = new MovieRepository();
                }
            }
        }
        return instance;
    }

    public Single<MovieResponse> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        if(Network.isConnected()) {
            return remoteDataSource.getMovieList();
        } else {
            MovieResponse result = new MovieResponse();
            result.setResult(localDataSource.getMovieList());
            return Single.just(result);
        }
    }

    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        if(Network.isConnected()) {
            return remoteDataSource.getDetailMovie(id);
        } else {
            DetailMovieResponse result = new DetailMovieResponse();
            result.setResult(localDataSource.getDetailMovie(id));
            return Single.just(result);
        }
    }

    public Single<CommentResponse> getCommentList(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        if(Network.isConnected()) {
            return remoteDataSource.getCommentList(id);
        } else {
            CommentResponse result = new CommentResponse();
            result.setResult(localDataSource.getCommentList(id));
            return Single.just(result);
        }
    }

    public void insertMovieListToRoom(List<Movie> movieList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertMovieListToRoom()");
        localDataSource.insertMovieList(movieList);
    }

    public void insertDetailMovieToRoom(List<DetailMovie> detailMovieList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertDetailMovieToRoom()");
        localDataSource.insertDetailMovie(detailMovieList);
    }

    public void insertCommentListToRoom(List<Comment> commentList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertDetailMovieToRoom()");
        localDataSource.insertCommentList(commentList);
    }

    public Single<Response> addComment(HashMap<String, Object> comment) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addComment()");
        if(Network.isConnected()) {
            return remoteDataSource.addComment(comment);
        } else {
            Response result = new Response();
            result.setMessage("movie createComment 실패");
            result.setCode(400);
            result.setResultType("string");
            result.setResult("네트워크 연결이 필요합니다.");
            return Single.just(result);
        }
    }

    public Single<Response> addLikeDisLike(HashMap<String, Object> count) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call addLikeDisLike()");
        if(Network.isConnected()) {
            return remoteDataSource.addLikeDisLike(count);
        } else {
            Response result = new Response();
            result.setMessage("movie addLikeDisLike 실패");
            result.setCode(400);
            result.setResultType("string");
            result.setResult("네트워크 연결이 필요합니다.");
            return Single.just(result);
        }
    }

    public Single<Response> recommendComment(HashMap<String, Object> recommend) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call recommendComment()");
        if(Network.isConnected()) {
            return remoteDataSource.recommendComment(recommend);
        } else {
            Response result = new Response();
            result.setMessage("movie recommendComment 실패");
            result.setCode(400);
            result.setResultType("string");
            result.setResult("네트워크 연결이 필요합니다.");
            return Single.just(result);
        }
    }
}
