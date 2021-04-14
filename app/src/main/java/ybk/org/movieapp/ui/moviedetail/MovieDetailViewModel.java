package ybk.org.movieapp.ui.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.remote.RemoteDataSource;

public class MovieDetailViewModel extends ViewModel {

    private MovieRepository repo;
    private MutableLiveData<List<DetailMovie>> movieInfo;
    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    public MutableLiveData<Integer> movieId = new MutableLiveData<>();
    public MutableLiveData<Integer> limit = new MutableLiveData<>();

    public void init() {
        if(movieInfo != null) {
            return;
        }
        LocalDataSource localDataSource = new LocalDataSource();
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        repo = new MovieRepository(localDataSource, remoteDataSource);
        movieInfo = repo.getDetailMovie(movieId.getValue());
        commentList = repo.getCommentList(movieId.getValue());
    }

    public LiveData<List<DetailMovie>> getDetailMovie() {
        return movieInfo;
    }

    public MutableLiveData<List<Comment>> getCommentList() {
        return commentList;
    }

    public void addComment(HashMap<String, Object> comment) {
        repo.addComment(comment);
    }

    public void recommendComment(HashMap<String, Object> param) {
        repo.recommendComment(param);
    }

    public void addLikeDisLike(HashMap<String, Object> param) {
        repo.addLikeDisLike(param);
    }
}
