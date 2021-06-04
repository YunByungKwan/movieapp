package ybk.org.movieapp.adapter;

import android.view.View;
import android.widget.ImageView;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import ybk.org.movieapp.data.local.entity.DetailMovie;
import ybk.org.movieapp.util.App;
import ybk.org.movieapp.util.Utils;

public class FoodBindingAdapter {

    @BindingAdapter("galleryList")
    public static void setGalleryList(RecyclerView recyclerView, List<DetailMovie> list) {
        if(recyclerView.getAdapter() == null) {
            recyclerView.setAdapter(new GalleryAdapter());
        }
        GalleryAdapter adapter = (GalleryAdapter) recyclerView.getAdapter();
        if(list != null) {
            String moviePhotos = list.get(0).getPhotos();
            if(moviePhotos != null) {
                String[] photos = Utils.parseStringInComma(moviePhotos);

                for(String photoUrl : photos) {
                    adapter.addItem(new GalleryItem(photoUrl));
                }
            }
            String movieVideos = list.get(0).getVideos();
            if(movieVideos != null) {
                String[] videos = Utils.parseStringInComma(movieVideos);

                for(String videoUrl: videos) {
                    adapter.addItem(new GalleryItem(videoUrl));
                }
            }
            recyclerView.setAdapter(adapter);
        }
    }

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

    @BindingAdapter("url")
    public static void setImage(ImageView imageView, String url) {
        Glide.with(App.getInstance().getBaseContext())
                .load(url)
                .into(imageView);
    }

    @BindingAdapter("resId")
    public static void loadImage(ImageView imageView, int resId) {
        imageView.setImageResource(resId);
    }
}
