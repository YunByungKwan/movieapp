package ybk.org.movieapp.di.component;

import android.content.Context;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import ybk.org.movieapp.di.module.ApplicationModule;
import ybk.org.movieapp.di.module.CommentListModule;
import ybk.org.movieapp.di.module.MovieApiModule;
import ybk.org.movieapp.di.module.MovieBookModule;
import ybk.org.movieapp.di.module.MovieDetailModule;
import ybk.org.movieapp.di.module.MovieListModule;
import ybk.org.movieapp.util.App;

@Singleton
@Component(modules = {
        ApplicationModule.class,
        AndroidSupportInjectionModule.class,
        CommentListModule.class,
        MovieDetailModule.class,
        MovieListModule.class,
        MovieApiModule.class,
        MovieBookModule.class
})
public interface AppComponent extends AndroidInjector<App> {

    @Component.Factory
    interface Factory {
        AppComponent create(@BindsInstance Context applicationContext);
    }
}
