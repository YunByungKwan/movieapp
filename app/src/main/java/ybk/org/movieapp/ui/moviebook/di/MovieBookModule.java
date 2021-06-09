package ybk.org.movieapp.ui.moviebook.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.moviebook.MovieBookViewModel;

@Module
public abstract class MovieBookModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieBookViewModel.class)
    abstract ViewModel bindViewModel(MovieBookViewModel viewmodel);
}
