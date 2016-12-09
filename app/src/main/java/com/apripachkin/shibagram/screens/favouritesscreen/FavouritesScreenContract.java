package com.apripachkin.shibagram.screens.favouritesscreen;

import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.screens.BaseContract;

/**
 * Created by Antony on 07.10.2016.
 */

public interface FavouritesScreenContract {
    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter {
        void onPhotoLiked(ShibaPhoto photo);

        void onResume();

        void onStop();

        void filterFlickrPhotos();

        void filter500PxPhotos();
    }
}
