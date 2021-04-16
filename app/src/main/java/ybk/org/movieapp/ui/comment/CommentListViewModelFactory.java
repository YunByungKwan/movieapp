package ybk.org.movieapp.ui.comment;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import ybk.org.movieapp.data.MovieRepository;

public class CommentListViewModelFactory implements ViewModelProvider.Factory {

    private MovieRepository repository;
    private int id;

    public CommentListViewModelFactory(
            MovieRepository repository, int id
    ) {
        this.repository = repository;
        this.id = id;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(CommentListViewModel.class)){
            return (T) new CommentListViewModel(repository, id);

        }
        throw new IllegalArgumentException("ViewModel not found");
    }
}
