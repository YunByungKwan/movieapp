package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;

public class MovieListViewModel extends BaseViewModel {

    private MutableLiveData<List<Movie>> _movieList = new MutableLiveData<>();
    public LiveData<List<Movie>> movieList = _movieList;
    private final MovieRepository repository;

    @Inject
    public MovieListViewModel(MovieRepository repository) {
        this.repository = repository;
    }

    public void getMovieList() {
        repository.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                    }

                    @Override
                    public void onSuccess(@NonNull MovieResponse movieResponse) {
                        _movieList.setValue(movieResponse.getResult());
                        repository.insertMovieListToRoom(movieResponse.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                    }
                });
    }
}
