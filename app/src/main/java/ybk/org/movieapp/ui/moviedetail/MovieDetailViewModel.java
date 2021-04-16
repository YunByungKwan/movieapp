package ybk.org.movieapp.ui.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public class MovieDetailViewModel extends ViewModel {

    private MutableLiveData<List<DetailMovie>> _detailMovie = new MutableLiveData<>();
    public LiveData<List<DetailMovie>> detailMovie = _detailMovie;

    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();

    private MovieRepository repository;

    public MovieDetailViewModel(MovieRepository repository, int movieId) {
        this.repository = repository;
        this.repository.getDetailMovie(_detailMovie, movieId);
        this.repository.getCommentList(commentList, movieId);
    }

    public void addComment(HashMap<String, Object> comment) {
        Dlog.d("=========> Call addComment()");
        repository.addComment(comment);
    }

    public void recommendComment(HashMap<String, Object> param) {
        Dlog.d("=========> Call recommendComment()");
        repository.recommendComment(param);
    }

    public void addLikeDisLike(HashMap<String, Object> param) {
        Dlog.d("=========> Call addLikeDisLike()");
        repository.addLikeDisLike(param);
    }
}
