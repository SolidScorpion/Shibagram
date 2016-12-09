package com.apripachkin.shibagram.models.shibaphoto.viewmodel;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.viewholder.ShibaPhotoHeaderViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractHeaderItem;

/**
 * Created by Antony on 03.08.2016.
 */
public class ShibaPhotoHeader extends AbstractHeaderItem<ShibaPhotoHeaderViewHolder> {
    private String id;
    private String title;
    private long timePosted;
    private View.OnClickListener imgBtnOnClickListener;

    public ShibaPhotoHeader(String id, String title, long timePosted) {
        this.id = id;
        this.title = title;
        this.timePosted = timePosted;
    }

    public View.OnClickListener getImgBtnOnClickListener() {
        return imgBtnOnClickListener;
    }

    public void setImgBtnOnClickListener(View.OnClickListener imgBtnOnClickListener) {
        this.imgBtnOnClickListener = imgBtnOnClickListener;
    }

    public long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(long timePosted) {
        this.timePosted = timePosted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ShibaPhotoHeader) {
            return ((ShibaPhotoHeader) o).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.header_layout;
    }

    @Override
    public ShibaPhotoHeaderViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(getLayoutRes(), parent, false);
        return new ShibaPhotoHeaderViewHolder(view, adapter);
    }

    @Override
    public void bindViewHolder(FlexibleAdapter adapter, ShibaPhotoHeaderViewHolder holder, int position, List payloads) {
        holder.setTime(getTimePosted());
        holder.setUser(getTitle());
        holder.mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgBtnOnClickListener.onClick(v);
            }
        });
    }

}