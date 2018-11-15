package com.tourye.zhong.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/27.
 * <p>
 * introduce:
 */

public class DynamicBean {

    private List<String> mUrls=new ArrayList<>();

    public List<String> getUrls() {
        return mUrls;
    }

    public void setUrls(List<String> urls) {
        mUrls = urls;
    }
}
