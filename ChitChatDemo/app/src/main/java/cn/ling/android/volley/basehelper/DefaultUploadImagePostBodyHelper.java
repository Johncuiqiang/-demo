package cn.ling.android.volley.basehelper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import cn.ling.android.ApplicationInfo;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DefaultUploadImagePostBodyHelper extends BasePostBodyHelper {

    private Bitmap mBitmap;
    private String mFilePath;
    private byte[] mBytes;
    private int mResid;

    public <T extends DefaultUploadImagePostBodyHelper> T setBitmap(Bitmap bitmap){
        mBitmap = bitmap;
        return (T) this;
    }

    public <T extends DefaultUploadImagePostBodyHelper> T setFilePath(String filePath){
        mFilePath = filePath;
        return (T) this;
    }

    public <T extends DefaultUploadImagePostBodyHelper> T setResid(int resid) {
        mResid = resid;
        return (T) this;
    }

    public <T extends DefaultUploadImagePostBodyHelper> T setBytes(byte[] bytes) {
        mBytes = bytes;
        return (T) this;
    }


    @Override
    protected void config(StringBuilder sb) {
        sb.append("Content-Disposition: form-data; name=\"pic\"; filename=\"").append("news_image").append("\"").append("\r\n");
        String filetype = "image/png";
        sb.append("Content-Type: ").append("image/png").append("\r\n");
    }

    @Override
    protected void writeContent(ByteArrayOutputStream baos) throws IOException {
        if (mBitmap != null) {
            contentToUpload(baos, mBitmap);
        }
        if ((mFilePath != null) && (mFilePath.trim().length() != 0)) {
            contentToUpload(baos, mFilePath);
        }

        if (mBytes != null) {
            contentToUpload(baos, mBytes);
        }
        if (mResid != 0) {
            contentToUpload(baos, BitmapFactory.decodeResource(ApplicationInfo.
                    getApplicatonContext().getResources(), mResid));
        }
    }
}
