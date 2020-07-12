package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import ybk.org.movieapp.repository.Repository;
import ybk.org.movieapp.data.Movie;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> items;
    private Repository repo;

    public void init() {
        if (items != null){
            return;
        }
        repo = Repository.getInstance();
        items = repo.getMovieList();

    }

    public LiveData<List<Movie>> getMovieList() {
        return items;
    }
}