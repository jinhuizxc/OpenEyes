package com.example.jh.openeyes.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.api.SearchApi;
import com.example.jh.openeyes.base.ToolbarActivity;
import com.example.jh.openeyes.retrofit.RetrofitFactory;
import com.example.jh.openeyes.rx.ErrorAction;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class SearchActivity extends ToolbarActivity implements View.OnClickListener {

    public static final String KEYWORD = "keyword";

    // 第三方库FlowLayout-lib
    private TagFlowLayout tagLayout;
    private EditText searchEdit;

    private List<String> tags = new ArrayList<>();


    @Override
    public int providerLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        tagLayout = (TagFlowLayout) findViewById(R.id.tag_layout);
        searchEdit = (EditText) findViewById(R.id.search_edit);
        ImageButton clearButton = (ImageButton) findViewById(R.id.clear_btn);
        clearButton.setOnClickListener(this);

        // 加载下方标签
        loadTrendingTag();
        // 设置EditText的内容输入
        keyListener();

    }

    private void loadTrendingTag() {
        SearchApi trendingApi = RetrofitFactory.getRetrofit()
                .createApi(SearchApi.class);

        trendingApi.getTrendingTag()
                .compose(bindToLifecycle())
                .filter(data -> data != null)
                .doOnNext(data -> tags.addAll(data))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                    bindData();
                }, ErrorAction.error(this));
    }

    // 绑定标签数据显示
    private void bindData() {
        tagLayout.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String tagName) {
                TextView tag = (TextView) LayoutInflater.from(SearchActivity.this)
                        .inflate(R.layout.item_tag, parent, false);
                tag.setText(tagName);
                return tag;
            }
        });

        tagLayout.setOnTagClickListener((view, position, parent) -> {
            startResultActivity(tags.get(position));
            return true;
        });
    }

    private void keyListener() {
        searchEdit.setOnKeyListener((v, keyCode, event) -> {
            //onKey will call twice, First: ACTION_DOWN, Second:ACTION_UP
            if (event.getAction() != KeyEvent.ACTION_DOWN) return true;

            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                if (searchEdit.getText().toString().isEmpty()) {
                    Toasty.warning(SearchActivity.this, "Keyword must not empty!").show();
                } else {
                    search(searchEdit.getText().toString());
                }
            } else if (keyCode == KeyEvent.KEYCODE_BACK) {
                finish();
            }
            return false;
        });
    }

    private void search(String key) {
        startResultActivity(key);
    }

    private void startResultActivity(String keyword) {
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KEYWORD, keyword);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        searchEdit.setText(null);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
