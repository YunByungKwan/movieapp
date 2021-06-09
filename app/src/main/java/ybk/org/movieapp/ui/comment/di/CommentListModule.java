package ybk.org.movieapp.ui.comment.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.comment.CommentListViewModel;

@Module
public abstract class CommentListModule {
    @Binds
    @IntoMap
    @ViewModelKey(CommentListViewModel.class)
    abstract  ViewModel bindViewModel(CommentListViewModel viewmodel);
}
