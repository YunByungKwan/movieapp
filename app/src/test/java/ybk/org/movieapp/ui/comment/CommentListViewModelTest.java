package ybk.org.movieapp.ui.comment;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.Comment;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.ui.TestUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class CommentListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    public MovieRepositoryImpl repository;

    public CommentListViewModel viewModel;

    public int id = 0;
    public CommentResponse response;
    public List<Comment> commentList;
    public HashMap<String, Object> hashMap;

    @Before
    public void setUp() {
        viewModel = new CommentListViewModel(repository);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__-> Schedulers.trampoline());

        response = new CommentResponse();
        commentList = new ArrayList();
        response.setResult(commentList);
        hashMap = new HashMap();
        mocking();
    }

    private void mocking() {
        Mockito.when(repository.getCommentList(id))
                .thenReturn(Single.just(response));
        Mockito.when(repository.addComment(hashMap))
                .thenReturn(Completable.complete());
        Mockito.when(repository.recommendComment(hashMap))
                .thenReturn(Completable.complete());
    }

    @Test
    public void testIfLiveDataHasValue() throws InterruptedException {
        viewModel.getCommentList(id);
        TestUtil.getOrAwaitValue(viewModel.commentList);
        assertThat(viewModel.commentList.getValue(), is(response.getResult()));
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
    public void testIfRepoInsertMovieListToRoomIsCalled() throws InterruptedException {
        Mockito.when(repository.insertCommentListToRoom(commentList))
                .thenReturn(Completable.complete());

        viewModel.getCommentList(id);
        TestUtil.getOrAwaitValue(viewModel.commentList);
        Mockito.verify(repository).insertCommentListToRoom(commentList);
    }

    @Test
    public void testRepoGetCommentList() {
        viewModel.getCommentList(id);

        repository.getCommentList(id)
                .test()
                .assertValue(response)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddComment() {
        viewModel.addComment(hashMap);

        repository.addComment(hashMap)
                .test()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoRecommendComment() {
        viewModel.recommendComment(hashMap);

        repository.recommendComment(hashMap)
                .test()
                .assertComplete()
                .assertNoErrors();
    }
}