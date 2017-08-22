package com.example.shichengxinag.monitorsystem.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CalendarView;
import android.widget.TextView;

import com.example.shichengxinag.monitorsystem.R;

import butterknife.BindView;
import butterknife.OnClick;
import cn.refactor.lib.colordialog.PromptDialog;

/**
 * Created by shichengxiang on 2017/8/22.
 */

public class LogFragment extends BaseFragment {
    private static Fragment mFragment;

    @BindView(R.id.tv_date)
    TextView mTv_date;

    public static Fragment newInstance() {
        if (mFragment == null)
            synchronized (LogFragment.class) {
                mFragment = new LogFragment();
            }
        return mFragment;
    }

    @Override
    public int getLayout() {
        return R.layout.fragment_log;
    }

    @Override
    public void init(Bundle savedInstanceState) {
    }

    @OnClick({R.id.tv_date})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.tv_date:
                displayCalendar();
                break;
        }
    }


    private void displayCalendar() {
        final AlertDialog dialog = new AlertDialog.Builder(getContext()).create();
        View contentView = LayoutInflater.from(getContext().getApplicationContext()).inflate(R.layout.dialog_calendar, null);
        dialog.setView(contentView);
        CalendarView calendarView = (CalendarView) contentView.findViewById(R.id.calendarView);
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mTv_date.setText(year + "-" + month + "-" + dayOfMonth);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
