package com.example.shichengxinag.monitorsystem.ui.tables;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

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

    void initTableData() {
        mListView.setAdapter(new TableAdapter());
    }

    class TableAdapter extends BaseAdapter {

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
            ViewHolder holder = null;
            if (convertView == null) {
                convertView = LayoutInflater.from(TableActivity.this).inflate(R.layout.layout_table_content8, null);
                holder = new ViewHolder(convertView);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
//            if (position == 0) {
//                convertView.setBackgroundColor(ContextCompat.getColor(TableActivity.this, R.color.table_title_bg));
//                holder.cols[0].setText("站名");
//                holder.cols[1].setText("测站编码");
//                holder.cols[2].setText("日期/时间");
//                holder.cols[3].setText("校前水位");
//                holder.cols[4].setText("校核水位");
//                holder.cols[5].setText("差值");
//                holder.cols[6].setText("备注");
//                holder.cols[7].setText("null");
//            } else {
//                convertView.setBackgroundColor(Color.parseColor("#ffffff"));
                holder.cols[0].setText("");
                holder.cols[1].setText("");
                holder.cols[2].setText("");
                holder.cols[3].setText("");
                holder.cols[4].setText("");
                holder.cols[5].setText("");
                holder.cols[6].setText("");
                holder.cols[7].setText("");
            return convertView;
        }

        class ViewHolder {
            @BindViews({R.id.col0, R.id.col1, R.id.col2, R.id.col3, R.id.col4, R.id.col5, R.id.col6, R.id.col7})
            TextView[] cols;

            public ViewHolder(View view) {
                ButterKnife.bind(this, view);
            }
        }
    }
}
