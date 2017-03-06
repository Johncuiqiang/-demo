package cn.ling.voice.chitchat.asr;

/**
 * Created by cuiqiang on 2016/12/20.
 */

public class ASRConstants {

    public static final String ASR_TYPE_LOCAL = "local"; // 本地听写
    public static final String ASR_TYPE_CLOUD = "cloud"; // 云端听写
    public static final String ASR_TYPE_RETURN = "json"; //返回结果类型
    public static final String ASR_TYPE_LANGUAGE = "zh_cn";// 设置语言
    public static final String ASR_SPEECH_ACCENT = "mandarin";// 设置语言区域
    public static final String ASR_SPEECH_VADBOS = "4000"; // 设置 语音前端点:静音超时时间，即用户多长时间不说话则当做超时处理
    public static final String ASR_SPEECH_VADEOS = "1000"; // 设置 语音后端点:后端点静音检测时间，即用户停止说话多长时间内即认为不再输入， 自动停止录音
    public static final String ASR_SPEECH_PTT = "0"; // 设置标点符号,设置为"0"返回结果无标点,设置为"1"返回结果有标点
    public static final String ASR_MIXED_THRESHOLD = "30";//识别的门限值
    public static final String KEY_GRAMMAR_ABNF_ID = "grammar_abnf_id";//构建语法

}
