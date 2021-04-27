package ybk.org.movieapp.ui.movielist;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.util.Dlog;

public class MovieListViewModel extends ViewModel {

    private MutableLiveData<List<Movie>> _movieList = new MutableLiveData<>();
    public LiveData<List<Movie>> movieList = _movieList;
    private final MovieRepository repository;

    public MovieListViewModel(MovieRepository repository) {
        this.repository = repository;
        getMovieList();
    }

    public void getMovieList() {
        repository.getMovieList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MovieResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull MovieResponse movieResponse) {
                        Dlog.d("=========> onSuccess()");
                        _movieList.postValue(movieResponse.getResult());
                        repository.insertMovieListToRoom(movieResponse.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.e("=========> onError()");
                        Dlog.e("=========> " + e.getMessage());
                    }
                });
    }
}