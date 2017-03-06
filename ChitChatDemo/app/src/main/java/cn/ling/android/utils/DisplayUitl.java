package cn.ling.android.utils;

import android.content.Context;
import android.util.DisplayMetrics;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DisplayUitl {

    private static DisplayMetrics sDisplayMetrics = ApplicationInfo.sApplicationContext.getResources().getDisplayMetrics();

    public static int dip2px(Context context, float dpValue) {
        return (int)(dpValue * sDisplayMetrics.density + 0.5F);
    }

    public static int px2dip(float pxValue) {
        return (int)(pxValue / sDisplayMetrics.density + 0.5F);
    }

    public static int sp2px(float spValue){
        return (int)(spValue * sDisplayMetrics.scaledDensity + 0.5F);
    }

    public static int px2sp(float pxValue) {
        return (int)(pxValue / sDisplayMetrics.scaledDensity + 0.5F);
    }
}
