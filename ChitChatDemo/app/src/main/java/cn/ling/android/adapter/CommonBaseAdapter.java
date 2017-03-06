package cn.ling.android.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by David小硕 on 2016/9/28.
 */

public abstract class CommonBaseAdapter<T> extends BaseAdapter {

    protected List<T> mData = new ArrayList(0);
    protected Context mContext;

    public CommonBaseAdapter(Context context) {
        this.mContext = context;
    }

    public void setData(List<T> data) {
        if (data != null) {
            mData = data;
        }
    }


    public List<T> getData() {
        return mData;
    }

    public void addData(List<T> data) {
        if (null != data) {
            mData.addAll(data);
        }
    }

    public void addData(T item) {
        if (null != item) {
            mData.add(item);
        }
    }

    public int getCount() {
        return mData.size();
    }

    public T getItem(int position) {
        if (isValid(position)) {
            return (T) mData.get(position);
        }
        return null;
    }

    protected boolean isValid(int position) {
        if ((position >= 0) && (position < mData.size())) {
            return true;
        }
        return false;
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (isValid(position)) {
            return generateView(position, convertView, parent);
        }
        return null;

    }

    public int getItemViewType(int position) {
        return 0;
    }

    public int getViewTypeCount() {
        return 1;
    }

    protected abstract View generateView(int position, View convertView, ViewGroup parent);
}
