package cn.ling.android.parser;


import org.json.JSONException;
import org.json.JSONObject;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class CommonJsonParserForSerialBaseData extends JsonBaseParser {

    private Class<? extends BaseEntity> mClassOfT;

    public CommonJsonParserForSerialBaseData(Class<? extends BaseEntity> classOfT) {
        mClassOfT = classOfT;
    }


    @Override
    public Object parseData(String paramString) throws JSONException, InstantiationException, IllegalAccessException {
        JSONObject object = new JSONObject(paramString);
        if (object.has("data") && object.has("code")) {
            Object dataObj = object.opt("data");
            BaseEntity baseData = GsonParseHelper.parse(dataObj.toString(),
                    mClassOfT);
            baseData.setStatus(object.optInt("code"));

            return baseData;
        } else {
            return null;
        }
    }
}
