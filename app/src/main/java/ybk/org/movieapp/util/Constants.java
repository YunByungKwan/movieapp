package ybk.org.movieapp.util;

import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Constants {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REQUEST_COMMENT_WRITE_CODE, REQUEST_COMMENT_LIST_CODE})
    @StringDef({BUNDLE_KEY_BIG_POSTER, BUNDLE_KEY_SMALL_POSTER, BUNDLE_KEY_TITLE,
            BUNDLE_KEY_RESERVATION_RATE, BUNDLE_KEY_WATCHING_AGE, BUNDLE_KEY_D_DAY,
            TAG_MOVIE_FRAGMENT, TAG_MOVIE_LIST_FRAGMENT, TAG_MOVIE_DETAIL_FRAGMENT})
    public @interface types {}

    public static final int REQUEST_COMMENT_WRITE_CODE = 100;
    public static final int REQUEST_COMMENT_LIST_CODE = 200;

    public static final String BUNDLE_KEY_ID = "ID";
    public static final String BUNDLE_KEY_BIG_POSTER = "BIG_POSTER";
    public static final String BUNDLE_KEY_SMALL_POSTER = "SMALL_POSTER";
    public static final String BUNDLE_KEY_TITLE = "TITLE";
    public static final String BUNDLE_KEY_RESERVATION_RATE = "RESERVATION_RATE";
    public static final String BUNDLE_KEY_WATCHING_AGE = "WATCHING_AGE";
    public static final String BUNDLE_KEY_D_DAY = "D_DAY";
    public static final String BUNDLE_KEY_POSITION = "POSITION";

    public static final String TAG = "MovieApp";

    public static final String TAG_COMMENT_WRITE_ACTIVITY = "COMMENT_WRITE_ACTIVITY";
    public static final String TAG_COMMENT_LIST_ACTIVITY = "COMMENT_LIST_ACTIVITY";
    public static final String TAG_COMMENT_ITEM_VIEW = "COMMENT_ITEM_VIEW";
    public static final String TAG_MOVIE_FRAGMENT = "MOVIE_FRAGMENT";
    public static final String TAG_MOVIE_LIST_FRAGMENT = "MOVIE_LST_FRAGMENT";
    public static final String TAG_MOVIE_DETAIL_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";

    public static void logd(String msg) {
        Log.d(TAG, msg);
    }

    public static void loge(String msg) {
        Log.e(TAG, msg);
    }
}
