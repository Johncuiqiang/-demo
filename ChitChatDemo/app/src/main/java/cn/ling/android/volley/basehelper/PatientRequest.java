package cn.ling.android.volley.basehelper;

import com.android.volley.Request;
import com.android.volley.AuthFailureError;
import com.android.volley.Response;

import java.util.Map;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class PatientRequest<T> extends Request<T> {

    protected BasePostBodyHelper mPostBodyHelper;
    protected BaseGetBodyHelper mGetBodyHelper;

    public PatientRequest(int method, String url, Response.ErrorListener listener) {
        super(method, url, listener);
    }

    public <S extends PatientRequest<T>> S setPostBodyHelper(BasePostBodyHelper postBodyHelper){
        mPostBodyHelper = postBodyHelper;
        return (S) this;
    }

    public <M extends PatientRequest<T>> M setGetBodyHelper(BaseGetBodyHelper getBodyHelper){
        mGetBodyHelper = getBodyHelper;
        return (M) this;
    }

    public byte[] getBody() throws AuthFailureError {
        if (mPostBodyHelper == null) {
            return super.getBody();
        }
        return mPostBodyHelper.getBody();
    }

    public String getBodyContentType(){
        if (mPostBodyHelper == null) {
            return super.getBodyContentType();
        }
        return mPostBodyHelper.getBodyContentType();
    }

    public Map<String, String> getHeaders()throws AuthFailureError{
        if (mGetBodyHelper != null) {
            return mGetBodyHelper.getHeaders();
        }
        if (mPostBodyHelper != null) {
            return mPostBodyHelper.getHeaders();
        }
        return super.getHeaders();
    }
}
