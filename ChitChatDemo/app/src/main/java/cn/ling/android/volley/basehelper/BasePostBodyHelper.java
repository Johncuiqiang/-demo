package cn.ling.android.volley.basehelper;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import cn.ling.android.network.PostParamter;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class BasePostBodyHelper extends BaseBodyHelper {

    protected static final String CRLF = "\r\n";
    protected static final String UTF_8 = "UTF-8";
    protected static final byte[] ZERO_BYTE_ARRAY = new byte[0];

    private static final String BOUNDARY = "7cd4a6d158c";
    private static final String MP_BOUNDARY = "--7cd4a6d158c";
    private static final String END_MP_BOUNDARY = "--7cd4a6d158c--";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final int BUFFER_SIZE = 51200;

    private List<PostParamter> mPostParameters = new ArrayList();

    public List<PostParamter> getPostParameters() {
        return mPostParameters;
    }

    public <T extends BasePostBodyHelper> T setPostParameters(List<PostParamter> postParameter){

        if (postParameter!=null){
            mPostParameters = postParameter;
        }
        return (T) this;
    }

    public <T extends BasePostBodyHelper> T addPostParameter(PostParamter postParameter){
        mPostParameters.add(postParameter);
        return (T) this;
    }


    public byte[] getBody(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream(BUFFER_SIZE);
        try {
            generateContent(baos);
        }catch (IOException e){
            e.printStackTrace();
        }
        return baos.toByteArray();
    }

    public String getBodyContentType() {
        return "multipart/form-data; boundary=7cd4a6d158c";
    }


    private void generateContent(ByteArrayOutputStream baos)throws IOException {
        List<PostParamter> postParameters = getPostParameters();
        for (PostParamter postParameter : postParameters) {
            paramToUpload(baos, postParameter.getName(),postParameter.getValue());
        }
        writeContent(baos);
        writeEndToUpload(baos);
        baos.flush();
        baos.close();
    }


    protected void contentToUpload(OutputStream out, byte[] bytes)throws IOException{
        writeBeginToUpload(out);
        out.write(bytes);
        out.write(CRLF.getBytes());
    }

    protected void contentToUpload(OutputStream out, String filePath)throws IOException{
        writeBeginToUpload(out);
        File file = new File(filePath);
        FileInputStream fis = new FileInputStream(file);
        byte[] buffer = new byte[2048];
        int count = 0;
        while (-1 != (count = fis.read(buffer))) {
            out.write(buffer, 0, count);
        }
        fis.close();
        out.write(CRLF.getBytes());

    }


    protected void contentToUpload(OutputStream out, Bitmap bitmap)throws IOException{
        writeBeginToUpload(out);
        bitmap.compress(Bitmap.CompressFormat.PNG, 75, out);
        out.write(CRLF.getBytes());
    }



    private void paramToUpload(OutputStream baos, String key, String value)throws IOException{
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


    private void writeBeginToUpload(OutputStream out)throws IOException{
        StringBuilder sb = new StringBuilder();
        sb.append(MP_BOUNDARY).append(CRLF);
        config(sb);
        sb.append(CRLF);
        byte[] res = sb.toString().getBytes();
        out.write(res);
    }



    private void writeEndToUpload(OutputStream baos)throws IOException{
        baos.write((CRLF+END_MP_BOUNDARY).getBytes());
    }




    protected abstract void config(StringBuilder paramStringBuilder);


    protected abstract void writeContent(ByteArrayOutputStream paramByteArrayOutputStream) throws  IOException;
}
