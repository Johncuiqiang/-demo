package cn.ling.android.volley.basehelper;

import com.android.volley.toolbox.BasicNetwork;
import com.android.volley.toolbox.ByteArrayPool;
import com.android.volley.toolbox.HttpStack;

import org.apache.http.Header;
import org.apache.http.HttpEntity;

import java.io.IOException;
import java.io.InputStream;
import java.util.zip.GZIPInputStream;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class ExpandBasicNetwork extends BasicNetwork {

    public ExpandBasicNetwork(HttpStack httpStack) {
        super(httpStack);
    }

    public ExpandBasicNetwork(HttpStack httpStack, ByteArrayPool pool) {
        super(httpStack, pool);
    }

//    public NetworkResponse performRequest(Request<?> request) throws VolleyError {
//
//    }

    protected InputStream getInputStream(HttpEntity entity) throws IOException {
        String contentEncodingValue = null;
        Header header = entity.getContentEncoding();
        if(header!=null){
            contentEncodingValue =header.getValue();
        }
        if (contentEncodingValue!=null){
            if (contentEncodingValue.equals("gzip")){
                return new GZIPInputStream(entity.getContent());
            }
        }
        return super.getInputStream(entity);
    }
}
