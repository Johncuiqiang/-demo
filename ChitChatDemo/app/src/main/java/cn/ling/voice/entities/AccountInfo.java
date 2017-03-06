package cn.ling.voice.entities;

import cn.ling.android.entities.BaseEntity;

/**
 * Created by David小硕 on 2016/9/29.
 */

public class AccountInfo extends BaseEntity {

    private int uid;
    private String token;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AccountInfo{" +
                "uid=" + uid +
                ", token='" + token + '\'' +
                '}';
    }
}
