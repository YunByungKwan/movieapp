package ybk.org.movieapp.data;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.Movie;

public interface BaseRepository {

    MutableLiveData<List<Movie>> getMovieList();

    MutableLiveData<List<DetailMovie>> getDetailMovie(final int id);

    MutableLiveData<List<Comment>> getCommentList(final int id);

    void addComment(HashMap<String, Object> comment);

    void addLikeDisLike(HashMap<String, Object> count);

    void recommendComment(HashMap<String, Object> recommend);
}
