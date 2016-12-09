package com.apripachkin.shibagram.screens;

import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;

import java.util.List;

/**
 * Created by Antony on 07.10.2016.
 */

public interface BaseContract {
    interface View {

        void showData(List<ShibaPhoto> data);

        void showMessage(String message);

        void sharePhoto(String text);
    }

    interface Presenter {
        void onShareButtonClicked(ShibaPhoto photo);

        void onResume();

        void onStop();

        void onPhotoLiked(ShibaPhoto photo);
    }
}
