package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:通用实体
 */

public class CommonBean {

    /**
     * status : 0
     * timestamp : 1540866023
     * data : null
     */

    private int status;
    private int timestamp;
    private Object data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
