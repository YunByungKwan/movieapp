package ybk.org.movieapp.ui.moviedetail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Dlog;

public class MovieDetailViewModel extends BaseViewModel {

    private MutableLiveData<List<DetailMovie>> _detailMovie = new MutableLiveData<>();
    public LiveData<List<DetailMovie>> detailMovie = _detailMovie;

    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    private MovieRepositoryImpl repository;

    @Inject
    public MovieDetailViewModel(MovieRepositoryImpl repository) {
        this.repository = repository;
    }

    public void getDetailMovie(int movieId) {
        repository.getDetailMovie(movieId)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                new SingleObserver<DetailMovieResponse>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull DetailMovieResponse detailMovieResponse) {
                        Dlog.d("=========> onSuccess()");
                        _detailMovie.postValue(detailMovieResponse.getResult());
                        repository.insertDetailMovieToRoom(detailMovieResponse.getResult());
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.e("=========> onError()");
                        Dlog.e("=========> " + e.getMessage());
                    }
                }
        );
    }

    public void getCommentList(int id) {
        repository.getCommentList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<CommentResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull CommentResponse commentResponse) {
                                Dlog.d("=========> onSuccess()");
                                List<Comment> _commentList = commentResponse.getResult();
                                commentList.postValue(_commentList);
                                repository.insertCommentListToRoom(_commentList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.e("=========> onError()");
                                Dlog.e("=========> " + e.getMessage());
                            }
                        }
                );
    }

    public void addComment(HashMap<String, Object> comment) {
        repository.addComment(comment)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<Response>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull Response response) {
                                Dlog.d("=========> onSuccess()");
                                Dlog.d("=========> Response: " + response.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.e("=========> onError()");
                                Dlog.e("=========> " + e.getMessage());
                            }
                        }
                );
    }

    public void recommendComment(HashMap<String, Object> param) {
        repository.recommendComment(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<Response>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull Response response) {
                                Dlog.d("=========> onSuccess()");
                                Dlog.d("=========> Response:  " + response.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.e("=========> onError()");
                                Dlog.e("=========> " + e.getMessage());
                            }
                        }
                );
    }

    public void addLikeDisLike(HashMap<String, Object> param) {
        repository.addLikeDisLike(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<Response>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull Response response) {
                                Dlog.d("=========> onSuccess()");
                                Dlog.d("=========> Response:  " + response.toString());
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.e("=========> onError()");
                                Dlog.e("=========> " + e.getMessage());
                            }
                        }
                );
    }
}
