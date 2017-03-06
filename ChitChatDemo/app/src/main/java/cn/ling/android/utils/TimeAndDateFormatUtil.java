package cn.ling.android.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.content.Context;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class TimeAndDateFormatUtil {

    private static final Context appContext = ApplicationInfo
            .getApplicatonContext();

    private static final SimpleDateFormat FORMATTER_ZH = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    private static final SimpleDateFormat FORMATTER_EN = new SimpleDateFormat(
            "EEE, dd MMM yyyy HH:mm:ss Z", Locale.ENGLISH);

    public static final SimpleDateFormat FORMATTER_yMdHms = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    public static final SimpleDateFormat FORMATTER_yMdHm = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm");

    public static final SimpleDateFormat FORMATTER_yMdH = new SimpleDateFormat(
            "yyyy-MM-dd HH");

    public static final SimpleDateFormat FORMATTER_yMd = new SimpleDateFormat(
            "yyyy-MM-dd");

    public static final SimpleDateFormat SUB_CONTENT_DATE_FORMAT = new SimpleDateFormat(
            "yyyyMMdd");

    public static final SimpleDateFormat FORMATER_yM = new SimpleDateFormat(
            "yyyy-MM");

    public static final SimpleDateFormat FORMATTER_y = new SimpleDateFormat(
            "yyyy");

    public static final SimpleDateFormat FORMATTER_Md = new SimpleDateFormat(
            "MM-dd");

    public static final SimpleDateFormat FORMATTER_MdHm = new SimpleDateFormat(
            "MM-dd HH:mm");

    public static final SimpleDateFormat FORMATTER_Hms = new SimpleDateFormat(
            "HH:mm:ss");

    public static final SimpleDateFormat FORMATTER_Hm = new SimpleDateFormat(
            "HH:mm");

    public static final SimpleDateFormat FORMATTER_M = new SimpleDateFormat(
            "mm");

    /**
     * @param aDateStr
     * @return
     */
    public static String formateDate_MdHm(String aDateStr) {
        String dateStr = null;
        try {
            Date date = FORMATTER_EN.parse(aDateStr.trim());
            dateStr = FORMATTER_MdHm.format(date);
        } catch (Exception e) {
            try {
                Date date = FORMATTER_ZH.parse(aDateStr.trim());
                dateStr = FORMATTER_MdHm.format(date);
            } catch (Exception e1) {
                dateStr = aDateStr;
            }
        }
        return dateStr;
    }

    /**
     * 获取当前的系统时间精确到毫秒
     *
     * @return
     */
    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前的系统时间精确到秒
     *
     * @return
     */
    public static long getCurrentTimeOfSeconds() {
        return System.currentTimeMillis() / 1000;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate_yMdHms(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_yMdHms.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String formatDate_yMdHms(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATTER_yMdHms.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy-MM-dd HH:mm
     */
    public static String formateDate_yMdHm(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_yMdHm.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyy-MM-dd HH:mm
     */
    public static String formatDate_yMdHm(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATTER_yMdHm.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy-MM-dd HH
     */
    public static String formateDate_yMdH(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_yMdH.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyy-MM-dd HH
     */
    public static String formatDate_yMdH(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATTER_yMdH.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy-MM-dd
     */
    public static String formateDate_yMd(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_yMd.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyy-MM-dd
     */
    public static String formateDate_yMd(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATTER_yMd.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyyMMdd
     */
    public static String formateDateSub_yMd(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = SUB_CONTENT_DATE_FORMAT.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyyMMdd
     */
    public static String formateDateSub_yMd(Date date) {
        String dateStr = null;
        try {
            dateStr = SUB_CONTENT_DATE_FORMAT.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy-MM
     */
    public static String formateDate_yM(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATER_yM.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return yyyy-MM
     */
    public static String formateDate_yM(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATER_yM.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return MM-dd
     */
    public static String formateDate_Md(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_Md.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param date Date
     * @return MM-dd
     */
    public static String formateDate_Md(Date date) {
        String dateStr = null;
        try {
            dateStr = FORMATTER_Md.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

    /**
     * 格式化时间
     *
     * @param time long
     * @return yyyy
     */
    public static String formateDate_y(long time) {
        String dateStr = null;
        try {
            Date date = new Date(time);
            dateStr = FORMATTER_y.format(date);
        } catch (Exception e) {
            dateStr = "";
        }
        return dateStr;
    }

}
