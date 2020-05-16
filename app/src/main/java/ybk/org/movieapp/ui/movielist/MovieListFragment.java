package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;
import ybk.org.movieapp.MovieItem;
import ybk.org.movieapp.R;
import ybk.org.movieapp.ViewPagerAdapter;
import ybk.org.movieapp.databinding.FragmentMovieListBinding;

public class MovieListFragment extends Fragment {

    private static final String TAG = "MovieListFragment";

    private FragmentMovieListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        binding = DataBindingUtil.bind(rootView);
        if(binding != null) {
            binding.setFragment(this);
        } else {
            Log.e(TAG, "DataBinding is null.");
        }

        setInitDataToMovieList();

        return rootView;
    }

    private void setInitDataToMovieList() {
        ArrayList<MovieItem>  movieList = new ArrayList<>();

        movieList.add(new MovieItem(R.drawable.image1, "1. 군도", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image2, "2. 공조", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image3, "3. 더킹", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image4, "4. 레지던트 이불", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image5, "5. 럭키", 61.6, 15, 1));
        movieList.add(new MovieItem(R.drawable.image6, "6. 아수라", 61.6, 15, 1));

        ViewPagerAdapter adapter = new ViewPagerAdapter(movieList);
        binding.pager.setAdapter(adapter);
    }
}