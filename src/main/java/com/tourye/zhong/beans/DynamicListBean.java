package com.tourye.zhong.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/10/31.
 * <p>
 * introduce:发表动态列表实体
 */

public class DynamicListBean implements Serializable {

    /**
     * status : 0
     * timestamp : 1540955080
     * data : [{"id":20,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":null,"images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/12cb5fc41ad542229b4d37b4c150a288"],"thumb_up_count":2,"comment_count":0,"already_thumb_up":false},{"id":19,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":null,"images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/09d5a6b688804a2cadc68fe062ca8d43"],"thumb_up_count":2,"comment_count":0,"already_thumb_up":false},{"id":18,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":null,"images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/d90e88845a8f48ecbc9536902e64d11f"],"thumb_up_count":0,"comment_count":0,"already_thumb_up":false},{"id":17,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":null,"images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/5a33b9b853504824a9ddc6cfad7c6af9"],"thumb_up_count":0,"comment_count":0,"already_thumb_up":false},{"id":16,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":null,"images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/3afc87cd1e9d4bea85d6ba9fe0fb722e"],"thumb_up_count":2,"comment_count":1,"already_thumb_up":false},{"id":15,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":"斤斤计较几节课","images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/b52fdc82cdb6448c99ff4136f305f0e0","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/8cca88e4fd5c4053be55b4810053c352","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/3138fbbd77c94abb90fdca44fb4e3c72"],"thumb_up_count":1,"comment_count":2,"already_thumb_up":false},{"id":13,"user_id":3,"avatar":"http://thirdwx.qlogo.cn/mmopen/2bv1RSicOuFkXhnph8XPctNlu8Z57a6dwZ8pbK6aJ54UjjYJVNYgsgDiaEIJnVBzylGMTbXrSa9VGzeRexEeQoCPQe4Pg8ogkw/132","nickname":"见一滴墨水的蓝","content":"来来来","images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/39d1c18a-919d-4c38-970f-88864adab964/0ae24d458c714c8593dbbe885f0b98b3","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/39d1c18a-919d-4c38-970f-88864adab964/9cf4fde2f8c24c6caed06f42b89ef97b","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/39d1c18a-919d-4c38-970f-88864adab964/ac3243859e914d7cbce1bb7a755ba8fe"],"thumb_up_count":1,"comment_count":1,"already_thumb_up":false},{"id":12,"user_id":17,"avatar":"http://thirdwx.qlogo.cn/mmopen/BfNQn5ptpOV3wUlibRH72VQhnphlRicKKhDUwS88304A61qgFwC3T6icRNkzJeZjpccphgc8heLCbZialWCQ8jQKfWZvZIjzDAZI/132","nickname":"在这里看到自己曾经的美好时光不想","content":"对于人际关系，我逐渐总结出一个最合乎我的性情的原则就是互相尊重，亲疏随缘。","images":["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/d33586ac9e40468b810c4d439a0048ff","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/8cf8b12729ea414d92e9559481c2b327","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/03dbaeb45e744106b3bdba98f83757ad","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/2e6482111bcf41cabd9ac386f0506b10","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/74a05936ff7b414ca9080a1d0ef388d4","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/59a7a15e85764fe7b83b0cb9cd4507ab","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/35d2362027ef4767bb8ffd92e25a243f","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/95335a8f4a1040cda28d9ddfb8dafe42","https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/fc19ec20-1ef8-42e3-9370-d33f29c23e27/d2ef350d836244bc8afa4a3d3281e939"],"thumb_up_count":3,"comment_count":6,"already_thumb_up":false},{"id":10,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":"群","images":[],"thumb_up_count":0,"comment_count":1,"already_thumb_up":false},{"id":9,"user_id":10,"avatar":"http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132","nickname":"晨晨-不起就出局app","content":"色同意法国红酒付看看赶紧看看飞机哈哈非凡哥夫君君仿古街刚刚还好吧","images":[],"thumb_up_count":0,"comment_count":0,"already_thumb_up":false}]
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

    public static class DataBean implements Serializable {
        /**
         * id : 20
         * user_id : 10
         * avatar : http://thirdwx.qlogo.cn/mmopen/XvJev9ib59MXcPGRa6kSc1MW4gic5nzcicJSblsPicsMibhG3ozjOXAFGpnpXoyrhibE3zL6NMc7E0OXtcmyWn200UxsP2dhJglabD/132
         * nickname : 晨晨-不起就出局app
         * content : null
         * images : ["https://ro-test.oss-cn-beijing.aliyuncs.com/image/2018-10-15/9b1bdf8f-43ef-4fb3-997d-8f90b0c79c16/12cb5fc41ad542229b4d37b4c150a288"]
         * thumb_up_count : 2
         * comment_count : 0
         * already_thumb_up : false
         */

        private int id;
        private int user_id;
        private String avatar;
        private String nickname;
        private String content;
        private int thumb_up_count;
        private int comment_count;
        private boolean already_thumb_up;
        private ArrayList<String> images;
        private String create_time;

        public String getCreate_time() {
            return create_time;
        }

        public void setCreate_time(String create_time) {
            this.create_time = create_time;
        }

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

        public ArrayList<String> getImages() {
            return images;
        }

        public void setImages(ArrayList<String> images) {
            this.images = images;
        }
    }
}
