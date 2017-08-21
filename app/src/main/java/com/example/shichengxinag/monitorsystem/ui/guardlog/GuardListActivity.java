package com.example.shichengxinag.monitorsystem.ui.guardlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.widget.AppCompatSpinner;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;
import com.example.shichengxinag.monitorsystem.utils.ScreenShot;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.refactor.lib.colordialog.ColorDialog;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

/**
 * Created by shichengxinag on 2017/7/7.
 */

public class GuardListActivity extends BaseActivity {


    @BindView(R.id.recyclerviewGuardLog)
    RecyclerView mRecyclerViewGuardList;
    GuardListAdapter mAdapter;


    @Override
    public int getLayout() {
        return R.layout.activity_guardlist;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mAdapter=new GuardListAdapter();
        mRecyclerViewGuardList.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerViewGuardList.setAdapter(mAdapter);
        mAdapter.refresh(Arrays.asList("","","","","","","","","","",""));
    }

    @OnClick({R.id.back})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.back:
                finish();
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    }


    class GuardListAdapter extends RecyclerView.Adapter<GuardListAdapter.ViewHolder>{

        List<String> mData=new ArrayList<>();
        public void refresh(List<String> list){
            if(list==null)
                mData.clear();
            else
                mData=list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view= LayoutInflater.from(getApplicationContext()).inflate(R.layout.item_guardlist,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

        }

        @Override
        public int getItemCount() {
            return mData.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            @OnClick({R.id.fl_displayDetail})
            public void onClick(View view){
                ColorDialog dialog = new ColorDialog(GuardListActivity.this);
                dialog.setTitle("维护进度");
                dialog.setContentText("已完成");
                dialog.setPositiveListener("确认", new ColorDialog.OnPositiveListener() {
                    @Override
                    public void onClick(ColorDialog dialog) {
                        dialog.dismiss();
                    }
                }).show();
            }
            @BindView(R.id.fl_displayDetail)
            View mV_displayDetail;
            @BindViews({R.id.t_col1,R.id.t_col2,R.id.t_col3,R.id.t_col4})
            TextView[] cols;
            public ViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this,itemView);
            }
        }
    }
}
