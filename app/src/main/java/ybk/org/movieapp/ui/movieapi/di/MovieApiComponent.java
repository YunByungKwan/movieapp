package ybk.org.movieapp.ui.movieapi.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.movieapi.MovieApiFragment;

@Subcomponent(modules = {MovieApiModule.class})
public interface MovieApiComponent {
    @Subcomponent.Factory
    interface Factory {
        public MovieApiComponent create();
    }

    MovieApiFragment inject(MovieApiFragment fragment);
}
