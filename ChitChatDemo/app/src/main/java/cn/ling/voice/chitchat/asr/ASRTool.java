package cn.ling.voice.chitchat.asr;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;
import com.iflytek.cloud.ErrorCode;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.util.ResourceUtil;
import cn.ling.voice.chitchat.util.JsonParser;
import cn.ling.voice.chitchat.util.XmlParser;
import static android.content.Context.MODE_PRIVATE;

/**
 * Created by cuiqiang on 2016/12/20.
 * asr主要逻辑处理类
 * <p>
 * ASRTool(Context context, String type) 初始化asr功能
 * setEngineType() 设置引擎类型
 * startRecognizer(ASRCallBackListener asrCallBackListener) 开始语音听写
 * getResourcePath() 识别资源路径
 * onDestroy() 结束并释放资源
 * setParam()  根据引擎设置参数
 * mInitListener 初始化监听器
 * mRecognizerListener 识别监听器
 * ASRListener 语音听写回调监听
 */
public class ASRTool {

    private Context mContext;
    private SpeechRecognizer mAsr;// 语音识别对象
    private SharedPreferences mSharedPreferences;//缓存
    private StringBuffer mStringBuffer = new StringBuffer();
    private ASRCallBackListener mASRCallBackListener;

    private int ret = 0;// 函数调用返回值
    private String mResultType = ASRConstants.ASR_TYPE_RETURN; // 返回结果格式，支持：xml,json
    private String mEngineType = ASRConstants.ASR_TYPE_CLOUD;// 默认引擎
    private String resPath = ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "asr/common.jet") + ";" + ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "asr/src_16k.jet");

    public ASRTool(Context context, String type) {
        mContext = context;
        // 初始化识别对象
        mAsr = SpeechRecognizer.createRecognizer(mContext, mInitListener);
        mEngineType = type;
        mSharedPreferences = mContext.getSharedPreferences(mContext.getPackageName(), MODE_PRIVATE);
        setEngineType();
    }

    //设置引擎类型
    private void setEngineType() {
        if (mEngineType == SpeechConstant.TYPE_LOCAL) {
            StringBuffer param = new StringBuffer();
            //加载识别本地资源，resPath为本地识别资源路径
            param.append(ResourceUtil.ASR_RES_PATH + "=" + resPath);
            param.append("," + ResourceUtil.ENGINE_START + "=" + SpeechConstant.ENG_ASR);
            boolean ret = SpeechUtility.getUtility().setParameter(ResourceUtil.ENGINE_START, param.toString());
        }
    }

    //开始识别
    public void startRecognizer(ASRCallBackListener asrCallBackListener) {
        setParam();
        mASRCallBackListener = asrCallBackListener;
        ret = mAsr.startListening(mRecognizerListener);
        if (ret != ErrorCode.SUCCESS) {
            Toast.makeText(mContext, "识别失败,错误码" + ret, Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 初始化监听器。
     */
    private InitListener mInitListener = new InitListener() {

        @Override
        public void onInit(int code) {
         if (code != ErrorCode.SUCCESS) {
             Toast.makeText(mContext, "初始化失败,错误码" + code, Toast.LENGTH_SHORT).show();
         }
        }
    };

    /**
     * 识别监听器。
     */
    private RecognizerListener mRecognizerListener = new RecognizerListener() {

        @Override
        public void onVolumeChanged(int volume, byte[] data) {
            Log.d("1111", "volume" + volume);
        }

        @Override
        public void onResult(final RecognizerResult result, boolean isLast) {
            if (null != result && !TextUtils.isEmpty(result.getResultString())) {
                String text = "";
                if (mResultType.equals("json")) {
                    text = JsonParser.parseJsonResult(result.getResultString());
                } else if (mResultType.equals("xml")) {
                    text = XmlParser.parseNluResult(result.getResultString());
                }
                // 显示
                mStringBuffer.append(text);
                if (isLast) {
                    String str = mStringBuffer.toString();
                    if (mASRCallBackListener != null) {
                        mASRCallBackListener.onResult(str);
                        mStringBuffer.delete(0, mStringBuffer.length() - 1);
                    }
                }
            } else {
                mASRCallBackListener.onResult(null);
            }
        }

        @Override
        public void onEndOfSpeech() {
            // 此回调表示：检测到了语音的尾端点，已经进入识别过程，不再接受语音输入
            Toast.makeText(mContext, "结束说话", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onBeginOfSpeech() {
            // 此回调表示：sdk内部录音机已经准备好了，用户可以开始语音输入
            Toast.makeText(mContext, "开始说话", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onError(SpeechError error) {
            int errorCode = error.getErrorCode();
            if (errorCode != 10118) {
                Toast.makeText(mContext, "onError Code：" + errorCode, Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onEvent(int eventType, int arg1, int arg2, Bundle obj) {
            // 以下代码用于获取与云端的会话id，当业务出错时将会话id提供给技术支持人员，可用于查询会话日志，定位出错原因
            // 若使用本地能力，会话id为null
            //	if (SpeechEvent.EVENT_SESSION_ID == eventType) {
            //		String sid = obj.getString(SpeechEvent.KEY_EVENT_SESSION_ID);
            //		Log.d(TAG, "session id =" + sid);
            //	}
        }
    };

    //获取识别资源路径
    private String getResourcePath() {
        StringBuffer tempBuffer = new StringBuffer();
        //识别通用资源
        tempBuffer.append(ResourceUtil.generateResourcePath(mContext, ResourceUtil.RESOURCE_TYPE.assets, "asr/common.jet"));
        //识别8k资源-使用8k的时候请解开注释
        // tempBuffer.append(";");
        // tempBuffer.append(ResourceUtil.generateResourcePath(this, RESOURCE_TYPE.assets, "asr/common_8k.jet"));
        return tempBuffer.toString();
    }

    /**
     * asr回调接口
     * onResult 语音听写结果
     */
    public interface ASRCallBackListener {

        void onResult(String text);
    }

    /**
     * 参数设置
     *
     * @param
     * @return
     */
    public boolean setParam() {
        boolean result = false;
        // 清空参数
        mAsr.setParameter(SpeechConstant.PARAMS, null);
        // 设置识别引擎
        mAsr.setParameter(SpeechConstant.ENGINE_TYPE, mEngineType);
        if ("cloud".equalsIgnoreCase(mEngineType)) {
            String grammarId = mSharedPreferences.getString(ASRConstants.KEY_GRAMMAR_ABNF_ID, null);
            if (TextUtils.isEmpty(grammarId)) {
                result = false;
            } else {
                // 设置返回结果格式
                mAsr.setParameter(SpeechConstant.RESULT_TYPE, mResultType);
                // 设置云端识别使用的语法id
                mAsr.setParameter(SpeechConstant.CLOUD_GRAMMAR, grammarId);
                result = true;
            }
        } else {
            mAsr.setParameter(ResourceUtil.ASR_RES_PATH, resPath);
            // 设置本地识别资源
            mAsr.setParameter(ResourceUtil.ASR_RES_PATH, getResourcePath());
            // 设置返回结果格式
            mAsr.setParameter(SpeechConstant.RESULT_TYPE, mResultType);
            // 设置本地识别使用语法id
            mAsr.setParameter(SpeechConstant.LOCAL_GRAMMAR, "call");
            // 设置识别的门限值
            mAsr.setParameter(SpeechConstant.MIXED_THRESHOLD, ASRConstants.ASR_MIXED_THRESHOLD);
            // 使用8k音频的时候请解开注释
            // mAsr.setParameter(SpeechConstant.SAMPLE_RATE, "8000");
            result = true;
        }
        // 设置语言
        mAsr.setParameter(SpeechConstant.LANGUAGE, ASRConstants.ASR_TYPE_LANGUAGE);
        // 设置语言区域
        mAsr.setParameter(SpeechConstant.ACCENT, ASRConstants.ASR_SPEECH_ACCENT);
        // 设置语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
        mAsr.setParameter(SpeechConstant.VAD_BOS, ASRConstants.ASR_SPEECH_VADBOS);
        // 设置语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
        mAsr.setParameter(SpeechConstant.VAD_EOS, ASRConstants.ASR_SPEECH_VADEOS);
        // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
        mAsr.setParameter(SpeechConstant.ASR_PTT, ASRConstants.ASR_SPEECH_PTT);
        // 设置音频保存路径，保存音频格式支持pcm、wav，设置路径为sd卡请注意WRITE_EXTERNAL_STORAGE权限
        // 注：AUDIO_FORMAT参数语记需要更新版本才能生效
        mAsr.setParameter(SpeechConstant.AUDIO_FORMAT, "wav");
        mAsr.setParameter(SpeechConstant.ASR_AUDIO_PATH, Environment.getExternalStorageDirectory() + "/msc/asr.wav");

        return result;
    }

    // 退出时释放连接
    public void onDestroy() {
        if (mAsr!=null){
            mAsr.cancel();
            mAsr.destroy();
        }
    }
}
