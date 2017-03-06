package cn.ling.android.models;

import com.android.volley.VolleyError;

import cn.ling.android.entities.ThirdPluginBaseDataEntity;
import cn.ling.android.logger.LogHelper;
import cn.ling.android.network.HttpFrameManager;
import cn.ling.android.network.HttpFrameType;
import cn.ling.android.utils.NetStatusUtil;
import cn.ling.android.utils.ToastHelper;
import cn.ling.android.volley.PostJsonBodyHelper;
import cn.ling.android.volley.PostStringBodyHelper;
import cn.ling.android.volley.VolleyRequestUtil;

/**
 * Created by David小硕 on 2016/11/10.
 */

public class Base3dPluginDataModel<T extends ThirdPluginBaseDataEntity> extends BasicModel<T>{

    protected IResponse3dPluginDataCallback mResponseCallback;
    protected Class<T> mResponseClassType;

    public Base3dPluginDataModel(IResponse3dPluginDataCallback responseCallback) {
        mResponseCallback = responseCallback;
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
            String requestURL = this.mApiPath + organizeGetParams();
            HttpFrameManager.getInstance().startLoad3dPluginWithGet(requestURL, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                @Override
                public void onResponse(T paramT) {
                    mResponseCallback.responsedCallback(paramT, null);
                    handlerResponseEntity(paramT);
                }

                @Override
                public void onErrorResponse(VolleyError paramVolleyError) {
                    mResponseCallback.responsedCallback(null, paramVolleyError);
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

    @Override
    public void startLoadWithPostMethod() {
        if (mCurrentHttpFrameType == HttpFrameType.Volley) {
            String requestParams;
            if(isJsonRPCServer()){
                requestParams = organizeJsonPostParams();
                HttpFrameManager.getInstance().startLoad3dPluginByVolleyWithPost(new PostJsonBodyHelper().setString(requestParams), mApiPath, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                    @Override
                    public void onResponse(T paramT) {
                        mResponseCallback.responsedCallback(paramT, null);
                        handlerResponseEntity(paramT);
                    }

                    @Override
                    public void onErrorResponse(VolleyError paramVolleyError) {
                        mResponseCallback.responsedCallback(null, paramVolleyError);
                        handlerErrorResponse(paramVolleyError);
                        if (isShowToastOnNetWorkError && !NetStatusUtil.hasNetwork()) {
                            ToastHelper.getInstance().showToast("请检查您的网路");
                        }
                    }
                });
            }else{
                requestParams = organizePostParams();
                HttpFrameManager.getInstance().startLoad3dPluginByVolleyWithPost(new PostStringBodyHelper().setString(requestParams), mApiPath, mResponseClassType, new VolleyRequestUtil.Listener<T>() {
                    @Override
                    public void onResponse(T paramT) {
                        mResponseCallback.responsedCallback(paramT, null);
                        handlerResponseEntity(paramT);
                    }

                    @Override
                    public void onErrorResponse(VolleyError paramVolleyError) {
                        mResponseCallback.responsedCallback(null, paramVolleyError);
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


}
