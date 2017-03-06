package cn.ling.android.threadpool;

/**
 * Created by David小硕 on 2016/9/28.
 */

public interface ITaskListener {

    public void onFinishedListener(ATask task, int resultCode, Object result);

}
