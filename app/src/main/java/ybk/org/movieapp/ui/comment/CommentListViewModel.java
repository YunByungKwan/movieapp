package ybk.org.movieapp.ui.comment;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ybk.org.movieapp.base.BaseViewModel;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;

public class CommentListViewModel extends BaseViewModel {

    public MutableLiveData<List<Comment>> commentList = new MutableLiveData<>();
    private MovieRepositoryImpl repository;

    @Inject
    public CommentListViewModel(MovieRepositoryImpl repository) {
        this.repository = repository;
    }

    public void getCommentList(int id) {
        repository.getCommentList(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        new SingleObserver<CommentResponse>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                            }

                            @Override
                            public void onSuccess(@NonNull CommentResponse commentResponse) {
                                List<Comment> _commentList = commentResponse.getResult();
                                commentList.postValue(_commentList);
                                repository.insertCommentListToRoom(_commentList);
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                            }
                        }
                );
    }

    public void addComment(HashMap<String, Object> comment) {
        repository.addComment(comment)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe();
    }

    public void recommendComment(HashMap<String, Object> param) {
        repository.recommendComment(param)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();
    }
}
