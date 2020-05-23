package ybk.org.movieapp;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import ybk.org.movieapp.databinding.FragmentMovieBinding;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public MovieFragment() {
        // Nothing.
    }

    public static MovieFragment newInstance(int poster, String title, double reservationRate,
                                            int watchingAge, int dDay) {

        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_KEY_POSTER, poster);
        bundle.putString(Constants.BUNDLE_KEY_TITLE, title);
        bundle.putDouble(Constants.BUNDLE_KEY_RESERVATION_RATE, reservationRate);
        bundle.putInt(Constants.BUNDLE_KEY_WATCHING_AGE, watchingAge);
        bundle.putInt(Constants.BUNDLE_KEY_D_DAY, dDay);

        movieFragment.setArguments(bundle);

        return movieFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.e(Constants.TAG_MOVIE_FRAGMENT, "Call onCreateView().");

        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        setDataBinding(view);

        if(getArguments() != null) {
            binding.ivMoviePoster.setImageResource(getArguments().getInt(Constants.BUNDLE_KEY_POSTER));
            binding.tvMovieTitle.setText(getArguments().getString(Constants.BUNDLE_KEY_TITLE));
            binding.tvReservationRate.setText(String.valueOf(getArguments().getDouble(Constants.BUNDLE_KEY_RESERVATION_RATE)));
            binding.tvWatchAge.setText(String.valueOf(getArguments().getInt(Constants.BUNDLE_KEY_WATCHING_AGE)));
            binding.tvDDay.setText(String.valueOf(getArguments().getInt(Constants.BUNDLE_KEY_D_DAY)));
        } else {
            Log.e(Constants.TAG_MOVIE_FRAGMENT, "getArguments() is null.");
        }

        binding.btnDetailSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_movie_list_to_nav_movie_detail, getArguments());
            }
        });

        return view;
    }

    private void setDataBinding(View view) {
        binding = DataBindingUtil.bind(view);

        if(binding != null) {
            binding.setFragment(this);
        } else {
            Log.e(Constants.TAG_MOVIE_FRAGMENT, "DataBinding is null.");
        }
    }
}
