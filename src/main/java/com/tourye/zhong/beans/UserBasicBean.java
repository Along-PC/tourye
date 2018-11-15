package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/11/2.
 * <p>
 * introduce:用户基本信息实体
 */

public class UserBasicBean {

    /**
     * status : 0
     * timestamp : 1541125436
     * data : {"id":47,"serial_number":"10001B","nickname":"阳光","avatar":"http://thirdwx.qlogo.cn/mmopen/2bv1RSicOuFkern2VZVQMtbzz5B9Jm49dnQiciaWKc0fMCUgvWriciaSeNfkpVFAZvNjbP1puKbVkibXYOPXVpNf1hiaULIvACOuv5ib/132","invite_id":null,"subscribe":false,"mobile_bound":true,"mobile":"18811364210"}
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
         * id : 47
         * serial_number : 10001B
         * nickname : 阳光
         * avatar : http://thirdwx.qlogo.cn/mmopen/2bv1RSicOuFkern2VZVQMtbzz5B9Jm49dnQiciaWKc0fMCUgvWriciaSeNfkpVFAZvNjbP1puKbVkibXYOPXVpNf1hiaULIvACOuv5ib/132
         * invite_id : null
         * subscribe : false
         * mobile_bound : true
         * mobile : 18811364210
         */

        private int id;
        private String serial_number;
        private String nickname;
        private String avatar;
        private Object invite_id;
        private boolean subscribe;
        private boolean mobile_bound;
        private String mobile;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getSerial_number() {
            return serial_number;
        }

        public void setSerial_number(String serial_number) {
            this.serial_number = serial_number;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public Object getInvite_id() {
            return invite_id;
        }

        public void setInvite_id(Object invite_id) {
            this.invite_id = invite_id;
        }

        public boolean isSubscribe() {
            return subscribe;
        }

        public void setSubscribe(boolean subscribe) {
            this.subscribe = subscribe;
        }

        public boolean isMobile_bound() {
            return mobile_bound;
        }

        public void setMobile_bound(boolean mobile_bound) {
            this.mobile_bound = mobile_bound;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }
    }
}
