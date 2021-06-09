package ybk.org.movieapp.di;

import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelBuilderModule {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(
            ViewModelFactory factory
    );
}
