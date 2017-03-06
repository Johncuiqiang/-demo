package cn.ling.android.volley;

import cn.ling.android.entities.BaseEntity;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DefaultPostGsonRequestForSerialBaseData<T extends BaseEntity> extends GsonRequestForSerialBaseData<T> {

    private PostJsonBodyHelper mBodyHelper;


    public DefaultPostGsonRequestForSerialBaseData(String url, Class<T> clazz,
                                                   Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, clazz, listener, errorListener);
    }

    public DefaultPostGsonRequestForSerialBaseData<T> setBodyHelper(
            PostJsonBodyHelper bodyHelper) {
        mBodyHelper = bodyHelper;
        return this;
    }

    @Override
    public byte[] getBody() throws AuthFailureError {
        if (null != mBodyHelper) {
            return mBodyHelper.getContent();
        } else {
            return super.getBody();
        }
    }



    @Override
    public String getBodyContentType() {
        final String contentType = mBodyHelper.getContentType();
        if (null == contentType) {
            return super.getBodyContentType();
        } else {
            return contentType;
        }
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        if (null != mBodyHelper) {
            return mBodyHelper.getHeaders();
        }
        return super.getHeaders();
    }
}
