package cn.ling.android.models;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import cn.ling.android.network.HttpFrameType;

/**
 * Created by David小硕 on 2016/11/10.
 */

public class BasicModel<T> implements IModel {

    protected Map<String, Object> mParams = new HashMap<String, Object>();
    protected boolean isShowToastOnNetWorkError = true;

    protected HttpFrameType mCurrentHttpFrameType = HttpFrameType.Volley;
    protected String mApiPath;

    @Override
    public void startLoadWithMethod(HttpMethod method) {

    }

    @Override
    public void prepareParams() {

    }

    @Override
    public void startLoadWithGetMethod() {

    }

    @Override
    public void startLoadWithPostMethod() {

    }

    // subclass implement
    public void handlerErrorResponse(Exception error) {

    }

    // subclass implement
    public <T> void handlerResponseEntity(T entity) {

    }

    protected Boolean isJsonRPCServer(){
        return false;
    }


    protected String organizeGetParams() {
        Iterator<String> iterator = this.mParams.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        sb.append("?");
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = this.mParams.get(key);
            sb.append(key).append("=");
            if(value instanceof  String){
                String tempValue = "";
                try {
                    tempValue = URLEncoder.encode((String)value, "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                sb.append(tempValue);
            }else{
                sb.append(value);
            }
            if (index < this.mParams.keySet().size() - 1) {
                sb.append("&");
            }
            index++;
        }
        return sb.toString();
    }

    protected String organizeJsonPostParams() {
        StringBuilder sb = new StringBuilder();
        Gson gson = new Gson();
        String resultJson = gson.toJson(this.mParams);
        sb.append(resultJson);
        return sb.toString();
    }

    protected String organizePostParams(){
        Iterator<String> iterator = this.mParams.keySet().iterator();
        StringBuilder sb = new StringBuilder();
        int index = 0;
        while (iterator.hasNext()) {
            String key = iterator.next();
            Object value = this.mParams.get(key);
            sb.append(key).append("=");
            sb.append(value);
            if (index < this.mParams.keySet().size() - 1) {
                sb.append("&");
            }
            index++;
        }
        return sb.toString();
    }

}
