package ybk.org.movieapp.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;
import java.util.List;

import ybk.org.movieapp.ui.movielist.MovieFragment;

public class MoviePagerAdapter extends FragmentStateAdapter {

    private List<MovieFragment> items = new ArrayList<>();

    public MoviePagerAdapter(@NonNull FragmentManager fragmentManager,
                             @NonNull Lifecycle lifecycle) {
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
