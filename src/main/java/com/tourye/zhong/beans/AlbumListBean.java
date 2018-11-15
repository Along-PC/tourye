package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:发现--相册列表
 */

public class AlbumListBean {

    /**
     * status : 0
     * timestamp : 1540869362
     * data : [{"id":3,"name":"爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金","cover":"http://static.ro-test.xorout.com/gallery_cover/2018OctMon113225/ueIBuM","count":18}]
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
         * id : 3
         * name : 爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金
         * cover : http://static.ro-test.xorout.com/gallery_cover/2018OctMon113225/ueIBuM
         * count : 18
         */

        private int id;
        private String name;
        private String cover;
        private int count;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }
    }
}
