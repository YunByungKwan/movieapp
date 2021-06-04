package ybk.org.movieapp.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import ybk.org.movieapp.R;
import ybk.org.movieapp.ui.moviedetail.MovieDetailFragment;
import ybk.org.movieapp.ui.movielist.MovieListFragment;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Utils;

public class MovieListActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public Menu menu;
    private NavController navController;
    private NavHostFragment hostFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movie_list, R.id.nav_movie_detail, R.id.nav_movie_api,
                R.id.nav_movie_book, R.id.nav_user_settings)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK && requestCode == Constants.REQUEST_COMMENT_WRITE_CODE) {
            if(data != null && hostFragment != null) {
                MovieDetailFragment detailFragment = (MovieDetailFragment) hostFragment
                        .getChildFragmentManager().getFragments().get(0);

                HashMap<String, Object> comment = new HashMap<>();
                comment.put(getString(R.string.comm_id), data.getIntExtra(getString(R.string.comm_id), 0));
                comment.put(getString(R.string.comm_writer), data.getStringExtra(getString(R.string.comm_writer)));
                comment.put(getString(R.string.comm_time),  data.getStringExtra(getString(R.string.comm_time)));
                comment.put(getString(R.string.comm_rating), data.getFloatExtra(getString(R.string.comm_rating), 0));
                comment.put(getString(R.string.comm_cont), data.getStringExtra(getString(R.string.comm_cont)));
                detailFragment.addComment(comment);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menu1) {
            sortBy(Constants.SORT_RESERVATION_RATE);
            return true;
        } else if(id == R.id.menu2) {
            sortBy(Constants.SORT_REVIEWER_RATING);
            return true;
        } else if(id == R.id.menu3) {
            sortBy(Constants.SORT_DATE);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void sortBy(int type) {
        showToast(type);
        FragmentManager navHostManager = Objects.requireNonNull(hostFragment).getChildFragmentManager();
        MovieListFragment fragment = (MovieListFragment) navHostManager.getFragments().get(0);
        fragment.sortMovieListBy(type);
    }

    private void showToast(int type) {
        String txt = "";
        if(type == Constants.SORT_RESERVATION_RATE) {
            txt = getString(R.string.menu_click_text1);
        } else if(type == Constants.SORT_REVIEWER_RATING) {
            txt = getString(R.string.menu_click_text2);
        } else {
            txt = getString(R.string.menu_click_text3);
        }
        Toast.makeText(getApplicationContext(),
                txt, Toast.LENGTH_SHORT).show();
    }

    public void showOptionMenu(boolean isShow) {
        if(menu == null) return;
        menu.setGroupVisible(R.id.menu_group, isShow);
    }
}
