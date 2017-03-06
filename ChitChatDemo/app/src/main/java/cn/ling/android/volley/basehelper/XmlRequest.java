package cn.ling.android.volley.basehelper;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.io.UnsupportedEncodingException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class XmlRequest extends PatientRequest<XmlPullParser> {

    private final Response.Listener<XmlPullParser> mListener;

    public XmlRequest(int method, String url, Response.ErrorListener errorListener, Response.Listener<XmlPullParser> listener) {
        super(method, url, errorListener);
        this.mListener = listener;
    }

    public XmlRequest(String url, Response.Listener<XmlPullParser> listener, Response.ErrorListener errorListener) {
        this(0, url, errorListener, listener);
    }


    @Override
    protected Response<XmlPullParser> parseNetworkResponse(NetworkResponse response) {
        try {
            String xmlString = new String(response.data,
                    HttpHeaderParser.parseCharset(response.headers));
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(xmlString));
            return Response.success(xmlPullParser,
                    HttpHeaderParser.parseCacheHeaders(response));
        } catch (UnsupportedEncodingException e) {
            return Response.error(new ParseError(e));
        } catch (XmlPullParserException e) {
            return Response.error(new ParseError(e));
        }
    }

    @Override
    protected void deliverResponse(XmlPullParser response) {
        mListener.onResponse(response);
    }
}
