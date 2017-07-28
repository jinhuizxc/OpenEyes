package com.example.jh.openeyes.binder.video;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.jh.openeyes.IntentManager;
import com.example.jh.openeyes.R;
import com.example.jh.openeyes.common.Holder;
import com.example.jh.openeyes.model.ItemList;
import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class VideoViewBinder extends ItemViewBinder<ItemList, Holder> {

    private Activity context;

    public VideoViewBinder(Activity context) {
        this.context = context;
    }

    @NonNull
    @Override
    protected Holder onCreateViewHolder(
            @NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_movie, parent, false);
        return new Holder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull Holder holder, @NonNull ItemList item) {
        holder.movieAlbum.setOriginalSize(16, 9);
        Glide.with(holder.movieAlbum.getContext())
                .load(item.data.cover.detail)
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(holder.movieAlbum);
        holder.movieDesc.setText(item.data.title);

        if (item.data.author != null) {
            holder.tag.setVisibility(View.VISIBLE);
            holder.tag.setText(item.data.author.name);
        } else {
            holder.tag.setVisibility(View.GONE);
        }

        RxView.clicks(holder.movieContent).throttleFirst(1000, TimeUnit.MILLISECONDS)
                .subscribe(aVoid -> {
                    fly(holder.movieAlbum, item);
                });
    }

    private void fly(View view, ItemList item) {
        IntentManager.flyToMovieDetail(context, item, view);
    }
}
