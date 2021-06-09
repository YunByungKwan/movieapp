package ybk.org.movieapp.ui.moviebook.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.moviebook.MovieBookFragment;

@Subcomponent(modules = {MovieBookModule.class})
public interface MovieBookComponent {
    @Subcomponent.Factory
    interface Factory {
        public MovieBookComponent create();
    }

    MovieBookFragment inject(MovieBookFragment fragment);
}