package com.tourye.zhong.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tourye.zhong.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:
 */

public class SameRoadAdapter extends RecyclerView.Adapter<SameRoadAdapter.SameRoadHolder>{

    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mStrings=new ArrayList<>();

    public SameRoadAdapter(Context context) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }

    @Override
    public SameRoadHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SameRoadHolder(mLayoutInflater.inflate(R.layout.item_fragment_same_game,parent,false));
    }

    @Override
    public void onBindViewHolder(SameRoadHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public class SameRoadHolder extends RecyclerView.ViewHolder{
        private TextView mTvItemFragmentSameGameCount;
        private TextView mTvItemFragmentSameGameTitle;
        private TextView mTvItemFragmentSameGameAction;
        private TextView mTvItemFragmentSameGameNum;


        public SameRoadHolder(View itemView) {
            super(itemView);
            mTvItemFragmentSameGameCount = (TextView) itemView.findViewById(R.id.tv_item_fragment_same_game_count);
            mTvItemFragmentSameGameTitle = (TextView) itemView.findViewById(R.id.tv_item_fragment_same_game_title);
            mTvItemFragmentSameGameAction = (TextView) itemView.findViewById(R.id.tv_item_fragment_same_game_action);
            mTvItemFragmentSameGameNum = (TextView) itemView.findViewById(R.id.tv_item_fragment_same_game_num);

        }
    }
}
