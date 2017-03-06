package cn.ling.voice.chitchat;

import android.content.Context;

import com.iflytek.cloud.SpeechError;

import cn.ling.android.threadpool.ATask;
import cn.ling.android.threadpool.DefaultNoNetworkTask;
import cn.ling.android.threadpool.ThreadPool;
import cn.ling.voice.chitchat.asr.ASRConstants;
import cn.ling.voice.chitchat.asr.ASRManager;
import cn.ling.voice.chitchat.asr.ASRTool;
import cn.ling.voice.chitchat.nlu.INLUNetRequest;
import cn.ling.voice.chitchat.nlu.NLUNetRequest;
import cn.ling.voice.chitchat.nlu.NLUResultEntity;
import cn.ling.voice.chitchat.tts.TTSConstants;
import cn.ling.voice.chitchat.tts.TTSManager;
import cn.ling.voice.chitchat.tts.TTSTool;

/**
 * Created by cuiqiang on 2016/12/21.
 * 闲聊逻辑处理类
 * <p>
 * init(Context context) 初始化闲聊逻辑
 * startChat() 开始闲聊
 * stopChat()  停止闲聊
 * asrListener  语音听写监听
 * ttsListener  语音合成监听
 * nluNetRequestCallBack nlu网络请求
 */

public class ChitChatManager {

    private static ChitChatManager INSTANCE;

    private Context mContext;
    private NLUNetRequest mNLUNetRequest;

    public static ChitChatManager getInstance() {
        if (null == INSTANCE) {
            synchronized (ASRManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new ChitChatManager();
                }
            }
        }
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
    }

    //开始闲聊
    public void startChat() {
        ATask task = new DefaultNoNetworkTask() {
            @Override
            public void run() {
                startChitChat();
            }
        };
        task.setPiority(ATask.TASK_LEVEL_HIGH);
        ThreadPool.getInstance().addTask(task);
    }

    //停止闲聊
    public void stopChat() {
        ASRManager.getInstance().onDestroy();
        TTSManager.getInstance().onDestroy();
        ThreadPool.getInstance().cancelAllTask();
    }

    //闲聊逻辑
    private void startChitChat() {
        mNLUNetRequest = new NLUNetRequest(mContext);
        ASRManager.getInstance().init(mContext, ASRConstants.ASR_TYPE_CLOUD);
        TTSManager.getInstance().init(mContext, TTSConstants.TTS_TYPE_CLOUD);
        ASRManager.getInstance().startRecognizer(asrListener);

    }

    //语音听写
    private ASRTool.ASRCallBackListener asrListener = new ASRTool.ASRCallBackListener() {
        @Override
        public void onResult(String text) {
            mNLUNetRequest.startNLUNetRequest(text, nluNetRequestCallBack);
        }
    };

    //NLU网络请求
    private NLUNetRequest.NLUNetRequestCallBack nluNetRequestCallBack = new INLUNetRequest.NLUNetRequestCallBack() {
        @Override
        public void success(NLUResultEntity resultEntity,String data) {
            String answer = resultEntity.getAnswer();
            TTSManager.getInstance().startSpeeh(answer, ttsListener);
        }

        @Override
        public void error(Exception exception) {

        }
    };

    //语音合成
    private TTSTool.TTSCallBackListener ttsListener = new TTSTool.TTSCallBackListener() {

        // 播放完成
        @Override
        public void onCompleted(SpeechError error) {
            ASRManager.getInstance().startRecognizer(asrListener);
        }
    };

}
