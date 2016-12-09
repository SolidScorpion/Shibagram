package com.apripachkin.shibagram.models.shibaphoto.viewmodel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.apripachkin.shibagram.R;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by Antony on 09.08.2016.
 */
public class SimpleProgressItem extends AbstractFlexibleItem<SimpleProgressItem.ProgressViewHolder> {
    @Override
    public boolean equals(Object o) {
        return this == o;//The default implementation
    }

    @Override
    public int getLayoutRes() {
        return R.layout.progress_item;
    }

    @Override
    public ProgressViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        return new ProgressViewHolder(inflater.inflate(getLayoutRes(), parent, false), adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ProgressViewHolder holder, int position, List payloads) {
        //nothing to bind
    }

    public static class ProgressViewHolder extends FlexibleViewHolder {

        public ProgressBar progressBar;

        public ProgressViewHolder(View view, FlexibleAdapter adapter) {
            super(view, adapter);
            progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        }
    }
}
