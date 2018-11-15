package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tourye.zhong.R;
import com.tourye.zhong.beans.ReplyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/10/11.
 * <p>
 * introduce:社区详情评论回复列表适配器
 */

public class CommentReplyAdapter extends RecyclerView.Adapter<CommentReplyAdapter.CommentReplyHolder> {
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReplyEntity> mRepliesBeans=new ArrayList<>();

    private OnItemClickListener mOnItemClickListener;
    private OnItemLongClickListener mOnItemLongClickListener;

    public CommentReplyAdapter(Context context) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public void setData(List<ReplyEntity> repliesBeans) {
        mRepliesBeans=repliesBeans;
        notifyDataSetChanged();
    }

    public void addData(List<ReplyEntity> repliesBeans) {
        mRepliesBeans.addAll(repliesBeans);
        notifyItemInserted(repliesBeans.size());
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        mOnItemLongClickListener = onItemLongClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    @Override
    public CommentReplyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommentReplyHolder(mLayoutInflater.inflate(R.layout.item_community_comment,parent,false));
    }

    @Override
    public void onBindViewHolder(CommentReplyHolder holder, final int position) {
        ReplyEntity replyEntity = mRepliesBeans.get(position);
        String content = replyEntity.getContent();
        String nickname = replyEntity.getNickname();
        String reply_to = replyEntity.getReply_to();
        String temp;
        if (TextUtils.isEmpty(reply_to)) {
            temp="<font color='#CC1C24'>"+nickname+": "+"</font>"+content;
        }else{
            temp="<font color='#CC1C24'>"+nickname+"</font>"+" 回复 "+"<font color='#CC1C24'>"+reply_to+": "+"</font>"+content;
        }
        holder.mTvItemFindCommunityComment.setText(Html.fromHtml(temp));
        holder.mTvItemFindCommunityComment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemLongClickListener.onItemLongClick(position);

                return true;
            }
        });
        holder.mTvItemFindCommunityComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mRepliesBeans.size();
    }

    /**
     * 条目点击事件
     */
    public interface OnItemClickListener{
        public void onItemClick(int position);
    }

    public interface OnItemLongClickListener{
        public void onItemLongClick(int position);
    }

    public class CommentReplyHolder extends RecyclerView.ViewHolder{
        private TextView mTvItemFindCommunityComment;

        public CommentReplyHolder(View itemView) {
            super(itemView);
            mTvItemFindCommunityComment = (TextView) itemView.findViewById(R.id.tv_item_find_community_comment);
        }
    }
}
