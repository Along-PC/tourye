package com.tourye.zhong.ui.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseFragment;
import com.tourye.zhong.beans.ArticleListBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.ui.activity.CreateArticleActivity;
import com.tourye.zhong.ui.adapter.FindArticleAdapter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/25.
 * <p>
 * introduce:发现--文章
 */

public class FindArticleFragment extends BaseFragment {
    private SmartRefreshLayout mRefreshLayoutFragmentFindArticle;
    private ListView mListFragmentFindArticle;
    private TextView mTvFragmentFindArticleCreate;
    private FindArticleAdapter mFindArticleAdapter;

    private int count=10;//一次请求多少数据
    private int offset=0;//从第几条数据开始请求

    @Override
    public void initView(View view) {
        mRefreshLayoutFragmentFindArticle = (SmartRefreshLayout) view.findViewById(R.id.refreshLayout_fragment_find_article);
        mListFragmentFindArticle = (ListView) view.findViewById(R.id.list_fragment_find_article);
        mTvFragmentFindArticleCreate = (TextView) view.findViewById(R.id.tv_fragment_find_article_create);
        mTvFragmentFindArticleCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(mActivity, CreateArticleActivity.class));
            }
        });
    }

    /**
     * 获取文章列表数据
     * @param isRefresh  是否刷新数据
     * @param isFirst  是否第一次执行
     */
    public void getArticleData(final boolean isRefresh, final boolean isFirst){
        Map<String,String> map=new HashMap<>();
        map.put("count",count+"");
        map.put("offset",offset+"");
        HttpUtils.getInstance().get(Constants.FIND_ARTICLE_LIST, map, new HttpCallback<ArticleListBean>() {

            @Override
            public void onFailureExecute() {
                super.onFailureExecute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindArticle.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindArticle.finishLoadMore();
                }
            }

            @Override
            public void onPreExcute() {
                super.onPreExcute();
                if (isFirst) {
                    return;
                }
                if (isRefresh) {
                    mRefreshLayoutFragmentFindArticle.finishRefresh();
                }else{
                    mRefreshLayoutFragmentFindArticle.finishLoadMore();
                }
            }

            @Override
            public void onSuccessExecute(ArticleListBean articleListBean) {
                if (articleListBean.getStatus()==0) {
                    List<ArticleListBean.DataBean> data = articleListBean.getData();

                    if (data!=null && data.size()>0) {
                        if (isFirst) {
                            mFindArticleAdapter = new FindArticleAdapter(mActivity, data);
                            mListFragmentFindArticle.setAdapter(mFindArticleAdapter);
                        }else{
                            if (isRefresh) {
                                mFindArticleAdapter.setDataBeans(data);
                            }else{
                                mFindArticleAdapter.addDataBeans(data);
                            }
                        }
                    }
                }
            }
        });
    }

    @Override
    public void initData() {
        getArticleData(true,true);

        mRefreshLayoutFragmentFindArticle.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshLayout) {
                offset=0;
                getArticleData(true,false);
            }
        });
        mRefreshLayoutFragmentFindArticle.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                offset+=count;
                getArticleData(false,false);
            }
        });

    }

    @Override
    public boolean isNeedTitle() {
        return false;
    }

    @Override
    public int getRootView() {
        return R.layout.fragment_find_article;
    }
}
