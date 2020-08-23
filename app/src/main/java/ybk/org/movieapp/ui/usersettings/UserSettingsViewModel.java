package ybk.org.movieapp.ui.usersettings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import ybk.org.movieapp.R;
import ybk.org.movieapp.util.App;

public class UserSettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserSettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(App.getInstance().getString(R.string.msg_service_not_ready));
    }

    public LiveData<String> getText() {
        return mText;
    }
}