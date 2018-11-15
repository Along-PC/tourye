package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:相册图片
 */

public class AlbumPhotosBean {
    /**
     * status : 0
     * timestamp : 1540884968
     * data : {"gallery":{"name":"爱情情请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金"},"album":{"name":"请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金"},"photos":["http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/BM2xJm","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/fbgyU8","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/sfdQBw","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/EDDjYF","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/s64acl","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/gfA9gk"]}
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
         * album : {"name":"请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金"}
         * photos : ["http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/BM2xJm","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/fbgyU8","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/sfdQBw","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/EDDjYF","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/s64acl","http://static.ro-test.xorout.com/photos/3/3/2018OctMon115028/gfA9gk"]
         */

        private GalleryBean gallery;
        private AlbumBean album;
        private List<String> photos;

        public GalleryBean getGallery() {
            return gallery;
        }

        public void setGallery(GalleryBean gallery) {
            this.gallery = gallery;
        }

        public AlbumBean getAlbum() {
            return album;
        }

        public void setAlbum(AlbumBean album) {
            this.album = album;
        }

        public List<String> getPhotos() {
            return photos;
        }

        public void setPhotos(List<String> photos) {
            this.photos = photos;
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

        public static class AlbumBean {
            /**
             * name : 请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金请呼叫看看卡卡啦啦啦啦啦啦空间基金金
             */

            private String name;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
