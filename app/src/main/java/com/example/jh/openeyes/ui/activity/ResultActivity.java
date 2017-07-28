package com.example.jh.openeyes.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.api.SearchApi;
import com.example.jh.openeyes.base.ToolbarActivity;
import com.example.jh.openeyes.model.ItemList;
import com.example.jh.openeyes.retrofit.RetrofitFactory;
import com.example.jh.openeyes.rx.ErrorAction;
import com.example.jh.openeyes.rx.RxScroller;
import com.example.jh.openeyes.ui.adapter.InterestingAdapter;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 作者：jinhui on 2017/7/28
 * 邮箱：1004260403@qq.com
 */

public class ResultActivity extends ToolbarActivity {

    private int start;
    private List<ItemList> itemLists = new ArrayList<>();
    private SearchApi searchApi;

    private InterestingAdapter adapter;


    @Override
    public int providerLayoutId() {
        return R.layout.activity_search_result;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        final String keyword = getIntent().getStringExtra(SearchActivity.KEYWORD);

        assert getSupportActionBar() != null;
        getSupportActionBar().setTitle(keyword);

        searchApi = RetrofitFactory.getRetrofit().createApi(SearchApi.class);

        adapter = new InterestingAdapter(this, itemLists);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        fetchResult(keyword);

        RxRecyclerView.scrollStateChanges(recyclerView)
                .compose(bindToLifecycle())
                .compose(RxScroller.scrollTransformer(layoutManager,
                        adapter, itemLists))
                .subscribe(newState -> {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        start += 10;
                        fetchResult(keyword);
                    }
                });
    }

    private void fetchResult(final String keyword) {
        searchApi.query(keyword, start)
                .compose(bindToLifecycle())
                .filter(searchResult -> searchResult != null)
                .map(searchResult -> searchResult.itemList)
                .doOnNext(itemList -> itemLists.addAll(itemList))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(itemLists -> {
                    adapter.notifyDataSetChanged();
                }, ErrorAction.error(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        } else if (item.getItemId() == R.id.search_action) {
            toSearch(this);
        }
        return super.onOptionsItemSelected(item);
    }
}
