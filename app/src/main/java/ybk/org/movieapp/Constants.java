package ybk.org.movieapp;

import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BUNDLE_KEY_POSTER, BUNDLE_KEY_TITLE, BUNDLE_KEY_RESERVATION_RATE,
            BUNDLE_KEY_WATCHING_AGE, BUNDLE_KEY_D_DAY,

            TAG_MOVIE_FRAGMENT, TAG_MOVIE_LIST_FRAGMENT, TAG_MOVIE_DETAIL_FRAGMENT})
    public @interface types {}

    public static final String BUNDLE_KEY_POSTER = "POSTER";
    public static final String BUNDLE_KEY_TITLE = "TITLE";
    public static final String BUNDLE_KEY_RESERVATION_RATE = "RESERVATIONRATE";
    public static final String BUNDLE_KEY_WATCHING_AGE = "WATCHINGAGE";
    public static final String BUNDLE_KEY_D_DAY = "DDAY";

    public static final String TAG_MOVIE_FRAGMENT = "MOVIE_FRAGMENT";
    public static final String TAG_MOVIE_LIST_FRAGMENT = "MOVIE_LST_FRAGMENT";
    public static final String TAG_MOVIE_DETAIL_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";
}
