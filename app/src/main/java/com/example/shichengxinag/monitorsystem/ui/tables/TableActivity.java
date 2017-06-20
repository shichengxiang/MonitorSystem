package com.example.shichengxinag.monitorsystem.ui.tables;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/20/020.
 */

public class TableActivity extends BaseActivity {
    @BindView(R.id.listView)
    ListView mListView;
    @Override
    public int getLayout() {
        return R.layout.activity_table;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initTableData();
    }
    void initTableData(){
        mListView.setAdapter(new TableAdapter());
    }
    class TableAdapter extends BaseAdapter{

        @Override
        public int getCount() {
            return 20;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view= LayoutInflater.from(TableActivity.this).inflate(R.layout.layout_table_content8,null);
            if(position==0){
                view.setBackgroundColor(ContextCompat.getColor(TableActivity.this,R.color.table_title_bg));
            }else {
                view.setBackgroundColor(Color.parseColor("#ffffff"));
            }
            return view;
        }
    }
}
