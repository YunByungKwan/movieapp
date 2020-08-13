package ybk.org.movieapp.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.widget.Toast;

public class Network {

    /** 인터넷이 연결되어 있는지 확인 */
    public static boolean isConnected() {
        if(getStatus() == 0 || getStatus() == -1) {
            return false;
        } else {
            return true;
        }
    }

    /** 네트워크 상태값 반환 */
    public static int getStatus() {
        ConnectivityManager cm = (ConnectivityManager) App.getInstance().getSystemService(
                Context.CONNECTIVITY_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            return getStatusMoreThanVersionM(cm);
        } else {
            return getStatusLessThanVersionM(cm);
        }
    }

    private static int getStatusMoreThanVersionM(ConnectivityManager cm) {
        if(cm != null) {
            NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());

            if(nc != null) {
                if(nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return Constants.NET_CELLULAR;
                } else if(nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return Constants.NET_WIFI;
                } else {
                    return Constants.NET_DISCONNCTED;
                }
            } else {
                return Constants.NET_DISCONNCTED;
            }
        } else {
            return 0;
        }
    }

    private static int getStatusLessThanVersionM(ConnectivityManager cm) {
        NetworkInfo info = cm.getActiveNetworkInfo();

        if(info != null) {

            int type = info.getType();

            if(type == ConnectivityManager.TYPE_MOBILE) {
                return Constants.NET_CELLULAR;
            } else if(type == ConnectivityManager.TYPE_WIFI) {
                return Constants.NET_WIFI;
            } else {
                return Constants.NET_DISCONNCTED;
            }
        }

        return -1;
    }

    /** 네트워크 상태를 보여주는 토스트 메시지 */
    public static void showToast(Context context) {
        if(isConnected()) {
            Toast.makeText(context, Constants.MSG_NET_OK, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, Constants.MSG_NET_FAIL, Toast.LENGTH_SHORT).show();
        }
    }

}
