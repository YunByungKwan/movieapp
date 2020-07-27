package ybk.org.movieapp.local;

import androidx.room.Entity;

@Entity(tableName = "movie")
public class MovieEntity {
    public String title;
    public String titile_eng;
    public String date;
    public float user_rating;
    public float audience_rating;
    public float reservation_rate;
    public int reservation_grade;
    public int grade;
    public String thumb;
    public String image;
}
