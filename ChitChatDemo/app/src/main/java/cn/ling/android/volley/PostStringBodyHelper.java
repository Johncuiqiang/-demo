package cn.ling.android.volley;

import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.zip.GZIPOutputStream;

import cn.ling.android.datas.ConstantHttpProtocal;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class PostStringBodyHelper {

    private static final String UTF_8 = "UTF-8";
    private static final byte[] ZERO_BYTE_ARRAY = new byte[0];

    private String mContentType = "application/x-www-form-urlencoded";

    private byte[] mBytes;
    private String mString;

    private Map<String, String> mHeaders = new HashMap<String, String>();

    public PostStringBodyHelper setBytes(byte[] bytes) {
        mBytes = bytes;

        return this;
    }

    public PostStringBodyHelper setString(String string) {
        mString = string;

        return this;
    }

    public String getContentType() {
        return mContentType;
    }

    public void setContentType(String contentType) {
        this.mContentType = contentType;
    }

    public byte[] getContent() {
        if (null != mBytes) {
            return translate(mBytes);
        }
        if (null != mString) {
            try {
                return translate(mString.getBytes(UTF_8));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        return ZERO_BYTE_ARRAY;
    }

    private byte[] translate(final byte[] bytes) {
        final String encoding = mHeaders
                .get(ConstantHttpProtocal.CONTENT_ENCODING);
        if (null != encoding) {
            if (encoding.equals(ConstantHttpProtocal.GZIP)) {
                GZIPOutputStream gos = null;
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                try {
                    gos = new GZIPOutputStream(new BufferedOutputStream(baos));
                    gos.write(bytes);
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    try {
                        gos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return baos.toByteArray();
            }
        }

        return bytes;
    }

    /**
     * @return
     */
    public Map<String, String> getHeaders() {
        return mHeaders;
    }

    public PostStringBodyHelper addHeader(final String key, final String value) {
        mHeaders.put(key, value);

        return this;
    }

    public PostStringBodyHelper addHeaders(Map<String, String> headers) {
        mHeaders.putAll(headers);

        return this;
    }

}
