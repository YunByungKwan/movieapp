package ybk.org.movieapp.ui.movielist;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ybk.org.movieapp.data.MovieRepository;

public class MovieListViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository repository;

    public MovieListViewModelFactory(MovieRepository repository) {
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MovieListViewModel.class)){
            return (T) new MovieListViewModel(repository);

        }
        throw new IllegalArgumentException("ViewModel not found");
    }
}
