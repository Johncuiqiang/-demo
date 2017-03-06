package cn.ling.android.volley;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;

import java.util.Map;

import cn.ling.android.entities.ThirdPluginBaseDataEntity;

/**
 * Created by David小硕 on 2016/11/10.
 */

public class Default3dPluginStringPostReqForSerialBaseData<T extends ThirdPluginBaseDataEntity> extends ThirdPluginStringRequestForSerialBaseData<T> {

    private PostStringBodyHelper mBodyHelper;

    public Default3dPluginStringPostReqForSerialBaseData(String url,
                                                         Class<T> clazz, Response.Listener<T> listener,
                                                         Response.ErrorListener errorListener) {
        super(Request.Method.POST, url, clazz, listener, errorListener);
    }

    public Default3dPluginStringPostReqForSerialBaseData<T> setBodyHelper(
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
