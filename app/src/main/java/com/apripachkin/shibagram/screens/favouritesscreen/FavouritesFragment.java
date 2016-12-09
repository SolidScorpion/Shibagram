package com.apripachkin.shibagram.screens.favouritesscreen;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.EmptyData;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.screens.BaseFragment;
import com.apripachkin.shibagram.utils.AnalyticsUtil;

import java.util.Arrays;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class FavouritesFragment extends BaseFragment implements FavouritesScreenContract.View, ShibaPhoto.ShibaPhotoInteractionListener {
    private FavouritesScreenContract.Presenter mPresenter;

    public FavouritesFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new FavouritesPresenterImpl(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.fragment_favourites_screen_name);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void showData(List<ShibaPhoto> data) {
        if (data.isEmpty()) {
            showEmptyScreen();
        } else {
            super.showData(data);
        }
    }

    private void showEmptyScreen() {
        mAdapter.addItem(0, new EmptyData());
        mFastScroller.setVisibility(View.GONE);
    }

    @Override
    public void onShareButtonClicked(ShibaPhoto photo) {
        mPresenter.onShareButtonClicked(photo);
    }

    @Override
    public void onPhotoLiked(ShibaPhoto photo) {
        super.onPhotoLiked(photo);
        mPresenter.onPhotoLiked(photo);
        int globalPositionOfHeader = mAdapter.getGlobalPositionOf(photo.getHeader());
        int globalPositionOfPhoto = mAdapter.getGlobalPositionOf(photo);
        mAdapter.removeItems(Arrays.asList(globalPositionOfHeader, globalPositionOfPhoto));
        AnalyticsUtil.getInstance(getContext()).sendEvent(
                AnalyticsConsants.FAVOURITES_FRAGMENT_CATEGORY,
                AnalyticsConsants.EVENT_REMOVE_LIKE_IN_FAV
        );
        if (mAdapter.isEmpty()) {
            showEmptyScreen();
        }
    }
}
