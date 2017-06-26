package com.example.shichengxinag.monitorsystem.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by shichengxinag on 2017/6/16.
 */

public abstract class BaseFragment extends Fragment {

    Unbinder binder;

    public abstract int getLayout();
    public abstract void init(Bundle savedInstanceState);
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root=inflater.inflate(getLayout(),container,false);
        binder = ButterKnife.bind(this, root);
        init(savedInstanceState);
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binder.unbind();
    }
}
