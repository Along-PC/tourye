package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:登录实体
 */

public class LoginBean {

    /**
     * status : 0
     * timestamp : 1540785468
     * data : eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJleHAiOjE1NDEzOTAyNjgsImlhdCI6MTU0MDc4NTQ2OCwidWlkIjoiMTAwMDFCIiwiY2xpZW50IjoiYXBwIiwibm9uY2UiOiJ6Q3VxSWlTWCJ9.jl0mUVTR_T1nmFKi8oC81nLDhuenNCp-vjV4NtXmtlo
     */

    private int status;
    private int timestamp;
    private String data;

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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
