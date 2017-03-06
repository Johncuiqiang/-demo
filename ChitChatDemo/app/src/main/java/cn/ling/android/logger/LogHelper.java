package cn.ling.android.logger;

import android.util.Log;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */



public class LogHelper {

    private static final String TAG = "LingCommonSdk";
    private static final String TAG_LOG_UI = "LingCommonSdk-Ui";
    private static final String TAG_LOG_NET = "LingCommonSdk-Net";
    private static final String TAG_LOG_DATA = "LingCommonSdk-Data";
    private static final String TAG_LOG_PARSER = "LingCommonSdk-Parser";
    private static final String TAG_LOG_IAMGE = "LingCommonSdk-Image";
    private static final String TAG_LOG_AD = "LingCommonSdk-Ad";
    private static final String TAG_LOG_TEST = "LingCommonSdk-Test";
    private static final String TAG_LOG_CRASH = "LingCommonSdk-Crash";

    public static final LogHelper UI = new LogHelper(TAG_LOG_UI);
    public static final LogHelper NET = new LogHelper(TAG_LOG_NET);
    public static final LogHelper DATA = new LogHelper(TAG_LOG_DATA);
    public static final LogHelper PARSER = new LogHelper(TAG_LOG_PARSER);
    public static final LogHelper IMAGE = new LogHelper(TAG_LOG_IAMGE);
    public static final LogHelper AD = new LogHelper(TAG_LOG_AD);
    public static final LogHelper TEST = new LogHelper(TAG_LOG_TEST);
    public static final LogHelper CRASH = new LogHelper(TAG_LOG_CRASH);
    private String mTag;

    private LogHelper(String tag) {
        this.mTag = tag;
    }

    public void debug(String msg) {
        d(mTag, msg);
    }

    public void debug(String msg, Throwable tr) {
        d(mTag, msg, tr);
    }

    public void error(String msg) {
        e(mTag, msg);
    }

    public void error(String msg, Throwable tr) {
        e(mTag, msg, tr);
    }

    public void info(String msg) {
        i(mTag, msg);
    }

    public void info(String msg, Throwable tr) {
        i(mTag, msg, tr);
    }

    public void verbose(String msg) {
        v(mTag, msg);
    }

    public void verbose(String msg, Throwable tr) {
        v(mTag, msg, tr);
    }

    public void warn(String msg) {
        w(mTag, msg);
    }

    public void warn(String msg, Throwable tr) {
        w(mTag, msg, tr);
    }

    private static void d(String tag, String msg) {
        if (ApplicationInfo.isDebug()) {
            Log.d(tag, msg);
        }
    }

    private static void d(String tag, String msg, Throwable tr) {
        if (ApplicationInfo.isDebug()) {
            Log.d(tag, msg, tr);
        }
    }

    private static void e(String tag, String msg) {
        if (ApplicationInfo.isDebug()) {
            Log.e(tag, msg);
        }
    }

    private static void e(String tag, String msg, Throwable tr) {
        Log.e(tag, msg, tr);
    }

    private static void i(String tag, String msg) {
        if (ApplicationInfo.isDebug()) {
            Log.i(tag, msg);
        }
    }

    private static void i(String tag, String msg, Throwable tr) {
        if (ApplicationInfo.isDebug()) {
            Log.i(tag, msg, tr);
        }
    }

    private static void v(String tag, String msg) {
        if (ApplicationInfo.isDebug()) {
            Log.v(tag, msg);
        }
    }

    private static void v(String tag, String msg, Throwable tr) {
        if (ApplicationInfo.isDebug()) {
            Log.v(tag, msg, tr);
        }
    }

    private static void w(String tag, String msg) {
        if (ApplicationInfo.isDebug()) {
            Log.w(tag, msg);
        }
    }

    private static void w(String tag, String msg, Throwable tr) {
        if (ApplicationInfo.isDebug()) {
            Log.w(tag, msg, tr);
        }
    }
}
