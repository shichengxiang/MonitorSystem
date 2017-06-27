package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.util.Log;
import android.view.View;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.presenter.AccountPresenter;
import com.example.shichengxinag.monitorsystem.view.AccountView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/19/019.
 */

public class LoginActivity extends BaseActivity implements AccountView {

    @BindView(R.id.nameWrapper)
    TextInputLayout ril_name;
    @BindView(R.id.pwdWrapper)
    TextInputLayout ril_pwd;

    AccountPresenter mPresenter;

    @Override
    public int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        mPresenter = new AccountPresenter(this);
    }

    @OnClick({R.id.toLogin,R.id.toMain})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.toLogin:
                startActivity(MenuActivity.class);
                break;
            case R.id.toMain:
                startActivity(Main2Activity.class);
                break;
        }
    }

    @Override
    public void onError(String msg) {

    }
}
