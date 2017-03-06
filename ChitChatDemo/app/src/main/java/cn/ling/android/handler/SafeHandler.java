package cn.ling.android.handler;

import android.os.Handler;
import android.os.Message;

import java.lang.ref.WeakReference;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class SafeHandler extends Handler {

    public interface IHandlerMessage{
        public void handlerMessage(Message msg);
    }

    private WeakReference<IHandlerMessage> mWeakReference;

    public SafeHandler(IHandlerMessage handlerMessage){
        mWeakReference = new WeakReference<IHandlerMessage>(handlerMessage);
    }


    @Override
    public void handleMessage(Message msg) {
        IHandlerMessage handlerMessage = mWeakReference.get();
        if (handlerMessage!=null){
            handlerMessage.handlerMessage(msg);
        }
    }
}
