package cn.ling.android.volley;

import cn.ling.android.parser.GsonParseHelper;
import cn.ling.android.volley.basehelper.GsonRequest;
import cn.ling.android.entities.BaseEntity;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class GsonRequestForSerialBaseData<T extends BaseEntity> extends GsonRequest<T> {

    public GsonRequestForSerialBaseData(int method, String url, Class<T> clazz,
                                        Response.Listener<T> listener, Response.ErrorListener errorListener) {
        super(method, url, clazz, listener, errorListener);
    }

    @Override
    protected Response<T> parseNetworkResponse(NetworkResponse response) {
        try {
            JSONObject object = new JSONObject(new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers)));
            if (object.has("data") && object.has("errno")) {
                Object dataObj = object.opt("data");
                T baseData = null;
                //                try {
                baseData = GsonParseHelper.safeParse(dataObj.toString(),
                        getClazz());
                baseData.setStatus(object.optInt("errno"));
                //                } catch (IllegalAccessException e) {
                //                    e.printStackTrace();
                //                } catch (InstantiationException e) {
                //                    e.printStackTrace();
                //                }
                return Response.success(baseData,
                        HttpHeaderParser.parseCacheHeaders(response));
            } else {
                return Response.error(new ParseError(new Throwable(
                        "result must contains data and code keys")));
            }
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (JSONException e) {
            return Response.error(new ParseError(e));
        }
    }
}
