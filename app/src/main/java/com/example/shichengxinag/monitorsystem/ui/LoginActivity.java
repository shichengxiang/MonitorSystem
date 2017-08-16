package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.jpush.TagAliasOperatorHelper;
import com.example.shichengxinag.monitorsystem.presenter.AccountPresenter;
import com.example.shichengxinag.monitorsystem.view.AccountView;

import java.util.LinkedHashSet;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;

import static com.example.shichengxinag.monitorsystem.jpush.TagAliasOperatorHelper.ACTION_SET;
import static com.example.shichengxinag.monitorsystem.jpush.TagAliasOperatorHelper.sequence;

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
//                setTag();
                startActivity(MapActivity.class);
                break;
            case R.id.toMain:
                startActivity(Main2Activity.class);
                break;
        }
    }

    @Override
    public void onError(String msg) {

    }
    private void setTag(){
        Set<String> tags = null;
        String alias = null;
        int action = -1;
        boolean isAliasAction = false;
        Set<String> set=new LinkedHashSet<>();
        set.add("tom");
        tags = set;
        action = ACTION_SET;
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = action;
        sequence++;
        if(isAliasAction){
            tagAliasBean.alias = alias;
        }else{
            tagAliasBean.tags = tags;
        }
        tagAliasBean.isAliasAction = isAliasAction;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(),sequence,tagAliasBean);
//        JPushInterface.setTags(this, 1001, new HashSet<String>().add("tom"));
    }
}
