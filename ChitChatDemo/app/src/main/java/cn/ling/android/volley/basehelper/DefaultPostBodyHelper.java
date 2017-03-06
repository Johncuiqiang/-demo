package cn.ling.android.volley.basehelper;

import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DefaultPostBodyHelper extends BasePostBodyHelper {

    private byte[] mBytes;
    private JSONObject mJsonObject;
    private String mString;


    @Override
    protected void config(StringBuilder sb) {
        sb.append("Content-Disposition: form-data; name=\"upload\"; filename=\"").append("news_image").append("\"").append("\r\n");
        String filetype = "image/png";
        sb.append("Content-Type: ").append(filetype).append("\r\n");
    }

    @Override
    protected void writeContent(ByteArrayOutputStream baos) throws IOException {
        contentToUpload(baos, getContent());
    }


    public DefaultPostBodyHelper setBytes(byte[] bytes) {
        this.mBytes = bytes;
        return this;
    }

    public DefaultPostBodyHelper setJsonObject(JSONObject jsonObject) {
        this.mJsonObject = jsonObject;
        return this;
    }

    public DefaultPostBodyHelper setString(String string) {
        this.mString = string;
        return this;
    }

    private byte[] getContent() {
        if (mBytes != null) {
            return mBytes;
        }
        if (mString != null) {
            try {
                return mString.getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        if (mJsonObject != null) {
            try {
                return mJsonObject.toString().getBytes("UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return ZERO_BYTE_ARRAY;
    }
}
