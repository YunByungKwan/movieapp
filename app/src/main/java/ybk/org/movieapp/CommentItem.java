package ybk.org.movieapp;

public class CommentItem {

    private String userId;

    private String registerTime;

    private float ratingCount;

    private String comment;

    private int recommendCount;

    public CommentItem(String userId,
                       String registerTime,
                       float ratingCount,
                       String comment,
                       int recommendCount) {
        this.userId = userId;
        this.registerTime = registerTime;
        this.ratingCount = ratingCount;
        this.comment = comment;
        this.recommendCount = recommendCount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(String registerTime) {
        this.registerTime = registerTime;
    }

    public float getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(float ratingCount) {
        this.ratingCount = ratingCount;
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
