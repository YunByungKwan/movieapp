package ybk.org.movieapp.di;

import androidx.lifecycle.SavedStateViewModelFactory;
import androidx.lifecycle.ViewModelProvider;

import dagger.Binds;
import dagger.Module;

@Module
public abstract class ViewModelBuilder {
    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(
            ViewModelFactory factory
    );


}
