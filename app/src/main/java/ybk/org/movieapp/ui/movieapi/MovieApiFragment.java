package ybk.org.movieapp.ui.movieapi;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import dagger.android.support.DaggerFragment;
import ybk.org.movieapp.ui.main.MovieListActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieApiBinding;

public class MovieApiFragment extends DaggerFragment {

    private FragmentMovieApiBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,
                R.layout.fragment_movie_api, container, false);
        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MovieListActivity)requireActivity()).showOptionMenu(false);
    }
}