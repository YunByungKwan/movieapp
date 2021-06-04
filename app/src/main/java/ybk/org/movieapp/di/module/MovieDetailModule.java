package ybk.org.movieapp.di.module;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelBuilder;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.comment.CommentListActivity;
import ybk.org.movieapp.ui.comment.CommentListViewModel;
import ybk.org.movieapp.ui.moviedetail.MovieDetailFragment;
import ybk.org.movieapp.ui.moviedetail.MovieDetailViewModel;

@Module
public abstract class MovieDetailModule {
    @ContributesAndroidInjector(modules = {
            ViewModelBuilder.class
    })
    abstract MovieDetailFragment movieDetailFragment();

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailViewModel.class)
    abstract ViewModel bindViewModel(MovieDetailViewModel viewmodel);
}
