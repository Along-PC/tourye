package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:赛事列表实体
 */

public class ActivityListBean {

    /**
     * status : 0
     * timestamp : 1540798092
     * data : [{"id":133,"name":"茶马108 | 第一届茶马古道越野挑战赛","price":1299900,"image":"https://static.zhong.tourye.cn/image_upload/2018-06-27-07-22-01/8Mm4Ka-1","thumbnail":"https://static.zhong.tourye.cn/image_upload/2018-05-20-04-24-26/OuPTBm-1","start_date":"2018-10-26 07:00:00","finish_date":"2018-10-29 16:00:00","sign_up_start_date":"2018-05-20 16:36:35","crowd_finish_date":"2018-09-26 00:00:00","order_finish_date":"2018-09-26 00:00:00"},{"id":164,"name":"茶马108 | 第一届归藏居问道茶马越野挑战赛","price":1690000,"image":"https://static.zhong.tourye.cn/image_upload/2018-07-27-05-52-29/URCleg-1","thumbnail":"https://static.zhong.tourye.cn/image_upload/2018-07-30-02-32-44/X2BRMQ-1","start_date":"2018-10-20 08:00:00","finish_date":"2018-10-23 20:00:00","sign_up_start_date":"2018-07-31 00:00:00","crowd_finish_date":"2018-09-20 00:00:00","order_finish_date":"2018-09-27 00:00:00"},{"id":150,"name":"2018第二届中国创投精英挑战赛--穿越茶马古道","price":0,"image":"https://static.zhong.tourye.cn/image_upload/2018-06-15-05-57-40/MR09AU-1","thumbnail":"https://static.zhong.tourye.cn/image_upload/2018-06-15-05-57-37/ciVMDa-1","start_date":"2018-06-14 17:52:47","finish_date":"2018-06-15 17:52:51","sign_up_start_date":"2018-06-13 00:00:01","crowd_finish_date":"2018-06-15 23:59:59","order_finish_date":"2018-06-15 23:59:59"},{"id":66,"name":"第二届生命密码族人徒步探索挑战赛","price":1280000,"image":"https://static.zhong.tourye.cn/old_images/2017-09-18/59bf9cdb4b69c.jpg","thumbnail":"https://static.zhong.tourye.cn/old_images/2017-09-18/59bf9cdb4b69c.jpg","start_date":"2018-05-20 00:00:00","finish_date":"2018-05-22 00:00:00","sign_up_start_date":"2018-05-01 00:05:43","crowd_finish_date":"2018-04-30 23:59:59","order_finish_date":"2018-05-01 23:59:59"},{"id":90,"name":"首届财富精英茶马古道徒步挑战赛","price":1380000,"image":"https://static.zhong.tourye.cn/old_images/2017-12-14/5a32213ac3103.jpg","thumbnail":"https://static.zhong.tourye.cn/old_images/2017-12-14/5a32213ac3103.jpg","start_date":null,"finish_date":null,"sign_up_start_date":null,"crowd_finish_date":"2018-03-05 00:00:00","order_finish_date":"2018-03-05 00:00:00"}]
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
         * id : 133
         * name : 茶马108 | 第一届茶马古道越野挑战赛
         * price : 1299900
         * image : https://static.zhong.tourye.cn/image_upload/2018-06-27-07-22-01/8Mm4Ka-1
         * thumbnail : https://static.zhong.tourye.cn/image_upload/2018-05-20-04-24-26/OuPTBm-1
         * start_date : 2018-10-26 07:00:00
         * finish_date : 2018-10-29 16:00:00
         * sign_up_start_date : 2018-05-20 16:36:35
         * crowd_finish_date : 2018-09-26 00:00:00
         * order_finish_date : 2018-09-26 00:00:00
         */

        private int id;
        private String name;
        private int price;
        private String image;
        private String thumbnail;
        private String start_date;
        private String finish_date;
        private String sign_up_start_date;
        private String crowd_finish_date;
        private String order_finish_date;

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

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getFinish_date() {
            return finish_date;
        }

        public void setFinish_date(String finish_date) {
            this.finish_date = finish_date;
        }

        public String getSign_up_start_date() {
            return sign_up_start_date;
        }

        public void setSign_up_start_date(String sign_up_start_date) {
            this.sign_up_start_date = sign_up_start_date;
        }

        public String getCrowd_finish_date() {
            return crowd_finish_date;
        }

        public void setCrowd_finish_date(String crowd_finish_date) {
            this.crowd_finish_date = crowd_finish_date;
        }

        public String getOrder_finish_date() {
            return order_finish_date;
        }

        public void setOrder_finish_date(String order_finish_date) {
            this.order_finish_date = order_finish_date;
        }
    }
}
