package com.tourye.zhong.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.DynamicListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.activity.CommunityDetailActivity;
import com.tourye.zhong.ui.adapter.FindCommunityAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:发现--社区
 */

public class FindCommunityFragment extends BaseFragment {
    private ListView mListFragmentFindCommunity;
    private FindCommunityAdapter mFindCommunityAdapter;
    private SmartRefreshLayout mRefreshLayoutFragmentFindCommunity;


    private int last_id=0;//上一次请求的最后一条数据的id
    private int count=10;//一次请求多少数据
    private int mPosition=998;//从哪个条目进入详情的
    private List<DynamicListBean.DataBean> mDataBeans=new ArrayList<>();//列表数据

    @Override
    public void initView(View view) {
        mListFragmentFindCommunity = (ListView) view.findViewById(R.id.list_fragment_find_community);
        mRefreshLayoutFragmentFindCommunity = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout_fragment_find_community);

        //注册event-bus
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    public void onDestroy() {
        //取消event-bus监听
        EventBus.getDefault().unregister(this);
        super.onDestroy();

    }

    //更新点赞状态
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(Msg msg){
        DynamicListBean.DataBean dataBean = mDataBeans.get(mPosition);
        dataBean.setAlready_thumb_up(msg.isIs_already_thumb());
        int count=msg.is_already_thumb==true?1:-1;
        dataBean.setThumb_up_count(dataBean.getThumb_up_count()+count);
        mFindCommunityAdapter.notifyDataSetChanged();
    }

    //动态更新动态列表
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void updateDynamicList(UpdateDynamicBean updateDynamicBean){
        if (updateDynamicBean.isUpdate_list()) {
            last_id=0;
            getDynamicList(true,false);
        }
    }

    //更新评论数量
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onUpdateCommentCount(CommentCountBean commentCountBean){
        DynamicListBean.DataBean dataBean = mDataBeans.get(mPosition);
        dataBean.setComment_count(dataBean.getComment_count()+commentCountBean.getUpdate_comment_count());
    }

    @Override
    public void initData() {
        getDynamicList(true,true);

        mRefreshLayoutFragmentFindCommunity.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                last_id=0;
                getDynamicList(true,false);
            }
        });
        mRefreshLayoutFragmentFindCommunity.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                getDynamicList(false,false);
            }
        });

        mListFragmentFindCommunity.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                mPosition = i;
                DynamicListBean.DataBean dataBean = mDataBeans.get(i);
                Intent intent = new Intent(mContext, CommunityDetailActivity.class);
                intent.putExtra("data",dataBean);
                mContext.startActivity(intent);
            }
        });
    }


    /**
     * 获取动态列表的数据
     * @param isRefresh
     * @param isFirst
     */
    public void getDynamicList(final boolean isRefresh, final boolean isFirst){
        Map<String,String> map=new HashMap<>();
        map.put("last_id",last_id+"");
        map.put("count",count+"");
        HttpUtils.getInstance().get(Constants.DYNAMIC_LIST, map, new HttpCallback<DynamicListBean>() {
            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindCommunity.finishRefresh();
                } else {
                    mRefreshLayoutFragmentFindCommunity.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindCommunity.finishRefresh();
                } else {
                    mRefreshLayoutFragmentFindCommunity.finishLoadMore();
                }
            }

            @Override
            public void onSuccessExecute(DynamicListBean dynamicListBean) {
                if (dynamicListBean.getStatus()==0) {
                    List<DynamicListBean.DataBean> data = dynamicListBean.getData();
                    if (data!=null && data.size()>0) {
                        if (isFirst) {
                            mDataBeans=data;
                            mFindCommunityAdapter = new FindCommunityAdapter(mActivity, mDataBeans);
                            mListFragmentFindCommunity.setAdapter(mFindCommunityAdapter);
                        }else{
                            if (isRefresh) {
                                mDataBeans=data;
                                mFindCommunityAdapter.setDatas(mDataBeans);
                            }else{
                                mDataBeans.addAll(data);
                                mFindCommunityAdapter.setDatas(mDataBeans);
                            }
                        }
                        DynamicListBean.DataBean dataBean = data.get(data.size() - 1);
                        last_id=dataBean.getId();
                    }
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
        return R.layout.fragment_find_community;
    }

    /**
     * 更新点赞状态实体
     */
    public static class Msg {
        private boolean is_already_thumb;
        private int update_comment_count;

        public boolean isIs_already_thumb() {
            return is_already_thumb;
        }

        public void setIs_already_thumb(boolean is_already_thumb) {
            this.is_already_thumb = is_already_thumb;
        }

        public int getUpdate_comment_count() {
            return update_comment_count;
        }

        public void setUpdate_comment_count(int update_comment_count) {
            this.update_comment_count = update_comment_count;
        }
    }

    /**
     * 更新动态实体---删除或者新增动态后刷新列表
     */
    public static class UpdateDynamicBean {
        private boolean update_list;

        public boolean isUpdate_list() {
            return update_list;
        }

        public void setUpdate_list(boolean update_list) {
            this.update_list = update_list;
        }
    }

    /**
     * 更新评论数量实体
     */
    public static class CommentCountBean {
        private int update_comment_count;

        public int getUpdate_comment_count() {
            return update_comment_count;
        }

        public void setUpdate_comment_count(int update_comment_count) {
            this.update_comment_count = update_comment_count;
        }
    }
}
