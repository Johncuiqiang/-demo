package cn.ling.voice.chitchat.nlu;


/**
 * Created by cuiqiang on 2016/12/22.
 */

public interface INLUNetRequest {


    //开始NLU请求
    void startNLUNetRequest(final String text, final NLUNetRequestCallBack mNLUNetRequestCallBack);

    //NLU回调接口
    interface NLUNetRequestCallBack {

        void success(NLUResultEntity resultEntity,String string);

        void error(Exception exception);
    }

}
