package com.example.jh.openeyes.binder.daily;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jh.openeyes.R;
import com.example.jh.openeyes.model.Category;

import me.drakeet.multitype.ItemViewBinder;

/**
 * 作者：jinhui on 2017/7/27
 * 邮箱：1004260403@qq.com
 */

public class CategoryViewBinder extends ItemViewBinder<Category, CategoryViewBinder.DateHolder> {

    @NonNull
    @Override
    protected DateHolder onCreateViewHolder(@NonNull LayoutInflater inflater,
                                            @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_category_title, parent, false);
        return new DateHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull DateHolder holder, @NonNull Category category) {
        holder.category.setText(category.categoryTitle);
    }

    class DateHolder extends RecyclerView.ViewHolder {
        TextView category;

        public DateHolder(View itemView) {
            super(itemView);
            category = (TextView) itemView.findViewById(R.id.date);
        }
    }
}
