package cn.ling.voice.chitchat.asr;

import android.content.Context;

/**
 * Created by cuiqiang on 2016/12/20.
 * ASR主要方法管理类
 * <p>
 * init(Context context, String type) 初始化asr功能
 * startRecognizer(ASRCallBackListener asrCallBackListener) 开始语音听写方法
 * onDestroy() 结束语音听写并释放资源
 */
public class ASRManager {

    private static ASRManager INSTANCE;

    private Context mContext;
    private ASRTool mASRTool;

    public static ASRManager getInstance() {
        if (null == INSTANCE) {
            synchronized (ASRManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ASRManager();
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
     *                暂只支持云端
     */
    public void init(Context context, String type) {
        mContext = context;
        mASRTool = new ASRTool(mContext, ASRConstants.ASR_TYPE_CLOUD);
    }

    /**
     * 开始语音听写方法
     *
     * @param asrCallBackListener 语音听写的回调监听
     */
    public void startRecognizer(ASRTool.ASRCallBackListener asrCallBackListener) {
        mASRTool.startRecognizer(asrCallBackListener);
    }

    //结束语音听写并释放资源
    public void onDestroy() {
        mASRTool.onDestroy();
    }

}
