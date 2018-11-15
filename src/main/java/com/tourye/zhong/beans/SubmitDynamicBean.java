package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/11/2.
 * <p>
 * introduce:上传动态时json格式
 */

public class SubmitDynamicBean {

    /**
     * content : xxx
     * images : [1,2,3]
     */

    private String content;
    private List<Integer> images;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }
}
