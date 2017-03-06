package cn.ling.android.utils;

import android.graphics.Bitmap;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;

import cn.ling.android.volley.StartVolleyManager;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class DownloadImageUtils {

    private static DownloadImageUtils INSTANCE;

    public interface DownloadImageListener {

        public void onSuccess(final Bitmap bitmap);

        public void onFailure(final Object errorMsg);

    }

    private DownloadImageUtils() {

    }

    public static DownloadImageUtils getInstance() {
        if (null == INSTANCE) {
            synchronized(DownloadImageUtils.class) {
                if (null == INSTANCE) {
                    INSTANCE = new DownloadImageUtils();
                }
            }
        }
        return INSTANCE;
    }

    /**
     * 通过Volley的方式下载图片
     * @param url
     * @param listener 回调
     */
    public void downloadImageByVolley(String url, final DownloadImageListener listener){
        RequestQueue requestQueue = StartVolleyManager.getRequestQueue();
        ImageRequest imageRequest  = new ImageRequest(url, new Response.Listener<Bitmap>() {
            @Override
            public void onResponse(Bitmap bitmap) {
                if(null != listener){
                    listener.onSuccess(bitmap);
                }
            }
        }, 0, 0, Bitmap.Config.ARGB_8888, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(null != listener){
                    listener.onFailure(volleyError);
                }
            }
        });
        requestQueue.add(imageRequest);
    }

}
