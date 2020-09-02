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
    private MovieListFragment movieListFragment;

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
        movieListFragment = (MovieListFragment) getSupportFragmentManager()
                .findFragmentById(R.id.nav_movie_list);

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
            if(data != null) {
                NavHostFragment hostFragment = (NavHostFragment) getSupportFragmentManager()
                        .findFragmentById(R.id.nav_host_fragment);

                if(hostFragment != null) {
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
                Animation transDown = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_down);
                ll.startAnimation(transDown);
                ll.setVisibility(View.VISIBLE);
                return false;
            }
        });
        return true;
    }

    public void onMenuItemSelected() {
        final Animation transUp = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.translate_up);

        orderBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                menu.findItem(R.id.menu_order).setIcon(R.drawable.order11);
                ll.startAnimation(transUp);
                ll.setVisibility(View.GONE);
                Constants.sortOrder = 1;
                Toast.makeText(getApplicationContext(),
                        getString(R.string.menu_click_text1), Toast.LENGTH_SHORT).show();
            }
        });
        orderBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.startAnimation(transUp);
                menu.findItem(R.id.menu_order).setIcon(R.drawable.order22);
                ll.setVisibility(View.GONE);
                Constants.sortOrder = 2;
                Toast.makeText(getApplicationContext(),
                        getString(R.string.menu_click_text2), Toast.LENGTH_SHORT).show();
            }
        });
        orderBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ll.startAnimation(transUp);
                menu.findItem(R.id.menu_order).setIcon(R.drawable.order33);
                ll.setVisibility(View.GONE);
                Constants.sortOrder = 3;
                Toast.makeText(getApplicationContext(),
                        getString(R.string.menu_click_text3), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void showMenu() {
        menu.findItem(R.id.menu_order).setVisible(true);
    }

    public void hideMenu() {
        menu.findItem(R.id.menu_order).setVisible(false);
    }
}
