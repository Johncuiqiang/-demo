package cn.ling.android.network;

import org.apache.http.HttpVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

import java.security.KeyStore;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class SslHttpClient {

    private static final int SOCKET_OPERATION_TIMEOUT = 6000;

    public static HttpClient newInstance(String userAgent) {

        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            MySslSocketFactory sslSocketFactory = new MySslSocketFactory(trustStore);

            HttpParams params = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(params,SOCKET_OPERATION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(params, SOCKET_OPERATION_TIMEOUT);
            HttpConnectionParams.setSocketBufferSize(params, 8192);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, "UTF-8");
            HttpProtocolParams.setUserAgent(params, userAgent);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http",PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sslSocketFactory, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);
            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }

    }
}
