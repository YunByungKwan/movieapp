package ybk.org.movieapp.ui.moviegallery;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import androidx.appcompat.app.AppCompatActivity;
import com.bumptech.glide.Glide;
import com.github.chrisbanes.photoview.PhotoView;
import ybk.org.movieapp.R;

public class MovieGalleryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_gallery);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        PhotoView photoView = (PhotoView) findViewById(R.id.photoView);

        Intent getIntent = getIntent();
        if(getIntent != null) {
            String url = getIntent.getStringExtra(getString(R.string.gallery_url));
            Glide.with(this).load(url).into(photoView);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                finish();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}