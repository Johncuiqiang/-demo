package cn.ling.android.threadpool;

import android.os.Build;


import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import cn.ling.android.parser.CommonJsonParserForSerialBaseData;
import cn.ling.android.parser.IParser;
import cn.ling.android.entities.UploadImageResult;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UploadImageTask extends BaseUploadImageTask {

    private int mReadTimeout = 30000;
    private int mConnectTimeout = 3000;

    public UploadImageTask(ITaskListener listener) {
        setTaskListener(listener);
    }

    @Deprecated
    @Override
    public void run() {

    }

    @Override
    public void run(HttpClient httpClient) {
        if (Build.VERSION.SDK_INT >= 9) {
            URL url = null;
            HttpURLConnection connection = null;
            try {
                url = new URL(getUrl());
                connection = (HttpURLConnection) url.openConnection();
                connection.setDoInput(true);
                connection.setDoOutput(true);
                connection.setReadTimeout(getReadTimeout());
                connection.setConnectTimeout(getConnectTimeout());
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Connection", "Keep-Alive");
                connection.setRequestProperty("Charset", "UTF-8");
                connection.setUseCaches(false);

                connection.setRequestProperty("Content-Type",
                        MULTIPART_FORM_DATA + "; boundary=" + BOUNDARY);
                connection.connect();

                OutputStream os = connection.getOutputStream();
                os.write(generateContent());
                os.flush();
                os.close();

                int responseCode = connection.getResponseCode();
                if (responseCode == ATask.TASK_OK) {
                    String responseMessage = connection.getResponseMessage();
                    IParser parser = new CommonJsonParserForSerialBaseData(
                            UploadImageResult.class);
                    UploadImageResult result = (UploadImageResult) parser
                            .parser(connection.getInputStream());
                    if (null != getTaskListener()) {
                        getTaskListener().onFinishedListener(this, ATask.TASK_OK,
                                result);
                    }
                } else {
                    if (null != getTaskListener()) {
                        getTaskListener().onFinishedListener(this,
                                ATask.TASK_NETWORK_ERR, null);
                    }
                }

                return;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (null != getTaskListener()) {
                getTaskListener().onFinishedListener(this, ATask.TASK_ERR, null);
            }
        } else {
            final HttpPost post = new HttpPost(getUrl());
            post.setHeader("Content-Type", MULTIPART_FORM_DATA + "; boundary="
                    + BOUNDARY);
            try {
                ByteArrayEntity formMultiEntity = new ByteArrayEntity(
                        generateContent());
                post.setEntity(formMultiEntity);
            } catch (IOException e) {
                e.printStackTrace();
            }

            HttpResponse response = null;
            try {
                response = httpClient.execute(post);
                int statusCode = response.getStatusLine().getStatusCode();
                if (statusCode == ATask.TASK_OK) {
                    IParser parser = new CommonJsonParserForSerialBaseData(
                            UploadImageResult.class);
                    UploadImageResult result = (UploadImageResult) parser
                            .parser(response.getEntity().getContent());
                    if (null != getTaskListener()) {
                        getTaskListener().onFinishedListener(this, ATask.TASK_OK,
                                result);
                    }
                } else {
                    if (null != getTaskListener()) {
                        getTaskListener().onFinishedListener(this,
                                ATask.TASK_NETWORK_ERR, null);
                    }
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (null != getTaskListener()) {
                getTaskListener().onFinishedListener(this, ATask.TASK_ERR, null);
            }
        }
    }

    public int getReadTimeout() {
        return mReadTimeout;
    }

    public UploadImageTask setReadTimeout(int readTimeout) {
        this.mReadTimeout = readTimeout;

        return this;
    }

    public int getConnectTimeout() {
        return mConnectTimeout;
    }

    public UploadImageTask setConnectTimeout(int connectTimeout) {
        this.mConnectTimeout = connectTimeout;

        return this;
    }

    @Override
    protected void config(StringBuilder sb) {
        sb.append(
                "Content-Disposition: form-data; name=\"upload\"; filename=\"")
                .append("news_image").append("\"").append(CRLF);
        final String filetype = "image/png";
        sb.append("Content-Type: ").append(filetype).append(CRLF);
    }
}
