package com.example.jh.openeyes.binder.related;

import android.support.annotation.NonNull;

import com.example.jh.openeyes.model.Header;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class RelatedHeader {

    public Header header;
    public boolean related;

    public RelatedHeader(@NonNull Header header, boolean related) {
        this.header = header;
        this.related = related;
    }

}
