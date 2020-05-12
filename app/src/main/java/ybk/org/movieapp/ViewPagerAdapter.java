package ybk.org.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

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