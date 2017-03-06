package cn.ling.android.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class DefaultPostStringRequestForSerialBaseData<T extends BaseEntity> extends StringRequestForSerialBaseData<T> {

    private PostStringBodyHelper mBodyHelper;


    public DefaultPostStringRequestForSerialBaseData(String url, Class<T> clazz,
                                                   Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(Method.POST, url, clazz, listener, errorListener);
    }

    public DefaultPostStringRequestForSerialBaseData<T> setBodyHelper(
            PostStringBodyHelper bodyHelper) {
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
