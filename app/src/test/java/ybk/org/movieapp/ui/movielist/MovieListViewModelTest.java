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
    public MovieRepositoryImpl repository;

    public MovieListViewModel viewModel;

    public MovieResponse response;
    public List<Movie> movieList;

    @Before
    public void setUp() {
        viewModel = new MovieListViewModel(repository);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__->Schedulers.trampoline());

        response = new MovieResponse();
        movieList = new ArrayList<>();
        response.setResult(movieList);

        mocking();
    }

    private void mocking() {
        Mockito.when(repository.getMovieList())
                .thenReturn(Single.just(response));
    }

    @Test
    public void testIfLiveDataHasValue() throws InterruptedException {
        viewModel.getMovieList();
        TestUtil.getOrAwaitValue(viewModel.movieList);
        assertThat(viewModel.movieList.getValue(), is(response.getResult()));
    }

    @Test
    public void testIfRepoGetMovieListIsCalled() {
        viewModel.getMovieList();
        Mockito.verify(repository).getMovieList();
    }

    @Test
    public void testIfRepoInsertMovieListToRoomIsCalled() throws InterruptedException {
        Mockito.when(repository.insertMovieListToRoom(movieList))
                .thenReturn(Completable.complete());
        viewModel.getMovieList();
        TestUtil.getOrAwaitValue(viewModel.movieList);
        Mockito.verify(repository).insertMovieListToRoom(movieList);
    }

    @Test
    public void testRepoGetMovieList() {
        repository
                .getMovieList()
                .test()
                .assertValue(response)
                .assertComplete()
                .assertNoErrors();
    }

    @Test(expected = RuntimeException.class)
    public void testRuntimeExOccursWhenGetMovieListIsCalled() {
        Mockito.when(repository.getMovieList())
                .thenThrow(RuntimeException.class);
        viewModel.getMovieList();
    }
}