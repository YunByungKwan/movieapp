package ybk.org.movieapp.ui.moviedetail.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.moviedetail.MovieDetailFragment;

@Subcomponent(modules = {MovieDetailModule.class})
public interface MovieDetailComponent {
    @Subcomponent.Factory
    interface Factory {
        MovieDetailComponent create();
    }

    MovieDetailFragment inject(MovieDetailFragment fragment);
}
