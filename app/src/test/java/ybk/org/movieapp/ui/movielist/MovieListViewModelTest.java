package ybk.org.movieapp.ui.movielist;

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
import java.util.List;

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.ui.TestUtil;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class MovieListViewModelTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepositoryImpl repo;

    private MovieListViewModel vm;

    private MovieResponse movieRes;
    private List<Movie> movieList;

    @Before
    public void setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(
                __->Schedulers.trampoline());
        vm = new MovieListViewModel(repo);
        movieRes = new MovieResponse();
        movieList = new ArrayList<>();
        movieRes.setResult(movieList);
        mocking();
    }

    private void mocking() {
        Mockito.when(repo.getMovieList()).thenReturn(Single.just(movieRes));
    }

    @Test
    public void testIfLiveDataHasValue() throws InterruptedException {
        vm.getMovieList();
        TestUtil.getOrAwaitValue(vm.movieList);
        assertThat(vm.movieList.getValue(), is(movieRes.getResult()));
    }

    @Test
    public void testIfRepoGetMovieListIsCalled() {
        vm.getMovieList();
        Mockito.verify(repo).getMovieList();
    }

    @Test
    public void testIfRepoInsertMovieListToRoomIsCalled() throws InterruptedException {
        Mockito.when(repo.insertMovieListToRoom(movieList))
                .thenReturn(Completable.complete());
        vm.getMovieList();
        TestUtil.getOrAwaitValue(vm.movieList);
        Mockito.verify(repo).insertMovieListToRoom(movieList);
    }

    @Test
    public void testRepoGetMovieList() {
        repo.getMovieList()
                .test()
                .assertValue(movieRes)
                .assertComplete()
                .assertNoErrors();
    }
}