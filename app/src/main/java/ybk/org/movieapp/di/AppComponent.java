package ybk.org.movieapp.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ybk.org.movieapp.ui.comment.di.CommentListComponent;
import ybk.org.movieapp.ui.movieapi.di.MovieApiComponent;
import ybk.org.movieapp.ui.moviebook.di.MovieBookComponent;
import ybk.org.movieapp.ui.moviedetail.di.MovieDetailComponent;
import ybk.org.movieapp.ui.movielist.di.MovieListComponent;
import ybk.org.movieapp.util.App;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        ApplicationModuleBinds.class,
        ViewModelBuilderModule.class,
        NetworkModule.class,
        AndroidSupportInjectionModule.class,
        SubcomponentsModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context applicationContext);
    }

    CommentListComponent.Factory commentListComponent();
    MovieDetailComponent.Factory movieDetailComponent();
    MovieListComponent.Factory movieListComponent();
    MovieApiComponent.Factory movieApiComponent();
    MovieBookComponent.Factory movieBookComponent();
}

@Module(subcomponents = {
        CommentListComponent.class,
        MovieDetailComponent.class,
        MovieListComponent.class,
        MovieApiComponent.class,
        MovieBookComponent.class
})
class SubcomponentsModule{}

