package cn.ling.android.ItemView.helper;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class WrapperItem<T> extends BaseStyle {

    private T wrapper;

    public T getWrapper() {
        return (T)wrapper;
    }

    public  WrapperItem<T> setWrapper(T wrapper) {
        this.wrapper = wrapper;
        return this;
    }
}
