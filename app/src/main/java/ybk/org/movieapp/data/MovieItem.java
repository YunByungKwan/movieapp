package ybk.org.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MovieItem {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("title_eng")
    @Expose
    private String titleEng;

    @SerializedName("date")
    @Expose
    private String date;

    @SerializedName("user_rating")
    @Expose
    private Double userRating;

    @SerializedName("audience_rating")
    @Expose
    private Double audienceRating;

    @SerializedName("reviewer_rating")
    @Expose
    private Double reviewerRating;

    @SerializedName("reservation_rate")
    @Expose
    private Double reservationRate;

    @SerializedName("reservation_grade")
    @Expose
    private Integer reservationGrade;

    @SerializedName("grade")
    @Expose
    private Integer grade;

    @SerializedName("thumb")
    @Expose
    private String thumb;

    @SerializedName("image")
    @Expose
    private String image;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleEng() {
        return titleEng;
    }

    public void setTitleEng(String titleEng) {
        this.titleEng = titleEng;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getUserRating() {
        return userRating;
    }

    public void setUserRating(Double userRating) {
        this.userRating = userRating;
    }

    public Double getAudienceRating() {
        return audienceRating;
    }

    public void setAudienceRating(Double audienceRating) {
        this.audienceRating = audienceRating;
    }

    public Double getReviewerRating() {
        return reviewerRating;
    }

    public void setReviewerRating(Double reviewerRating) {
        this.reviewerRating = reviewerRating;
    }

    public Double getReservationRate() {
        return reservationRate;
    }

    public void setReservationRate(Double reservationRate) {
        this.reservationRate = reservationRate;
    }

    public Integer getReservationGrade() {
        return reservationGrade;
    }

    public void setReservationGrade(Integer reservationGrade) {
        this.reservationGrade = reservationGrade;
    }

    public Integer getGrade() {
        return grade;
    }

    public void setGrade(Integer grade) {
        this.grade = grade;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
