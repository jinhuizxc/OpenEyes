package com.example.jh.openeyes.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.ui.activity.SearchActivity;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public abstract class ToolbarActivity extends RxAppCompatActivity {

    public Toolbar toolbar;
    public ActionBar actionBar;

    public abstract int providerLayoutId();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(providerLayoutId());
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        actionBar = getSupportActionBar();
        if(actionBar != null){
            // 创建返回箭头 置为true显示箭头，false不显示箭头
            // ---来源于 compile 'com.android.support:appcompat-v7:25.3.1'
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public void toSearch(Activity activity){
        Intent intent = new Intent(activity, SearchActivity.class);
        startActivity(intent);
    }
}
