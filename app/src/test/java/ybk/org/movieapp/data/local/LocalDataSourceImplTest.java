package ybk.org.movieapp.data.local;

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
import java.util.concurrent.ExecutorService;

import ybk.org.movieapp.data.local.db.MovieDatabase;
import ybk.org.movieapp.data.local.entity.Movie;

@RunWith(MockitoJUnitRunner.class)
public class LocalDataSourceImplTest {

    @Rule
    public TestRule rule = new InstantTaskExecutorRule();

    @Mock
    private MovieDatabase db;

    @Mock
    private ExecutorService executorService;

    private LocalDataSourceImpl local;
    private List<Movie> movieList;

    @Before
    public void setUp() {
        local = new LocalDataSourceImpl(db, executorService);
        movieList = new ArrayList<>();
        mocking();
    }

    private void mocking() {

    }

    @Test
    public void testIfDaoGetMovieListIsCalled() {
        local.getMovieList();
    }
}