package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.repository.Repository;
import ybk.org.movieapp.data.Comment;
import ybk.org.movieapp.data.DetailMovie;

public class CommentListViewModel extends ViewModel {
    private MutableLiveData<List<DetailMovie>> item;
    public MutableLiveData<List<Comment>> commentList;
    private Repository repo;
    public MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public void init() {
        if(item != null) {
            return;
        }
        repo = Repository.getInstance();
        item = repo.getDetailMovie(movieId.getValue());
        commentList = repo.getCommentList(movieId.getValue());
    }

    public MutableLiveData<List<Comment>> getCommentAllList() {
        return commentList;
    }

    public void addComment(HashMap<String, Object> comment) {
        repo.addComment(comment);
    }

    public void recommendComment(HashMap<String, Object> param) {
        repo.recommendComment(param);
    }
}
