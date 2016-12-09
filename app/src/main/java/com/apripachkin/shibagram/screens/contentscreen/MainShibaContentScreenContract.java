package com.apripachkin.shibagram.screens.contentscreen;

import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.screens.BaseContract;
import com.apripachkin.shibagram.utils.SharedPreferencesUtil;

import java.util.List;

/**
 * Created by Antony on 07.10.2016.
 */

public interface MainShibaContentScreenContract {
    interface View extends BaseContract.View {

        void loadMore(List<ShibaPhoto> data);

        void showDialog();

        void hideDialog();

        void onLoadMore();

        SharedPreferencesUtil getSharedPreferencesUtil();

        void showDoubleTap();
    }

    interface Presenter extends BaseContract.Presenter {
        void refreshContent();

        void loadMore(int currentItemCount);

        void onPhotoLiked(ShibaPhoto photo);

        void onResume();

        void onStop();

        void fetchData();

        void showCacheEntries();
    }
}
