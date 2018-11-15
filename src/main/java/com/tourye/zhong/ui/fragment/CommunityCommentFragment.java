package com.tourye.zhong.ui.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.beans.CommonBean;
import com.tourye.zhong.beans.CreateCommentBean;
import com.tourye.zhong.beans.ReplyBean;
import com.tourye.zhong.beans.ReplyEntity;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.adapter.CommunityCommentAdapter;
import com.tourye.zhong.utils.SaveUtil;
import com.tourye.zhong.views.dialogs.DeleteCommentDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/10/15.
 * <p>
 * introduce:社区详情评论页面
 */

public class CommunityCommentFragment extends BaseCommunityFragment {
    private RecyclerView mListFragmentCommunityComment;
    private SmartRefreshLayout mRefreshLayoutFragmentCommunityComment;


    private CommunityCommentAdapter mCommunityCommentAdapter;

    private int last_id=0;//上一条评论的id
    private int count=10;//每次请求评论的数量
    private int id;//动态的id

    private List<ReplyBean.DataBean> mDataBeans=new ArrayList<>();//评论数据

    @Override
    public void initView(View view) {
        mListFragmentCommunityComment = (RecyclerView) view.findViewById(R.id.list_fragment_community_comment);
        mRefreshLayoutFragmentCommunityComment = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout_fragment_community_comment);

        //禁用下拉刷新
        mRefreshLayoutFragmentCommunityComment.setEnableRefresh(false);
//        mRefreshLayoutFragmentCommunityComment.setEnableAutoLoadMore(false);
        mRefreshLayoutFragmentCommunityComment.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getCommentList(false);
            }
        });
    }


    /**
     * 获取评论列表
     * @param isRefresh 是否是刷新操作
     */
    public void getCommentList(final boolean isRefresh){
        Map<String,String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("count",count+"");
        map.put("last_id",last_id+"");
        HttpUtils.getInstance().get(Constants.COMMENT_LIST, map, new HttpCallback<ReplyBean>() {
            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isRefresh) {

                }else{
                    mRefreshLayoutFragmentCommunityComment.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isRefresh) {

                }else{
                    mRefreshLayoutFragmentCommunityComment.finishLoadMore();
                }
            }

            @Override
            public void onSuccessExecute(ReplyBean replyBean) {
                if (replyBean.getStatus()!=0) {
                    return;
                }
                List<ReplyBean.DataBean> data = replyBean.getData();
                if (data!=null && data.size()>0) {
                    ReplyBean.DataBean dataBean = data.get(data.size() - 1);
                    last_id=dataBean.getId();
                    if (isRefresh) {
                        mDataBeans=data;
                    }else{
                        mDataBeans.addAll(data);
                    }
                    mCommunityCommentAdapter.setData(mDataBeans);

                }
            }
        });


    }

    /**
     * 新增评论
     */
    @Override
    public void addComment(final String text) {
        super.addComment(text);

        Map<String,String> map=new HashMap<>();
        map.put("id",id+"");
        map.put("content",text);
        HttpUtils.getInstance().post(Constants.CREATE_COMMENT, map, new HttpCallback<CreateCommentBean>() {
            @Override
            public void onSuccessExecute(CreateCommentBean createCommentBean) {
                if (createCommentBean.getStatus()==0) {
                    //生成的评论id
                    int data = createCommentBean.getData();
                    String user_name = SaveUtil.getString("user_name", "");
                    ReplyBean.DataBean dataBean = new ReplyBean.DataBean();
                    dataBean.setNickname(user_name);
                    dataBean.setContent(text);
                    dataBean.setId(data);
                    dataBean.setReplies(new ArrayList<ReplyEntity>());
                    mDataBeans.add(0,dataBean);
                    mCommunityCommentAdapter.setData(mDataBeans);
                    mListFragmentCommunityComment.smoothScrollToPosition(0);
                    //发送事件，更新动态列表中评论的数量
                    FindCommunityFragment.CommentCountBean commentCountBean = new FindCommunityFragment.CommentCountBean();
                    commentCountBean.setUpdate_comment_count(1);
                    EventBus.getDefault().post(commentCountBean);
                }
            }
        });

    }

    @Override
    public void initData() {

        Bundle arguments = getArguments();
        id=arguments.getInt("data");

        mCommunityCommentAdapter = new CommunityCommentAdapter(mActivity);
        mListFragmentCommunityComment.setLayoutManager(new LinearLayoutManager(mActivity));
        mListFragmentCommunityComment.setAdapter(mCommunityCommentAdapter);
        mCommunityCommentAdapter.setOnItemclickListener(new CommunityCommentAdapter.OnItemclickListener() {
            @Override
            public void onItemclick(final int pos) {
                DeleteCommentDialog deleteCommentDialog = new DeleteCommentDialog(mActivity);
                deleteCommentDialog.setDeleteCommentCallback(new DeleteCommentDialog.DeleteCommentCallback() {
                    @Override
                    public void deleteComment() {
                        delComment(pos,mCommunityCommentAdapter);

                    }
                });
                deleteCommentDialog.show();
            }
        });
        getCommentList(true);
    }

    /**
     * 删除评论
     * @param pos
     * @param communityCommentAdapter
     */
    private void delComment(final int pos, CommunityCommentAdapter communityCommentAdapter) {
        ReplyBean.DataBean dataBean = mDataBeans.get(pos);
        int id = dataBean.getId();
        Map<String,String> map=new HashMap<>();
        map.put("id",id+"");
        HttpUtils.getInstance().post(Constants.DELETE_COMMENT, map, new HttpCallback<CommonBean>() {
            @Override
            public void onSuccessExecute(CommonBean commonBean) {
                if (commonBean.getStatus()==0) {
                    mDataBeans.remove(pos);
                    mCommunityCommentAdapter.setData(mDataBeans);
                    //发送事件，更新动态列表中评论的数量
                    FindCommunityFragment.CommentCountBean commentCountBean = new FindCommunityFragment.CommentCountBean();
                    commentCountBean.setUpdate_comment_count(-1);
                    EventBus.getDefault().post(commentCountBean);
                }
            }
        });
    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_community_comment;
    }

}
