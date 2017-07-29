package com.example.shichengxinag.monitorsystem.ui.guardlog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatSpinner;
import android.view.View;

import com.example.shichengxinag.monitorsystem.R;
import com.example.shichengxinag.monitorsystem.ui.BaseActivity;
import com.example.shichengxinag.monitorsystem.utils.ScreenShot;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import droidninja.filepicker.FilePickerBuilder;
import droidninja.filepicker.FilePickerConst;

/**
 * Created by shichengxinag on 2017/7/7.
 */

public class GuardListActivity extends BaseActivity {

    @BindView(R.id.spinnerLog)
    AppCompatSpinner mSpinner;

    @Override
    public int getLayout() {
        return R.layout.activity_guardlist;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
    }

    @OnClick({R.id.click_upload, R.id.click_toScreenShot, R.id.click_toCamera})
    public void onClickEvent(View view) {
        switch (view.getId()) {
            case R.id.click_upload:
                displayLoading();
                screenShot();
                break;
            case R.id.click_toScreenShot:
                screenShot();
                break;
            case R.id.click_toCamera:
                FilePickerBuilder.getInstance()
                        .setMaxCount(100)
                        .pickPhoto(GuardListActivity.this);
                break;
        }
    }

    private void screenShot() {
        ScreenShot shot = new ScreenShot(this);
        shot.takeScreenshot(getWindow().getDecorView(), new Runnable() {
            @Override
            public void run() {

            }
        }, true, true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == FilePickerConst.REQUEST_CODE_PHOTO) {
            List<String> photoPaths = new ArrayList<>();
            photoPaths.addAll(data.getStringArrayListExtra(FilePickerConst.KEY_SELECTED_MEDIA));
        }
    }
}
