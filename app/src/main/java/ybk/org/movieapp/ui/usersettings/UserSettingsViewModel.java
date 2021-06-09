package ybk.org.movieapp.ui.usersettings;

import javax.inject.Inject;

import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepositoryImpl;

public class UserSettingsViewModel extends BaseViewModel {

    private MovieRepositoryImpl repository;

    @Inject
    public UserSettingsViewModel(MovieRepositoryImpl repository) {
        this.repository = repository;
    }
}
