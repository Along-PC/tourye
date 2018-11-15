package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/11/2.
 * <p>
 * introduce:上传文件实体
 */

public class UploadImageBean {

    /**
     * status : 0
     * timestamp : 1541145691
     * data : {"id":628,"url":"http://static.ro-test.xorout.com/1/2018-11-02/10001B/9cf9233f46cd4affa439bfd5d5a94680"}
     */

    private int status;
    private int timestamp;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 628
         * url : http://static.ro-test.xorout.com/1/2018-11-02/10001B/9cf9233f46cd4affa439bfd5d5a94680
         */

        private int id;
        private String url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
