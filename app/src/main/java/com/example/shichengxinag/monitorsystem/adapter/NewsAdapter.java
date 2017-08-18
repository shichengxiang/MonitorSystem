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
    List<String> mData=new ArrayList<>();
    public NewsAdapter(Context context){
        this.mContext=context;
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
        @BindView(R.id.alarmTag)
        View alarm;
        @BindView(R.id.click_toDispatch)
        View mView_toDispatch;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
