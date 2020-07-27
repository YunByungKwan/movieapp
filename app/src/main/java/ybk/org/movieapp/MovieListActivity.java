package ybk.org.movieapp;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.room.Room;

import java.util.HashMap;

import ybk.org.movieapp.local.MovieDatabase;
import ybk.org.movieapp.ui.moviedetail.MovieDetailFragment;
import ybk.org.movieapp.util.Constants;

public class MovieListActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movie_list, R.id.nav_movie_detail, R.id.nav_movie_api,
                R.id.nav_movie_book, R.id.nav_user_settings)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        // 데이터베이스 생성
        MovieDatabase dataBase = MovieDatabase.getInstance(this);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_COMMENT_WRITE_CODE) {
            if(data != null) {
                NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

                if(hostFragment != null) {
                    MovieDetailFragment detailFragment = (MovieDetailFragment) hostFragment
                            .getChildFragmentManager().getFragments().get(0);

                    HashMap<String, Object> comment = new HashMap<>();
                    comment.put(Constants.COMM_ID, data.getIntExtra(Constants.COMM_ID, 0));
                    comment.put(Constants.COMM_WRITER, data.getStringExtra(Constants.COMM_WRITER));
                    comment.put(Constants.COMM_TIME,  data.getStringExtra(Constants.COMM_TIME));
                    comment.put(Constants.COMM_RATING, data.getFloatExtra(Constants.COMM_RATING, 0));
                    comment.put(Constants.COMM_CONT, data.getStringExtra(Constants.COMM_CONT));
                    detailFragment.addComment(comment);
                }
            }
        }
    }
}
