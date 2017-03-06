package cn.ling.voice.chitchat.tts;

import android.content.Context;

/**
 * Created by cuiqiang on 2016/12/20.
 * TTS主要方法管理类
 * <p>
 * init(Context context, String type) 初始化tts功能
 * startRecognizer(String text,TTSListener ttsListener) 开始语音合成方法
 * onDestroy() 结束语音合成并释放资源
 * pauseTTS()  暂停播放
 * cancelTTS() 取消播放
 * resumeTTS() 继续播放
 */
public class TTSManager {

    private static TTSManager INSTANCE;

    private Context mContext;
    private TTSTool mTTSTool;

    public static TTSManager getInstance() {
        if (null == INSTANCE) {
            synchronized (TTSManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new TTSManager();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 初始化方法
     *
     * @param context
     * @param type    初始化引擎类型
     *                支持本地合成，云端合成
     */
    public void init(Context context, String type) {
        mContext = context;
        mTTSTool = new TTSTool(mContext, type);
    }

    /**
     * 开始语音合成
     *
     * @param text        语音合成的字符串
     * @param ttsCallBackListener 语音合成的回调监听
     */
    public void startSpeeh(String text, TTSTool.TTSCallBackListener ttsCallBackListener) {
        mTTSTool.startSpeeh(text, ttsCallBackListener);
    }

    //取消播放
    public void cancelTTS() {
        mTTSTool.cancelTTS();
    }

    //暂停播放
    public void pauseTTS() {
        mTTSTool.pauseTTS();
    }

    //继续播放
    public void resumeTTS() {
        mTTSTool.resumeTTS();
    }

    // 释放资源
    public void onDestroy() {
        mTTSTool.onDestroy();
    }

}
