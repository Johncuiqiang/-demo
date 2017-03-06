package cn.ling.android.volley;

import cn.ling.android.volley.basehelper.DefaultUploadImagePostBodyHelper;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UploadTextPostBodyHelper extends DefaultUploadImagePostBodyHelper {

    @Override
    protected void config(StringBuilder sb) {
        sb.append("Content-Disposition: form-data; name=\"upload\"; value=\"")
                .append("new_text").append("\"").append(CRLF);
        final String filetype = "text/*";
        sb.append("Content-Type: ").append(filetype).append(CRLF);
    }

}
