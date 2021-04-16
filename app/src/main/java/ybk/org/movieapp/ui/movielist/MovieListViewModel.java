package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Movie;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> _movieList = new MutableLiveData<>();
    public LiveData<List<Movie>> movieList = _movieList;

    private final MovieRepository repository;

    public MovieListViewModel(MovieRepository repository) {
        this.repository = repository;
        this.repository.getMovieList(_movieList);
    }
}