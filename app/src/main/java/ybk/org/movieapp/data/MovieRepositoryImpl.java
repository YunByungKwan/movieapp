package ybk.org.movieapp.data;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Single;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.di.ApplicationModule;
import ybk.org.movieapp.util.Network;

public class MovieRepositoryImpl implements MovieRepository {

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
        if(Network.isConnected()) {
            return remoteDataSource.getMovieList();
        } else {
            return localDataSource.getMovieList();
        }
    }

    @Override
    public Single<DetailMovieResponse> getDetailMovie(final int id) {
        if(Network.isConnected()) {
            return remoteDataSource.getDetailMovie(id);
        } else {
            return localDataSource.getDetailMovie(id);
        }
    }

    @Override
    public Single<CommentResponse> getCommentList(int id) {
        if(Network.isConnected()) {
            return remoteDataSource.getCommentList(id);
        } else {
            return localDataSource.getCommentList(id);
        }
    }

    @Override
    public Completable insertMovieListToRoom(List<Movie> movieList) {
        localDataSource.insertMovieList(movieList);
        return Completable.complete();
    }

    @Override
    public Completable insertDetailMovieToRoom(List<DetailMovie> detailMovieList) {
        localDataSource.insertDetailMovie(detailMovieList);
        return Completable.complete();
    }

    @Override
    public Completable insertCommentListToRoom(List<Comment> commentList) {
        localDataSource.insertCommentList(commentList);
        return Completable.complete();
    }

    @Override
    public Completable addComment(HashMap<String, Object> comment) {
        if(Network.isConnected()) {
            return remoteDataSource.addComment(comment);
        } else {
            return Completable.error(RuntimeException::new);
        }
    }

    @Override
    public Completable addLikeDisLike(HashMap<String, Object> count) {
        if(Network.isConnected()) {
            return remoteDataSource.addLikeDisLike(count);
        } else {
            return Completable.error(RuntimeException::new);
        }
    }

    @Override
    public Completable recommendComment(HashMap<String, Object> recommend) {
        if(Network.isConnected()) {
            return remoteDataSource.recommendComment(recommend);
        } else {
            return Completable.error(RuntimeException::new);
        }
    }
}
