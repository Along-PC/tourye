package com.tourye.zhong.views.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.utils.SaveUtil;

import cn.sharesdk.onekeyshare.OnekeyShare;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.tencent.qzone.QZone;
import cn.sharesdk.wechat.friends.Wechat;
import cn.sharesdk.wechat.moments.WechatMoments;

/**
 * Created by longlongren on 2018/8/17.
 * <p>
 * introduce:分享dialog
 */

public class ShareDialog extends Dialog implements View.OnClickListener {

    private final Context mContext;
    private LinearLayout mLlDialogShareWechat;
    private LinearLayout mLlDialogShareWechatMoment;
    private LinearLayout mLlDialogShareQq;
    private LinearLayout mLlDialogShareQqZone;
    private LinearLayout mLlDialogShareSina;
    private LinearLayout mLlDialogShare;
    private LinearLayout mLlDialogShareDownload;
    private TextView mTvDialogShareCancel;

    private String mTitle="";
    private String mDesc="";
    private String mImgUrl="";
    private String mLink="";
    private String mUrlDownload="";//要下载的图片的地址

    private DownloadCallback mDownloadCallback;

    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };


    public ShareDialog(@NonNull Context context) {
        super(context);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        getWindow().setGravity(Gravity.BOTTOM);
        setContentView(R.layout.dialog_share);

        WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
        layoutParams.gravity=Gravity.BOTTOM;
        layoutParams.width= WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height= WindowManager.LayoutParams.WRAP_CONTENT;
        getWindow().getDecorView().setPadding(0, 0, 0, 0);
        getWindow().setAttributes(layoutParams);

        getWindow().setWindowAnimations(R.style.HeadDialogStyle);

        mContext = context;

        mLlDialogShareWechat = (LinearLayout) findViewById(R.id.ll_dialog_share_wechat);
        mLlDialogShareWechatMoment = (LinearLayout) findViewById(R.id.ll_dialog_share_wechat_moment);
        mLlDialogShareQq = (LinearLayout) findViewById(R.id.ll_dialog_share_qq);
        mLlDialogShareQqZone = (LinearLayout) findViewById(R.id.ll_dialog_share_qq_zone);
        mLlDialogShareSina = (LinearLayout) findViewById(R.id.ll_dialog_share_sina);
        mLlDialogShare = (LinearLayout) findViewById(R.id.ll_dialog_share);
        mLlDialogShareDownload = (LinearLayout) findViewById(R.id.ll_dialog_share_download);
        mTvDialogShareCancel = (TextView) findViewById(R.id.tv_dialog_share_cancel);
        mTvDialogShareCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        mLlDialogShareWechat.setOnClickListener(this);
        mLlDialogShareWechatMoment.setOnClickListener(this);
        mLlDialogShareQq.setOnClickListener(this);
        mLlDialogShareQqZone.setOnClickListener(this);
        mLlDialogShareSina.setOnClickListener(this);
        mLlDialogShareDownload.setOnClickListener(this);

    }

    /**
     * 主页分享弹框---早起页面
     * @param isPunch 是否打卡成功
     */
    public void showMainDialog(boolean isPunch){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","8:00");
        String punch_continue = SaveUtil.getString("punch_continue", "~");
        if (isPunch) {
            mTitle="坚持早起第"+punch_continue+"天，今天"+punch_time+"，可以领一笔现金奖励，你要来玩玩吗";
            mDesc="坚持每天早起也能瓜分奖金 | 不起就出局";
            mImgUrl=user_head;
            mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#/user/share/"+punch_id;

        }else{
            mTitle="不起就出局：坚持每天早起也能瓜分奖金";
            mDesc="最火的早起打卡坚持社群";
            mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
            mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#/wake/wake";

        }
        mLlDialogShare.setVisibility(View.GONE);
        show();

    }

    /**
     * 主页分享弹框---早起页面      因qq、微博申请流程问题，暂时先上微信
     * @param isPunch 是否打卡成功
     */
    public void showMainDialogTemp(boolean isPunch){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","8:00");
        String punch_continue = SaveUtil.getString("punch_continue", "~");
        mLlDialogShareDownload.setVisibility(View.GONE);
        if (isPunch) {
            mTitle="坚持早起第"+punch_continue+"天，今天"+punch_time+"，可以领一笔现金奖励，你要来玩玩吗";
            mDesc="坚持每天早起也能瓜分奖金 | 不起就出局";
            mImgUrl=user_head;
//            mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#/user/share/"+punch_id;
            mLink= Constants.SHARE_URL+"/app/?#/guest/user/share/"+punch_id;

        }else{
            mTitle="不起就出局：坚持每天早起也能瓜分奖金";
            mDesc="最火的早起打卡坚持社群";
            mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
//            mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#/wake/wake";
            mLink=Constants.SHARE_URL+"/app/?#/guest/wake";

        }
        mLlDialogShare.setVisibility(View.GONE);
        show();

    }



    /**
     * 影响: /influence/influence
     局票: /wake/point
     局票明细: /wake/point_detail
     局票任务: /wake/point_task
     用户分享: /user/share/{打卡id}
     复活卡: /challenge/reborn_card
     特权: /point/card
     打卡记录: /user/signin_record
     个人中心: /user/home
     早起奖金: /user/wake_bonus
     早起奖金明细: /user/account_detail
     影响奖金: /user/influence_bonus
     影响奖金明细: /user/influence_detail
     邀请卡: /user/invite_card
     提醒设置: /user/setting
     成就卡: /user/achieve
     * @param linkUrl 要分享的链接
     */
    public void showDialog(String linkUrl){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","");
        String punch_continue = SaveUtil.getString("punch_continue", "");
        mTitle="不起就出局：坚持每天早起也能瓜分奖金";
        mDesc="最火的早起打卡坚持社群";
        mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
        mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#"+linkUrl;

        mLlDialogShare.setVisibility(View.GONE);
        show();
    }

    public void showDialogTemp(String linkUrl){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","");
        String punch_continue = SaveUtil.getString("punch_continue", "");
        mTitle="不起就出局：坚持每天早起也能瓜分奖金";
        mDesc="最火的早起打卡坚持社群";
        mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
//        mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#"+linkUrl;
        mLink=Constants.SHARE_URL+"/app/?#/guest/wake";
//        mLink="https://ss0.bdstatic.com/94oJfD_bAAcT8t7mm9GUKT-xh_/timg?image&quality=100&size=b4000_4000&sec=1537165730&di=8f732f137920766133223e07b2961a8a&src=http://imgsrc.baidu.com/imgad/pic/item/7acb0a46f21fbe09334115c061600c338644adc3.jpg";
        mLlDialogShareDownload.setVisibility(View.GONE);
        show();
    }

    public void showDialog(String linkUrl,DownloadCallback downloadCallback){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","");
        String punch_continue = SaveUtil.getString("punch_continue", "");

        mTitle="不起就出局：坚持每天早起也能瓜分奖金";
        mDesc="最火的早起打卡坚持社群";
        mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
        mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#"+linkUrl;
        mLlDialogShare.setVisibility(View.VISIBLE);

        mDownloadCallback=downloadCallback;

        show();
    }

    public void showDialogTemp(String linkUrl,DownloadCallback downloadCallback){
        String user_id = SaveUtil.getString("user_id", "");
        String user_head = SaveUtil.getString("user_head", "");
        String punch_id = SaveUtil.getString("punch_id", "");
        String punch_time = SaveUtil.getString("punch_time","");
        String punch_continue = SaveUtil.getString("punch_continue", "");

        mTitle="不起就出局：坚持每天早起也能瓜分奖金";
        mDesc="最火的早起打卡坚持社群";
        mImgUrl="https://static.wake.runorout.cn/meta/logo.png";
//        mLink="https://wake.runorout.cn/app/?referrer="+user_id+"#"+linkUrl;
        mLink=Constants.SHARE_URL+"/app/?#/guest/wake";
        mLlDialogShareDownload.setVisibility(View.VISIBLE);

        mDownloadCallback=downloadCallback;

        show();
    }

    public void share(String platform){
        final OnekeyShare oks = new OnekeyShare();
        //指定分享的平台，如果为空，还是会调用九宫格的平台列表界面
        oks.setPlatform(platform);
        //关闭sso授权
        oks.disableSSOWhenAuthorize();
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle(mTitle);
        // titleUrl是标题的网络链接，仅在Linked-in,QQ和QQ空间使用
        oks.setTitleUrl(mLink);
        // text是分享文本，所有平台都需要这个字段
        oks.setText(mDesc);
        //分享网络图片，新浪微博分享网络图片需要通过审核后申请高级写入接口，否则请注释掉测试新浪微博
        oks.setImageUrl(mImgUrl);
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
//        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl(mLink);
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment(mDesc);
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(mLink);
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl(mLink);
        //分享成功失败的回调
//        oks.setCallback(new PlatformActionListener() {
//
//            @Override
//            public void onError(Platform arg0, int arg1, Throwable arg2) {
//                // TODO Auto-generated method stub
//
//            }
//
//            @Override
//            public void onComplete(Platform arg0, int arg1, HashMap<String, Object> arg2) {
//                // TODO Auto-generated method stub
//                System.out.println("-----------------FENXIANGCHENGGGON");
//                mHandler.post(new Runnable() {
//                    public void run() {
//                        Toast.makeText(mContext,"成功", Toast.LENGTH_SHORT).show();
//                        System.out.println("----------");
//                    }
//                });
//
//            }
//
//            @Override
//            public void onCancel(Platform arg0, int arg1) {
//                // TODO Auto-generated method stub
//
//            }
//        });

        //启动分享
        oks.show(mContext);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_dialog_share_wechat:
                share(Wechat.NAME);
                break;
            case R.id.ll_dialog_share_wechat_moment:
                share(WechatMoments.NAME);
                break;
            case R.id.ll_dialog_share_qq:
                share(QQ.NAME);
                break;
            case R.id.ll_dialog_share_qq_zone:
                share(QZone.NAME);
                break;
            case R.id.ll_dialog_share_sina:
                share(SinaWeibo.NAME);
                break;
            case R.id.ll_dialog_share_download:
                mDownloadCallback.download();
                dismiss();
                break;
        }
        dismiss();
    }

    public interface DownloadCallback{
        public void download();
    }

}
