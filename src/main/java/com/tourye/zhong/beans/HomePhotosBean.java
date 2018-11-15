package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/29.
 * <p>
 * introduce:首页底部轮播图实体
 */

public class HomePhotosBean {

    /**
     * status : 0
     * timestamp : 1540801989
     * data : ["https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/iJoEXq","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/6d2tHw","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/BBDoub","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/xUB7C7","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/SzLtTJ","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104838/n9Gmt1","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104839/S0D0n6","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104839/yxbhRF","https://ro-test.oss-cn-beijing.aliyuncs.com/home_page_photos/2018SepMon104839/5HL0uu"]
     */

    private int status;
    private int timestamp;
    private List<String> data;

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

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
