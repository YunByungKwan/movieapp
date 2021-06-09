package ybk.org.movieapp.ui.moviebook;

import javax.inject.Inject;

import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepositoryImpl;

public class MovieBookViewModel extends BaseViewModel {

    private MovieRepositoryImpl repository;

    @Inject
    public MovieBookViewModel(MovieRepositoryImpl repository) {
        this.repository = repository;
    }
}
