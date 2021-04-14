package ybk.org.movieapp.data;

import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.remote.ApiService;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.data.remote.RetrofitClient;
import ybk.org.movieapp.util.App;

public class MovieRepository implements BaseRepository {

    private final LocalDataSource localDataSource;
    private final RemoteDataSource remoteDataSource;

    public MovieRepository(LocalDataSource localDataSource, RemoteDataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public MutableLiveData<List<Movie>> getMovieList() {
        return remoteDataSource.getMovieList();
    }

    @Override
    public MutableLiveData<List<DetailMovie>> getDetailMovie(final int id) {
        return remoteDataSource.getDetailMovie(id);
    }

    @Override
    public MutableLiveData<List<Comment>> getCommentList(final int id) {
        return remoteDataSource.getCommentList(id);
    }

    @Override
    public void addComment(HashMap<String, Object> comment) {
        remoteDataSource.addComment(comment);
    }

    @Override
    public void addLikeDisLike(HashMap<String, Object> count) {
        remoteDataSource.addLikeDisLike(count);
    }

    @Override
    public void recommendComment(HashMap<String, Object> recommend) {
        remoteDataSource.recommendComment(recommend);
    }
}
