package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.util.Dlog;

public class CommentListViewModel extends ViewModel {

    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    private MovieRepository repository;

    public CommentListViewModel(MovieRepository repository, int id) {
        this.repository = repository;
        this.repository.getCommentList(commentList, id);
    }

    public void addComment(HashMap<String, Object> comment) {
        Dlog.d("=========> Call addComment()");
        repository.addComment(comment);
    }

    public void recommendComment(HashMap<String, Object> param) {
        Dlog.d("=========> Call recommendComment()");
        repository.recommendComment(param);
    }
}
