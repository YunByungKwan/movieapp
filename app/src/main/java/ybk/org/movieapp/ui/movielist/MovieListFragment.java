package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;
import ybk.org.movieapp.data.Movie;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieListBinding;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding binding;
    private MovieListViewModel viewModel;
    private List<Movie> movieList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);

        viewModel = ViewModelProviders.of(this).get(MovieListViewModel.class);
        viewModel.init();
        viewModel.getMovieList().observe(getViewLifecycleOwner(), new Observer<List<Movie>>() {
            @Override
            public void onChanged(List<Movie> movieItems) {
                movieList = movieItems;

                setPagerAdapter();
            }
        });

        setDataBinding(view);

        // setPagerAdapter();

        return view;
    }

    /** 데이터바인딩 설정 */
    private void setDataBinding(View view) {
        binding = DataBindingUtil.bind(view);

        if(binding != null) {
            binding.setFragment(this);
        } else {
            Log.e(Constants.TAG_MOVIE_LIST_FRAGMENT, "DataBinding is null.");
        }
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
            Log.e("TAG", "size:"+ movieList.size());
            for(int i = 0; i < movieList.size(); i++) {
                adapter.addItem(MovieFragment.newInstance(
                        movieList.get(i).getId(),
                        movieList.get(i).getImage(),
                        movieList.get(i).getThumb(),
                        movieList.get(i).getTitle(),
                        movieList.get(i).getReservationRate(),
                        movieList.get(i).getGrade(),
                        1,
                        adapter.getItemCount()));
            }
        }
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