package com.example.jh.openeyes.widget;

import android.content.Context;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowInsets;

import com.example.jh.openeyes.R;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class InsetsDrawerLayout extends DrawerLayout implements View.OnApplyWindowInsetsListener {

    private Toolbar toolbar;
    private RecyclerView recyclerView;


    public InsetsDrawerLayout(Context context) {
        super(context);
    }

    public InsetsDrawerLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public InsetsDrawerLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        setOnApplyWindowInsetsListener(this);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        recyclerView = (RecyclerView) findViewById(R.id.recycleView);
    }

    @Override
    public WindowInsets onApplyWindowInsets(View v, WindowInsets insets) {
        int l = insets.getSystemWindowInsetLeft();
        int t = insets.getStableInsetTop();
        int r = insets.getStableInsetRight();

        toolbar.setPadding(l, toolbar.getPaddingTop() + t, 0, 0);
        final boolean ltr = recyclerView.getLayoutDirection() == View.LAYOUT_DIRECTION_LTR;
        setPadding(getPaddingLeft(), getPaddingTop(),
                getPaddingEnd() + (ltr ? r:0), getPaddingBottom());
        setOnApplyWindowInsetsListener(null);

        return insets.consumeSystemWindowInsets();
    }


}
