package ybk.org.movieapp.ui.movieapi;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;
import ybk.org.movieapp.ui.main.MovieListActivity;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieApiBinding;
import ybk.org.movieapp.ui.moviebook.MovieBookViewModel;
import ybk.org.movieapp.util.App;

public class MovieApiFragment extends Fragment {

    @Inject
    public ViewModelProvider.Factory viewModelFactory;

    @Inject
    public MovieBookViewModel viewModel;

    private FragmentMovieApiBinding binding;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        App.getInstance().appComponent()
                .movieApiComponent().create().inject(this);
    }

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