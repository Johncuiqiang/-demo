package cn.ling.voice.models;

import cn.ling.android.models.BaseModel;
import cn.ling.android.models.HttpMethod;
import cn.ling.android.models.IResponseCallback;
import cn.ling.voice.entities.AccountInfo;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class AccoutModel extends BaseModel {

    public AccountInfo mResponseEntity;

    public AccoutModel(IResponseCallback responseCallback) {
        super(responseCallback);
        this.mApiPath = "https://ishutu.com/passport/login/";
    }


    @Override
    public void handlerResponseEntity(Object entity) {
        super.handlerResponseEntity(entity);
        this.mResponseEntity = (AccountInfo) entity;
    }

    public void executeReqAccoutInfo(){
        mParams.put("channel", "appstore");
        mParams.put("client", "ios");
        mParams.put("deviceid", "448A5693-196A-4FAA-B021-82B4F71093C5");
        mParams.put("emailphone", "davidxiaoshuo0814@163.com");
        mParams.put("password", "123456");
        mParams.put("version", "1.0");

        this.mResponseClassType = AccountInfo.class;
        this.startLoadWithMethod(HttpMethod.Post);
    }

    @Override
    protected Boolean isJsonRPCServer() {
        return false;
    }
}
