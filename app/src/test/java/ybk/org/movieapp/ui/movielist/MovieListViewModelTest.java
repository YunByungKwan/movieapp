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

import io.reactivex.rxjava3.android.plugins.RxAndroidPlugins;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.exceptions.Exceptions;
import io.reactivex.rxjava3.schedulers.Schedulers;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.MovieResponse;

@RunWith(MockitoJUnitRunner.class)
public class MovieListViewModelTest {

    @Mock
    public MovieRepositoryImpl repository;

    public MovieListViewModel viewModel;

    public MovieResponse response;

    @Before
    public void setUp() {
        viewModel = new MovieListViewModel(repository);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(__->Schedulers.trampoline());

        response = new MovieResponse();
    }

    @Test
    public void testIfRepoGetMovieListIsCalled() {
        Mockito.when(repository.getMovieList())
                .thenReturn(Single.just(response));

        viewModel.getMovieList();
        Mockito.verify(repository).getMovieList();
    }

    @Test
    public void testRepoGetMovieList() {
        Mockito.when(repository.getMovieList())
                .thenReturn(Single.just(response));

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