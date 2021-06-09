package ybk.org.movieapp.ui.movieapi;

import javax.inject.Inject;

import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepositoryImpl;

public class MovieApiViewModel extends BaseViewModel {

    private MovieRepositoryImpl repository;

    @Inject
    public MovieApiViewModel(MovieRepositoryImpl repository) {
        this.repository = repository;
    }
}
