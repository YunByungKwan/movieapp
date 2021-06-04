package ybk.org.movieapp.di.module;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ybk.org.movieapp.data.local.DataSource;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.di.ApplicationModuleBinds;

@Module(includes = {ApplicationModuleBinds.class})
public class ApplicationModule {
    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MovieRemoteDataSource {}

    @Qualifier
    @Retention(RetentionPolicy.RUNTIME)
    public @interface MovieLocalDataSource {}

    @Singleton
    @MovieRemoteDataSource
    @Provides
    public DataSource provideTasksRemoteDataSource() {
        return new RemoteDataSource();
    }

    @Singleton
    @MovieLocalDataSource
    @Provides
    public DataSource provideTasksLocalDataSource() {
        return new LocalDataSource();
    }
}
