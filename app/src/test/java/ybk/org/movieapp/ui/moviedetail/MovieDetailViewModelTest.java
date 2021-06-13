package ybk.org.movieapp.ui.moviedetail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Response;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepositoryImpl repo;

    private MovieDetailViewModel vm;

    private DetailMovieResponse detailMovieRes;
    private CommentResponse commentRes;
    private Response res;

    private final int id = 0;
    private HashMap<String, Object> param;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __-> Schedulers.trampoline());
        vm = new MovieDetailViewModel(repo);
        detailMovieRes = new DetailMovieResponse();
        commentRes = new CommentResponse();
        res = new Response();
        param = new HashMap<>();
        mocking();
    }

    private void mocking() {
        Mockito.when(repo.getDetailMovie(id)).thenReturn(Single.just(detailMovieRes));
        Mockito.when(repo.getCommentList(id)).thenReturn(Single.just(commentRes));
        Mockito.when(repo.addComment(param)).thenReturn(Completable.complete());
        Mockito.when(repo.recommendComment(param)).thenReturn(Completable.complete());
        Mockito.when(repo.addLikeDisLike(param)).thenReturn(Completable.complete());
    }

    @Test
    public void testIfRepoGetDetailMovieIsCalled() {
        vm.getDetailMovie(id);
        Mockito.verify(repo).getDetailMovie(id);
    }

    @Test
    public void testIfRepoGetCommentListIsCalled() {
        vm.getCommentList(id);
        Mockito.verify(repo).getCommentList(id);
    }

    @Test
    public void testIfRepoAddCommentIsCalled() {
        vm.addComment(param);
        Mockito.verify(repo).addComment(param);
    }

    @Test
    public void testIfRepoRecommendCommentIsCalled() {
        vm.recommendComment(param);
        Mockito.verify(repo).recommendComment(param);
    }

    @Test
    public void testIfRepoAddLikeDisLikeIsCalled() {
        vm.addLikeDisLike(param);
        Mockito.verify(repo).addLikeDisLike(param);
    }

    @Test
    public void testRepoGetDetailMovie() {
        repo.getDetailMovie(id)
                .test()
                .assertValue(detailMovieRes)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoGetCommentList() {
        repo.getCommentList(id)
                .test()
                .assertValue(commentRes)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddComment() {
        repo.addComment(param)
                .test()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoRecommendComment() {
        repo.recommendComment(param)
                .test()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddLikeDisLike() {
        repo.addLikeDisLike(param)
                .test()
                .assertComplete()
                .assertNoErrors();
    }
}