package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import ybk.org.movieapp.data.local.entity.Movie;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieListBinding;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Network;
import ybk.org.movieapp.util.Utils;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding binding;
    public MovieListViewModel viewModel;
    private List<Movie> movieList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        viewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        viewModel.init();
        viewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> _movieList) {
                Utils.loge("onChanged");
                movieList = _movieList;
                for(int i = 0; i < movieList.size(); i++) {
                    Utils.loge(movieList.get(i).getTitle() + ", " + movieList.get(i).getReservationRate());
                }
                if(movieList.size() != 0) {
                    setPagerAdapter();
                    Network.showToast(getActivity());
                } else {
                    Toast.makeText(getActivity(), getString(R.string.msg_no_data), Toast.LENGTH_SHORT).show();
                }
            }
        });
        dataBinding(view);
        return view;
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

    private void dataBinding(View view) {
        binding = DataBindingUtil.bind(view);
        if(binding != null) {
            binding.setFragment(this);
        }
    }

    public void sortMovieListBy(final int type) {
        Collections.sort(movieList, new Comparator<Movie>() {
            @Override
            public int compare(Movie movie, Movie t1) {
                if(type == Constants.SORT_RESERVATION_RATE) {
                    return compareAwithB(movie.getReservationRate(), t1.getReservationRate());
                } else if(type == Constants.SORT_REVIEWER_RATING) {
                    return compareAwithB(movie.getReviewerRating(), t1.getReviewerRating());
                } else {
                    return compareAwithB(movie.getDate(), t1.getDate());
                }

            }
        });

        for(int i = 0; i < movieList.size(); i++) {
            Utils.loge(movieList.get(i).getTitle() + ", " + movieList.get(i).getReservationRate());
        }

        setPagerAdapter();
    }

    private int compareAwithB(Double a, Double b) {
        return b.compareTo(a);
    }

    private int compareAwithB(String a, String b) {
        return b.compareTo(a);
    }

    private static class MoviePagerAdapter extends FragmentStateAdapter {

        private List<MovieFragment> items = new ArrayList<>();

        MoviePagerAdapter(@NonNull FragmentManager fragmentManager,
                                 @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        void addItem(MovieFragment item) {
            items.add(item);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return items.get(position);
        }

        @Override
        public int getItemCount() {
            return items.size();
        }
    }
}