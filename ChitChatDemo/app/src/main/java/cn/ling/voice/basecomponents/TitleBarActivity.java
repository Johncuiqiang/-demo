package cn.ling.voice.basecomponents;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import cn.ling.android.activities.BaseActivity;
import cn.ling.android.utils.NetStatusUtil;
import cn.ling.voice.R;
import cn.ling.voice.customui.CommonToast;
import cn.ling.voice.customui.TitleBar;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class TitleBarActivity extends BaseActivity implements TitleBar.OnClickListener {

    protected TitleBar mTitleBar;
    protected boolean mIsNeed2Or3gEstimate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        estimateNetStatus();
    }

    private void estimateNetStatus(){
        if(NetStatusUtil.hasNetwork()){
            CommonToast.makeText(this,getResources().getString(R.string.notNetNotify), Toast.LENGTH_SHORT);
        }
        if(mIsNeed2Or3gEstimate){
            if(NetStatusUtil.is2gOr3g()){
                CommonToast.makeText(this,getResources().getString(R.string.notWifigNotify), Toast.LENGTH_SHORT);
            }
        }
    }


    private void initTitleBar() {
        mTitleBar = getViewById(R.id.titleBar);
        mTitleBar.setOnClickListener(this);
    };

    public void setTitleLeft(View view) {
        checkTitleBar();
        mTitleBar.setLeft(view);
    }

    public void setTitleLeft(View view, int gravity) {
        checkTitleBar();
        mTitleBar.setLeft(view, gravity);
    }

    public void setTitleMiddle(View view) {
        checkTitleBar();
        mTitleBar.setMiddle(view);
    }

    public void setTitleRight(View view) {
        checkTitleBar();
        mTitleBar.setRight(view);
    }

    public void setTitleRight(View view, int gravity) {
        checkTitleBar();
        mTitleBar.setRight(view, gravity);
    }

    private void checkTitleBar() {
        if (null == mTitleBar) {
            initTitleBar();
        }
    }

    @Override
    public void onClickLeft() {
        finish();
    }

    @Override
    public void onClickMiddle() {
        // do nothing
    }

    @Override
    public void onClickRight() {
        // do nothing
    }
}
