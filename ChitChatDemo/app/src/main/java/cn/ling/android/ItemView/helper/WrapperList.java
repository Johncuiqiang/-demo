package cn.ling.android.ItemView.helper;


import java.util.List;

/**
 * Created by David小硕 on 2016/9/28.
 */

class WrapperList<T> extends BaseStyle {

    List<T> list;

    public List<T> getList() {
        return list;
    }

    public WrapperList<T> setList(List<T> list) {
        this.list = list;
        return this;
    }
}
