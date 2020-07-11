package ybk.org.movieapp.ui.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ybk.org.movieapp.data.CommentItem;
import ybk.org.movieapp.data.DetailMovieItem;
import ybk.org.movieapp.Repository;
import ybk.org.movieapp.util.Constants;

public class MovieDetailViewModel extends ViewModel {
    /**
     * private MutableLiveData<List<MovieItem>> items;
     *     private Repository repo;
     *
     *     public void init() {
     *         if (items != null){
     *             return;
     *         }
     *         repo = Repository.getInstance();
     *         items = repo.getMovieList();
     *
     *     }
     *
     *     public LiveData<List<MovieItem>> getMovieList() {
     *         return items;
     *     }
     * */

    private MutableLiveData<List<DetailMovieItem>> item;
    private MutableLiveData<List<CommentItem>> comments;
    private Repository repo;
    public MutableLiveData<Integer> _movieId = new MutableLiveData<>();
    public LiveData<Integer> movieId = _movieId;
    public MutableLiveData<Integer> _limit = new MutableLiveData<>();
    public LiveData<Integer> limit = _limit;

    public void init() {
        Constants.loge("Call init()");
        if(item != null) {
            return;
        }
        Constants.loge("상세 영화정보 가져오기 직전 movieId: " + movieId.getValue());
        repo = Repository.getInstance();
        item = repo.getDetailMovie(movieId.getValue());
        comments = repo.getCommentList(movieId.getValue(), limit.getValue());
    }

    public LiveData<List<DetailMovieItem>> getDetailMovie() {
        return item;
    }
    public LiveData<List<CommentItem>> getCommnetList() {
        return comments;
    }
}
