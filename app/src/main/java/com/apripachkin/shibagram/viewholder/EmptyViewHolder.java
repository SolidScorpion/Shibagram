package com.apripachkin.shibagram.viewholder;

import android.view.View;
import android.widget.TextView;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.utils.ViewUtils;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by Antony on 10.10.2016.
 */
public class EmptyViewHolder extends FlexibleViewHolder {
    public TextView textView;

    public EmptyViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        textView = (TextView) view.findViewById(R.id.tvEmptyView);
        ViewUtils.setEmptyTypeFace(textView);
    }
}
