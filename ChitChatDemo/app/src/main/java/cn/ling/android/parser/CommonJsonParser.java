package cn.ling.android.parser;

import org.json.JSONException;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class CommonJsonParser extends JsonBaseParser {

    private Class<?> mClassOfT;

    public CommonJsonParser(Class<?> classOfT) {
        this.mClassOfT = classOfT;
    }

    @Override
    public Object parseData(String dataJson) throws JSONException, InstantiationException, IllegalAccessException {
        return GsonParseHelper.parse(dataJson, mClassOfT);
    }
}
