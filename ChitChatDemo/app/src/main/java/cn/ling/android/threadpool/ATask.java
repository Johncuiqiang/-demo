package cn.ling.android.threadpool;

import android.text.TextUtils;

import org.apache.http.client.HttpClient;

import java.util.ArrayList;
import java.util.List;

import cn.ling.android.network.PostParamter;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class ATask {

    /* task 级别 */
    public static final int TASK_LEVEL_NORMAL = 0x00;
    public static final int TASK_LEVEL_HIGH = 0x01;
    public static final int TASK_LEVEL_BACKGROUND = 0x02;
    public static final int TASK_OK = 200;
    public static final int TASK_NETWORK_ERR = 0x01;
    public static final int TASK_ERR = 0x02;
    public static final int TASK_NOMORE = 0x03;

    private String mUrl;
    private int mPiority;
    private int mTypte;
    private boolean mIsNeedNetWork;
    private ITaskListener mListener;
    private List<PostParamter> mPostParameter = new ArrayList<PostParamter>();

    public ATask setUrl(String url) {
        this.mUrl = url;
        if (TextUtils.isEmpty(mUrl)) {
            this.mIsNeedNetWork = true;
        }
        return this;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public ATask setPiority(int piority) {
        this.mPiority = piority;
        return this;
    }

    public int getPiority() {
        return this.mPiority;
    }

    public ATask setType(int type) {
        this.mTypte = type;
        return this;
    }

    public int getType() {
        return this.mTypte;
    }

    public ATask setNeedNetWork(boolean isNeedNetWork) {
        this.mIsNeedNetWork = isNeedNetWork;
        return this;
    }

    public boolean isNeedNetWork() {
        return this.mIsNeedNetWork;
    }

    public ATask setTaskListener(ITaskListener listener) {
        this.mListener = listener;
        return this;
    }

    public ITaskListener getTaskListener() {
        return this.mListener;
    }

    public ATask setPostParameters(List<PostParamter> params) {
        if (params != null) {
            this.mPostParameter = params;
        }
        return this;
    }

    public ATask addPostParameters(PostParamter paramter) {
        mPostParameter.add(paramter);
        return this;
    }

    public List<PostParamter> getPostParameters() {
        return this.mPostParameter;
    }


    public abstract void run();

    public abstract void run(HttpClient httpClient);


}
