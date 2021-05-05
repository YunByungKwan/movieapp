package ybk.org.movieapp.adapter;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import com.bumptech.glide.Glide;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class FoodBindingAdapter {

    @BindingAdapter("galleryItem")
    public static void setGalleryItem(ImageView imageView, GalleryItem item) {
        StringBuilder sb = new StringBuilder();
        if(Utils.isVideo(item.getUrl())) {
            String[] array = item.getUrl().split("/");
            String id = array[3];
            sb.append("https://img.youtube.com/vi/");
            sb.append(id);
            sb.append("/0.jpg");
        } else {
            sb.append(item.getUrl());
        }
        Glide.with(App.getInstance()).load(sb.toString()).into(imageView);
    }

    @BindingAdapter("playIcon")
    public static void setPlayIcon(ImageView imageView, GalleryItem item) {
        if(Utils.isVideo(item.getUrl())) {
            imageView.setVisibility(View.VISIBLE);
            imageView.bringToFront();
        }
    }
}
