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

@Module
public abstract class CommentListModule {
    @ContributesAndroidInjector(modules = {
            ViewModelBuilder.class
    })
    abstract CommentListActivity commentListActivity();

    @Binds
    @IntoMap
    @ViewModelKey(CommentListViewModel.class)
    abstract  ViewModel bindViewModel(CommentListViewModel viewmodel);
}
