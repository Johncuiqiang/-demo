package cn.ling.android.parser;

import java.io.InputStream;

/**
 * Created by David小硕 on 2016/9/28.
 */

public interface IParser {

    public abstract Object parser(InputStream paramInputStream);
}
