package cn.ling.android.activities;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.WindowManager;

import cn.ling.android.ActivityManager;
import cn.ling.android.IObjectDescription;
import cn.ling.android.handler.SafeHandler;


/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class BaseFragmentActivity extends FragmentActivity implements IObjectDescription,SafeHandler.IHandlerMessage{

    protected String mUniqueIdentifier = "";
    protected Handler mDefaultHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(visibleStatusBar()){
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                    WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
        mUniqueIdentifier = String.valueOf(hashCode());
        mDefaultHandler = new SafeHandler(this);
        ActivityManager.addActivity(this);
        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityManager.removeActivity(this);
    }

    @Override
    public void handlerMessage(Message msg) {

    }

    @Override
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    protected boolean visibleStatusBar(){
        return false;
    }

    public <T extends View> T getViewById(int id){
        return (T) super.findViewById(id);
    }


    protected abstract void init(Bundle savedInstanceState);
}
