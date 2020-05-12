package ybk.org.movieapp.ui.movieapi;

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

public class MovieApiFragment extends Fragment {

    private MovieApiViewModel movieApiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieApiViewModel =
                ViewModelProviders.of(this).get(MovieApiViewModel.class);
        View root = inflater.inflate(R.layout.fragment_movie_api, container, false);
        final TextView textView = root.findViewById(R.id.tv_movie_api);
        movieApiViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return root;
    }
}