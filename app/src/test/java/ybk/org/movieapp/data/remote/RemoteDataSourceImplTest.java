package ybk.org.movieapp.data.remote;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;

@RunWith(MockitoJUnitRunner.class)
public class RemoteDataSourceImplTest {

    @Mock
    private ApiService apiService;

    private RemoteDataSourceImpl remote;
    private MovieResponse movieRes;
    private DetailMovieResponse detailMovieRes;
    private CommentResponse commentRes;
    private HashMap<String, Object> param;
    private int id;
    @Before
    public void setUp() {
        remote = new RemoteDataSourceImpl(apiService);
        movieRes = new MovieResponse();
        detailMovieRes = new DetailMovieResponse();
        commentRes = new CommentResponse();
        param = new HashMap<>();
        id = 0;
        mocking();
    }

    private void mocking() {
        Mockito.when(apiService.getMovieList()).thenReturn(Single.just(movieRes));
        Mockito.when(apiService.getDetailMovie(id)).thenReturn(Single.just(detailMovieRes));
        Mockito.when(apiService.getCommentList(id)).thenReturn(Single.just(commentRes));
        Mockito.when(apiService.addComment(param)).thenReturn(Completable.complete());
        Mockito.when(apiService.addLikeDisLike(param)).thenReturn(Completable.complete());
        Mockito.when(apiService.recommendComment(param)).thenReturn(Completable.complete());
    }

    @Test
    public void testIfApiServiceGetMovieListIsCalled() {
        remote.getMovieList();
        Mockito.verify(apiService).getMovieList();
    }

    @Test
    public void testIfApiServiceGetDetailMovieIsCalled() {
        remote.getDetailMovie(id);
        Mockito.verify(apiService).getDetailMovie(id);
    }

    @Test
    public void testIfApiServiceGetCommentListIsCalled() {
        remote.getCommentList(id);
        Mockito.verify(apiService).getCommentList(id);
    }

    @Test
    public void testIfApiServiceAddCommentIsCalled() {
        remote.addComment(param);
        Mockito.verify(apiService).addComment(param);
    }

    @Test
    public void testIfApiServiceAddLikeDisLikeIsCalled() {
        remote.addLikeDisLike(param);
        Mockito.verify(apiService).addLikeDisLike(param);
    }

    @Test
    public void testIfApiServiceRecommendCommentIsCalled() {
        remote.recommendComment(param);
        Mockito.verify(apiService).recommendComment(param);
    }
}