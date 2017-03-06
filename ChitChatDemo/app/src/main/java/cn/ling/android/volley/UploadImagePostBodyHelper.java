package cn.ling.android.volley;

import cn.ling.android.volley.basehelper.DefaultUploadImagePostBodyHelper;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UploadImagePostBodyHelper extends DefaultUploadImagePostBodyHelper {

    @Override
    protected void config(StringBuilder sb) {
        sb.append(
                "Content-Disposition: form-data; name=\"upload\"; filename=\"")
                .append("news_image").append("\"").append(CRLF);
        final String filetype = "image/png";
        sb.append("Content-Type: ").append(filetype).append(CRLF);
    }

}
