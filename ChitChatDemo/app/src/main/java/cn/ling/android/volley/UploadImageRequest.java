package cn.ling.android.volley;

import com.android.volley.Response;

import cn.ling.android.volley.basehelper.BasePostBodyHelper;
import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UploadImageRequest<T extends BaseEntity> extends GsonRequestForSerialBaseData<T> {

    /**
     * @param
     * @param url
     * @param clazz
     * @param listener
     * @param errorListener
     */
    public UploadImageRequest(String url, Class<T> clazz, Response.Listener<T> listener,
                              Response.ErrorListener errorListener) {
        super(Method.POST, url, clazz, listener, errorListener);
    }

    @Override
    public UploadImageRequest<T> setPostBodyHelper(
            BasePostBodyHelper postBodyHelper) {
        if (postBodyHelper instanceof UploadImagePostBodyHelper) {
            return super.setPostBodyHelper(postBodyHelper);
        } else {
            throw new IllegalArgumentException(
                    "param must be instantce of UploadImagePostBodyHelper");
        }
    }
}
