package cn.ling.android.volley.basehelper;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class PatientVolleyBuilder {

    private String userAgent;
    private Context appContext;
    private HttpStack httpStack;

    public String getUserAgent() {
        return userAgent;
    }

    public Context getAppContext() {
        return appContext;
    }

    public HttpStack getHttpStack() {
        return httpStack;
    }

    public PatientVolleyBuilder setUserAgent(String userAgent) {
        this.userAgent = userAgent;
        return this;
    }

    public PatientVolleyBuilder setAppContext(Context appContext) {
        this.appContext = appContext;
        return this;
    }

    public PatientVolleyBuilder setHttpStack(HttpStack httpStack) {
        this.httpStack = httpStack;
        return this;
    }

    public RequestQueue build() {
        return PatientVolley.newRequestQueue(this);
    }
}
