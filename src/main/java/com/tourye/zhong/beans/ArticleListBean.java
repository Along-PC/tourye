package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/30.
 * <p>
 * introduce:文章列表实体
 */

public class ArticleListBean {

    /**
     * status : 0
     * timestamp : 1540863893
     * data : [{"id":9,"title":"坚持就是胜利","author":"达达22","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon014249/Q94GLV","url":"https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw"},{"id":3,"title":"爱是我们最美好的坚持","author":"呆呆","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon103704/R7mZsy","url":"https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw"},{"id":4,"title":"2018 车戈 | \u201c理想，行动，坚持，徒步和做企业一样\u201d","author":"呆呆","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon103818/0TLExz","url":"https://mp.weixin.qq.com/s/0vwh_T2c0ifqsRjGkiisSA"},{"id":5,"title":"你知道吗？中国徒步史上的未解之谜","author":"烽火戏诸侯","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon104042/jmRon2","url":"https://mp.weixin.qq.com/s/XJBfeLKyf2RUAJ0cdmqDtQ"},{"id":8,"title":"坚持就是胜利","author":"达达","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon014110/x1Lwwd","url":"https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw"},{"id":7,"title":"填写战队信息页面\u2014提交按钮，队长头衔为空时，提醒的是\u201c参数不正确\u201d，提交不成功；战队标签为空时，可以提交成功。","author":"填写战队信息页面\u2014提交按钮，队长头衔为空时，提醒的是\u201c参数不正确\u201d，提交不成功；战队标签为空时，可以提交成功。","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon110153/h4YhqQ","url":"https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw"},{"id":6,"title":"陶冶户外互联网版图战略发布","author":"呆呆","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon104646/Tv7rKi","url":"https://mp.weixin.qq.com/s/3yHtIDeQLQ3u-J1xMrvvkA"},{"id":2,"title":"大漠黄沙，广袤戈壁，爱是我们最美好的坚持","author":"呆呆","cover":"http://static.ro-test.xorout.com/article_cover/2018OctMon103526/2DIXbn","url":"https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw"}]
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
         * id : 9
         * title : 坚持就是胜利
         * author : 达达22
         * cover : http://static.ro-test.xorout.com/article_cover/2018OctMon014249/Q94GLV
         * url : https://mp.weixin.qq.com/s/p0fkUWd_q5DYURxivlEFNw
         */

        private int id;
        private String title;
        private String author;
        private String cover;
        private String url;

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

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getCover() {
            return cover;
        }

        public void setCover(String cover) {
            this.cover = cover;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
