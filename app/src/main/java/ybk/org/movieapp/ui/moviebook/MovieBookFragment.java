package ybk.org.movieapp.ui.moviebook;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import ybk.org.movieapp.R;

public class MovieBookFragment extends Fragment {

    private MovieBookViewModel movieBookViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieBookViewModel =
                ViewModelProviders.of(this).get(MovieBookViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movie_book, container, false);

        final TextView textView = root.findViewById(R.id.tv_movie_book);
        movieBookViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}