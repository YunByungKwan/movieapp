package ybk.org.movieapp.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetailMovie {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("id")
    @Expose
    private Integer id;
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
    @SerializedName("photos")
    @Expose
    private Object photos;
    @SerializedName("videos")
    @Expose
    private Object videos;
    @SerializedName("outlinks")
    @Expose
    private Object outlinks;
    @SerializedName("genre")
    @Expose
    private String genre;
    @SerializedName("duration")
    @Expose
    private Integer duration;
    @SerializedName("audience")
    @Expose
    private Integer audience;
    @SerializedName("synopsis")
    @Expose
    private String synopsis;
    @SerializedName("director")
    @Expose
    private String director;
    @SerializedName("actor")
    @Expose
    private String actor;
    @SerializedName("like")
    @Expose
    private Integer like;
    @SerializedName("dislike")
    @Expose
    private Integer dislike;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Object getPhotos() {
        return photos;
    }

    public void setPhotos(Object photos) {
        this.photos = photos;
    }

    public Object getVideos() {
        return videos;
    }

    public void setVideos(Object videos) {
        this.videos = videos;
    }

    public Object getOutlinks() {
        return outlinks;
    }

    public void setOutlinks(Object outlinks) {
        this.outlinks = outlinks;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Integer getAudience() {
        return audience;
    }

    public void setAudience(Integer audience) {
        this.audience = audience;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getDislike() {
        return dislike;
    }

    public void setDislike(Integer dislike) {
        this.dislike = dislike;
    }

}
