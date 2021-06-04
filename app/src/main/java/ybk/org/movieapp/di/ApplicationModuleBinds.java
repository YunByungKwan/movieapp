package ybk.org.movieapp.di;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.MovieRepositoryImpl;

@Module
public abstract class ApplicationModuleBinds {
    @Singleton
    @Binds
    abstract MovieRepository bindRepository(MovieRepositoryImpl repository);
}
