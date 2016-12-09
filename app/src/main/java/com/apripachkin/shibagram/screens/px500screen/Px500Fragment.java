package com.apripachkin.shibagram.screens.px500screen;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.screens.BaseContract;
import com.apripachkin.shibagram.screens.BaseFragment;

/**
 * Created by Pripachkin on 07.10.2016.
 */

public class Px500Fragment extends BaseFragment implements BaseContract.View {
    private Px500Contract.Presenter mPresenter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new Px500Presenter(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.fragment_500px_screen_name);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onShareButtonClicked(ShibaPhoto photo) {
        mPresenter.onShareButtonClicked(photo);
    }

    @Override
    public void onPhotoLiked(ShibaPhoto photo) {
        super.onPhotoLiked(photo);
        mPresenter.onPhotoLiked(photo);
    }
}
