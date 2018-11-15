package com.tourye.zhong.beans;

/**
 * Created by longlongren on 2018/10/12.
 * <p>
 * introduce:
 */

public class CommunityCommentBean {
    private String mComment;
    private boolean isExpand;
    private boolean isVisible=true;
    private CommentBean mCommentBean;

    public String getComment() {
        return mComment;
    }

    public void setComment(String comment) {
        mComment = comment;
    }

    public boolean isExpand() {
        return isExpand;
    }

    public void setExpand(boolean expand) {
        isExpand = expand;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public CommentBean getCommentBean() {
        return mCommentBean;
    }

    public void setCommentBean(CommentBean commentBean) {
        mCommentBean = commentBean;
    }
}
