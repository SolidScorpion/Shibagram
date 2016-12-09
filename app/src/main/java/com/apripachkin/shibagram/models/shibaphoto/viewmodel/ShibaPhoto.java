package com.apripachkin.shibagram.models.shibaphoto.viewmodel;


import android.content.Context;
import android.support.graphics.drawable.VectorDrawableCompat;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.viewholder.ShibaPhotoViewHolder;
import com.bumptech.glide.Glide;

import java.util.Date;
import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.AbstractSectionableItem;
import eu.davidea.flipview.FlipView;

/**
 * Created by apripachkin on 12/28/15.
 */
public class ShibaPhoto extends AbstractSectionableItem<ShibaPhotoViewHolder, ShibaPhotoHeader> implements Comparable<ShibaPhoto> {
    private String imageUrl;
    private String shareImgUrl;
    private String authorUsername;
    private String description;
    private String serviceType;
    private String authorUrl;
    private String id;
    private long timePosted;
    private boolean isLiked = false;
    private boolean likedFromDoubleTap = false;
    private ShibaPhotoInteractionListener mListener;
    private VectorDrawableCompat stubImg;
    private VectorDrawableCompat errorImg;

    public ShibaPhoto(ShibaPhotoHeader header, String imageUrl, String shareImgUrl, String authorUsername, String description, long timePosted, String serviceType, String authorUrl, String id, boolean isLiked) {
        super(header);
        this.id = id;
        this.authorUrl = authorUrl;
        this.shareImgUrl = shareImgUrl;
        this.isLiked = isLiked;
        this.serviceType = serviceType;
        this.imageUrl = imageUrl;
        this.authorUsername = authorUsername;
        this.description = description;
        this.timePosted = timePosted;
    }

    public String getShareImgUrl() {
        return shareImgUrl;
    }

    public void setShareImgUrl(String shareImgUrl) {
        this.shareImgUrl = shareImgUrl;
    }

    public boolean isLikedFromDoubleTap() {
        return likedFromDoubleTap;
    }

    public void setLikedFromDoubleTap(boolean likedFromDoubleTap) {
        this.likedFromDoubleTap = likedFromDoubleTap;
    }

    public String getAuthorUrl() {
        return authorUrl;
    }

    public void setAuthorUrl(String authorUrl) {
        this.authorUrl = authorUrl;
    }

    public void setOnLikedListener(ShibaPhotoInteractionListener listener) {
        mListener = listener;
    }

    public boolean isLiked() {
        return isLiked;
    }

    public void setLiked(boolean liked) {
        isLiked = liked;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public long getTimePosted() {
        return timePosted;
    }

    public void setTimePosted(long timePosted) {
        this.timePosted = timePosted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getAuthorUsername() {
        return authorUsername;
    }

    public void setAuthorUsername(String authorUsername) {
        this.authorUsername = authorUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public int compareTo(ShibaPhoto another) {
        Date firstDate = new Date(timePosted);
        Date secondDate = new Date(another.getTimePosted());
        return firstDate.compareTo(secondDate);
    }

    @Override
    public String toString() {
        return "ShibaPhoto{" +
                ", imageUrl='" + imageUrl + '\'' +
                ", authorUsername='" + authorUsername + '\'' +
                ", description='" + description + '\'' +
                ", timePosted=" + timePosted +
                '}';
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + serviceType.hashCode();
        result = 31 * result + (int) (timePosted ^ (timePosted >>> 32));
        result = 31 * result + (isLiked ? 1 : 0);
        result = 31 * result + (likedFromDoubleTap ? 1 : 0);
        return result;
    }

    @Override
    public int getLayoutRes() {
        return R.layout.shibaphoto_item;
    }

    @Override
    public ShibaPhotoViewHolder createViewHolder(FlexibleAdapter adapter, LayoutInflater inflater, ViewGroup parent) {
        View view = inflater.inflate(getLayoutRes(), parent, false);
        return new ShibaPhotoViewHolder(view, adapter);
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof ShibaPhoto) {
            return ((ShibaPhoto) o).getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public void bindViewHolder(final FlexibleAdapter adapter, final ShibaPhotoViewHolder holder, final int position, List payloads) {
        final Context context = holder.imgShareButton.getContext();
        Glide.clear(holder.imgContent);
        initStubImgs(context);
        holder.setDescription(getDescription());
        getHeader().setImgBtnOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.showInfoDialog(ShibaPhoto.this);
            }
        });
        final FlipView imageLikeBtn = holder.imageLikeBtn;
        imageLikeBtn.flipSilently(isLiked());
        final GestureDetector gd = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return true;
            }

            @Override
            public boolean onDoubleTap(MotionEvent e) {
                holder.doubleTapLike();
                processLikePress(imageLikeBtn, true);
                return true;
            }
        });
        holder.imgContent.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gd.onTouchEvent(motionEvent);
            }
        });
        imageLikeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                processLikePress(imageLikeBtn, false);
            }
        });
        holder.imgShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShareButtonClicked(ShibaPhoto.this);
            }
        });
        Glide.with(context)
                .load(getImageUrl())
                .error(errorImg)
                .crossFade()
                .into(holder.imgContent);
    }

    private void initStubImgs(Context context) {
        if (errorImg == null) {
            errorImg = VectorDrawableCompat.create(context.getResources(), R.drawable.stub_error, context.getTheme());
        }
        if (stubImg == null) {
            stubImg = VectorDrawableCompat.create(context.getResources(), R.drawable.stub_img, context.getTheme());
        }
    }

    private void processLikePress(FlipView imageLikeBtn, boolean fromDoubleTap) {
        boolean liked = isLiked();
        boolean newLikeState = !liked;
        imageLikeBtn.flip(newLikeState, 200);
        setLiked(newLikeState);
        likedFromDoubleTap = fromDoubleTap;
        mListener.onPhotoLiked(this);
    }

    public interface ShibaPhotoInteractionListener {
        void onShareButtonClicked(ShibaPhoto photo);

        void onPhotoLiked(ShibaPhoto photo);

        void showInfoDialog(ShibaPhoto shibaPhoto);
    }
}
