package com.example.shichengxinag.monitorsystem.ui.tables;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
 * Created by shichengxinag on 2017/8/17.
 */

public class SelectStaffActivity extends BaseActivity {


    @BindView(R.id.recyclerview)
    RecyclerView mRecyclerViewStaffs;
    StaffsAdapter mStaffsAdapter;

    private String names_selectd;

    @Override
    public int getLayout() {
        return R.layout.activity_selectstaff;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mStaffsAdapter=new StaffsAdapter();
        mRecyclerViewStaffs.setLayoutManager(new LinearLayoutManager(this.getApplicationContext()));
        mRecyclerViewStaffs.setAdapter(mStaffsAdapter);
        mStaffsAdapter.refresh(Arrays.asList("","","","","","","","","","","","","",""));

    }
    class StaffsAdapter extends RecyclerView.Adapter<StaffsAdapter.ViewHolder>{

        public List<String> mData=new ArrayList<>();
        public void refresh(List<String> list){
            if(list==null)
                mData.clear();
            else
                mData=list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_stafflist,null);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (position==0){
                holder.cols[0].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[1].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[2].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[3].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[4].setTextColor(getResources().getColor(R.color.blue));
                holder.cols[0].setText("部门");
                holder.cols[1].setText("所属部门");
                holder.cols[2].setText("职能");
                holder.cols[3].setText("联系方式");
                holder.cols[4].setText("状态");
                holder.mTable_line.setEnabled(false);
            }else {
                holder.cols[0].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[1].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[2].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[3].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[4].setTextColor(getResources().getColor(R.color.txt_normal));
                holder.cols[0].setText("张"+position);
                holder.cols[1].setText("荆门"+position);
                holder.cols[2].setText("维护"+position);
                holder.cols[3].setText("1328911009"+position);
                holder.cols[4].setText(position/2==0?"无聊中":"忙碌中");
                holder.mTable_line.setEnabled(true);
            }
            holder.mTable_line.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemSelected();
                }
            });

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{
            @BindView(R.id.table_line)
            View mTable_line;
            @BindViews({R.id.t_col1,R.id.t_col2,R.id.t_col3,R.id.t_col4,R.id.t_col5})
            TextView[] cols;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
    public void onItemSelected(){

    }

    @OnClick({R.id.iv_back,R.id.table_line})
    public void onClickEvent(View view){
        switch (view.getId()){
            case R.id.iv_back:
                finish();
                break;
            case R.id.click_selected:
                finish();
                Intent intent=new Intent();
//                intent.putExtra("")
                setResult(101,intent);
                break;
        }
    }
}
