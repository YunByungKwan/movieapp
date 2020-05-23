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
import ybk.org.movieapp.util.Constants;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public MovieFragment() {
        // Nothing.
    }

    public static MovieFragment newInstance(int bigPoster, int smallPoster, String title,
                                            double reservationRate, int watchingAge, int dDay,
                                            int pos) {

        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUNDLE_KEY_BIG_POSTER, bigPoster);
        bundle.putInt(Constants.BUNDLE_KEY_SMALL_POSTER, smallPoster);
        bundle.putString(Constants.BUNDLE_KEY_TITLE, title);
        bundle.putDouble(Constants.BUNDLE_KEY_RESERVATION_RATE, reservationRate);
        bundle.putInt(Constants.BUNDLE_KEY_WATCHING_AGE, watchingAge);
        bundle.putInt(Constants.BUNDLE_KEY_D_DAY, dDay);
        bundle.putInt(Constants.BUNDLE_KEY_POSITION, pos);

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
            int poster_id = getArguments().getInt(Constants.BUNDLE_KEY_BIG_POSTER);
            String title = getArguments().getString(Constants.BUNDLE_KEY_TITLE);
            double reservationRate = getArguments().getDouble(Constants.BUNDLE_KEY_RESERVATION_RATE);
            int watchingAge = getArguments().getInt(Constants.BUNDLE_KEY_WATCHING_AGE);
            int dDay = getArguments().getInt(Constants.BUNDLE_KEY_D_DAY);
            int position = getArguments().getInt(Constants.BUNDLE_KEY_POSITION);
            StringBuilder sb = new StringBuilder();
            sb.append(position + 1).append(". ").append(title);

            binding.ivMoviePoster.setImageResource(poster_id);
            binding.tvMovieTitle.setText(sb);
            binding.tvReservationRate.setText(String.valueOf(reservationRate));
            binding.tvWatchAge.setText(String.valueOf(watchingAge));
            binding.tvDDay.setText(String.valueOf(dDay));
        } else {
            Log.e(Constants.TAG_MOVIE_FRAGMENT, "getArguments() is null.");
        }

        binding.btnDetailSee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v)
                        .navigate(R.id.action_nav_movie_list_to_nav_movie_detail, getArguments());
            }
        });

        return view;
    }

    /** 데이터바인딩 설정 */
    private void setDataBinding(View view) {
        binding = DataBindingUtil.bind(view);

        if(binding != null) {
            binding.setFragment(this);
        } else {
            Log.e(Constants.TAG_MOVIE_FRAGMENT, "DataBinding is null.");
        }
    }
}
