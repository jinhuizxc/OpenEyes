package com.example.jh.openeyes.binder.related;

import android.support.annotation.NonNull;

import com.example.jh.openeyes.model.Data;
import com.example.jh.openeyes.model.Header;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class HeaderItem {

    public Header header;
    public Data data;
    public boolean isShowArrowIcon;

    public HeaderItem(@NonNull Header header, boolean isShowArrowIcon) {
        this.header = header;
        this.isShowArrowIcon = isShowArrowIcon;
    }

    public HeaderItem(@NonNull Data data, Header header, boolean isShowArrowIcon) {
        this.data = data;
        this.header = header;
        this.isShowArrowIcon = isShowArrowIcon;
    }

}
