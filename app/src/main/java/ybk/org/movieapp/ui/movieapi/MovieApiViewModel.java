package ybk.org.movieapp.ui.movieapi;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieApiViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MovieApiViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("서비스 준비중입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}