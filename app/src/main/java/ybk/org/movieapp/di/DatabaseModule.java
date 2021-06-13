package ybk.org.movieapp.di;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import ybk.org.movieapp.data.local.dao.MovieDao;
import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.util.App;

@Module
public class DatabaseModule {
    @Provides
    public MovieDatabase provideMovieDatabase() {
        return MovieDatabase.getInstance(App.getInstance());
    }

    @Provides
    public ExecutorService provideExecutorService() {
        return Executors.newFixedThreadPool(4);
    }
}
