package cn.ling.android.fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.ling.android.IObjectDescription;
import cn.ling.android.handler.SafeHandler;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class BaseFragment extends Fragment implements IObjectDescription, SafeHandler.IHandlerMessage {

    protected String mUniqueIdentifier = "";
    protected Handler mDefaultHandler;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUniqueIdentifier = String.valueOf(this);
        mDefaultHandler = new SafeHandler(this);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return createView(inflater, container, savedInstanceState);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init(savedInstanceState);
        afterInit(savedInstanceState);
    }


    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    public <T extends View> T getViewById(int id){
        return (T) getView().findViewById(id);
    }

    public <T extends View> T getViewWithTag(Object tag){
        return (T) getView().findViewWithTag(tag);
    }


    @Override
    public void handlerMessage(Message msg) {

    }

    @Override
    public String getUniqueIdentifier() {
        return mUniqueIdentifier;
    }

    protected void afterInit(Bundle savedInstanceState) {}
    protected abstract void init(Bundle paramBundle);
    protected abstract View createView(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle);
}
