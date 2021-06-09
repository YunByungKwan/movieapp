package ybk.org.movieapp.di;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.LocalDataSourceImpl;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.data.remote.RemoteDataSourceImpl;

@Module
public abstract class ApplicationModule {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MovieRemoteDataSource {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MovieLocalDataSource {}

    @Singleton
    @MovieRemoteDataSource
    @Binds
    abstract public RemoteDataSource bindMovieRemoteDataSource(
            RemoteDataSourceImpl remoteDataSource
    );

    @Singleton
    @MovieLocalDataSource
    @Binds
    abstract public LocalDataSource bindMovieLocalDataSource(
            LocalDataSourceImpl localDataSource
    );
}
