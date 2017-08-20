package com.example.shichengxinag.monitorsystem.adapter;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.tables.SelectStaffActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by shichengxinag on 2017/7/11.
 */

public class NewsAdapter extends BaseAdapter {
    private Context mContext;
    public static final int NEWSTYPE_GENERAL=101;
    public static final int NEWSTYPE_FAULT=102;
    public static final int NEWSTYPE_TASK=103;
    private int NEWTYPE=NEWSTYPE_GENERAL;
    List<String> mData=new ArrayList<>();
    public NewsAdapter(Context context,int tag){
        this.mContext=context;
        this.NEWTYPE=tag;
    }

    private int index1=1;//0001
    private int index2=6;//1000
    public void getStatus(int s){
        if(s==index1){

        }else if(s==index2){

        }else if(s==(index1|index2)){

        }
    }

    /**刷新
     * @param list
     */
    public void refresh(List<String> list){
        if(list!=null)
            mData=list;
        if (list==null)
            mData.clear();
        notifyDataSetChanged();
    }

    /**
     * 加载更多
     * @param list
     */
    public void loadMore(List<String> list){
        if(list!=null)
            mData.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return mData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView= LayoutInflater.from(mContext).inflate(R.layout.item_newsdetail,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        if(NEWTYPE==NEWSTYPE_GENERAL || NEWTYPE== NEWSTYPE_FAULT){
            holder.rl_buttons.setVisibility(View.GONE);
        }else if(NEWTYPE==NEWSTYPE_TASK){
            holder.rl_buttons.setVisibility(View.VISIBLE);
        }



        holder.mView_toDispatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mContext.startActivity(new Intent(mContext, SelectStaffActivity.class));
            }
        });
        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.alarm, "alpha", 0.1f, 1);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setDuration(200);
        animator.start();
        return convertView;
    }
    class ViewHolder{
        @BindView(R.id.rl_buttons)
        View rl_buttons;
        @BindView(R.id.alarmTag)
        View alarm;
        @BindView(R.id.click_toDispatch)
        View mView_toDispatch;
        @BindView(R.id.click_toAccept)
        View mView_toAccept;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
