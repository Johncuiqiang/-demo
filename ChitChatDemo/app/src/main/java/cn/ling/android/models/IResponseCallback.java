package cn.ling.android.models;


import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */

public interface IResponseCallback<T extends BaseEntity> {
    public abstract void responsedCallback(T dataEntity, int errorCode, Exception error);
}
