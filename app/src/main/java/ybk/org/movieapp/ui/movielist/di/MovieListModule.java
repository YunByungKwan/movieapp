package ybk.org.movieapp.ui.movielist.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.movielist.MovieListViewModel;

@Module
public abstract class MovieListModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract ViewModel bindViewModel(MovieListViewModel viewmodel);
}
