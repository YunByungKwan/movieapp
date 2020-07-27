package ybk.org.movieapp.local;

import androidx.room.Entity;

@Entity(tableName = "comment")
public class CommentEntity {
    public int total;
    public String writer;
    public String review_id;
    public String writer_image;
    public String time;
    public String timestamp;
    public float rating;
    public String contents;
    public int recommend;
    public int id;
}
