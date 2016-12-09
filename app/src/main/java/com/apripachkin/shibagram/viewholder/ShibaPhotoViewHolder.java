package com.apripachkin.shibagram.viewholder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Build;
import android.text.Html;
import android.text.Spanned;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.utils.ViewUtils;

import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flipview.FlipView;
import eu.davidea.viewholders.FlexibleViewHolder;

/**
 * Created by Antony on 03.08.2016.
 */
public class ShibaPhotoViewHolder extends FlexibleViewHolder {
    private static final DecelerateInterpolator DECCELERATE_INTERPOLATOR = new DecelerateInterpolator();
    private static final AccelerateInterpolator ACCELERATE_INTERPOLATOR = new AccelerateInterpolator();
    public FlipView imageLikeBtn;
    public ImageView imgContent;
    public ImageView imgShareButton;
    private View circleBackground;
    private ImageView likeImageView;
    private TextView mItemDescription;

    public ShibaPhotoViewHolder(View view, FlexibleAdapter adapter) {
        super(view, adapter);
        mItemDescription = (TextView) view.findViewById(R.id.tvItemDescription);
        imageLikeBtn = (FlipView) view.findViewById(R.id.imgLikeBtn);
        imgContent = (ImageView) view.findViewById(R.id.imgShibaPhoto);
        imgShareButton = (ImageView) view.findViewById(R.id.imgShareBtn);
        circleBackground = view.findViewById(R.id.circleBg);
        likeImageView = (ImageView) view.findViewById(R.id.paw_like);
        ViewUtils.setDescriptionTypeFace(mItemDescription);
    }

    public void setDescription(String description) {
        Spanned text = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            text = Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT);
        } else {
            text = Html.fromHtml(description);
        }
        mItemDescription.setText(text);
    }

    public void doubleTapLike() {
        circleBackground.setVisibility(View.VISIBLE);
        likeImageView.setVisibility(View.VISIBLE);

        circleBackground.setScaleY(0.1f);
        circleBackground.setScaleX(0.1f);
        circleBackground.setAlpha(1f);
        likeImageView.setScaleY(0.1f);
        likeImageView.setScaleX(0.1f);

        AnimatorSet animatorSet = new AnimatorSet();

        ObjectAnimator bgScaleYAnim = ObjectAnimator.ofFloat(circleBackground, "scaleY", 0.1f, 1f);
        bgScaleYAnim.setDuration(200);
        bgScaleYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator bgScaleXAnim = ObjectAnimator.ofFloat(circleBackground, "scaleX", 0.1f, 1f);
        bgScaleXAnim.setDuration(200);
        bgScaleXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator bgAlphaAnim = ObjectAnimator.ofFloat(circleBackground, "alpha", 1f, 0f);
        bgAlphaAnim.setDuration(200);
        bgAlphaAnim.setStartDelay(150);
        bgAlphaAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleUpYAnim = ObjectAnimator.ofFloat(likeImageView, "scaleY", 0.1f, 1f);
        imgScaleUpYAnim.setDuration(300);
        imgScaleUpYAnim.setInterpolator(DECCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleUpXAnim = ObjectAnimator.ofFloat(likeImageView, "scaleX", 0.1f, 1f);
        imgScaleUpXAnim.setDuration(300);
        imgScaleUpXAnim.setInterpolator(DECCELERATE_INTERPOLATOR);

        ObjectAnimator imgScaleDownYAnim = ObjectAnimator.ofFloat(likeImageView, "scaleY", 1f, 0f);
        imgScaleDownYAnim.setDuration(300);
        imgScaleDownYAnim.setInterpolator(ACCELERATE_INTERPOLATOR);
        ObjectAnimator imgScaleDownXAnim = ObjectAnimator.ofFloat(likeImageView, "scaleX", 1f, 0f);
        imgScaleDownXAnim.setDuration(300);
        imgScaleDownXAnim.setInterpolator(ACCELERATE_INTERPOLATOR);

        animatorSet.playTogether(bgScaleYAnim, bgScaleXAnim, bgAlphaAnim, imgScaleUpYAnim, imgScaleUpXAnim);
        animatorSet.play(imgScaleDownYAnim).with(imgScaleDownXAnim).after(imgScaleUpYAnim);

        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                reset();
            }
        });
        animatorSet.start();
    }

    public void reset() {
        circleBackground.setVisibility(View.GONE);
        likeImageView.setVisibility(View.GONE);
    }

}
