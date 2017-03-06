package cn.ling.android.ItemView;

import android.widget.LinearLayout;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import cn.ling.android.ItemView.helper.IData;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class BaseLinearLayout<T> extends LinearLayout implements IData<T> {

    protected T mItem;

    public BaseLinearLayout(Context context) {
        super(context);
        setContentView();
        initView();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        setContentView();
        initView();
    }

    public BaseLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setContentView();
        initView();
    }

    protected void setContentView(){
        LayoutInflater.from(getContext()).inflate(getLayoutResourceId(), this);
    }

    @Override
    public void setData(T item) {
        mItem = item;
        notifyDataChanged();
    }

    public <T extends View> T getViewById(int id){
        return (T) findViewById(id);
    }

    public <T extends View> T getViewWithTag(Object tag){
        return (T) findViewWithTag(tag);
    }

    protected abstract int getLayoutResourceId();
    protected abstract void initView();
    protected abstract void notifyDataChanged();
}
