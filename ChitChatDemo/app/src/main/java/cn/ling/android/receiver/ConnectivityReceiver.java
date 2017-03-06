package cn.ling.android.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import cn.ling.android.utils.NetStatusUtil;


/**
 * Created by David小硕 on 2016/9/28.
 */

public class ConnectivityReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (!action.equals("android.net.conn.CONNECTIVITY_CHANGE")) {
            return;
        }
        NetStatusUtil.changed();
    }
}
