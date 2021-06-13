package ybk.org.movieapp.data;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.HashMap;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import ybk.org.movieapp.data.local.LocalDataSourceImpl;
import ybk.org.movieapp.data.local.entity.CommentResponse;
import ybk.org.movieapp.data.local.entity.DetailMovieResponse;
import ybk.org.movieapp.data.local.entity.MovieResponse;
import ybk.org.movieapp.data.remote.RemoteDataSourceImpl;
import ybk.org.movieapp.util.Network;

import static org.mockito.Mockito.mockStatic;

@RunWith(MockitoJUnitRunner.class)
public class MovieRepositoryImplTest {
    private static MockedStatic<Network> mNetwork;

    @Mock
    public LocalDataSourceImpl localDataSource;

    @Mock
    public RemoteDataSourceImpl remoteDataSource;

    public MovieRepositoryImpl repository;
    private MovieResponse movieRes;
    private DetailMovieResponse detailMovieRes;
    private CommentResponse commentRes;
    private HashMap<String, Object> param;
    private int id;

    @BeforeClass
    public static void beforeClass() {
        mNetwork = mockStatic(Network.class);
    }

    @AfterClass
    public static void afterClass() {
        mNetwork.close();
    }

    @Before
    public void setUp() {
        repository = new MovieRepositoryImpl(localDataSource, remoteDataSource);
        movieRes = new MovieResponse();
        detailMovieRes = new DetailMovieResponse();
        commentRes = new CommentResponse();
        param = new HashMap<>();
        id = 0;

        mocking();
    }

    private void mocking() {
        Mockito.when(localDataSource.getMovieList())
                .thenReturn(Single.just(movieRes));
        Mockito.when(remoteDataSource.getMovieList())
                .thenReturn(Single.just(movieRes));
        Mockito.when(localDataSource.getDetailMovie(id))
                .thenReturn(Single.just(detailMovieRes));
        Mockito.when(remoteDataSource.getDetailMovie(id))
                .thenReturn(Single.just(detailMovieRes));
        Mockito.when(localDataSource.getCommentList(id))
                .thenReturn(Single.just(commentRes));
        Mockito.when(remoteDataSource.getCommentList(id))
                .thenReturn(Single.just(commentRes));

    }

    @Test
    public void testIfRemoteGetMovieListIsCalled_withNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(true);

        repository.getMovieList();
        Mockito.verify(remoteDataSource).getMovieList();
    }

    @Test
    public void testIfLocalGetMovieListIsCalled_withNoNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(false);

        repository.getMovieList();
        Mockito.verify(localDataSource).getMovieList();
    }

    @Test
    public void testIfRemoteGetDetailMovieIsCalled_withNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(true);

        repository.getDetailMovie(id);
        Mockito.verify(remoteDataSource).getDetailMovie(id);
    }

    @Test
    public void testIfLocalGetDetailMovieIsCalled_withNoNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(false);

        repository.getDetailMovie(id);
        Mockito.verify(localDataSource).getDetailMovie(id);
    }

    @Test
    public void testIfRemoteGetCommentListIsCalled_withNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(true);

        repository.getCommentList(id);
        Mockito.verify(remoteDataSource).getCommentList(id);
    }

    @Test
    public void testIfLocalGetCommentListIsCalled_withNoNetwork() {
        Mockito.when(Network.isConnected())
                .thenReturn(false);

        repository.getCommentList(id);
        Mockito.verify(localDataSource).getCommentList(id);
    }

    @Test
    public void testIfRemoteAddCommentIsCalled_WithNetwork() {
        Mockito.when(Network.isConnected()).thenReturn(true);
        repository.addComment(param);
        Mockito.verify(remoteDataSource).addComment(param);
    }

    @Test
    public void testIfRemoteAddLikeDisLikeIsCalled_WithNetwork() {
        Mockito.when(Network.isConnected()).thenReturn(true);
        repository.addLikeDisLike(param);
        Mockito.verify(remoteDataSource).addLikeDisLike(param);
    }

    @Test
    public void testIfRemoteRecommendCommentIsCalled_WithNetwork() {
        Mockito.when(Network.isConnected()).thenReturn(true);
        repository.recommendComment(param);
        Mockito.verify(remoteDataSource).recommendComment(param);
    }
}