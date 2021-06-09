package ybk.org.movieapp.ui.movieapi.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.movieapi.MovieApiViewModel;

@Module
public abstract class MovieApiModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieApiViewModel.class)
    abstract ViewModel bindViewModel(MovieApiViewModel viewmodel);
}
