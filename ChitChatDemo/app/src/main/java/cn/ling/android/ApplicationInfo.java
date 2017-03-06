package cn.ling.android;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class ApplicationInfo {
    private static String sVersionName;
    private static int sVersionCode;
    private static String sPackageName;
    public static Context sApplicationContext;

    private static boolean sIsDebug;

    public static void init(Application application) {
        sApplicationContext = application.getApplicationContext();
        PackageManager packageManager = application.getPackageManager();
        android.content.pm.ApplicationInfo applicationInfo = application
                .getApplicationInfo();
        sPackageName = applicationInfo.packageName;
        try {
            sVersionName = packageManager.getPackageInfo(sPackageName, 0).versionName;
            sVersionCode = packageManager.getPackageInfo(sPackageName, 0).versionCode;
        } catch (NameNotFoundException e) {
            sVersionName = "";
            e.printStackTrace();
        }
    }

    /**
     * 获取application Context
     *
     * @return
     */
    public static Context getApplicatonContext() {
        return sApplicationContext;
    }

    /**
     * 获取版本名
     *
     * @return
     */
    public static String getVersionName() {
        return sVersionName;
    }

    /**
     * 获取版本号
     *
     * @return
     */
    public static int getVersionCode() {
        return sVersionCode;
    }

    /**
     * 获取应用包名
     *
     * @return
     */
    public static String getPackageName() {
        return sPackageName;
    }

    public static void setDebug(boolean flag) {
        sIsDebug = flag;
    }

    public static boolean isDebug() {
        return sIsDebug;
    }
}
