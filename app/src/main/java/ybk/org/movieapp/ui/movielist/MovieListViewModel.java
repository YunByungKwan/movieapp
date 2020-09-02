package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ybk.org.movieapp.data.Repository;
import ybk.org.movieapp.data.local.entity.Movie;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> movieList;

    public void init() {
        Repository repo = Repository.getInstance();

        if(movieList == null) {
            movieList = repo.getMovieList();
        }
    }

    public LiveData<List<Movie>> getMovieList() {
        return movieList;
    }

    public void sortMovieListByReservationRate() {
        Collections.sort(movieList.getValue(), new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie t1) {
                if(movie.getReservationRate() > t1.getReservationRate()) {
                    return -1;
                } else if(movie.getReservationRate() < t1.getReservationRate()) {
                    return 1;
                }
                return 0;
            }
        });
    }
}