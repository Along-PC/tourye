package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tourye.zhong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/10/9.
 * <p>
 * introduce:社区详情---点赞列表适配器
 */

public class CommunityThumbAdapter extends RecyclerView.Adapter<CommunityThumbAdapter.CommunityThumbHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mStrings=new ArrayList<>();

    public CommunityThumbAdapter(Context context, List<String> strings) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
        mStrings = strings;
    }

    @Override
    public CommunityThumbHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommunityThumbHolder(mLayoutInflater.inflate(R.layout.item_community_detail_thumb,parent,false));
    }

    @Override
    public void onBindViewHolder(CommunityThumbHolder holder, int position) {
        holder.mTvItemCommunityDetailThumb.setText(mStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public class CommunityThumbHolder extends RecyclerView.ViewHolder{
        private ImageView mImgItemCommunityDetailThumb;
        private TextView mTvItemCommunityDetailThumb;

        public CommunityThumbHolder(View itemView) {
            super(itemView);
            mImgItemCommunityDetailThumb = (ImageView) itemView.findViewById(R.id.img_item_community_detail_thumb);
            mTvItemCommunityDetailThumb = (TextView) itemView.findViewById(R.id.tv_item_community_detail_thumb);
        }
    }
}
