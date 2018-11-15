package com.tourye.zhong.ui.adapter;

import android.app.Activity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tourye.zhong.Constants;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.ChildReplyBean;
import com.tourye.zhong.beans.CommonBean;
import com.tourye.zhong.beans.CreateReplyBean;
import com.tourye.zhong.beans.ReplyBean;
import com.tourye.zhong.beans.ReplyEntity;
import com.tourye.zhong.net.HttpCallback;
import com.tourye.zhong.net.HttpUtils;
import com.tourye.zhong.utils.GlideCircleTransform;
import com.tourye.zhong.utils.SaveUtil;
import com.tourye.zhong.views.dialogs.CommentDialogInnerFragment;
import com.tourye.zhong.views.dialogs.DeleteCommentDialog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by longlongren on 2018/10/11.
 * <p>
 * introduce:社区详情评论适配器
 */

public class CommunityCommentAdapter extends RecyclerView.Adapter<CommunityCommentAdapter.CommunityCommentHolder> {
    private Activity mContext;
    private LayoutInflater mLayoutInflater;
    private List<ReplyBean.DataBean> mDataBeans = new ArrayList<>();
    private OnItemclickListener mOnItemclickListener;

    public CommunityCommentAdapter(Activity context) {
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    /**
     * 列表条目点击事件
     *
     * @param onItemclickListener
     */
    public void setOnItemclickListener(OnItemclickListener onItemclickListener) {
        mOnItemclickListener = onItemclickListener;
    }

    public void setData(List<ReplyBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
        notifyDataSetChanged();
    }

    @Override
    public CommunityCommentHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new CommunityCommentHolder(mLayoutInflater.inflate(R.layout.item_community_detail_comment, parent, false));
    }

    @Override
    public void onBindViewHolder(final CommunityCommentHolder holder, final int position) {
        final ReplyBean.DataBean dataBean = mDataBeans.get(position);

        //设置二级评论
        final List<ReplyEntity> replies = dataBean.getReplies();
        final CommentReplyAdapter commentReplyAdapter = new CommentReplyAdapter(mContext);
        holder.mListCommunityDetailComment.setLayoutManager(new LinearLayoutManager(mContext));
        holder.mListCommunityDetailComment.setAdapter(commentReplyAdapter);
        //二级评论条目点击事件--点击
        commentReplyAdapter.setOnItemClickListener(new CommentReplyAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(final int i) {
                addReply(i, replies, commentReplyAdapter);
            }
        });
        //二级评论条目点击事件--长按
        commentReplyAdapter.setOnItemLongClickListener(new CommentReplyAdapter.OnItemLongClickListener() {
            @Override
            public void onItemLongClick(final int position) {
                ReplyEntity replyEntity = replies.get(position);
                int user_id_temp = replyEntity.getUser_id();
                int user_id = SaveUtil.getInt("user_id", -998);
                if (user_id != user_id_temp) {
                    return;
                }
                DeleteCommentDialog deleteCommentDialog = new DeleteCommentDialog(mContext);
                deleteCommentDialog.setDeleteCommentCallback(new DeleteCommentDialog.DeleteCommentCallback() {
                    @Override
                    public void deleteComment() {
                        deleteReply(replies, position,commentReplyAdapter);

                    }
                });
                deleteCommentDialog.show();
            }
        });
        if (replies != null && replies.size() > 0) {
            for (int i = 0; i < replies.size(); i++) {
                replies.get(i).setComment_id(dataBean.getId());
            }
            commentReplyAdapter.setData(replies);
        }

        //加载更多二级评论
        if (dataBean.getReply_count() > 2) {
            final int remainder_count = dataBean.getReply_count() - replies.size();//剩余回复数
            dataBean.setRemainder_count(remainder_count);
            holder.mTvCommunityDetailCommentExpand.setVisibility(View.VISIBLE);
            holder.mTvCommunityDetailCommentExpand.setText("还有" + remainder_count + "条回复>");
        } else {
            holder.mTvCommunityDetailCommentExpand.setVisibility(View.GONE);
        }
        holder.mTvCommunityDetailCommentExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getMoreReply(dataBean, replies, commentReplyAdapter, holder);
            }
        });


        //加载头像
        RequestOptions requestOptions = new RequestOptions().transform(new GlideCircleTransform(BaseApplication.mApplicationContext));
        Glide.with(BaseApplication.mApplicationContext).load(dataBean.getAvatar()).apply(requestOptions).into(holder.mImgCommunityDetailCommentHead);
        //姓名
        holder.mTvCommunityDetailCommentName.setText(dataBean.getNickname());

        //点击回复
        holder.mImgCommunityDetailCommentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addReply(replies, commentReplyAdapter, dataBean);
            }

        });
        holder.mTvCommunityDetailCommentMessage.setText(dataBean.getContent());

        //设置条目点击事件
        holder.mLlItemCommunityDetailComment.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnItemclickListener.onItemclick(position);
                return true;
            }
        });

    }

    /**
     * 删除回复
     *
     * @param replies
     * @param position
     */
    private void deleteReply(final List<ReplyEntity> replies, final int position, final CommentReplyAdapter commentReplyAdapter) {
        ReplyEntity replyEntity = replies.get(position);
        Map<String, String> map = new HashMap<>();
        map.put("id", replyEntity.getId() + "");
        HttpUtils.getInstance().post(Constants.DELETE_REPLY, map, new HttpCallback<CommonBean>() {
            @Override
            public void onSuccessExecute(CommonBean commonBean) {
                if (commonBean.getStatus()==0) {
                    replies.remove(position);
                    commentReplyAdapter.notifyDataSetChanged();
                }
            }
        });

    }


    /**
     * 点击消息图标添加回复
     *
     * @param replies
     * @param commentReplyAdapter
     * @param dataBean
     */
    public void addReply(final List<ReplyEntity> replies, final CommentReplyAdapter commentReplyAdapter, final ReplyBean.DataBean dataBean) {
        CommentDialogInnerFragment commentDialogInnerFragment = new CommentDialogInnerFragment();
        commentDialogInnerFragment.setCommentCallback(new CommentDialogInnerFragment.CommentCallback() {
            @Override
            public void comment(final String text) {
                Map<String, String> map = new HashMap<>();
                map.put("id", dataBean.getId() + "");
                map.put("content", text);
                HttpUtils.getInstance().post(Constants.CREATE_REPLY, map, new HttpCallback<CreateReplyBean>() {
                    @Override
                    public void onSuccessExecute(CreateReplyBean createReplyBean) {
                        if (createReplyBean.getStatus() == 0) {
                            int id = createReplyBean.getData();
                            int user_id = SaveUtil.getInt("user_id", -998);
                            ReplyEntity reply = new ReplyEntity();
                            reply.setId(id);
                            reply.setComment_id(dataBean.getId());
                            reply.setUser_id(user_id);
                            reply.setContent(text);
                            reply.setNickname(SaveUtil.getString("user_name", ""));
                            replies.add(0, reply);
                            commentReplyAdapter.setData(replies);
                        }
                    }
                });
            }
        });
        commentDialogInnerFragment.show(mContext.getFragmentManager(), "CommunityCommentAdapter");
    }

    /**
     * 点击列表添加回复
     *
     * @param pos
     * @param replies
     * @param commentReplyAdapter
     */
    public void addReply(final int pos, final List<ReplyEntity> replies, final CommentReplyAdapter commentReplyAdapter) {
        CommentDialogInnerFragment commentDialogInnerFragment = new CommentDialogInnerFragment();
        commentDialogInnerFragment.setCommentCallback(new CommentDialogInnerFragment.CommentCallback() {
            @Override
            public void comment(final String text) {
                final ReplyEntity replyEntity = replies.get(pos);
                Map<String, String> map = new HashMap<>();
                map.put("id", replyEntity.getComment_id() + "");
                map.put("content", text);
                map.put("reply_to", replyEntity.getId() + "");
                HttpUtils.getInstance().post(Constants.CREATE_REPLY, map, new HttpCallback<CreateReplyBean>() {
                    @Override
                    public void onSuccessExecute(CreateReplyBean createReplyBean) {
                        if (createReplyBean.getStatus() == 0) {
                            int id = createReplyBean.getData();
                            int user_id = SaveUtil.getInt("user_id", -998);
                            ReplyEntity reply = new ReplyEntity();
                            reply.setId(id);
                            reply.setUser_id(user_id);
                            reply.setContent(text);
                            reply.setComment_id(replyEntity.getComment_id());
                            reply.setNickname(SaveUtil.getString("user_name", ""));
                            reply.setReply_to(replies.get(pos).getNickname());
                            replies.add(0, reply);
                            commentReplyAdapter.setData(replies);
                        }
                    }
                });
            }
        });
        commentDialogInnerFragment.show(mContext.getFragmentManager(), "CommunityCommentAdapter");

    }

    /**
     * 获取更多回复
     *
     * @param dataBean            当前评论数据
     * @param replies             当前回复数据
     * @param commentReplyAdapter 回复列表适配器
     */
    private void getMoreReply(final ReplyBean.DataBean dataBean, final List<ReplyEntity> replies, final CommentReplyAdapter commentReplyAdapter, final CommunityCommentHolder holder) {

        ReplyEntity repliesBean = replies.get(replies.size() - 1);
        Map<String, String> map = new HashMap<>();
        map.put("id", dataBean.getId() + "");
        map.put("count", 10 + "");
        map.put("last_id", repliesBean.getId() + "");

        HttpUtils.getInstance().get(Constants.REPLY_LIST, map, new HttpCallback<ChildReplyBean>() {
            @Override
            public void onSuccessExecute(ChildReplyBean childReplyBean) {
                if (childReplyBean.getStatus() != 0) {
                    return;
                }
                List<ReplyEntity> data = childReplyBean.getData();
                if (data != null && data.size() > 0) {
//                    commentReplyAdapter.addData(data);
                    if (data.size() < 10) {
                        holder.mTvCommunityDetailCommentExpand.setVisibility(View.GONE);
                    } else {
                        int remainder_count = dataBean.getRemainder_count();
                        remainder_count = remainder_count - data.size();
                        dataBean.setRemainder_count(remainder_count);
                        holder.mTvCommunityDetailCommentExpand.setText("还有" + remainder_count + "条回复>");
                    }
                    replies.addAll(data);
                    commentReplyAdapter.setData(replies);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    /**
     * 条目点击事件
     */
    public interface OnItemclickListener {
        public void onItemclick(int pos);
    }

    public class CommunityCommentHolder extends RecyclerView.ViewHolder {

        private ImageView mImgCommunityDetailCommentHead;
        private TextView mTvCommunityDetailCommentName;
        private TextView mTvCommunityDetailCommentTime;
        private ImageView mImgCommunityDetailCommentSubmit;
        private TextView mTvCommunityDetailCommentMessage;
        private RecyclerView mListCommunityDetailComment;
        private TextView mTvCommunityDetailCommentExpand;
        private LinearLayout mLlItemCommunityDetailComment;
        private LinearLayout mLlItemCommunityDetailReply;


        public CommunityCommentHolder(View itemView) {
            super(itemView);
            mLlItemCommunityDetailComment = (LinearLayout) itemView.findViewById(R.id.ll_item_community_detail_comment);
            mImgCommunityDetailCommentHead = (ImageView) itemView.findViewById(R.id.img_community_detail_comment_head);
            mTvCommunityDetailCommentName = (TextView) itemView.findViewById(R.id.tv_community_detail_comment_name);
            mTvCommunityDetailCommentTime = (TextView) itemView.findViewById(R.id.tv_community_detail_comment_time);
            mImgCommunityDetailCommentSubmit = (ImageView) itemView.findViewById(R.id.img_community_detail_comment_submit);
            mTvCommunityDetailCommentMessage = (TextView) itemView.findViewById(R.id.tv_community_detail_comment_message);
            mListCommunityDetailComment = (RecyclerView) itemView.findViewById(R.id.list_community_detail_comment);
            mTvCommunityDetailCommentExpand = (TextView) itemView.findViewById(R.id.tv_community_detail_comment_expand);
            mLlItemCommunityDetailReply = (LinearLayout) itemView.findViewById(R.id.ll_item_community_detail_reply);

        }

    }
}
