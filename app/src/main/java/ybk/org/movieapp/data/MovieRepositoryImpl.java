package ybk.org.movieapp.data;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

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
import ybk.org.movieapp.di.ApplicationModule;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Network;

public class MovieRepositoryImpl implements MovieRepository {

    private final String CLASS_NAME = this.getClass().getName();
    private LocalDataSource localDataSource;
    private RemoteDataSource remoteDataSource;

    @Inject
    public MovieRepositoryImpl(
            @ApplicationModule.MovieLocalDataSource LocalDataSource localDataSource,
            @ApplicationModule.MovieRemoteDataSource RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Single<MovieResponse> getMovieList() {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        if(Network.isConnected()) {
            return remoteDataSource.getMovieList();
        } else {
            return localDataSource.getMovieList();
        }
    }

    @Override
    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getMovieList()");
        if(Network.isConnected()) {
            return remoteDataSource.getDetailMovie(id);
        } else {
            return localDataSource.getDetailMovie(id);
        }
    }

    @Override
    public Single<CommentResponse> getCommentList(int id) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call getCommentList()");
        if(Network.isConnected()) {
            return remoteDataSource.getCommentList(id);
        } else {
            return localDataSource.getCommentList(id);
        }
    }

    @Override
    public void insertMovieListToRoom(List<Movie> movieList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertMovieListToRoom()");
        localDataSource.insertMovieList(movieList);
    }

    @Override
    public void insertDetailMovieToRoom(List<DetailMovie> detailMovieList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertDetailMovieToRoom()");
        localDataSource.insertDetailMovie(detailMovieList);
    }

    @Override
    public void insertCommentListToRoom(List<Comment> commentList) {
        Dlog.d("=========> [" + CLASS_NAME + "] Call insertDetailMovieToRoom()");
        localDataSource.insertCommentList(commentList);
    }

    @Override
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

    @Override
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

    @Override
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
