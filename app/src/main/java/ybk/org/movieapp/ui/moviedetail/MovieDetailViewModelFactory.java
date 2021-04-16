package ybk.org.movieapp.ui.moviedetail;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ybk.org.movieapp.data.MovieRepository;

public class MovieDetailViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository repository;
    private int id;

    public MovieDetailViewModelFactory(MovieRepository repository, int id) {
        this.repository = repository;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MovieDetailViewModel.class)){
            return (T) new MovieDetailViewModel(repository, id);

        }
        throw new IllegalArgumentException("ViewModel not found");
    }
}
