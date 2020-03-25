package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public MovieListViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is movie list fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}