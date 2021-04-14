package ybk.org.movieapp.data.local;

import androidx.lifecycle.MutableLiveData;
import java.util.HashMap;
import java.util.List;
import ybk.org.movieapp.data.DataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;

public class LocalDataSource implements DataSource {

    @Override
    public MutableLiveData<List<Movie>> getMovieList() {
        return null;
    }

    @Override
    public MutableLiveData<List<DetailMovie>> getDetailMovie(int id) {
        return null;
    }

    @Override
    public MutableLiveData<List<Comment>> getCommentList(int id) {
        return null;
    }

    @Override
    public void addComment(HashMap<String, Object> comment) {

    }

    @Override
    public void addLikeDisLike(HashMap<String, Object> count) {

    }

    @Override
    public void recommendComment(HashMap<String, Object> recommend) {

    }

}
