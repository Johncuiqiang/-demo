package cn.ling.android.threadpool;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import cn.ling.android.ApplicationInfo;
import cn.ling.android.network.PostParamter;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class BaseUploadImageTask extends ATask {

    public static final String CRLF = "\r\n";
    public static final String BOUNDARY = "7cd4a6d158c";
    public static final String MP_BOUNDARY = "--7cd4a6d158c";
    public static final String END_MP_BOUNDARY = "--7cd4a6d158c--";
    public static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final int BUFFER_SIZE = 51200;

    private Bitmap mBitmap;
    private String mFilePath;
    private byte[] mBytes;
    private int mResid;

    protected byte[] generateContent() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
        List<PostParamter> postParameters = getPostParameters();
        for (PostParamter postParameter : postParameters) {
            paramToUpload(baos, postParameter.getName(),
                    postParameter.getValue());

        }
        writeContent(baos);
        writeEndToUpload(baos);
        baos.flush();
        baos.close();
        return baos.toByteArray();

    }

    private void paramToUpload(OutputStream baos, String key, String value) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(MP_BOUNDARY).append(CRLF);
        sb.append("content-disposition: form-data; name=\"").append(key)
                .append("\"").append(CRLF);
        sb.append("Content-Type: text").append(CRLF);
        sb.append(CRLF);
        sb.append(value).append(CRLF);
        byte[] res = sb.toString().getBytes();
        baos.write(res);
    }

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
            contentToUpload(baos, BitmapFactory.decodeResource(
                    ApplicationInfo.getApplicatonContext().getResources(), mResid));
        }
    }

    private void contentToUpload(OutputStream out, Bitmap bitmap) throws IOException {
        writeBeginToUpload(out);
        bitmap.compress(Bitmap.CompressFormat.PNG, 75, out);
        out.write(CRLF.getBytes());
    }

    private void contentToUpload(OutputStream out, String filePath) throws IOException {
        writeBeginToUpload(out);
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[1024];
        int count = 0;
        while (-1 != (count = fis.read(buffer))) {
            out.write(buffer, 0, count);
        }
        fis.close();
        out.write(CRLF.getBytes());
    }

    private void contentToUpload(OutputStream out, byte[] bytes) throws IOException {
        writeBeginToUpload(out);
        out.write(bytes);
        out.write(CRLF.getBytes());
    }

    private void writeBeginToUpload(OutputStream out) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append(MP_BOUNDARY).append(CRLF);
        config(sb);
        sb.append(CRLF);
        byte[] res = sb.toString().getBytes();
        out.write(res);
    }

    private void writeEndToUpload(OutputStream baos) throws IOException {
        baos.write((CRLF + END_MP_BOUNDARY).getBytes());
    }

    protected void config(StringBuilder sb) {
        sb.append("Content-Disposition: form-data; name=\"pic\"; filename=\"").append("news_image").append("\"").append(CRLF);
        String filetype = "image/png";
        sb.append("Content-Type: ").append("image/png").append(CRLF);
    }


    public BaseUploadImageTask setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
        return this;
    }

    public BaseUploadImageTask setFilePath(String filePath){
        mFilePath = filePath;
        return this;
    }

    public BaseUploadImageTask setBytes(byte[] bytes){
        mBytes = bytes;
        return this;
    }

    public BaseUploadImageTask setResId(int resId){
        mResid = resId;
        return this;
    }
}
