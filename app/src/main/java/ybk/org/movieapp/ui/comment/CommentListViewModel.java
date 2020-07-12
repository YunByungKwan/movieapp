package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ybk.org.movieapp.repository.Repository;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.data.DetailMovie;
import ybk.org.movieapp.util.Constants;

public class CommentListViewModel extends ViewModel {
    private MutableLiveData<List<DetailMovie>> item;
    private MutableLiveData<List<Comment>> comments;
    private Repository repo;
    public MutableLiveData<Integer> _movieId = new MutableLiveData<>();
    public LiveData<Integer> movieId = _movieId;

    public void init() {
        Constants.loge("Call init()");
        if(item != null) {
            return;
        }
        Constants.loge("상세 영화정보 가져오기 직전 movieId: " + movieId.getValue());
        repo = Repository.getInstance();
        item = repo.getDetailMovie(movieId.getValue());
        comments = repo.getCommentList(movieId.getValue());
    }

    public LiveData<List<Comment>> getCommentAllList() {
        return comments;
    }
}
