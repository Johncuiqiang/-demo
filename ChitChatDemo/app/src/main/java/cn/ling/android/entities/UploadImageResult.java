package cn.ling.android.entities;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class UploadImageResult extends BaseEntity {

    private String picid;
    private String obj_url;

    public String getPicid() {
        return picid;
    }

    public void setPicid(String picid) {
        this.picid = picid;
    }

    public String getObj_url() {
        return obj_url;
    }

    public void setObj_url(String obj_url) {
        this.obj_url = obj_url;
    }

    @Override
    public String toString() {
        return "UploadImageResult{" +
                "picid='" + picid + '\'' +
                ", obj_url='" + obj_url + '\'' +
                '}';
    }
}
