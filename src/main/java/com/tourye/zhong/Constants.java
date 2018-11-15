package com.tourye.zhong;

/**
 * Created by longlongren on 2018/9/12.
 * <p>
 * introduce:网络连接地址常量
 */

public class Constants {

    public static final String BASE_URL=BuildConfig.BASE_URL;
    public static final String SHARE_URL="";

    //获取验证码
    public static final String GET_VERIFY_CODE=BASE_URL+"/auth/send_login_sms";
    //登录
    public static final String USER_LOGIN=BASE_URL+"/auth/check_login_sms";
    //路线列表
    public static final String ROADS_LIST=BASE_URL+"/activity/roads";
    //赛事列表
    public static final String ACTIVITY_LIST=BASE_URL+"/activity/list";
    //首页顶部按钮连接
    public static final String HOME_PAGE_LINK=BASE_URL+"/ui/home_page_links";
    //首页底部图片
    public static final String HOME_PAGE_PHOTOS=BASE_URL+"/ui/home_page_photos";
    //首页视频
    public static final String HOME_PAGE_VIDEO=BASE_URL+"/ui/home_page_videos";
    //发现---文章列表
    public static final String FIND_ARTICLE_LIST=BASE_URL+"/discover/articles";
    //创建文章
    public static final String CREATE_ARTICLE=BASE_URL+"/discover/create_contribution";
    //发现--视频列表
    public static final String FIND_VIDEO_LIST=BASE_URL+"/discover/videos";
    //发现--相册列表
    public static final String FIND_ALBUM_LIST=BASE_URL+"/discover/galleries";
    //相册二级列表
    public static final String CHILD_ALBUM_LIST=BASE_URL+"/discover/albums";
    //相册照片
    public static final String ALBUM_PHOTOS=BASE_URL+"/discover/photos";
    //发表动态列表
    public static final String DYNAMIC_LIST=BASE_URL+"/community/post_list";
    //动态详情
    public static final String DYNAMIC_DETAIL=BASE_URL+"/community/post_detail";
    //点赞
    public static final String THUMB_UP=BASE_URL+"/community/thumb_up";
    //取消点赞
    public static final String THUMB_UP_CANCEL=BASE_URL+"/community/cancel_thumb_up";
    //评论列表
    public static final String COMMENT_LIST=BASE_URL+"/community/comment_list";
    //回复列表
    public static final String REPLY_LIST=BASE_URL+"/community/reply_list";
    //删除回复
    public static final String DELETE_REPLY=BASE_URL+"/community/delete_reply";
    //删除评论
    public static final String DELETE_COMMENT=BASE_URL+"/community/delete_comment";
    //删除动态
    public static final String DELETE_DYNAMIC=BASE_URL+"/community/delete_post";
    //发表回复
    public static final String CREATE_REPLY=BASE_URL+"/community/create_reply";
    //发表评论
    public static final String CREATE_COMMENT=BASE_URL+"/community/create_comment";
    //用户基本信息
    public static final String USER_BASIC_INFO=BASE_URL+"/user/basic_info";
    //上传图片
    public static final String UPLOAD_IMAGE=BASE_URL+"/framework/upload_image";
    //发表动态
    public static final String CREATE_DYNAMIC=BASE_URL+"/community/create_post";
    //账户信息
    public static final String USER_ACCOUNT=BASE_URL+"/user/account";







}
