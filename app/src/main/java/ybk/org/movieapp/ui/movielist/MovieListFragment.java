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
import androidx.viewpager2.adapter.FragmentStateAdapter;
import java.util.ArrayList;
import java.util.List;
import ybk.org.movieapp.Constants;
import ybk.org.movieapp.MovieFragment;
import ybk.org.movieapp.R;
import ybk.org.movieapp.databinding.FragmentMovieListBinding;

public class MovieListFragment extends Fragment {

    private FragmentMovieListBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        Log.e(Constants.TAG_MOVIE_LIST_FRAGMENT, "Call onCreateView().");

        View view = inflater.inflate(R.layout.fragment_movie_list, container, false);
        setDataBinding(view);

        setPagerAdapter();

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
        adapter.addItem(MovieFragment.newInstance(R.drawable.image1,
                adapter.getItemCount() + 1 +". " + "군도", 61.6,
                15, 1));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image2,
                adapter.getItemCount() + 1 +". " + "공조", 61.6,
                15, 1));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image3,
                adapter.getItemCount() + 1 +". " + "더킹", 61.6,
                15, 1));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image4,
                adapter.getItemCount() + 1 +". " + "레지던트 이불", 61.6,
                15, 1));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image5,
                adapter.getItemCount() + 1 +". " + "럭키", 61.6,
                15, 1));
        adapter.addItem(MovieFragment.newInstance(R.drawable.image6,
                adapter.getItemCount() + 1 +". " + "아수라", 61.6,
                15, 1));
    }

    private class MoviePagerAdapter extends FragmentStateAdapter {

        private List<MovieFragment> items = new ArrayList<>();

        public MoviePagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        public void addItem(MovieFragment item) {
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