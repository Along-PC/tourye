package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.DynamicListBean;
import com.tourye.zhong.beans.ThumbUpBean;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.utils.DateFormatUtils;
import com.tourye.zhong.utils.GlideCircleTransform;
import com.tourye.zhong.views.MeasureGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/9/27.
 * <p>
 * introduce:发现--社区适配器
 */

public class FindCommunityAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<DynamicListBean.DataBean> mDatas = new ArrayList<>();

    public FindCommunityAdapter(Context context, List<DynamicListBean.DataBean> datas) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
        mDatas = datas;
    }

    public void setDatas(List<DynamicListBean.DataBean> datas) {
        mDatas = datas;
        notifyDataSetChanged();
    }

    public void addDatas(List<DynamicListBean.DataBean> datas) {
        mDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        FindCommunityHolder findCommunityHolder;
        if (view == null) {
            view = mLayoutInflater.inflate(R.layout.item_fragment_find_community, viewGroup, false);
            findCommunityHolder = new FindCommunityHolder(view);
            view.setTag(findCommunityHolder);
        } else {
            findCommunityHolder = (FindCommunityHolder) view.getTag();
        }
        //获取数据，开始赋值
        final DynamicListBean.DataBean dataBean = mDatas.get(i);
        //头像
        RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleTransform(BaseApplication.mApplicationContext));
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getAvatar()).apply(requestOptions).into(findCommunityHolder.mImgItemFindCommunityHead);
        findCommunityHolder.mTvItemFindCommunityName.setText(dataBean.getNickname());
        String content = dataBean.getContent();
        //文字内容
        if (TextUtils.isEmpty(content)) {
            findCommunityHolder.mTvItemFindCommunityContent.setVisibility(View.GONE);
        }else{
            findCommunityHolder.mTvItemFindCommunityContent.setVisibility(View.VISIBLE);
            findCommunityHolder.mTvItemFindCommunityContent.setText(dataBean.getContent());
        }
        //发表时间
        String create_time = dataBean.getCreate_time();
        if (TextUtils.isEmpty(create_time)) {
            findCommunityHolder.mTvItemFindCommunityTime.setText("");
        }else{
            findCommunityHolder.mTvItemFindCommunityTime.setText(DateFormatUtils.format(create_time));
        }
        //图片数据
        List<String> urls =dataBean.getImages();
        if (urls != null) {
            int size = urls.size();
            switch (size) {
                case 1:
                    findCommunityHolder.mGridItemFindCommunity.setNumColumns(1);
                    break;
                case 2:
                case 3:
                case 4:
                    findCommunityHolder.mGridItemFindCommunity.setNumColumns(2);
                    break;
                default:
                    findCommunityHolder.mGridItemFindCommunity.setNumColumns(3);
                    break;

            }
            FindCommunityChildAdapter findCommunityChildAdapter = new FindCommunityChildAdapter(mContext, dataBean.getImages());
            findCommunityHolder.mGridItemFindCommunity.setAdapter(findCommunityChildAdapter);
        }
        //点击条目跳转详情
//        view.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(mContext, CommunityDetailActivity.class);
//                intent.putExtra("data",dataBean);
//                mContext.startActivity(intent);
//            }
//        });
        //点赞数
        findCommunityHolder.mTvItemFindCommunityThumb.setText(dataBean.getThumb_up_count()+"");
        //点赞状态
        findCommunityHolder.mImgItemFindCommunityThumb.setSelected(dataBean.isAlready_thumb_up());
        final FindCommunityHolder finalFindCommunityHolder = findCommunityHolder;
        findCommunityHolder.mRlItemFindCommunityThumb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //当发出网络请求，在没有拿到网络请求结果时，取消监听
                finalFindCommunityHolder.mRlItemFindCommunityThumb.setOnClickListener(null);
                if (finalFindCommunityHolder.mImgItemFindCommunityThumb.isSelected()) {
                    cancel_thumb_up(dataBean,finalFindCommunityHolder);
                }else{
                    thumb_up(dataBean,finalFindCommunityHolder);
                }
            }
        });

        return view;
    }



    //取消点赞
    public void cancel_thumb_up(final DynamicListBean.DataBean dataBean, final FindCommunityHolder holder){
        Map<String,String> map=new HashMap<>();
        map.put("id",dataBean.getId()+"");
        HttpUtils.getInstance().post(Constants.THUMB_UP_CANCEL, map, new HttpCallback<ThumbUpBean>() {
            @Override
            public void onSuccessExecute(ThumbUpBean thumbUpBean) {
                if (thumbUpBean.getStatus()==0) {
                    if (thumbUpBean.isData()) {
                        holder.mImgItemFindCommunityThumb.setSelected(false);
                        dataBean.setThumb_up_count(dataBean.getThumb_up_count()-1);
                        dataBean.setAlready_thumb_up(false);
                        //拿到网络请求结果，重新添加监听
                        holder.mTvItemFindCommunityThumb.setText(dataBean.getThumb_up_count()+"");
                        holder.mImgItemFindCommunityThumb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                holder.mImgItemFindCommunityThumb.setOnClickListener(null);
                                if (holder.mImgItemFindCommunityThumb.isSelected()) {
                                    cancel_thumb_up(dataBean,holder);
                                }else{
                                    thumb_up(dataBean,holder);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    //点赞
    public void thumb_up(final DynamicListBean.DataBean dataBean, final FindCommunityHolder holder){
        Map<String,String> map=new HashMap<>();
        map.put("id",dataBean.getId()+"");
        HttpUtils.getInstance().post(Constants.THUMB_UP, map, new HttpCallback<ThumbUpBean>() {
            @Override
            public void onSuccessExecute(ThumbUpBean thumbUpBean) {
                if (thumbUpBean.getStatus()==0) {
                    if (thumbUpBean.isData()) {
                        holder.mImgItemFindCommunityThumb.setSelected(true);
                        dataBean.setAlready_thumb_up(true);
                        int thumb_up_count = dataBean.getThumb_up_count();
                        dataBean.setThumb_up_count(thumb_up_count+1);
                        //拿到网络请求结果，重新添加监听
                        holder.mTvItemFindCommunityThumb.setText(dataBean.getThumb_up_count()+"");
                        holder.mImgItemFindCommunityThumb.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                holder.mImgItemFindCommunityThumb.setOnClickListener(null);
                                if (holder.mImgItemFindCommunityThumb.isSelected()) {
                                    cancel_thumb_up(dataBean,holder);
                                }else{
                                    thumb_up(dataBean,holder);
                                }
                            }
                        });
                    }
                }
            }
        });
    }

    public class FindCommunityHolder {
        private ImageView mImgItemFindCommunityHead;
        private TextView mTvItemFindCommunityName;
        private TextView mTvItemFindCommunityTime;
        private MeasureGridView mGridItemFindCommunity;
        private RelativeLayout mRlItemFindCommunityShare;
        private TextView mTvItemFindCommunityShare;
        private RelativeLayout mRlItemFindCommunityComment;
        private TextView mTvItemFindCommunityComment;
        private RelativeLayout mRlItemFindCommunityThumb;
        public TextView mTvItemFindCommunityThumb;
        private TextView mTvItemFindCommunityContent;
        private ImageView mImgItemFindCommunityThumb;

        public FindCommunityHolder(View view) {
            mImgItemFindCommunityHead = (ImageView) view.findViewById(R.id.img_item_find_community_head);
            mTvItemFindCommunityName = (TextView) view.findViewById(R.id.tv_item_find_community_name);
            mTvItemFindCommunityTime = (TextView) view.findViewById(R.id.tv_item_find_community_time);
            mGridItemFindCommunity = (MeasureGridView) view.findViewById(R.id.grid_item_find_community);
            mRlItemFindCommunityShare = (RelativeLayout) view.findViewById(R.id.rl_item_find_community_share);
            mTvItemFindCommunityShare = (TextView) view.findViewById(R.id.tv_item_find_community_share);
            mRlItemFindCommunityComment = (RelativeLayout) view.findViewById(R.id.rl_item_find_community_comment);
            mTvItemFindCommunityComment = (TextView) view.findViewById(R.id.tv_item_find_community_comment);
            mRlItemFindCommunityThumb = (RelativeLayout) view.findViewById(R.id.rl_item_find_community_thumb);
            mTvItemFindCommunityThumb = (TextView) view.findViewById(R.id.tv_item_find_community_thumb);
            mTvItemFindCommunityContent = (TextView) view.findViewById(R.id.tv_item_find_community_content);
            mImgItemFindCommunityThumb = (ImageView) view.findViewById(R.id.img_item_find_community_thumb);
        }
    }
}
