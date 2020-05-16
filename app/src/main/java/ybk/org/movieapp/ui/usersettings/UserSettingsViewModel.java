package ybk.org.movieapp.ui.usersettings;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserSettingsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public UserSettingsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("서비스 준비중입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}