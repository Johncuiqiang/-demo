package cn.ling.android.ItemView.helper;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class BaseStyle implements IStyle {

    private transient int mPatientStyle = -1;

    @Override
    public IStyle setStyle(int style) {
        mPatientStyle = style;
        return this;
    }

    @Override
    public int getStyle() {
        return mPatientStyle;
    }
}
