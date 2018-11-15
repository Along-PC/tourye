package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:二级相册实体
 */

public class ChildAlbumBean {

    /**
     * status : 0
     * timestamp : 1540871285
     * data : {"gallery":{"name":"爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金"},"albums":[{"id":3,"name":"请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金","cover":"http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/BM2xJm"}]}
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
         * gallery : {"name":"爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金"}
         * albums : [{"id":3,"name":"请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金","cover":"http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/BM2xJm"}]
         */

        private GalleryBean gallery;
        private List<AlbumsBean> albums;

        public GalleryBean getGallery() {
            return gallery;
        }

        public void setGallery(GalleryBean gallery) {
            this.gallery = gallery;
        }

        public List<AlbumsBean> getAlbums() {
            return albums;
        }

        public void setAlbums(List<AlbumsBean> albums) {
            this.albums = albums;
        }

        public static class GalleryBean {
            /**
             * name : 爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }

        public static class AlbumsBean {
            /**
             * id : 3
             * name : 请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金
             * cover : http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/BM2xJm
             */

            private int id;
            private String name;
            private String cover;

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
        }
    }
}
