package ybk.org.movieapp.ui.usersettings.di;

import dagger.Subcomponent;
import ybk.org.movieapp.ui.usersettings.UserSettingsFragment;

@Subcomponent(modules = {UserSettingsModule.class})
public interface UserSettingsComponent {
    @Subcomponent.Factory
    interface Factory {
        UserSettingsComponent create();
    }

    UserSettingsFragment inject(UserSettingsFragment fragment);
}