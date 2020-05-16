package ybk.org.movieapp.ui.moviebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieBookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MovieBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("서비스 준비중입니다.");
    }

    public LiveData<String> getText() {
        return mText;
    }
}