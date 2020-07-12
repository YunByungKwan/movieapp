package ybk.org.movieapp.ui.comment;

public class NewComment {

    private String id;
    private String writer;
    private String time;
    private float rating;
    private String contents;

    public NewComment(String id, String writer, String time, float rating, String contents) {
        this.id = id;
        this.writer = writer;
        this.time = time;
        this.rating = rating;
        this.contents = contents;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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
}
