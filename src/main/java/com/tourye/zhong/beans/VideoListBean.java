package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:视频列表实体
 */

public class VideoListBean {

    /**
     * status : 0
     * timestamp : 1540867884
     * data : [{"id":2,"title":"美食","length":"05:21:00","frame_url":"https://v.qq.com/iframe/player.html?vid=s0606i3aqqu"},{"id":1,"title":"薛之谦节目视频请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金","length":"05:21:00","frame_url":"https://v.qq.com/iframe/player.html?vid=s0606i3aqqu"}]
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
         * id : 2
         * title : 美食
         * length : 05:21:00
         * frame_url : https://v.qq.com/iframe/player.html?vid=s0606i3aqqu
         */

        private int id;
        private String title;
        private String length;
        private String frame_url;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLength() {
            return length;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public String getFrame_url() {
            return frame_url;
        }

        public void setFrame_url(String frame_url) {
            this.frame_url = frame_url;
        }
    }
}
