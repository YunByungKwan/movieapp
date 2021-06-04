package ybk.org.movieapp.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelBuilder;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.moviebook.MovieBookFragment;
import ybk.org.movieapp.ui.moviebook.MovieBookViewModel;

@Module
public abstract class MovieBookModule {
    @ContributesAndroidInjector(modules = {
            ViewModelBuilder.class
    })
    abstract MovieBookFragment movieBookFragment();

    @Binds
    @IntoMap
    @ViewModelKey(MovieBookViewModel.class)
    abstract ViewModel bindViewModel(MovieBookViewModel viewmodel);
}
