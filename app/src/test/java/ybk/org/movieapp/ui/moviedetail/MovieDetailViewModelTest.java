package ybk.org.movieapp.ui.moviedetail;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.Response;

@RunWith(MockitoJUnitRunner.class)
public class MovieDetailViewModelTest {

    @Mock
    public MovieRepositoryImpl repository;

    public MovieDetailViewModel viewModel;

    public final int id = 0;
    public DetailMovieResponse detailMovieRes;
    public CommentResponse commentRes;
    public Response res;
    public HashMap<String, Object> hashMap;

    @Before
    public void setUp() {
        viewModel = new MovieDetailViewModel(repository);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__-> Schedulers.trampoline());

        detailMovieRes = new DetailMovieResponse();
        commentRes = new CommentResponse();
        res = new Response();
        hashMap = new HashMap<>();

        mocking();
    }

    private void mocking() {
        Mockito.when(repository.getDetailMovie(id))
                .thenReturn(Single.just(detailMovieRes));
        Mockito.when(repository.getCommentList(id))
                .thenReturn(Single.just(commentRes));
        Mockito.when(repository.addComment(hashMap))
                .thenReturn(Single.just(res));
        Mockito.when(repository.recommendComment(hashMap))
                .thenReturn(Single.just(res));
        Mockito.when(repository.addLikeDisLike(hashMap))
                .thenReturn(Single.just(res));
    }

    @Test
    public void testIfRepoGetDetailMovieIsCalled() {
        viewModel.getDetailMovie(id);

        Mockito.verify(repository).getDetailMovie(id);
    }

    @Test
    public void testIfRepoGetCommentListIsCalled() {
        viewModel.getCommentList(id);

        Mockito.verify(repository).getCommentList(id);
    }

    @Test
    public void testIfRepoAddCommentIsCalled() {
        viewModel.addComment(hashMap);

        Mockito.verify(repository).addComment(hashMap);
    }

    @Test
    public void testIfRepoRecommendCommentIsCalled() {
        viewModel.recommendComment(hashMap);

        Mockito.verify(repository).recommendComment(hashMap);
    }

    @Test
    public void testIfRepoAddLikeDisLikeIsCalled() {
        viewModel.addLikeDisLike(hashMap);

        Mockito.verify(repository).addLikeDisLike(hashMap);
    }

    @Test
    public void testRepoGetDetailMovie() {
        repository
                .getDetailMovie(id)
                .test()
                .assertValue(detailMovieRes)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoGetCommentList() {
        repository
                .getCommentList(id)
                .test()
                .assertValue(commentRes)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddComment() {
        repository
                .addComment(hashMap)
                .test()
                //.assertValue()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoRecommendComment() {
        repository
                .recommendComment(hashMap)
                .test()
                .assertValue(res)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddLikeDisLike() {
        repository
                .addLikeDisLike(hashMap)
                .test()
                .assertValue(res)
                .assertComplete()
                .assertNoErrors();
    }
}