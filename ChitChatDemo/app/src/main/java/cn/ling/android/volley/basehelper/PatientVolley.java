package cn.ling.android.volley.basehelper;

import android.os.Build;

import com.android.volley.toolbox.Volley;
import com.android.volley.Network;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.DiskBasedCache;
import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.X509TrustManager;

import cn.ling.android.network.SslHttpClient;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class PatientVolley extends Volley {

    private static final String DEFAULT_CACHE_DIR="volley";

    public static RequestQueue newRequestQueue(PatientVolleyBuilder builder){

        File cacheDir = new File(builder.getAppContext().getCacheDir(),DEFAULT_CACHE_DIR);
        String userAgent = builder.getUserAgent();
        HttpStack stack = builder.getHttpStack();

        if (stack==null){
            if(Build.VERSION.SDK_INT>=9){
                X509TrustManager xtm = new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return new X509Certificate[0];
                    }
                };
                SSLContext sslContext = null;
                try{
                    sslContext = SSLContext.getInstance("TLS");
                    sslContext.init(null,new X509TrustManager[]{xtm},new SecureRandom());
                }catch (NoSuchAlgorithmException e){
                    e.printStackTrace();
                    sslContext = null;
                } catch (KeyManagementException e) {
                    e.printStackTrace();
                    sslContext = null;
                }
                if (sslContext!=null){
                    stack = new HurlStack(null,sslContext.getSocketFactory());
                }else {
                    stack = new HurlStack();
                }

            }else{
                stack = new HttpClientStack(SslHttpClient.newInstance(userAgent));
            }

        }

        Network network = new ExpandBasicNetwork(stack);
        RequestQueue queue = new RequestQueue(new DiskBasedCache(cacheDir),network);
        queue.start();

        return queue;
    }

}
