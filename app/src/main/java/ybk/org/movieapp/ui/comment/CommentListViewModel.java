package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.SingleObserver;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepository;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.util.Dlog;

public class CommentListViewModel extends ViewModel {

    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    private MovieRepository repository;

    public CommentListViewModel(MovieRepository repository, int id) {
        this.repository = repository;
        getCommentList(id);
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
}
