package ybk.org.movieapp.ui.moviebook;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import ybk.org.movieapp.R;
import ybk.org.movieapp.util.App;

public class MovieBookViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MovieBookViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue(App.getInstance().getString(R.string.msg_service_not_ready));
    }

    public LiveData<String> getText() {
        return mText;
    }
}