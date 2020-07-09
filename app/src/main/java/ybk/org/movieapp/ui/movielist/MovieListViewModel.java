package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.List;
import ybk.org.movieapp.Repository;
import ybk.org.movieapp.data.MovieItem;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<MovieItem>> items;
    private Repository repo;

    public void init() {
        if (items != null){
            return;
        }
        repo = Repository.getInstance();
        items = repo.getMovieList();

    }

    public LiveData<List<MovieItem>> getMovieList() {
        return items;
    }
}