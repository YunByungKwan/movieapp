package ybk.org.movieapp.ui.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.R;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.Repository;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class MovieDetailViewModel extends ViewModel {

    private Repository repo;
    private MutableLiveData<List<DetailMovie>> movieInfo;
    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    public MutableLiveData<Integer> movieId = new MutableLiveData<>();
    public MutableLiveData<Integer> limit = new MutableLiveData<>();

    public void init() {
        if(movieInfo != null) {
            return;
        }
        repo = Repository.getInstance();
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
