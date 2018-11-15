package com.tourye.zhong.beans;

import java.util.List;

/**
 * Created by longlongren on 2018/10/22.
 * <p>
 * introduce:二级评论实体
 */

public class CommentBean {
    private List<String> mStrings;
    private boolean isExpand=false;

    public List<String> getStrings() {
        return mStrings;
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }
}
