package com.example.jh.openeyes.ui.activity;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.base.ToolbarActivity;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class InterestingActivity extends ToolbarActivity {

    public static final String RELATED_VIDEO = "related";
    public static final String RELATED_HEADER_VIDEO = "related_header";

    private static int categoryId;
    private static boolean related;
    private static boolean relatedHeader;

    @Override
    public int providerLayoutId() {
        return R.layout.activity_interesting;
    }
}
