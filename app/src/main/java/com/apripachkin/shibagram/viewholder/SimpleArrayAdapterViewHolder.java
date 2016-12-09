package com.apripachkin.shibagram.viewholder;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.apripachkin.shibagram.utils.ViewUtils;

/**
 * Created by Antony on 01.08.2016.
 */
public class SimpleArrayAdapterViewHolder extends RecyclerView.ViewHolder {
    public TextView mArrayItemTv;

    public SimpleArrayAdapterViewHolder(View itemView) {
        super(itemView);
        mArrayItemTv = (TextView) itemView.findViewById(android.R.id.text1);
        mArrayItemTv.setTextColor(ContextCompat.getColor(itemView.getContext(), android.R.color.black));
        ViewUtils.setTitleTypeFace(mArrayItemTv);
    }

    public void setText(String text) {
        mArrayItemTv.setText(text);
    }
}
