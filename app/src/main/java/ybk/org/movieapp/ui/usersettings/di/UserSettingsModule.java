package ybk.org.movieapp.ui.usersettings.di;

import androidx.lifecycle.ViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import ybk.org.movieapp.di.ViewModelKey;
import ybk.org.movieapp.ui.usersettings.UserSettingsViewModel;

@Module
public abstract class UserSettingsModule {
    @Binds
    @IntoMap
    @ViewModelKey(UserSettingsViewModel.class)
    abstract ViewModel bindViewModel(UserSettingsViewModel viewmodel);
}