package ybk.org.movieapp.ui.comment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import ybk.org.movieapp.data.MovieRepositoryImpl;

@RunWith(MockitoJUnitRunner.class)
public class CommentListViewModelTest {

    @Mock
    public MovieRepositoryImpl repository;
    public CommentListViewModel viewModel;

    @Before
    public void setUp() {
        viewModel = new CommentListViewModel(repository);
    }

    @Test
    public void test() {

    }
}