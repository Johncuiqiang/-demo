package cn.ling.android.utils;

import android.graphics.Bitmap;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import cn.ling.android.entities.UploadImageResult;
import cn.ling.android.threadpool.ATask;
import cn.ling.android.threadpool.DefaultNoNetworkTask;
import cn.ling.android.threadpool.ITaskListener;
import cn.ling.android.threadpool.ThreadPool;
import cn.ling.android.threadpool.UploadImageTask;
import cn.ling.android.volley.StartVolleyManager;
import cn.ling.android.volley.UploadImagePostBodyHelper;
import cn.ling.android.volley.UploadImageRequest;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class UploadImageUtils {

    private static UploadImageUtils INSTANCE;

    private static final String CHARSET = "UTF-8";
    private static final String CRLF = "\r\n";
    private static final int TIME_OUT = 10 * 10000000;
    private static final String BOUNDARY = "7cd4a6d158c";
    private static final String MP_BOUNDARY = "--7cd4a6d158c";
    private static final String END_MP_BOUNDARY = "--7cd4a6d158c--";
    private static final String MULTIPART_FORM_DATA = "multipart/form-data";
    private static final String CONTENT_TYPE = "multipart/form-data";


    public interface UploadImageByVolleyListener{

        public void onSuccess(final UploadImageResult result);

        public void onFailure(final VolleyError error);
    }

    private UploadImageUtils(){

    }

    public static UploadImageUtils getInstance(){
        if(null == INSTANCE){
            synchronized(UploadImageUtils.class){
                if(null == INSTANCE){
                    INSTANCE = new UploadImageUtils();
                }
            }
        }
        return INSTANCE;
    }


    public void uploadImageByVolley(final String url, final Bitmap bitmap, final UploadImageByVolleyListener listener){
        if(null == bitmap){
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        UploadImageRequest<UploadImageResult> request = new UploadImageRequest<UploadImageResult>(url,
                UploadImageResult.class, new Response.Listener<UploadImageResult>() {
            @Override
            public void onResponse(UploadImageResult result) {
                if(null != listener){
                    listener.onSuccess(result);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                if(null != listener){
                    listener.onFailure(volleyError);
                }
            }
        }).setPostBodyHelper(new UploadImagePostBodyHelper().setBytes(baos.toByteArray()));
        StartVolleyManager.getRequestQueue().add(request);
    }

    public void uploadImageByATaskThread(final String url,final Bitmap bitmap,final UploadImageByVolleyListener
            listener){
        if(null == bitmap || TextUtils.isEmpty(url)){
            return;
        }
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
            baos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        final UploadImageTask task = (UploadImageTask) new UploadImageTask(new ITaskListener() {
            @Override
            public void onFinishedListener(ATask task, int resultCode, Object result) {
                if(resultCode == ATask.TASK_OK){
                    UploadImageResult imageResult = (UploadImageResult) result;
                    if(null != listener){
                        listener.onSuccess(imageResult);
                    }
                }else{
                    if(null != listener){
                        listener.onFailure(null);
                    }
                }
            }
        }).setBitmap(bitmap).setUrl(url);
        ThreadPool.getInstance().addTask(task);
    }

    public void uploadImageByOriginalMethod(final String url,final Bitmap bitmap,final UploadImageByVolleyListener
            listener){

        final String uploadUrl = url;
        ATask task  = new DefaultNoNetworkTask() {
            @Override
            public void run() {
                try {
                    URL url = new URL(uploadUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setReadTimeout(TIME_OUT);
                    conn.setConnectTimeout(TIME_OUT);
                    conn.setDoInput(true); // 允许输入流
                    conn.setDoOutput(true); // 允许输出流
                    conn.setUseCaches(false); // 不允许使用缓存
                    conn.setRequestMethod("POST"); // 请求方式
                    conn.setRequestProperty("Charset", CHARSET); // 设置编码
                    conn.setRequestProperty("connection", "keep-alive");
                    conn.setRequestProperty("Content-Type", CONTENT_TYPE + ";boundary="
                            + BOUNDARY);

                    OutputStream outputSteam = conn.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(outputSteam);
                    StringBuffer sb = new StringBuffer();
                    sb.append(MP_BOUNDARY);
                    sb.append(CRLF);
                    sb.append("Content-Disposition: form-data; name=\"upload\"; filename=\""
                            + "news_image" + "\"" + CRLF);
                    sb.append("Content-Type: application/octet-stream; charset="
                            + CHARSET + CRLF);
                    sb.append(CRLF);
                    dos.write(sb.toString().getBytes());
                    byte[] bytes = ImageUtils.BitmapRecoverByte(bitmap);
                    dos.write(bytes);
                    dos.write(CRLF.getBytes());
                    byte[] end_data = (END_MP_BOUNDARY + CRLF)
                            .getBytes();
                    dos.write(end_data);
                    dos.flush();

                    /**
                     * 获取响应码 200=成功 当响应成功，获取响应的流
                     */
                    int res = conn.getResponseCode();
                    if (res == 200) {
                        System.out.println("成功！");
                    }

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        task.setPiority(ATask.TASK_LEVEL_BACKGROUND);
        ThreadPool.getInstance().addTask(task);
    }
}
