package ybk.org.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


/**
 * private static class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {
 *     private List<String> mItems;
 *
 *     public PhotoAdapter(List<String> mItems) {
 *         this.mItems = mItems;
 *     }
 *
 *     @NonNull
 *     @Override
 *     public PhotoAdapter.PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
 *         View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
 *         return new PhotoViewHolder(view);
 *     }
 *
 *     @Override
 *     public void onBindViewHolder(@NonNull PhotoAdapter.PhotoViewHolder holder, int position) {
 *         holder.binding.setImageUrl(mItems.get(position));
 *     }
 *
 *     @Override
 *     public int getItemCount() {
 *         return mItems.size();
 *     }
 *
 *     public class PhotoViewHolder extends RecyclerView.ViewHolder {
 *         ItemPhotoBinding binding;
 *
 *         public PhotoViewHolder(@NonNull View itemView) {
 *             super(itemView);
 *             binding = DataBindingUtil.bind(itemView);
 *         }
 *     }
 * }
 * */
public class ViewPagerAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<MovieItem> mItems;

    public ViewPagerAdapter(ArrayList<MovieItem> items) {
        this.mItems = items;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        if(holder instanceof MovieViewHolder){
            MovieViewHolder viewHolder = (MovieViewHolder) holder;
            viewHolder.onBind(mItems.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }
}