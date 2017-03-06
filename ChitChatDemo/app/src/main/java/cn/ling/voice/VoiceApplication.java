package cn.ling.voice;

import android.app.Application;
import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import cn.ling.android.ApplicationInfo;
import cn.ling.android.network.HttpFrameManager;
import cn.ling.android.receiver.ConnectivityReceiver;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class VoiceApplication extends Application {

    private static Context mApplicationContext;
    private ConnectivityReceiver mConnectivityReceiver;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = getApplicationContext();
        ApplicationInfo.init(this);
        ApplicationInfo.setDebug(true);
        registConnectivityReceiver();
        initVoice();
    }

    private void registConnectivityReceiver() {
        mConnectivityReceiver = new ConnectivityReceiver();
        final IntentFilter filter = new IntentFilter();
        filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mConnectivityReceiver, filter);
        HttpFrameManager.getInstance().initHttpFrame();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        unRegistConnectivityReceiver();
    }

    private void unRegistConnectivityReceiver() {
        if (mConnectivityReceiver != null) {
            try {
                unregisterReceiver(mConnectivityReceiver);
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
    }

    public static Context getAppContext(){
        return mApplicationContext;
    }

    private void initVoice(){
        //此接口在非主进程调用会返回null对象，如需在非主进程使用语音功能，
        //请使用参数:SpeechConstant.APPID +"=5858c1ae," + SpeechConstant.FORCE_LOGIN +"=true"。
        SpeechUtility.createUtility(this, SpeechConstant.APPID + "=5858c1ae");
    }
}
