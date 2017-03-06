package cn.ling.voice.chitchat.tts;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechSynthesizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.SynthesizerListener;
import com.iflytek.cloud.util.ResourceUtil;

/**
 * Created by cuiqiang on 2016/12/20.
 * tts主要逻辑处理类
 * <p>
 * TTSTool(Context context, String type) 初始化tts功能
 * setEngineType()选择初始化引擎类型
 * startSpeeh(String text, TTSCallBackListener ttsCallBackListener) 开始语音合成并播放
 * cancelTTS() 取消播放
 * resumeTTS() 继续播放
 * pauseTTS() 暂停播放
 * setParam() 根据引擎类型设置参数
 * getResourcePath() 获取发音人资源路径
 * onDestroy() 结束并释放资源
 * mTtsInitListener 初始化监听
 * synthesizerListener 语音合成监听
 * mTTSListener 语音合成回调监听
 */
public class TTSTool {

    private String mEngineType;  // 引擎类型
    private String voicerCloud;  // 默认云端发音人
    private String voicerLocal;  // 默认本地发音人

    private Context mContext;
    private SpeechSynthesizer mTts;//合成对象
    private TTSCallBackListener mTTSCallBackListener;

    public TTSTool(Context context, String type) {
        mContext = context;
        voicerCloud = TTSConstants.VOICER_CLOUD_DEFAULT;
        voicerLocal = TTSConstants.VOICER_lOCAL_DEFAULT;
        mEngineType = type;
        setEngineType();
    }

    //根据引擎初始化本地合成还是云端合成
    private void setEngineType() {
        if (mEngineType == SpeechConstant.TYPE_CLOUD) {
            mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);
        } else if (mEngineType == SpeechConstant.TYPE_LOCAL) {
            mTts = SpeechSynthesizer.createSynthesizer(mContext, mTtsInitListener);
            String path = ResourceUtil.TTS_RES_PATH + "=" + ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet") + "," + ResourceUtil.ENGINE_START + "=tts";
            SpeechUtility.getUtility().setParameter(ResourceUtil.ENGINE_START, path);
        }
    }

    //开始播放
    public void startSpeeh(String text, TTSCallBackListener ttsCallBackListener) {
        setParam();
        mTTSCallBackListener = ttsCallBackListener;
        int code = mTts.startSpeaking(text, synthesizerListener);
        if (code != ErrorCode.SUCCESS) {
            Toast.makeText(mContext, "语音合成失败,错误码：" + code, Toast.LENGTH_SHORT).show();
        }
    }

    //取消播放
    public void cancelTTS() {
        mTts.stopSpeaking();
    }

    //暂停播放
    public void pauseTTS() {
        mTts.pauseSpeaking();
    }

    //继续播放
    public void resumeTTS() {
        mTts.resumeSpeaking();
    }

    //语音合成
    private SynthesizerListener synthesizerListener = new SynthesizerListener() {

        //开始播放
        @Override
        public void onSpeakBegin() {

        }

        //暂停播放
        @Override
        public void onSpeakPaused() {

        }

        //继续播放
        @Override
        public void onSpeakResumed() {

        }

        // 合成进度
        @Override
        public void onBufferProgress(int percent, int beginPos, int endPos, String info) {
        }

        // 播放进度
        @Override
        public void onSpeakProgress(int percent, int beginPos, int endPos) {

        }

        // 播放完成
        @Override
        public void onCompleted(SpeechError error) {
            mTTSCallBackListener.onCompleted(error);
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {

        }
    };

    /**
     * 回调接口
     * onCompleted(SpeechError error)语音合成结束
     * error错误码
     */
    public interface TTSCallBackListener {

        void onCompleted(SpeechError error);
    }

    /**
     * 初始化监听。
     */
    private InitListener mTtsInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            if (code != ErrorCode.SUCCESS) {
                Toast.makeText(mContext, "初始化失败,错误码：" + code, Toast.LENGTH_SHORT).show();
            } else {
                // 初始化成功，之后可以调用startSpeaking方法
                // 注：有的开发者在onCreate方法中创建完合成对象之后马上就调用startSpeaking进行合成，
                // 正确的做法是将onCreate中的startSpeaking调用移至这里
            }
        }
    };

    //获取发音人资源路径
    private String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        //合成通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "tts/common.jet"));
        tempBuffer.append(";");
        //发音人资源
        tempBuffer.append(ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "tts/" + voicerLocal + ".jet"));
        return tempBuffer.toString();
    }

    /**
     * 参数设置
     *
     * @param
     * @return
     */
    private void setParam() {
        //清空参数
        mTts.setParameter(SpeechConstant.PARAMS, null);
        //设置合成
        if (mEngineType.equals(SpeechConstant.TYPE_CLOUD)) {
            //设置使用云端引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerCloud);
        } else {
            //设置使用本地引擎
            mTts.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_LOCAL);
            //设置发音人资源路径
            mTts.setParameter(ResourceUtil.TTS_RES_PATH, getResourcePath());
            //设置发音人
            mTts.setParameter(SpeechConstant.VOICE_NAME, voicerLocal);
        }
        // 设置播放合成音频打断音乐播放，默认为true
        mTts.setParameter(SpeechConstant.KEY_REQUEST_FOCUS, "true");
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mTts.setParameter(SpeechConstant.SPEED, TTSConstants.TTS_SET_SPEED);//设置合成语速
        mTts.setParameter(SpeechConstant.PITCH, TTSConstants.TTS_SET_PITCH);//设置合成音调
        mTts.setParameter(SpeechConstant.VOLUME, TTSConstants.TTS_SET_VOLUME);//设置合成音量
        mTts.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mTts.setParameter(SpeechConstant.TTS_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/tts.wav");

    }

    // 退出时释放连接
    public void onDestroy() {
        if (mTts!=null){
            mTts.stopSpeaking();
            mTts.destroy();
        }
    }
}
