package ybk.org.movieapp;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

public class MovieViewHolder extends RecyclerView.ViewHolder {

    private ImageView iv_movie_poster;
    private TextView tv_movie_title;
    private TextView tv_reservation_rate;
    private TextView tv_watch_age;
    private TextView tv_d_day;
    private Button btn_detail_see;

    MovieItem data;

    MovieViewHolder(View itemView) {
        super(itemView);

        iv_movie_poster = itemView.findViewById(R.id.iv_movie_poster);
        tv_movie_title = itemView.findViewById(R.id.tv_movie_title);
        tv_reservation_rate = itemView.findViewById(R.id.tv_reservation_rate);
        tv_watch_age = itemView.findViewById(R.id.tv_watch_age);
        tv_d_day = itemView.findViewById(R.id.tv_d_day);
        btn_detail_see = itemView.findViewById(R.id.btn_detail_see);
    }

    public void onBind(MovieItem data){
        this.data = data;

        iv_movie_poster.setImageResource(data.getMoviePoster_id());
        tv_movie_title.setText(data.getMovieTitle());
        tv_reservation_rate.setText(String.valueOf(data.getReservationRate()));
        tv_watch_age.setText(String.valueOf(data.getWatchingAge()));
        tv_d_day.setText(String.valueOf(data.getdDay()));
        btn_detail_see.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.action_nav_movie_list_to_nav_movie_detail);
            }
        });
    }
}
