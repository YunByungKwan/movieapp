package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ybk.org.movieapp.adapter.MoviePagerAdapter;
import ybk.org.movieapp.ui.main.MovieListActivity;
import ybk.org.movieapp.data.MovieRepositoryImpl;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieListBinding;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Dlog;
import ybk.org.movieapp.util.Network;
import ybk.org.movieapp.util.Utils;

public class MovieListFragment extends DaggerFragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    private FragmentMovieListBinding binding;
    public MovieListViewModel viewModel;
    private List<Movie> movieList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this, viewModelFactory).get(MovieListViewModel.class);
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_list, container, false);
        binding.setFragment(this);
        binding.setViewmodel(viewModel);

        viewModel.movieList.observe(getViewLifecycleOwner(), _movieList -> {
            movieList = _movieList;
            if(_movieList == null) {
                Dlog.d("_movieList is null");
            }
            if(movieList.size() != 0) {
                setPagerAdapter();
                Network.showToast(getActivity());
            } else {
                Toast.makeText(getActivity(), getString(R.string.msg_no_data), Toast.LENGTH_SHORT).show();
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MovieListActivity)requireActivity()).showOptionMenu(true);
    }

    /** ViewPager에 어뎁터를 설정함 */
    private void setPagerAdapter() {
        MoviePagerAdapter adapter = new MoviePagerAdapter(getChildFragmentManager(), getLifecycle());
        addMoviesToAdapter(adapter);
        binding.pager.setAdapter(adapter);
    }

    /** 영화를 추가함 */
    private void addMoviesToAdapter(MoviePagerAdapter adapter) {
        if(movieList != null) {
            for(Movie movie: movieList) {
                adapter.addItem(MovieFragment.newInstance(
                        movie.getId(),
                        movie.getImage(),
                        movie.getTitle(),
                        movie.getReservationRate(),
                        movie.getGrade(),
                        adapter.getItemCount()));
            }
        }
    }

    public void sortMovieListBy(final int type) {
        Collections.sort(movieList, (movie, t1) -> {
            if(type == Constants.SORT_RESERVATION_RATE) {
                return Utils.compareAwithB(movie.getReservationRate(), t1.getReservationRate());
            } else if(type == Constants.SORT_REVIEWER_RATING) {
                return Utils.compareAwithB(movie.getReviewerRating(), t1.getReviewerRating());
            } else {
                return Utils.compareAwithB(movie.getDate(), t1.getDate());
            }

        });
        setPagerAdapter();
    }
}