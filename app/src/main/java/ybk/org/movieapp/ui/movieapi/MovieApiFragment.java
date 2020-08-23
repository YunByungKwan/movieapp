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
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import ybk.org.movieapp.R;

public class MovieApiFragment extends Fragment {

    private MovieApiViewModel movieApiViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        movieApiViewModel = new ViewModelProvider(this).get(MovieApiViewModel.class);

        View view = inflater.inflate(R.layout.fragment_movie_api, container, false);

        final TextView textView = view.findViewById(R.id.tv_movie_api);
        movieApiViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return view;
    }
}