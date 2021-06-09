package ybk.org.movieapp.ui.movielist.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.movielist.MovieListFragment;

@Subcomponent(modules = {MovieListModule.class})
public interface MovieListComponent {
    @Subcomponent.Factory
    interface Factory {
        MovieListComponent create();
    }

    MovieListFragment inject(MovieListFragment fragment);
}
