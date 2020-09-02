package ybk.org.movieapp;

import android.content.Intent;
import android.os.Bundle;
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

import ybk.org.movieapp.ui.moviedetail.MovieDetailFragment;
import ybk.org.movieapp.ui.movielist.MovieListFragment;
import ybk.org.movieapp.util.Constants;
import ybk.org.movieapp.util.Utils;

public class MovieListActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    public Menu menu;
    private LinearLayout ll;
    private ImageButton orderBtn1;
    private ImageButton orderBtn2;
    private ImageButton orderBtn3;
    private NavController navController;
    private NavHostFragment hostFragment;
    private boolean isMenuOpen = false;
    private Animation transDown;
    private Animation transUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ll = findViewById(R.id.linearLayout);
        orderBtn1 = (ImageButton) findViewById(R.id.ib_order_1);
        orderBtn2 = (ImageButton) findViewById(R.id.ib_order_2);
        orderBtn3 = (ImageButton) findViewById(R.id.ib_order_3);
        hostFragment = (NavHostFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_host_fragment);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        transDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_down);
        transUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_up);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_movie_list, R.id.nav_movie_detail, R.id.nav_movie_api,
                R.id.nav_movie_book, R.id.nav_user_settings)
                .setDrawerLayout(drawer)
                .build();

        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
        onMenuItemSelected();
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
        super.onCreateOptionsMenu(menu);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        this.menu = menu;
        this.menu.findItem(R.id.menu_order).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                if(!isMenuOpen) {
                    ll.startAnimation(transDown);
                    ll.setVisibility(View.VISIBLE);
                    isMenuOpen = true;
                } else {
                    ll.startAnimation(transUp);
                    ll.setVisibility(View.GONE);
                    isMenuOpen = false;

                }
                return false;
            }
        });
        return true;
    }

    public void onMenuItemSelected() {
        orderBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortBy(Constants.SORT_RESERVATION_RATE);
            }
        });
        orderBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortBy(Constants.SORT_REVIEWER_RATING);
            }
        });
        orderBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sortBy(Constants.SORT_DATE);
            }
        });
    }

    private void sortBy(int type) {
        Animation transUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_up);
        setMenuIcon(type);
        ll.startAnimation(transUp);
        ll.setVisibility(View.GONE);
        showToast(type);
        FragmentManager navHostManager = Objects.requireNonNull(hostFragment).getChildFragmentManager();
        MovieListFragment fragment = (MovieListFragment) navHostManager.getFragments().get(0);
        fragment.sortMovieListBy(type);
        isMenuOpen = false;
    }

    private void setMenuIcon(int type) {
        int resId = 0;
        if(type == Constants.SORT_RESERVATION_RATE) {
            resId = R.drawable.order11;
        } else if(type == Constants.SORT_REVIEWER_RATING) {
            resId = R.drawable.order22;
        } else {
            resId = R.drawable.order33;
        }
        menu.findItem(R.id.menu_order).setIcon(resId);
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

    public void showMenu() {
        menu.findItem(R.id.menu_order).setVisible(true);
    }

    public void hideMenu() {
        menu.findItem(R.id.menu_order).setVisible(false);
        ll.setVisibility(View.GONE);
    }
}
