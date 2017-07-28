package com.example.jh.openeyes.register;

import android.app.Activity;

import com.example.jh.openeyes.binder.daily.CategoryViewBinder;
import com.example.jh.openeyes.binder.daily.DailyViewBinder;
import com.example.jh.openeyes.binder.related.Card;
import com.example.jh.openeyes.binder.related.CardViewBinder;
import com.example.jh.openeyes.binder.related.HeaderItem;
import com.example.jh.openeyes.binder.related.HeaderViewBinder;
import com.example.jh.openeyes.binder.related.RelatedHeader;
import com.example.jh.openeyes.binder.related.RelatedHeaderViewBinder;
import com.example.jh.openeyes.binder.video.FooterForward;
import com.example.jh.openeyes.binder.video.FooterForwardViewBinder;
import com.example.jh.openeyes.binder.video.VideoViewBinder;
import com.example.jh.openeyes.model.Category;
import com.example.jh.openeyes.model.ItemList;

import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class Register {

    public static void registerItem(MultiTypeAdapter adapter, Activity context) {
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(ItemList.class, new DailyViewBinder(context));
    }

    public static void registerRelatedItem(MultiTypeAdapter adapter, Activity context) {
        registerCommonItem(adapter, context);
    }

    public static void registerFindItem(MultiTypeAdapter adapter, Activity context) {
        adapter.register(FooterForward.class, new FooterForwardViewBinder());
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(ItemList.class, new VideoViewBinder(context));
        registerCommonItem(adapter, context);
    }

    public static void registerAuthorItem(MultiTypeAdapter adapter, Activity context) {
        adapter.register(Category.class, new CategoryViewBinder());
        adapter.register(ItemList.class, new VideoViewBinder(context));
        registerCommonItem(adapter, context);
    }

    private static void registerCommonItem(MultiTypeAdapter adapter, Activity context) {
        adapter.register(HeaderItem.class, new HeaderViewBinder());
        adapter.register(Card.class, new CardViewBinder(context));
        adapter.register(RelatedHeader.class, new RelatedHeaderViewBinder());
    }

}
