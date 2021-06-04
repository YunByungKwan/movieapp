package ybk.org.movieapp.ui.movielist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import com.bumptech.glide.Glide;

import dagger.android.support.DaggerFragment;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieBinding;
import ybk.org.movieapp.util.App;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public MovieFragment() {}

    public static MovieFragment newInstance(int id, String img, String title, double reservationRate,
                                            int grade, int pos) {
        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(App.getInstance().getString(R.string.bun_id), id);
        bundle.putString(App.getInstance().getString(R.string.bun_img), img);
        bundle.putString(App.getInstance().getString(R.string.bun_title), title);
        bundle.putDouble(App.getInstance().getString(R.string.bun_reserv_rate), reservationRate);
        bundle.putInt(App.getInstance().getString(R.string.bun_grade), grade);
        bundle.putInt(App.getInstance().getString(R.string.bun_pos), pos);
        movieFragment.setArguments(bundle);

        return movieFragment;
    }

    @Nullable
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, @Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        binding = DataBindingUtil.bind(view);
        if(binding != null) {
            binding.setFragment(this);
        }
        return view;
    }

    /** 영화 제목에 숫자를 붙임 */
    public StringBuilder addNumberToTitle(int pos, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(pos + 1).append(". ").append(title);

        return sb;
    }

    public void onClickDetailSeeButton(View v) {
        Navigation.findNavController(v)
                .navigate(R.id.act_nav_movie_list_to_detail, getArguments());
    }
}
