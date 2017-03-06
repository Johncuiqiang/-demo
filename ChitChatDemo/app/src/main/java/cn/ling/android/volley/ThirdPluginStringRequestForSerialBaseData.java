package cn.ling.android.volley;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import cn.ling.android.entities.ThirdPluginBaseDataEntity;
import cn.ling.android.parser.GsonParseHelper;
import cn.ling.android.volley.basehelper.GsonRequest;

/**
 * Created by David小硕 on 2016/11/10.
 */

public class ThirdPluginStringRequestForSerialBaseData<T extends ThirdPluginBaseDataEntity> extends GsonRequest<T> {


    public ThirdPluginStringRequestForSerialBaseData(int method,
                                                     String url, Class clazz,
                                                     Response.Listener listener,
                                                     Response.ErrorListener errorListener) {
        super(method, url, clazz, listener, errorListener);
    }

    @Override
    protected Response parseNetworkResponse(NetworkResponse response) {
        try {
            JSONObject object = new JSONObject(new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers)));
            T baseData = null;
            baseData = GsonParseHelper.safeParse(object.toString(), getClazz());
            return Response.success(baseData,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return super.parseNetworkResponse(response);
    }
}
