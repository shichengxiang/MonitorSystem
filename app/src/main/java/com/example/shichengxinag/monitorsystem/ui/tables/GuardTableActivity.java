package com.example.shichengxinag.monitorsystem.ui.tables;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.style.LineHeightSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/8/20/020.
 */

public class GuardTableActivity extends BaseActivity {

    @BindView(R.id.guardListView)
    RecyclerView mGuardRecyclerView;
    GuardListAdapter mGuardListAdapter;
    @Override
    public int getLayout() {
        return R.layout.activity_guardtable;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mGuardListAdapter=new GuardListAdapter();
        mGuardRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mGuardRecyclerView.setAdapter(mGuardListAdapter);
        mGuardListAdapter.refresh(Arrays.asList("","","","","","","",""));
    }
    @OnClick({R.id.back})
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.back:
                finish();
                break;
        }
    }




    class GuardListAdapter extends RecyclerView.Adapter<GuardListAdapter.ViewHolder>{
        private List<String> mData=new ArrayList<>();

        public void refresh(List<String> list){
            if(list==null)
                mData.clear();
            else
                mData=list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View item= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_table_guardlist,null);
            return new ViewHolder(item);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if(position==0){
                holder.cols[0].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[1].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[2].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[3].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[4].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[5].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[0].setText("序号");
                holder.cols[1].setText("测站编号");
                holder.cols[2].setText("名称");
                holder.cols[3].setText("地址");
                holder.cols[4].setText("状态");
                holder.cols[5].setText("button");
            }else {
                holder.cols[0].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[1].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[2].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[3].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[4].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[5].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[0].setText((position-1)+"");
                holder.cols[1].setText("chuyu63343941"+position);
                holder.cols[2].setText("荆门");
                holder.cols[3].setText("荆门具体地址");
                holder.cols[4].setText("");
//                holder.cols[5].setText("");
            }
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{


            @BindViews({R.id.col1,R.id.col2,R.id.col3,R.id.col4,R.id.col5,R.id.col6})
            TextView[] cols;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }

    }

}
