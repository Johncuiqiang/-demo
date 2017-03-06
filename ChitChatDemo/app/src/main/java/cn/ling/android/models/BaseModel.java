package cn.ling.android.models;


import com.android.volley.VolleyError;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.ling.android.entities.BaseEntity;
import cn.ling.android.logger.LogHelper;
import cn.ling.android.network.HttpFrameManager;
import cn.ling.android.network.HttpFrameType;
import cn.ling.android.utils.NetStatusUtil;
import cn.ling.android.utils.ToastHelper;
import cn.ling.android.volley.PostJsonBodyHelper;
import cn.ling.android.volley.PostStringBodyHelper;
import cn.ling.android.volley.VolleyRequestUtil;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class BaseModel<T extends BaseEntity> implements IModel {

    protected IResponseCallback mResponseCallback;
    protected Map<String, Object> mParams = new HashMap<String, Object>();
    protected boolean isShowToastOnNetWorkError = true;

    protected HttpFrameType mCurrentHttpFrameType = HttpFrameType.Volley;
    protected String mApiPath;
    protected Class<T> mResponseClassType;


    public BaseModel(IResponseCallback responseCallback) {
        this.mResponseCallback = responseCallback;
    }

    @Override
    public void startLoadWithMethod(HttpMethod method) {
        if (method == HttpMethod.Post) {
            startLoadWithPostMethod();
        } else if (method == HttpMethod.Get) {
            startLoadWithGetMethod();
        }
    }

    @Override
    public void prepareParams() {

    }

    @Override
    public void startLoadWithPostMethod() {
        if (mCurrentHttpFrameType == HttpFrameType.Volley) {

            String requestParams;
            if(isJsonRPCServer()){
                requestParams = organizeJsonPostParams();
                HttpFrameManager.getInstance().startLoatByVolleyWithPost(new PostJsonBodyHelper().setString(requestParams),
                        mApiPath, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                            @Override
                            public void onResponse(T paramT) {
                                mResponseCallback.responsedCallback(paramT, paramT.getStatus(), null);
                                handlerResponseEntity(paramT);
                            }

                            @Override
                            public void onErrorResponse(VolleyError paramVolleyError) {
                                mResponseCallback.responsedCallback(null, -1, paramVolleyError);
                                handlerErrorResponse(paramVolleyError);
                                if (isShowToastOnNetWorkError && !NetStatusUtil.hasNetwork()) {
                                    ToastHelper.getInstance().showToast("请检查您的网路");
                                }
                            }
                        });
            }else{
                requestParams = organizePostParams();
                HttpFrameManager.getInstance().startLoatByVolleyWithPost(new PostStringBodyHelper().setString(requestParams),
                        mApiPath, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                            @Override
                            public void onResponse(T paramT) {
                                mResponseCallback.responsedCallback(paramT, paramT.getStatus(), null);
                                handlerResponseEntity(paramT);
                            }

                            @Override
                            public void onErrorResponse(VolleyError paramVolleyError) {
                                mResponseCallback.responsedCallback(null, -1, paramVolleyError);
                                handlerErrorResponse(paramVolleyError);
                                if (isShowToastOnNetWorkError && !NetStatusUtil.hasNetwork()) {
                                    ToastHelper.getInstance().showToast("请检查您的网路");
                                }
                            }
                        });
            }

        } else if (mCurrentHttpFrameType == HttpFrameType.Other) {
            //TODO: other http frame
        }
    }

    @Override
    public void startLoadWithGetMethod() {
        if (mCurrentHttpFrameType == HttpFrameType.Volley) {
            if (null == mApiPath || mApiPath.equals("")) {
                LogHelper.NET.error("please set api path");
                return;
            }
            //drop api path end character "/"
            if (mApiPath.endsWith("/")) {
                mApiPath = mApiPath.substring(mApiPath.length() - 1);
            }

            if (null == this.mResponseClassType) {
                LogHelper.NET.error("please set responsed class Type");
                return;
            }
            String requestURL = this.mApiPath + organizeGetParams();;
            HttpFrameManager.getInstance().startLoadByVolleyWithGet(requestURL, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                @Override
                public void onResponse(T paramT) {
                    mResponseCallback.responsedCallback(paramT, paramT.getStatus(), null);
                    handlerResponseEntity(paramT);
                }

                @Override
                public void onErrorResponse(VolleyError paramVolleyError) {
                    mResponseCallback.responsedCallback(null, -1, paramVolleyError);
                    handlerErrorResponse(paramVolleyError);
                    if (isShowToastOnNetWorkError && !NetStatusUtil.hasNetwork()) {
                        ToastHelper.getInstance().showToast("请检查您的网路");
                    }
                }
            });
        } else if (mCurrentHttpFrameType == HttpFrameType.Other) {
            //TODO: other http frame
        }
    }

    // subclass implement
    public void handlerErrorResponse(Exception error) {

    }

    // subclass implement
    public <T> void handlerResponseEntity(T entity) {

    }

    protected Boolean isJsonRPCServer(){
        return false;
    }

    private String organizeGetParams() {
        Iterator<String> iterator = this.mParams.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = this.mParams.get(key);
            sb.append(key).append("=");
            sb.append(value);
            if (index < this.mParams.keySet().size() - 1) {
                sb.append("&");
            }
            index++;
        }
        return sb.toString();
    }

    private String organizeJsonPostParams() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String resultJson = gson.toJson(this.mParams);
        sb.append(resultJson);
        return sb.toString();
    }

    private String organizePostParams(){
        Iterator<String> iterator = this.mParams.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = this.mParams.get(key);
            sb.append(key).append("=");
            sb.append(value);
            if (index < this.mParams.keySet().size() - 1) {
                sb.append("&");
            }
            index++;
        }
        return sb.toString();
    }


}
