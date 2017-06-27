package com.example.shichengxinag.monitorsystem.fragment;

import android.os.Bundle;
import android.view.View;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.utils.U;

import butterknife.BindView;

/**
 * Created by Administrator on 2017/6/24/024.
 */

public class SearchFrament extends BaseFragment {
    @BindView(R.id.topView)
    View topView;
    @Override
    public int getLayout() {
        return R.layout.fragment_search;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        U.setTopView(getContext(),topView);
    }
}
