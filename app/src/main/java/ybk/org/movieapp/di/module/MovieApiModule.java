package ybk.org.movieapp.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelBuilder;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.movieapi.MovieApiFragment;
import ybk.org.movieapp.ui.movieapi.MovieApiViewModel;

@Module
public abstract class MovieApiModule {
    @ContributesAndroidInjector(modules = {
            ViewModelBuilder.class
    })
    abstract MovieApiFragment movieApiFragment();

    @Binds
    @IntoMap
    @ViewModelKey(MovieApiViewModel.class)
    abstract ViewModel bindViewModel(MovieApiViewModel viewmodel);
}
