package ybk.org.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommentItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("writer")
    @Expose
    private String writer;
    @SerializedName("movieId")
    @Expose
    private Integer movieId;
    @SerializedName("writer_image")
    @Expose
    private Object writerImage;
    @SerializedName("time")
    @Expose
    private String time;
    @SerializedName("timestamp")
    @Expose
    private Integer timestamp;
    @SerializedName("rating")
    @Expose
    private float rating;
    @SerializedName("contents")
    @Expose
    private String contents;
    @SerializedName("recommend")
    @Expose
    private Integer recommend;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Integer getMovieId() {
        return movieId;
    }

    public void setMovieId(Integer movieId) {
        this.movieId = movieId;
    }

    public Object getWriterImage() {
        return writerImage;
    }

    public void setWriterImage(Object writerImage) {
        this.writerImage = writerImage;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Integer timestamp) {
        this.timestamp = timestamp;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Integer getRecommend() {
        return recommend;
    }

    public void setRecommend(Integer recommend) {
        this.recommend = recommend;
    }

}
