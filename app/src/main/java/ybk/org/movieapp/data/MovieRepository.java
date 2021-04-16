package ybk.org.movieapp.data;

import androidx.lifecycle.MutableLiveData;

import java.util.HashMap;
import java.util.List;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ybk.org.movieapp.data.local.LocalDataSource;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResult;
import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.data.local.entity.DetailMovieResult;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResult;
import ybk.org.movieapp.data.local.entity.Response;
import ybk.org.movieapp.data.remote.RemoteDataSource;
import ybk.org.movieapp.util.Dlog;

public class MovieRepository {

    private LocalDataSource localDataSource = LocalDataSource.getInstance();
    private RemoteDataSource remoteDataSource = RemoteDataSource.getInstance();
    private volatile static MovieRepository instance;

    private MovieRepository() {}

    public static MovieRepository getInstance() {
        if(instance == null) {
            synchronized (MovieRepository.class) {
                if(instance == null) {
                    instance = new MovieRepository();
                }
            }
        }
        return instance;
    }

    public void getMovieList(MutableLiveData<List<Movie>> movieList) {
        Dlog.d("=========> Call getMovieList()");

        remoteDataSource.getMovieList()
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new SingleObserver<MovieResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull MovieResult movieResult) {
                                Dlog.d("=========> onSuccess()");
                                movieList.postValue(movieResult.getResult());
                                if(movieResult.getResult().size() != 0) {
                                    // 통신 성공시 Room에 저장
                                    localDataSource.insertMovieList(movieResult.getResult());
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.e("=========> onError()");
                                // Room에서 데이터를 불러온다
                                localDataSource.getMovieList(movieList);
                            }
                        }
                );
    }

    public void getDetailMovie(
            MutableLiveData<List<DetailMovie>> detailMovie, final int id
    ) {
        remoteDataSource.getDetailMovie(id)
                .subscribeOn(Schedulers.io())
                .subscribe(
                        new SingleObserver<DetailMovieResult>() {
                            @Override
                            public void onSubscribe(@NonNull Disposable d) {
                                Dlog.d("=========> onSubscribe()");
                            }

                            @Override
                            public void onSuccess(@NonNull DetailMovieResult detailMovieResult) {
                                Dlog.d("=========> onSuccess()");
                                detailMovie.postValue(detailMovieResult.getResult());
                                if(detailMovieResult.getResult().size() != 0) {
                                    // 통신 성공시 Room에 저장
                                    localDataSource.insertDetailMovie(detailMovieResult.getResult());
                                }
                            }

                            @Override
                            public void onError(@NonNull Throwable e) {
                                Dlog.d("=========> onError()");
                                // Room에서 데이터를 불러온다
                                localDataSource.getDetailMovie(detailMovie, id);
                            }
                        });
    }

    public void getCommentList(
            MutableLiveData<List<Comment>> commentList, final int id
    ) {
        remoteDataSource.getCommentList(id)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<CommentResult>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull CommentResult commentResult) {
                        Dlog.d("=========> onSuccess()");
                        commentList.postValue(commentResult.getResult());
                        if(commentResult.getResult().size() != 0) {
                            localDataSource.insertCommentList(commentResult.getResult());
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.e("=========> onError()");
                        localDataSource.getCommentList(commentList, id);
                    }
                });
    }

    public void addComment(HashMap<String, Object> comment) {
        remoteDataSource.addComment(comment)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        Dlog.d("=========> onSuccess()");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.d("=========> onError()");
                    }
                });
    }

    public void addLikeDisLike(HashMap<String, Object> count) {
        remoteDataSource.addLikeDisLike(count)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        Dlog.d("=========> onSuccess()");
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.d("=========> onError()");
                    }
                });
    }

    public void recommendComment(HashMap<String, Object> recommend) {
        remoteDataSource.recommendComment(recommend)
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleObserver<Response>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        Dlog.d("=========> onSubscribe()");
                    }

                    @Override
                    public void onSuccess(@NonNull Response response) {
                        Dlog.d("=========> onSuccess()");
                    }


                    @Override
                    public void onError(@NonNull Throwable e) {
                        Dlog.d("=========> onError()");
                    }
                });
    }
}
