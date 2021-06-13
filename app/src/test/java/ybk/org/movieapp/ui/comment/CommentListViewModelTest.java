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
    private MovieRepositoryImpl repo;

    private CommentListViewModel vm;

    private final int id = 0;
    private CommentResponse commentRes;
    private List<Comment> commentList;
    private HashMap<String, Object> hashMap;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __-> Schedulers.trampoline());
        vm = new CommentListViewModel(repo);
        commentRes = new CommentResponse();
        commentList = new ArrayList();
        commentRes.setResult(commentList);
        hashMap = new HashMap();
        mocking();
    }

    private void mocking() {
        Mockito.when(repo.getCommentList(id)).thenReturn(Single.just(commentRes));
        Mockito.when(repo.addComment(hashMap)).thenReturn(Completable.complete());
        Mockito.when(repo.recommendComment(hashMap)).thenReturn(Completable.complete());
    }

    @Test
    public void testIfLiveDataHasValue() throws InterruptedException {
        vm.getCommentList(id);
        TestUtil.getOrAwaitValue(vm.commentList);
        assertThat(vm.commentList.getValue(), is(commentRes.getResult()));
    }

    @Test
    public void testIfRepoGetCommentListIsCalled() {
        vm.getCommentList(id);
        Mockito.verify(repo).getCommentList(id);
    }

    @Test
    public void testIfRepoAddCommentIsCalled() {
        vm.addComment(hashMap);
        Mockito.verify(repo).addComment(hashMap);
    }

    @Test
    public void testIfRepoRecommendCommentIsCalled() {
        vm.recommendComment(hashMap);
        Mockito.verify(repo).recommendComment(hashMap);
    }

    @Test
    public void testIfRepoInsertMovieListToRoomIsCalled() throws InterruptedException {
        Mockito.when(repo.insertCommentListToRoom(commentList))
                .thenReturn(Completable.complete());

        vm.getCommentList(id);
        TestUtil.getOrAwaitValue(vm.commentList);
        Mockito.verify(repo).insertCommentListToRoom(commentList);
    }

    @Test
    public void testRepoGetCommentList() {
        vm.getCommentList(id);
        repo.getCommentList(id)
                .test()
                .assertValue(commentRes)
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoAddComment() {
        vm.addComment(hashMap);
        repo.addComment(hashMap)
                .test()
                .assertComplete()
                .assertNoErrors();
    }

    @Test
    public void testRepoRecommendComment() {
        vm.recommendComment(hashMap);
        repo.recommendComment(hashMap)
                .test()
                .assertComplete()
                .assertNoErrors();
    }
}