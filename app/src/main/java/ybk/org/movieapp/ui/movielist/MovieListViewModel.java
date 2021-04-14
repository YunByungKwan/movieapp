package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.remote.RemoteDataSource;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;
    private MovieRepository repo;

    public void init() {
        LocalDataSource localDataSource = new LocalDataSource();
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        repo = new MovieRepository(localDataSource, remoteDataSource);

        if(movieList == null) {
            movieList = repo.getMovieList();
        }
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

}