package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import ybk.org.movieapp.repository.Repository;
import ybk.org.movieapp.data.Movie;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;

    public void init() {
        if (movieList != null){
            return;
        }
        Repository repo = Repository.getInstance();
        movieList = repo.getMovieList();
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }
}