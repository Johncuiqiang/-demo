package cn.ling.android.models;

import cn.ling.android.entities.ThirdPluginBaseDataEntity;

/**
 * Created by David小硕 on 2016/11/10.
 */

public interface IResponse3dPluginDataCallback<T extends ThirdPluginBaseDataEntity> {

    public abstract void responsedCallback(T dataEntity, Exception error);
}
