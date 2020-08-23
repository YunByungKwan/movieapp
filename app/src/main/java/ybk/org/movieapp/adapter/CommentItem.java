package ybk.org.movieapp.adapter;

public class CommentItem {

    private int id;
    private String writer;
    private String registerTime;
    private float rating;
    private String comment;
    private int recommendCount;

    public CommentItem(int id, String writer, String registerTime, float rating, String comment, int recommendCount) {
        this.id = id;
        this.writer = writer;
        this.registerTime = registerTime;
        this.rating = rating;
        this.comment = comment;
        this.recommendCount = recommendCount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getRecommendCount() {
        return recommendCount;
    }

    public void setRecommendCount(int recommendCount) {
        this.recommendCount = recommendCount;
    }

}
