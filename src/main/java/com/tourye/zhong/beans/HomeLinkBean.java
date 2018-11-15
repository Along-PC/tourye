package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:首页顶部连接实体
 */

public class HomeLinkBean {

    /**
     * status : 0
     * timestamp : 1540800109
     * data : [{"image":"http://static.ro-test.xorout.com/image_upload/2018-10-26-04-16-29/GZ7n8J-1","text":"test","url":"https://www.baidu.com/"},{"image":"http://static.ro-test.xorout.com/image_upload/2018-10-26-04-16-29/GZ7n8J-1","text":"test","url":"https://www.baidu.com/"},{"image":"http://static.ro-test.xorout.com/image_upload/2018-10-26-04-16-29/GZ7n8J-1","text":"test","url":"https://www.baidu.com/"},{"image":"http://static.ro-test.xorout.com/image_upload/2018-10-26-04-16-29/GZ7n8J-1","text":"test","url":"https://www.baidu.com/"}]
     */

    private int status;
    private int timestamp;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * image : http://static.ro-test.xorout.com/image_upload/2018-10-26-04-16-29/GZ7n8J-1
         * text : test
         * url : https://www.baidu.com/
         */

        private String image;
        private String text;
        private String url;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
