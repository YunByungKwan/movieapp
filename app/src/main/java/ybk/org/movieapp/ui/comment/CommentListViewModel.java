package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.remote.RemoteDataSource;

public class CommentListViewModel extends ViewModel {

    private MutableLiveData<List<DetailMovie>> item;
    public MutableLiveData<List<Comment>> commentList;
    private MovieRepository repo;
    public MutableLiveData<Integer> movieId = new MutableLiveData<>();

    public void init() {
        if(repo != null) {
            return;
        }
        LocalDataSource localDataSource = new LocalDataSource();
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        repo = new MovieRepository(localDataSource, remoteDataSource);
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
