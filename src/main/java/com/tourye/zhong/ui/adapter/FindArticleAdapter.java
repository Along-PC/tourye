package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.ArticleListBean;
import com.tourye.zhong.ui.activity.ArticleDetailActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/26.
 * <p>
 * introduce:发现---文章适配器
 */

public class FindArticleAdapter extends BaseAdapter{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ArticleListBean.DataBean> mDataBeans=new ArrayList<>();

    public FindArticleAdapter(Context context, List<ArticleListBean.DataBean> dataBeans) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mDataBeans = dataBeans;
    }

    //下拉刷新
    public void setDataBeans(List<ArticleListBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    //加载更多
    public void addDataBeans(List<ArticleListBean.DataBean> dataBeans) {
        mDataBeans.addAll(dataBeans);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDataBeans.size();
    }

    @Override
    public Object getItem(int i) {
        return mDataBeans.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FindArticleHolder findArticleHolder=null;
        if (view==null) {
            view=mLayoutInflater.inflate(R.layout.item_fragment_find_article,viewGroup,false);
            findArticleHolder=new FindArticleHolder(view);
            view.setTag(findArticleHolder);
        }else{
            findArticleHolder= (FindArticleHolder) view.getTag();
        }

        final ArticleListBean.DataBean dataBean = mDataBeans.get(i);
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.holder).error(R.drawable.holder);
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getCover()).apply(requestOptions).into(findArticleHolder.mImgFindArticle);
        findArticleHolder.mTvFindArticleName.setText(dataBean.getAuthor());
        findArticleHolder.mTvFindArticleTitle.setText(dataBean.getTitle());
        findArticleHolder.mRlFindArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, ArticleDetailActivity.class);
                intent.putExtra("data",dataBean.getUrl());
                mContext.startActivity(intent);
            }
        });
        return view;
    }

    public class FindArticleHolder{
        private ImageView mImgFindArticle;
        private TextView mTvFindArticleTitle;
        private TextView mTvFindArticleName;
        private RelativeLayout mRlFindArticle;
        
        public FindArticleHolder(View view){
            mImgFindArticle = (ImageView) view.findViewById(R.id.img_find_article);
            mTvFindArticleTitle = (TextView) view.findViewById(R.id.tv_find_article_title);
            mTvFindArticleName = (TextView) view.findViewById(R.id.tv_find_article_name);
            mRlFindArticle = (RelativeLayout) view.findViewById(R.id.rl_find_article);

        }
    }
}
