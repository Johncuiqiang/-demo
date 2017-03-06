package cn.ling.android.utils;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.provider.MediaStore;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import cn.ling.android.ApplicationInfo;
import cn.ling.voice.constants.ConstantDatas;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class Util {

    private static String USER_AGENT;

    public static String getUserAgent() {
        if (null == USER_AGENT) {
            synchronized (Util.class) {
                if (null == USER_AGENT) {
                    USER_AGENT = String.format("%s__%s__%s__%s__%s",
                            android.os.Build.MODEL.replace(" ", "_"),
                            ConstantDatas.PRODUCT_NAME,
                            ApplicationInfo.getVersionName(),
                            ConstantDatas.PLATFORM, "os"
                                    + android.os.Build.VERSION.RELEASE);
                }
            }
        }

        return USER_AGENT;
    }

    /**
     * 隐藏输入法
     */
    public static void dismissInputMethod(Context context) {
        InputMethodManager mInputMethod = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (mInputMethod.isActive()) {
            Activity activiy = (Activity) context;
            if (activiy != null) {
                View currentFocus = activiy.getCurrentFocus();
                if (currentFocus != null) {
                    IBinder windowToken = currentFocus.getWindowToken();
                    if (windowToken != null) {
                        mInputMethod.hideSoftInputFromWindow(windowToken,
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    }
                }

            }
        }

    }

    /**
     * @Title: awakenInputMethod
     * @Description: 唤出输入法
     * @param @param context
     * @return void
     * @throws
     */
    public static void awakenInputMethod(Context context) {
        InputMethodManager mInputMethod = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (!mInputMethod.isActive()) {
            Activity activiy = (Activity) context;
            if (activiy != null) {
                View currentFocus = activiy.getCurrentFocus();
                if (currentFocus != null) {
                    IBinder windowToken = currentFocus.getWindowToken();
                    if (windowToken != null) {
                        mInputMethod.showSoftInputFromInputMethod(windowToken,
                                InputMethodManager.SHOW_FORCED);
                    }
                }
            }
        }
    }



    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public static long getCurrentTime1000() {
        return System.currentTimeMillis() / 1000;
    }



    /**
     * 打开相机
     *
     * @param context
     * @param filePath
     *            文件名
     * @param resultCode
     *            返回的code
     */
    public static void openCamera(Context context, String filePath,
                                  int resultCode) {
        Activity act = (Activity) context;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(filePath)));
        act.startActivityForResult(intent, resultCode);
    }

    /**
     * 手机号判定
     *
     * @param mobiles
     * @return
     */
    public static boolean isMobileNO(String mobiles) {
        // Pattern p =
        // Pattern.compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
        Pattern p = Pattern.compile("1[0-9]{10}");
        Matcher m = p.matcher(mobiles);
        return m.matches();
    }

    public static String translateToHexStringWithBracket(byte[] bytes) {
        if (null == bytes) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        String hex = null;
        final int len = bytes.length;
        sb.append('[');
        for (int i = 0; i < len; i++) {
            hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
            if (i != len - 1) {
                sb.append(", ");
            }
        }
        sb.append(']');

        return sb.toString();
    }
}
