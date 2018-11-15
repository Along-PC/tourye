package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/31.
 * <p>
 * introduce:动态详情实体
 */

public class CommunityDetailBean {


    /**
     * status : 0
     * timestamp : 1540970083
     * data : {"id":10,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":"群","images":[],"thumb_up_count":0,"comment_count":1,"already_thumb_up":false,"create_time":"2018-10-15 10:17:07"}
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
         * id : 10
         * user_id : 10
         * avatar : http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132
         * nickname : 晨晨-不起就出局app
         * content : 群
         * images : []
         * thumb_up_count : 0
         * comment_count : 1
         * already_thumb_up : false
         * create_time : 2018-10-15 10:17:07
         */

        private int id;
        private int user_id;
        private String avatar;
        private String nickname;
        private String content;
        private int thumb_up_count;
        private int comment_count;
        private boolean already_thumb_up;
        private String create_time;
        private List<?> images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getThumb_up_count() {
            return thumb_up_count;
        }

        public void setThumb_up_count(int thumb_up_count) {
            this.thumb_up_count = thumb_up_count;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public boolean isAlready_thumb_up() {
            return already_thumb_up;
        }

        public void setAlready_thumb_up(boolean already_thumb_up) {
            this.already_thumb_up = already_thumb_up;
        }

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

        public List<?> getImages() {
            return images;
        }

        public void setImages(List<?> images) {
            this.images = images;
        }
    }
}
