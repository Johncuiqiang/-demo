package cn.ling.voice.chitchat.nlu;

import cn.ling.android.models.Base3dPluginDataModel;
import cn.ling.android.models.HttpMethod;
import cn.ling.android.models.IResponse3dPluginDataCallback;

import static cn.ling.voice.chitchat.nlu.NLUNetRequestApi.API_PATH_NLU;

/**
 * Created by cuiqiang on 2016/12/22.
 */
public class NLUModel extends Base3dPluginDataModel {

    private NLUResultEntity mResponseEntity;

    public NLUModel(IResponse3dPluginDataCallback responseCallback) {
        super(responseCallback);
        this.mApiPath = API_PATH_NLU;
    }

    @Override
    public void handlerResponseEntity(Object entity) {
        super.handlerResponseEntity(entity);
        this.mResponseEntity = (NLUResultEntity) entity;
    }

    public void executeReqNLUResult(String text) {
        mParams.put("userid", NLUNetRequestApi.NLU_USER_ID);
        mParams.put("words", text);
        mParams.put("score", NLUNetRequestApi.NLU_SCORE);

        this.mResponseClassType = NLUResultEntity.class;
        this.startLoadWithMethod(HttpMethod.Post);
    }

    @Override
    protected Boolean isJsonRPCServer() {
        return false;
    }

}
