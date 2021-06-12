package ybk.org.movieapp.data;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import ybk.org.movieapp.data.local.LocalDataSourceImpl;
import ybk.org.movieapp.data.remote.RemoteDataSourceImpl;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryImplTest {

    @Mock
    public LocalDataSourceImpl localDataSource;

    @Mock
    public RemoteDataSourceImpl remoteDataSource;

    public MovieRepositoryImpl repository;

    @Before
    public void setUp() {
        repository = new MovieRepositoryImpl(localDataSource, remoteDataSource);
    }

    @Test
    public void test() {

    }
}