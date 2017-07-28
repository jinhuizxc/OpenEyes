package com.example.jh.openeyes.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.base.ToolbarActivity;
import com.example.jh.openeyes.ui.fragment.SettingsFragment;

/**
 * 作者：jinhui on 2017/7/28
 * 邮箱：1004260403@qq.com
 */

public class SettingActivity extends ToolbarActivity {

    @Override
    public int providerLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        toolbar.setNavigationOnClickListener(v -> finish());

        getFragmentManager().beginTransaction()
                .replace(R.id.container, new SettingsFragment())
                .commit();

    }

}
