package cn.ling.android.models;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */


public interface IModel<T> {

    public abstract void startLoadWithMethod(HttpMethod method);

    public abstract void prepareParams();

    public abstract <T extends BaseEntity> void startLoadWithPostMethod();

    public abstract <T extends BaseEntity> void startLoadWithGetMethod();

}
