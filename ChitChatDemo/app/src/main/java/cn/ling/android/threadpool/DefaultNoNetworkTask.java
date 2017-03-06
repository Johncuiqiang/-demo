package cn.ling.android.threadpool;

import org.apache.http.client.HttpClient;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class DefaultNoNetworkTask extends ATask {

    @Override
    public void run() {

    }

    @Deprecated
    public void run(HttpClient client){

    }
}
