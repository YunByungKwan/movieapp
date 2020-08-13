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
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieBinding;
import ybk.org.movieapp.util.Constants;

public class MovieFragment extends Fragment {

    private FragmentMovieBinding binding;

    public MovieFragment() { }

    public static MovieFragment newInstance(int id, String img, String title, double reservationRate,
                                            int grade, int pos) {
        MovieFragment movieFragment = new MovieFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(Constants.BUN_ID, id);
        bundle.putString(Constants.BUN_IMG, img);
        bundle.putString(Constants.BUN_TITLE, title);
        bundle.putDouble(Constants.BUN_RESERV_RATE, reservationRate);
        bundle.putInt(Constants.BUN_GRADE, grade);
        bundle.putInt(Constants.BUN_POS, pos);
        movieFragment.setArguments(bundle);

        return movieFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        dataBinding(view);
        setMovieInfo();
        return view;
    }

    private void dataBinding(View view) {
        binding = DataBindingUtil.bind(view);
        if(binding != null) {
            binding.setFragment(this);
        }
    }

    private void setMovieInfo() {
        if(getArguments() != null) {
            String img = getArguments().getString(Constants.BUN_IMG);
            String title = getArguments().getString(Constants.BUN_TITLE);
            String reservationRate = String.valueOf(getArguments().getDouble(Constants.BUN_RESERV_RATE));
            String grade = String.valueOf(getArguments().getInt(Constants.BUN_GRADE));
            int pos = getArguments().getInt(Constants.BUN_POS);

            Glide.with(getContext()).load(img).into(binding.ivMoviePoster);
            binding.tvMovieTitle.setText(addNumberToTitle(pos, title));
            binding.tvReservationRate.setText(reservationRate);
            binding.tvWatchAge.setText(grade);
        }
    }

    /** 영화 제목에 숫자를 붙임 */
    private StringBuilder addNumberToTitle(int pos, String title) {
        StringBuilder sb = new StringBuilder();
        sb.append(pos + 1).append(". ").append(title);

        return sb;
    }

    /** 상세보기 버튼 클릭시 이벤트 */
    public void onClickDetailSeeButton(View v) {
        Navigation.findNavController(v).navigate(R.id.act_nav_movie_list_to_detail, getArguments());
    }
}
