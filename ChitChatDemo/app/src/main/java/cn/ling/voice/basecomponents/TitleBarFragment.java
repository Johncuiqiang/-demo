package cn.ling.voice.basecomponents;

import android.view.View;

import cn.ling.android.fragments.BaseFragment;
import cn.ling.voice.R;
import cn.ling.voice.customui.TitleBar;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class TitleBarFragment extends BaseFragment implements TitleBar.OnClickListener{

    protected TitleBar mTitleBar;

    private void initTitleBar() {
        mTitleBar = getViewById(R.id.titleBar);
        mTitleBar.setOnClickListener(this);
    };

    public void setTitleLeft(View view) {
        if (null == mTitleBar) {
            initTitleBar();
        }
        mTitleBar.setLeft(view);
    }

    public void setTitleMiddle(View view) {
        if (null == mTitleBar) {
            initTitleBar();
        }
        mTitleBar.setMiddle(view);
    }

    public void setTitleRight(View view) {
        if (null == mTitleBar) {
            initTitleBar();
        }
        mTitleBar.setRight(view);
    }

    @Override
    public void onClickLeft() {

    }

    @Override
    public void onClickMiddle() {

    }

    @Override
    public void onClickRight() {

    }

}
