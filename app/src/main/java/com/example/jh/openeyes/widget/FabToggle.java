package com.example.jh.openeyes.widget;

import android.content.Context;
import android.support.v7.widget.AppCompatImageButton;
import android.util.AttributeSet;
import android.widget.ImageButton;

import com.example.jh.openeyes.R;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class FabToggle extends AppCompatImageButton {

    private int minOffset;

    public FabToggle(Context context) {
        this(context, null);
    }

    public FabToggle(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FabToggle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        minOffset = context.getResources().getDimensionPixelSize(R.dimen.fab_min_offset);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        minOffset -= h / 2;
    }

    public void setOffset(int offset) {
        offset = Math.max(minOffset, offset);
        setTranslationY(offset);
    }
}
