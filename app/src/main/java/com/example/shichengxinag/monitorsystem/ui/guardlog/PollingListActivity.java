package com.example.shichengxinag.monitorsystem.ui.guardlog;

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
import cn.refactor.lib.colordialog.ColorDialog;

/**
 * Created by shichengxinag on 2017/8/22.
 */

public class PollingListActivity extends BaseActivity {
    @BindView(R.id.recyclerviewPollingList)
    RecyclerView mRecyclerViewPollingList;
    PollingListAdapter mPollingListAdapter;

    @Override
    public int getLayout() {
        return R.layout.activity_pollinglist;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mPollingListAdapter=new PollingListAdapter();
        mRecyclerViewPollingList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewPollingList.setAdapter(mPollingListAdapter);
        mPollingListAdapter.refresh(Arrays.asList("","","","","","","","","","","","","","","","",""));
    }

    @OnClick({R.id.back})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }

    class PollingListAdapter extends RecyclerView.Adapter<PollingListAdapter.ViewHolder> {

        List<String> mData = new ArrayList<>();

        public void refresh(List<String> list) {
            if (list == null)
                mData.clear();
            else
                mData = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guardlist, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.cols[0].setText("zb456789"+position);
            holder.cols[1].setText("楚禹"+"");
            holder.cols[2].setText("张三"+"");
            holder.cols[3].setText("巡检中"+"");
//            holder.cols[4].setText(position+"");
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            @OnClick({R.id.fl_displayDetail})
            public void onClick(View view) {
                ColorDialog dialog = new ColorDialog(PollingListActivity.this);
                dialog.setTitle("维护进度");
                dialog.setContentText("已完成");
                dialog.setPositiveListener("确认", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
            }
            @BindViews({R.id.t_col1, R.id.t_col2, R.id.t_col3, R.id.t_col4})
            TextView[] cols;

            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
            }
        }
    }
}
