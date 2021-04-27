package ybk.org.movieapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ybk.org.movieapp.ui.main.MovieListActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(this, MovieListActivity.class);
            startActivity(intent);
            finish();
        }, 2000);
    }
}
