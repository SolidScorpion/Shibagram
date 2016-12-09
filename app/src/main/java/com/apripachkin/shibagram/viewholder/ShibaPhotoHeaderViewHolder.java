package com.apripachkin.shibagram.viewholder;

import android.text.format.DateUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.utils.ViewUtils;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by Antony on 03.08.2016.
 */
public class ShibaPhotoHeaderViewHolder extends FlexibleViewHolder {
    public ImageView mImageView;
    private TextView mUser;
    private TextView mTimePosted;

    public ShibaPhotoHeaderViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter, true);
        mImageView = (ImageView) view.findViewById(R.id.imgOptionBtn);
        mUser = (TextView) view.findViewById(R.id.tvHeaderName);
        mTimePosted = (TextView) view.findViewById(R.id.tvHeaderTime);
        ViewUtils.setPhotoTitleTypeFace(mUser);
        ViewUtils.setPhotoTitleTypeFace(mTimePosted);
    }

    public void setUser(String userName) {
        mUser.setText(userName);
    }

    public void setTime(long time) {
        CharSequence relativeTimeSpanString = DateUtils.getRelativeTimeSpanString(time, System.currentTimeMillis(), 0, DateUtils.FORMAT_ABBREV_RELATIVE);
        mTimePosted.setText(relativeTimeSpanString);
    }
}
