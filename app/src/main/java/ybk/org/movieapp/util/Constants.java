package ybk.org.movieapp.util;

import android.annotation.SuppressLint;
import android.util.Log;
import androidx.annotation.IntDef;
import androidx.annotation.StringDef;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ybk.org.movieapp.R;

public class Constants {
    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REQUEST_COMMENT_WRITE_CODE, REQUEST_COMMENT_LIST_CODE})
    @StringDef({BUN_IMG, BUNDLE_KEY_SMALL_POSTER, BUN_TITLE,
            BUN_RESERV_RATE, BUN_GRADE, BUN_DAY,
            TAG_MOVIE_FRAGMENT, TAG_MOVIE_LIST_FRAGMENT, TAG_MOVIE_DETAIL_FRAGMENT})
    public @interface types {}

    public static final int REQUEST_COMMENT_WRITE_CODE = 100;
    public static final int REQUEST_COMMENT_LIST_CODE = 200;

    public static final String BUN_ID = "ID";
    public static final String BUN_IMG = "BIG_POSTER";
    public static final String BUNDLE_KEY_SMALL_POSTER = "SMALL_POSTER";
    public static final String BUN_TITLE = "TITLE";
    public static final String BUN_RESERV_RATE = "RESERVATION_RATE";
    public static final String BUN_GRADE = "WATCHING_AGE";
    public static final String BUN_DAY = "D_DAY";
    public static final String BUN_POS = "POSITION";

    public static final String TAG = "MovieApp";

    public static final String TAG_COMMENT_ITEM_VIEW = "COMMENT_ITEM_VIEW";
    public static final String TAG_MOVIE_FRAGMENT = "MOVIE_FRAGMENT";
    public static final String TAG_MOVIE_LIST_FRAGMENT = "MOVIE_LST_FRAGMENT";
    public static final String TAG_MOVIE_DETAIL_FRAGMENT = "MOVIE_DETAIL_FRAGMENT";

    public static final String MOV_ID = "movie_id";
    public static final String MOV_TITLE = "movieName";
    public static final String MOV_RATING = "movieRating";
    public static final String MOV_GRADE = "movieGrade";

    public static final String COMM_ID = "id";
    public static final String COMM_WRITER = "writer";
    public static final String COMM_TIME = "time";
    public static final String COMM_RATING = "rating";
    public static final String COMM_CONT = "contents";

    public static final String POST_LIKE = "likeyn";
    public static final String POST_DISLIKE = "dislikeyn";

    public static final String MSG_EMPTY = "한줄평을 100자 이내로 작성해주세요";

    /** Grade를 이미지 id로 변환 */
    public static int convertGradeToResId(int grade) {
        switch (grade) {
            case 12:
                return R.drawable.ic_12;
            case 15:
                return R.drawable.ic_15;
            case 19:
                return R.drawable.ic_19;
            default:
                return R.drawable.ic_all;
        }
    }

    /** 현재 시간을 구함 (형식: yyyy-MM-dd HH:mm:ss) */
    public static String getCurrentTime() {
        Calendar cal = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(cal.getTime());
    }

    public static void logd(String msg) {
        Log.d(TAG, msg);
    }

    public static void loge(String msg) {
        Log.e(TAG, msg);
    }
}
