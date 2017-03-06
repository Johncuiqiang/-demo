package cn.ling.android.network;

import cn.ling.android.entities.BaseEntity;
import cn.ling.android.entities.ThirdPluginBaseDataEntity;
import cn.ling.android.volley.PostJsonBodyHelper;
import cn.ling.android.volley.PostStringBodyHelper;
import cn.ling.android.volley.StartVolleyManager;
import cn.ling.android.volley.VolleyRequestUtil;
import cn.ling.android.volley.basehelper.BasePostBodyHelper;

public class HttpFrameManager {

    private static HttpFrameManager INSTANCE;

    private HttpFrameType mCurrentFrameType = HttpFrameType.Volley;

    public static HttpFrameManager getInstance(){
        if(null == INSTANCE) {
            synchronized (HttpFrameManager.class) {
                if (null == INSTANCE) {
                    INSTANCE = new HttpFrameManager();
                }
            }
        }
        return INSTANCE;
    }

    public void initHttpFrame(){
        if(mCurrentFrameType == HttpFrameType.Volley){
            StartVolleyManager.init();
        }else if(mCurrentFrameType == HttpFrameType.Other){

        }
    }

    public <T extends ThirdPluginBaseDataEntity> void startLoad3dPluginByVolleyWithPost(
            PostJsonBodyHelper bodyHelper, String url, Class<T> clazz,
            final VolleyRequestUtil.Listener<T> listener){
        VolleyRequestUtil.add3dPluginPostGsonRequestForSerialBaseData(bodyHelper, url, clazz, listener);
    }

    public <T extends  ThirdPluginBaseDataEntity> void startLoad3dPluginByVolleyWithPost(
            PostStringBodyHelper bodyHelper,String url, Class<T> clazz,
            final VolleyRequestUtil.Listener<T> listener){
        VolleyRequestUtil.add3dPluginPostGsonRequestForSerialBaseData(bodyHelper, url, clazz, listener);
    }


    public <T extends BaseEntity> void startLoatByVolleyWithPost(PostJsonBodyHelper bodyHelper, String url, Class<T> clazz,
                                                                 final VolleyRequestUtil.Listener<T> listener){
        VolleyRequestUtil.addPostGsonRequestForSerialBaseData(bodyHelper, url, clazz, listener);
    }

    public <T extends BaseEntity> void startLoatByVolleyWithPost(PostStringBodyHelper bodyHelper, String url, Class<T> clazz,
                                                                 final VolleyRequestUtil.Listener<T> listener){
        VolleyRequestUtil.addPostStringRequestForSerialBaseData(bodyHelper,url,clazz,listener);
    }


    public <T extends BaseEntity> void startLoadByVolleyWithGet(String url, Class<T> clazz, final VolleyRequestUtil.Listener<T> listener){

        VolleyRequestUtil.addGetGsonRequestForSerialBaseData(url,clazz,listener);
    }

    public <T extends ThirdPluginBaseDataEntity> void startLoad3dPluginWithGet(String url, Class<T> clazz, final VolleyRequestUtil.Listener<T> listener){

        VolleyRequestUtil.add3dPluginGetGsonRequestForSeialBaseData(url, clazz, listener);
    }
}
