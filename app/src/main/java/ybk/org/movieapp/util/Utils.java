package ybk.org.movieapp.util;

import android.annotation.SuppressLint;
import android.util.Log;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import ybk.org.movieapp.R;

public class Utils {

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

    /** url이 비디오인지 판별 */
    public static Boolean isVideo(String url) {
        return url.contains("youtu.be");
    }

    /** 콤마로 구분된 문자열을 파싱함 */
    public static String[] parseStringInComma(String src) {
        return src.split(",");
    }

    /** 현재 시간을 구함 (형식: yyyy-MM-dd HH:mm:ss) */
    public static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(calendar.getTime());
    }

}
