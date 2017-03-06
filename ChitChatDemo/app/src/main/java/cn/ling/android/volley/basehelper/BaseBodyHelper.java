package cn.ling.android.volley.basehelper;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class BaseBodyHelper {

    private Map<String,String> mHeaders = new HashMap();

    public Map<String,String> getHeaders(){
        return mHeaders;
    }

    public <T extends BaseBodyHelper> T addHeader(String key,String value){
        mHeaders.put(key,value);
        return (T) this;
    }

    public <T extends BaseBodyHelper> T addHeaders(Map<String, String> headers) {
        mHeaders.putAll(headers);
        return (T) this;
    }
}
