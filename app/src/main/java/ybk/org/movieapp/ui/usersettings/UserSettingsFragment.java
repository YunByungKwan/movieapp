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
import androidx.lifecycle.ViewModelProviders;

import ybk.org.movieapp.R;

public class UserSettingsFragment extends Fragment {

    private UserSettingsViewModel userSettingsViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        userSettingsViewModel =
                ViewModelProviders.of(this).get(UserSettingsViewModel.class);
        View rootView = inflater.inflate(R.layout.fragment_user_settings, container, false);

        final TextView textView = rootView.findViewById(R.id.tv_user_settings);
        userSettingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return rootView;
    }
}