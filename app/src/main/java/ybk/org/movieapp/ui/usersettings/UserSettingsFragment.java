package ybk.org.movieapp.ui.usersettings;

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

import ybk.org.movieapp.MovieListActivity;
import ybk.org.movieapp.R;

public class UserSettingsFragment extends Fragment {

    private UserSettingsViewModel userSettingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userSettingsViewModel = new ViewModelProvider(this).get(UserSettingsViewModel.class);

        View view = inflater.inflate(R.layout.fragment_user_settings, container, false);

        final TextView textView = view.findViewById(R.id.tv_user_settings);
        userSettingsViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MovieListActivity)requireActivity()).showOptionMenu(false);
    }
}