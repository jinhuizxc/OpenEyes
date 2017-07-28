package com.example.jh.openeyes;

import android.content.Intent;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;

import com.example.jh.openeyes.api.DailyApi;
import com.example.jh.openeyes.base.ToolbarActivity;
import com.example.jh.openeyes.model.Daily;
import com.example.jh.openeyes.register.Register;
import com.example.jh.openeyes.retrofit.RetrofitFactory;
import com.example.jh.openeyes.rx.RxScroller;
import com.example.jh.openeyes.ui.activity.FindInterestingActivity;
import com.example.jh.openeyes.ui.activity.SettingActivity;
import com.example.jh.openeyes.ui.activity.VideoAuthorActivity;
import com.jakewharton.rxbinding2.support.v4.widget.RxSwipeRefreshLayout;
import com.jakewharton.rxbinding2.support.v7.widget.RxRecyclerView;

import io.reactivex.Flowable;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 2017/7/27 尝试写一个开眼视频的demo
 * 从零开始，认真做app
 */
public class MainActivity extends ToolbarActivity {

    public static final String PROVIDER_ITEM = "item";
    public static final String CATEGORY_ID = "categoryId";
    public static final String TITLE = "title";

    private MultiTypeAdapter adapter;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout refreshLayout;
    private DrawerLayout drawerLayout;
    private DailyApi dailyApi;
    private Items items = new Items();
    private String dateTime = "";

    @Override
    public int providerLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
        refreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);

        // 创建菜单图标，默认是箭头
//        actionBar.setHomeAsUpIndicator(R.drawable.ic_menu_black_24dp);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // 监听方式
        // 1、DrawerLayout.DrawerListener
        // 2、DrawerLayout.SimpleDrawerListener
        drawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
                Window window = getWindow();

                // change the color to any color with transparency
                window.setStatusBarColor(ContextCompat.getColor(
                        MainActivity.this, android.R.color.transparent));
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getWindow().setStatusBarColor(ContextCompat.getColor(
                        MainActivity.this, R.color.colorPrimaryDark));
            }

            @Override
            public void onDrawerStateChanged(int newState) {
                super.onDrawerStateChanged(newState);
            }
        });

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        // 设置侧滑的方法
        setupDrawerContent(navigationView);

        dailyApi = RetrofitFactory.getRetrofit().createApi(DailyApi.class);
        setupRecyclerView();

        RxSwipeRefreshLayout.refreshes(refreshLayout)
                .compose(bindToLifecycle())
                .subscribe(aVoid -> loadData(true));

    }

    // 这是activity的什么方法啊？
    @Override
    public void onPostCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onPostCreate(savedInstanceState, persistentState);
        loadData(true);
    }

    private void loadData() {
        loadData(false /*Load more data. */);
    }

    private void loadData(boolean clear) {
        Flowable<Daily> result;
        if(clear)result = dailyApi.getDaily();
        else result = dailyApi.getDaily(Long.decode(dateTime));

    }

    // 配置RecyclerView
    private void setupRecyclerView() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        adapter = new MultiTypeAdapter(items);

        Register.registerItem(adapter, this);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

        RxRecyclerView.scrollStateChanges(recyclerView)
                .compose(bindToLifecycle())
                .filter(integer -> !refreshLayout.isRefreshing())
                .compose(RxScroller.scrollTransformer(layoutManager,
                        adapter, items))
                .subscribe(newState -> {
                    if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                        loadData();
                    }
                });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    getWindow().setStatusBarColor(ContextCompat.getColor(
                            MainActivity.this, android.R.color.transparent));
                } else {
                    getWindow().setStatusBarColor(ContextCompat.getColor(
                            MainActivity.this, R.color.colorPrimaryDark));
                }
            }
        });
    }



    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(menuItem -> {
            menuItem.setChecked(false);
            drawerLayout.closeDrawers();
            findInteresting(menuItem);
            return true;
        });

    }

    private void findInteresting(MenuItem item) {
        int id;
        String title;
        switch (item.getItemId()) {
            case R.id.nav_cute_pet:
                id = 26;
                title = getResources().getString(R.string.cute_pet);
                break;
            case R.id.nav_funny:
                id = 28;
                title = getResources().getString(R.string.funny);
                break;
            case R.id.nav_game:
                id = 30;
                title = getResources().getString(R.string.game);
                break;
            case R.id.nav_science:
                id = 32;
                title = getResources().getString(R.string.science);
                break;
            case R.id.nav_highlights:
                id = 34;
                title = getResources().getString(R.string.highlights);
                break;
            case R.id.nav_life:
                id = 36;
                title = getResources().getString(R.string.life);
                break;
            case R.id.nav_variety:
                id = 38;
                title = getResources().getString(R.string.variety);
                break;
            case R.id.nav_eating:
                id = 4;
                title = getResources().getString(R.string.eating);
                break;
            case R.id.nav_foreshow:
                id = 8;
                title = getResources().getString(R.string.foreshow);
                break;
            case R.id.nav_ad:
                id = 14;
                title = getResources().getString(R.string.advertisement);
                break;
            case R.id.nav_record:
                id = 22;
                title = getResources().getString(R.string.record);
                break;
            case R.id.nav_fashion:
                id = 24;
                title = getResources().getString(R.string.fashion);
                break;
            case R.id.nav_creative:
                id = 2;
                title = getResources().getString(R.string.creative);
                break;
            case R.id.nav_sports:
                id = 18;
                title = getResources().getString(R.string.sports);
                break;
            case R.id.nav_journey:
                id = 6;
                title = getResources().getString(R.string.journey);
                break;
            case R.id.nav_story:
                id = 12;
                title = getResources().getString(R.string.story);
                break;
            case R.id.nav_cartoon:
                id = 10;
                title = getResources().getString(R.string.cartoon);
                break;
            case R.id.nav_music:
                id = 20;
                title = getResources().getString(R.string.music);
                break;
            case R.id.nav_author:
                startActivity(new Intent(this, VideoAuthorActivity.class));
                return;
            case R.id.settings:
                startActivity(new Intent(this, SettingActivity.class));
                return;
            default:
                return;
        }
        Intent intent = new Intent(this, FindInterestingActivity.class);
        intent.putExtra(CATEGORY_ID, id);
        intent.putExtra(TITLE, title);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // 打开侧滑！
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        } else if (item.getItemId() == R.id.search_action) {
            toSearch(this);
        } else if (item.getItemId() == R.id.action_settings) {
            startActivity(new Intent(this, SettingActivity.class));
        }
        return super.onOptionsItemSelected(item);
    }
}
