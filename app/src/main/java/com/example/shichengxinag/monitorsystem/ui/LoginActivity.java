package com.example.shichengxinag.monitorsystem.ui;

import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.view.View;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.jpush.TagAliasOperatorHelper;
import com.example.shichengxinag.monitorsystem.presenter.AccountPresenter;
import com.example.shichengxinag.monitorsystem.view.AccountView;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import me.weyye.hipermission.HiPermission;
import me.weyye.hipermission.PermissionCallback;
import me.weyye.hipermission.PermissionItem;

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
        setTag();
        applyPermissions();
    }

    @OnClick({R.id.toLogin, R.id.toMain})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.toLogin:
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

    private void setTag() {
        Set<String> tags = null;
        String alias = null;
        int action = -1;
        boolean isAliasAction = false;
        Set<String> set = new LinkedHashSet<>();
        set.add("tom");
        tags = set;
        action = ACTION_SET;
        TagAliasOperatorHelper.TagAliasBean tagAliasBean = new TagAliasOperatorHelper.TagAliasBean();
        tagAliasBean.action = action;
        sequence++;
        if (isAliasAction) {
            tagAliasBean.alias = alias;
        } else {
            tagAliasBean.tags = tags;
        }
        tagAliasBean.isAliasAction = isAliasAction;
        TagAliasOperatorHelper.getInstance().handleAction(getApplicationContext(), sequence, tagAliasBean);
//        JPushInterface.setTags(this, 1001, new HashSet<String>().add("tom"));
    }

    public void applyPermissions() {
        List<PermissionItem> permissionItems = new ArrayList<PermissionItem>();
        permissionItems.add(new PermissionItem(android.Manifest.permission.CAMERA, "照相", R.drawable.ic_camera));
        permissionItems.add(new PermissionItem(android.Manifest.permission.READ_EXTERNAL_STORAGE, "位置", R.drawable.ic_file));
        HiPermission.create(this)
                .permissions(permissionItems)
                .checkMutiPermission(new PermissionCallback() {
                    @Override
                    public void onClose() {
                    }

                    @Override
                    public void onFinish() {
                    }

                    @Override
                    public void onDeny(String permission, int position) {
                        finish();
                    }

                    @Override
                    public void onGuarantee(String permission, int position) {
                    }
                });
    }
}
