package cn.ling.voice.chitchat.nlu;

import android.content.Context;
import android.media.AudioManager;

import cn.ling.android.logger.LogHelper;
import cn.ling.android.models.IResponse3dPluginDataCallback;


/**
 * Created by cuiqiang on 2016/12/22.
 * NLU网络请求
 * <p>
 * startNLUNetRequest(String text, NLUNetRequestCallBack mNLUNetRequestCallBack)执行NLU网络请求
 */

public class NLUNetRequest implements INLUNetRequest {

    private Context mContext;

    public NLUNetRequest(Context context) {
        this.mContext = context;
    }

    @Override
    public void startNLUNetRequest(String text, final NLUNetRequestCallBack mNLUNetRequestCallBack) {
        NLUModel nluModel = new NLUModel(new IResponse3dPluginDataCallback<NLUResultEntity>() {
            @Override
            public void responsedCallback(NLUResultEntity dataEntity, Exception error) {
                if (null == error) {
                    String data = entityAnalyse(dataEntity);
                    if (data==null){
                        data="coming soon";
                    }
                    mNLUNetRequestCallBack.success(dataEntity,data);
                }else{
                    mNLUNetRequestCallBack.error(error);
                }
            }
        });
        nluModel.executeReqNLUResult(text);
    }


    private String entityAnalyse(NLUResultEntity resultEntity) {
        String domain = resultEntity.getDomain();
        NLUResultEntity.Param param = resultEntity.getParam();
        if (param == null) return null;
        switch (domain) {
            case "basic":
                return resultEntity.getAnswer();
            case "music":
               return musicDomain(param);
            case "weather":
                weatherDomain(param);
                return resultEntity.getAnswer();
            case "time":
               return timeDomain(param);
            case "date":
                return dateDomain(param);
            case "news":
                return newsDomain(param);
            case "volume":
                return volumeDomain(param);
            case "joke":
                return jokeDomain(param);
            case "express":
                return expressDomain(param);
            case "move":
                return moveDomain(param);
            default:
                break;
        }
        return null;
    }


    //天气
    private void weatherDomain(NLUResultEntity.Param param) {
        //湿度
        String humidity = param.getDetail().getCondition().getHumidity();
        //风向
        String windDir = param.getDetail().getCondition().getWindDir();
        //风力等级
        String windLevel = param.getDetail().getCondition().getWindLevel();
        //天气情况
        String condition = param.getDetail().getCondition().getCondition();
        //温度
        String temp = param.getDetail().getCondition().getTemp();

        //夜间天气
        String conditionNight = param.getDetail().getForecast().get(0).getConditionNight();
        //夜间风向
        String windDirNight = param.getDetail().getForecast().get(0).getWindDirNight().get(0);
        //夜间温度
        String tempNight = param.getDetail().getForecast().get(0).getTempNight();
        //夜间风力
        String windLevelNight = param.getDetail().getForecast().get(0).getWindLevelNight().get(0);

        //白天天气
        String conditionDay = param.getDetail().getForecast().get(1).getConditionDay();
        //白天风向
        String windDirDay = param.getDetail().getForecast().get(1).getWindDirDay().get(0);
        //白天温度
        String tempDay = param.getDetail().getForecast().get(1).getTempDay();
        //白天风力
        String windLevelDay = param.getDetail().getForecast().get(1).getWindLevelDay().get(0);

    }

    //音乐
    private String musicDomain(NLUResultEntity.Param param) {
        String actionMusic = param.getAction();
        if ("search".equals(actionMusic)) {
            String style = param.getStyle();//风格
            String song = param.getSong();//歌名
            String artist = param.getArtist();//歌手

            StringBuilder builder = new StringBuilder();
            builder.append("主人，您要查找的歌曲");
            if (style != null) {
                builder.append(" 风格是 ").append(style);
            }
            if (artist != null) {
                builder.append(" 歌手是 ").append(style);
            }
            if (song != null) {
                builder.append(" 歌名是 ").append(style);
            }
            return builder.toString();
        }

        return null;
    }

    //音量
    private String volumeDomain(NLUResultEntity.Param param) {
        String actionVolume = param.getAction();
        if ("volume_down".equals(actionVolume)) {
            AudioManager mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            LogHelper.TEST.debug("444 NLU   volume_down");
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume - 2, 1);
            return "好的";
        } else if ("volume_up".equals(actionVolume)) {
            AudioManager mAudioManager = (AudioManager)mContext.getSystemService(Context.AUDIO_SERVICE);
            int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
            LogHelper.TEST.debug("444 NLU   volume_up");
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume + 2, 1);
            return "好的";
        }
        return null;
    }

    //时间
    private String timeDomain(NLUResultEntity.Param param) {
        return  null;
    }

    //日期
    private String dateDomain(NLUResultEntity.Param param) {
        return  null;
    }

    //新闻
    private String newsDomain(NLUResultEntity.Param param) {
        return  null;
    }

    //笑话
    private String jokeDomain(NLUResultEntity.Param param) {
        return  null;
    }

    //移动
    private String moveDomain(NLUResultEntity.Param param) {
        return  null;
    }

    //快递
    private String expressDomain(NLUResultEntity.Param param) {
        return  null;
    }


}
