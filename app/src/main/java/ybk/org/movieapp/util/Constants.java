package ybk.org.movieapp.util;

import android.annotation.SuppressLint;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import ybk.org.movieapp.R;

public class Constants {

    public static final int REQUEST_COMMENT_WRITE_CODE = 100;
    public static final int REQUEST_COMMENT_LIST_CODE = 200;

    public static final int NET_DISCONNCTED = 0;
    public static final int NET_CELLULAR = 1;
    public static final int NET_WIFI = 2;

    public static final String BUN_ID = "ID";
    public static final String BUN_IMG = "BIG_POSTER";
    public static final String BUN_TITLE = "TITLE";
    public static final String BUN_RESERV_RATE = "RESERVATION_RATE";
    public static final String BUN_GRADE = "WATCHING_AGE";
    public static final String BUN_DAY = "D_DAY";
    public static final String BUN_POS = "POSITION";

    public static final String TAG = "MovieApp";
    public static final String TAG_COMMENT_ITEM_VIEW = "COMMENT_ITEM_VIEW";

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
    public static final String MSG_NET_OK = "인터넷이 연결되어 있습니다.\n데이터베이스에 저장함";
    public static final String MSG_NET_FAIL = "인터넷이 연결되어 있지 않습니다.\n데이터베이스로부터 로딩함";
    public static final String MSG_REQ_NET = "인터넷이 연결되어 있지 않습니다\n인터넷 연결 후 작성해주세요.";
    public static final String MSG_NO_DATA = "데이터가 존재하지 않습니다.";

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
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

    public static void logd(String msg) {
        Log.d(TAG, msg);
    }

    public static void loge(String msg) {
        Log.e(TAG, msg);
    }

}
