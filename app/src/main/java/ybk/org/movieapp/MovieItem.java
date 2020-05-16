package ybk.org.movieapp;

public class MovieItem {

    private int moviePoster_id;
    private String movieTitle;
    private double reservationRate;
    private int watchingAge;
    private int dDay;

    public MovieItem(int moviePoster_id, String movieTitle, double reservationRate, int watchingAge, int dDay) {
        this.moviePoster_id = moviePoster_id;
        this.movieTitle = movieTitle;
        this.reservationRate = reservationRate;
        this.watchingAge = watchingAge;
        this.dDay = dDay;
    }

    public int getMoviePoster_id() {
        return moviePoster_id;
    }

    public void setMoviePoster_id(int moviePoster_id) {
        this.moviePoster_id = moviePoster_id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public double getReservationRate() {
        return reservationRate;
    }

    public void setReservationRate(double reservationRate) {
        this.reservationRate = reservationRate;
    }

    public int getWatchingAge() {
        return watchingAge;
    }

    public void setWatchingAge(int watchingAge) {
        this.watchingAge = watchingAge;
    }

    public int getdDay() {
        return dDay;
    }

    public void setdDay(int dDay) {
        this.dDay = dDay;
    }
}
