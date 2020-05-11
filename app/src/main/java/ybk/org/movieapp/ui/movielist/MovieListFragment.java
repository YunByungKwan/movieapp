package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

import ybk.org.movieapp.MovieItem;
import ybk.org.movieapp.R;
import ybk.org.movieapp.ViewPagerAdapter;

public class MovieListFragment extends Fragment {

    private MovieListViewModel movieListViewModel;

    private ViewPager2 pager;

    private ArrayList<MovieItem> movieList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieListViewModel =
                ViewModelProviders.of(this).get(MovieListViewModel.class);

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_movie_list,
                container, false);

        movieList = new ArrayList<MovieItem>();
        movieList.add(new MovieItem(R.drawable.image1, "1. 군도", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image2, "2. 공조", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image3, "3. 더킹", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image4, "4. 레지던트 이불", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image5, "5. 럭키", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image6, "6. 아수라", 61.6, 15, 1));

        pager = (ViewPager2) rootView.findViewById(R.id.pager);
        ViewPagerAdapter adapter = new ViewPagerAdapter(movieList);
        pager.setAdapter(adapter);

        return rootView;
        //
    }
}