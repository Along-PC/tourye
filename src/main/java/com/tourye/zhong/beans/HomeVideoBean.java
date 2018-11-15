package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:首页视频实体
 */

public class HomeVideoBean {

    /**
     * status : 0
     * timestamp : 1540803819
     * data : [{"url":"http://static.ro-test-video.xorout.com/video_collection/2018-10-25/3f00864b62fc47f4bd16c88d34e5ee47","cover":"http://static.ro-test.xorout.com/image_upload/2018-10-25-04-01-04/PUOheg-1","width":1920,"height":822}]
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
         * url : http://static.ro-test-video.xorout.com/video_collection/2018-10-25/3f00864b62fc47f4bd16c88d34e5ee47
         * cover : http://static.ro-test.xorout.com/image_upload/2018-10-25-04-01-04/PUOheg-1
         * width : 1920
         * height : 822
         */

        private String url;
        private String cover;
        private int width;
        private int height;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }
}
