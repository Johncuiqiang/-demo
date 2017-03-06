package cn.ling.voice.models;

import cn.ling.android.models.BaseModel;
import cn.ling.android.models.HttpMethod;
import cn.ling.android.models.IResponseCallback;
import cn.ling.voice.entities.CategoryListEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class CategoryListModel extends BaseModel {

    public CategoryListEntity responseEntity;

    public CategoryListModel(IResponseCallback responseCallback){
        super(responseCallback);
        this.mApiPath = "https://ishutu.com/api/category/subscribe";
    }

    public void executedReqCategoryListAction(){
        mParams.put("channel", "appstore");
        mParams.put("client", "ios");
        mParams.put("deviceid", "98AA1AA7-CED8-49DD-AC51-0985224BC3E0");
        mParams.put("uid", 4);
        mParams.put("version", "1.0");

        this.mResponseClassType = CategoryListEntity.class;
        this.startLoadWithMethod(HttpMethod.Get);
    }


    @Override
    public void handlerResponseEntity(Object entity) {
        super.handlerResponseEntity(entity);
        responseEntity = (CategoryListEntity) entity;
    }
}
