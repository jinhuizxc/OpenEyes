package com.example.jh.openeyes.ui.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.example.jh.openeyes.api.ReplyApi;
import com.example.jh.openeyes.model.ItemList;
import com.example.jh.openeyes.model.ReplyList;
import com.example.jh.openeyes.ui.adapter.ReplyAdapter;
import com.example.jh.openeyes.widget.FabToggle;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class MovieDetailActivity extends RxAppCompatActivity {

    private ReplyApi replyApi;
    private List<ReplyList> datas = new ArrayList<>();
    private ItemList item;

    private FabToggle play;
    private ReplyAdapter adapter;
    private View movieDescription;

    private int fabOffset;
    private int lastId;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }
}
