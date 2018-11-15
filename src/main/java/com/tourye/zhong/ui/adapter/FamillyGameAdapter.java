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
 * Created by longlongren on 2018/9/21.
 * <p>
 * introduce:亲子赛事适配器
 */

public class FamillyGameAdapter extends RecyclerView.Adapter<FamillyGameAdapter.FamillyGameHolder>{
    private Context mContext;
    private LayoutInflater mLayoutInflater;
    private List<String> mStrings=new ArrayList<>();

    public FamillyGameAdapter(Context context) {
        mContext = context;
        mLayoutInflater=LayoutInflater.from(mContext);
    }

    public void setStrings(List<String> strings) {
        mStrings = strings;
    }

    @Override
    public FamillyGameHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FamillyGameHolder(mLayoutInflater.inflate(R.layout.item_fragment_familly_game,parent,false));
    }

    @Override
    public void onBindViewHolder(FamillyGameHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return mStrings.size();
    }

    public class FamillyGameHolder extends RecyclerView.ViewHolder{

        private ImageView mImgItemFragmentFamillygameList;
        private TextView mTvItemFamillygameListTitle;
        private TextView mTvItemFamillygameListTime;
        private TextView mTvItemFamillygameListPrice;

        public FamillyGameHolder(View itemView) {
            super(itemView);
            mImgItemFragmentFamillygameList = (ImageView) itemView.findViewById(R.id.img_item_fragment_famillygame_list);
            mTvItemFamillygameListTitle = (TextView) itemView.findViewById(R.id.tv_item_famillygame_list_title);
            mTvItemFamillygameListTime = (TextView) itemView.findViewById(R.id.tv_item_famillygame_list_time);
            mTvItemFamillygameListPrice = (TextView) itemView.findViewById(R.id.tv_item_famillygame_list_price);

        }
    }
}
