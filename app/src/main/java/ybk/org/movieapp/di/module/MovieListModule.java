package ybk.org.movieapp.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelBuilder;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.movielist.MovieListFragment;
import ybk.org.movieapp.ui.movielist.MovieListViewModel;

@Module
public abstract class MovieListModule {
    @ContributesAndroidInjector(modules = {
            ViewModelBuilder.class
    })
    abstract MovieListFragment movieListFragment();

    @Binds
    @IntoMap
    @ViewModelKey(MovieListViewModel.class)
    abstract ViewModel bindViewModel(MovieListViewModel viewmodel);
}
