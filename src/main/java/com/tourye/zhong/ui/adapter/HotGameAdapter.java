package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tourye.zhong.R;
import com.tourye.zhong.base.BaseApplication;
import com.tourye.zhong.beans.ActivityListBean;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:热门赛事适配器
 */

public class HotGameAdapter extends RecyclerView.Adapter<HotGameAdapter.HotGameHolder>{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<ActivityListBean.DataBean> mDataBeans=new ArrayList<>();

    public HotGameAdapter(Context context) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public void setDataBeans(List<ActivityListBean.DataBean> dataBeans) {
        mDataBeans = dataBeans;
    }

    @Override
    public HotGameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = mLayoutInflater.inflate(R.layout.item_frament_hot_game_list, parent, false);
        HotGameHolder hotGameHolder = new HotGameHolder(inflate);
        return hotGameHolder;
    }

    @Override
    public void onBindViewHolder(HotGameHolder holder, int position) {
        ActivityListBean.DataBean dataBean = mDataBeans.get(position);
        if (mContext!=null) {
            Glide.with(BaseApplication.mApplicationContext).load(dataBean.getImage()).into(holder.mImgItemFragmentHotgameList);
        }
        String crowd_finish_date = dataBean.getCrowd_finish_date();
        String order_finish_date = dataBean.getOrder_finish_date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date crowd_finish;
        Date order_finish;
        try {
            crowd_finish = simpleDateFormat.parse(crowd_finish_date);
            order_finish = simpleDateFormat.parse(order_finish_date);
            Calendar instance = Calendar.getInstance();
            Date current = instance.getTime();
            if (current.after(crowd_finish) && current.after(order_finish)) {
                holder.mTvItemHotgameListState.setText("已截止");
            }else{
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date start_date = simpleDateFormat.parse(dataBean.getStart_date());
                Date finish_date = simpleDateFormat.parse(dataBean.getFinish_date());
                String start_time = sdf.format(start_date);
                String finish_time = sdf.format(finish_date);
                holder.mTvItemHotgameListState.setText(start_time+"-"+finish_time);
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        holder.mTvItemHotgameListPrice.setText(dataBean.getPrice()+"元");
        holder.mTvItemHotgameListTitle.setText(dataBean.getName());
    }

    @Override
    public int getItemCount() {
        return mDataBeans.size();
    }

    public class HotGameHolder extends RecyclerView.ViewHolder{
        private ImageView mImgItemFragmentHotgameList;
        private TextView mTvItemHotgameListTitle;
        private TextView mTvItemHotgameListState;
        private TextView mTvItemHotgameListPrice;

        public HotGameHolder(View itemView) {
            super(itemView);
            mImgItemFragmentHotgameList = (ImageView) itemView.findViewById(R.id.img_item_fragment_hotgame_list);
            mTvItemHotgameListTitle = (TextView) itemView.findViewById(R.id.tv_item_hotgame_list_title);
            mTvItemHotgameListState = (TextView) itemView.findViewById(R.id.tv_item_hotgame_list_state);
            mTvItemHotgameListPrice = (TextView) itemView.findViewById(R.id.tv_item_hotgame_list_price);
        }
    }
}
