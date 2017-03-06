package cn.ling.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class NetStatusUtil {

    public static final int NO_NETWORK = 0;
    public static final int WIFI_NETWORK = 1;
    public static final int MOBILE_NETWORK = 2;
    private static int NET_STATUS = 0;


    public static void changed() {
        int netStatus = 0;
        ConnectivityManager connectivityManager = (ConnectivityManager) ApplicationInfo.getApplicatonContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if ((networkInfo != null) && (networkInfo.isConnectedOrConnecting())) {
            int type = networkInfo.getType();
            String typeName = networkInfo.getTypeName();
            System.out.println("type: " + type + "  typeName: " + typeName);
            if (typeName.equalsIgnoreCase("WIFI")) {
                netStatus = 1;
            }else if (typeName.equalsIgnoreCase("MOBILE")) {
                netStatus = 2;
            }
        }
        setStatus(netStatus);
    }

    public static boolean hasNetwork(){
        return NET_STATUS != 0;
    }

    public static boolean isWifi() {
        return NET_STATUS == 1;
    }

    public static boolean is2gOr3g() {
        return NET_STATUS == 2;
    }

    private static void setStatus(int status) {
        NET_STATUS = status;
    }
}
