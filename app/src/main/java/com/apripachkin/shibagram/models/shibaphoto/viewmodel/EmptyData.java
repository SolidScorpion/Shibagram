package com.apripachkin.shibagram.models.shibaphoto.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.viewholder.EmptyViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;

/**
 * Created by Antony on 10.10.2016.
 */

public class EmptyData extends AbstractFlexibleItem<EmptyViewHolder> {

    @Override
    public EmptyViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View inflate = inflater.inflate(getLayoutRes(), parent, false);
        return new EmptyViewHolder(inflate, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, EmptyViewHolder holder, int position, List payloads) {
        TextView textView = holder.textView;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.empty_layout;
    }


    @Override
    public boolean equals(Object o) {
        return false;
    }
}
