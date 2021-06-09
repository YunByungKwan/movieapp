package ybk.org.movieapp.ui.moviedetail.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.moviedetail.MovieDetailViewModel;

@Module
public abstract class MovieDetailModule {
    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    abstract ViewModel bindViewModel(MovieDetailViewModel viewmodel);
}
