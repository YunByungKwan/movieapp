package ybk.org.movieapp.local;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "detail_movie")
public class DetailMovieEntity {

    @PrimaryKey
    public int id;

    public String name;
    public String title;
    public String date;
    public float user_rating;
    public float audience_rating;
    public float reviewer_rating;
    public float reservation_rate;
    public int reservation_grade;
    public int grade;
    public String thumb;
    public String image;
    public String photos;
    public String videos;
    public String outlinks;
    public String genre;
    public int duration;
    public int audience;
    public String synopsis;
    public String director;
    public String actor;
    public int like;
    public int dislike;
}
