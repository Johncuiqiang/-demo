package cn.ling.android.entities;

import java.io.Serializable;

/**
 * Created by David小硕 on 2016/9/28.
 */

public class BaseEntity implements Serializable {

    public static final int STATUS_OK = 1;
    public static final int STATUS_ERROR = -1;

    private static final long serialVersionUID = 1L;

    private transient int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
