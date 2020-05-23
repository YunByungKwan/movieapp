package ybk.org.movieapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ViewPagerAdapter extends RecyclerView.Adapter<MovieViewHolder> {

    private ArrayList<MovieItem> movies;

    public ViewPagerAdapter(ArrayList<MovieItem> items) {
        this.movies = items;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.fragment_movie, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.onBind(movies.get(position));
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }
}