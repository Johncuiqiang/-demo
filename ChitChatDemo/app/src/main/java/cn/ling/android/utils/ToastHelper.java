package cn.ling.android.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Looper;
import android.util.SparseArray;
import android.widget.Toast;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class ToastHelper {

    private static ToastHelper INSTANCE;
    private Context mAppContext;
    private SparseArray<Toast> mSparseArray;

    public static ToastHelper getInstance() {
        if (null == INSTANCE) {
            synchronized (ToastHelper.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ToastHelper();
                }
            }
        }
        return INSTANCE;
    }

    private ToastHelper() {
        mAppContext = ApplicationInfo.getApplicatonContext();
        mSparseArray = new SparseArray<Toast>();
    }

    @SuppressLint("ShowToast")
    public Toast obtainToast() {
        Toast toast = null;
        Looper looper = Looper.myLooper();
        if (looper == null) {
            looper = Looper.getMainLooper();
        }
        int hashCode = looper.hashCode();
        toast = mSparseArray.get(hashCode);
        if (toast == null) {
            toast = Toast.makeText(mAppContext, "", Toast.LENGTH_SHORT);
            this.mSparseArray.put(hashCode, toast);
        }
        return toast;
    }

    public void showToast(String str) {
        Toast toast = obtainToast();
        try {
            toast.setText(str);
            toast.show();
        } catch (Exception e) {

        }
    }

    public void showToast(int resId) {
        Toast toast = obtainToast();
        try {
            toast.setText(resId);
            toast.show();
        } catch (Exception e) {

        }
    }

    public void remove() {
        this.mSparseArray.remove(Looper.myLooper().hashCode());
    }
}
