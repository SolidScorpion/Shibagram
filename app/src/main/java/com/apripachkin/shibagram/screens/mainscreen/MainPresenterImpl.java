package com.apripachkin.shibagram.screens.mainscreen;


import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaLikedPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;

import io.realm.Realm;

/**
 * Created by Pripachkin on 02.08.2016.
 */
public class MainPresenterImpl implements MainScreenContract.Presenter {
    private MainScreenContract.View mView;
    private Realm mRealm;

    public MainPresenterImpl(MainScreenContract.View mView) {
        this.mView = mView;
    }


    @Override
    public void processFavourites() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ShibaLikedPhotoPojo data = realm.where(ShibaLikedPhotoPojo.class).findFirst();
                if (data != null) {
                    mView.showFavourites();
                } else {
                    mView.showMessage("No favourites!");
                }
            }
        });
    }

    @Override
    public void showFiltered(final String serviceType) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ShibaPhotoPojo pojo = realm.where(ShibaPhotoPojo.class).contains("serviceType", serviceType).findFirst();
                if (pojo != null) {
                    mView.showFiltered(serviceType);
                } else {
                    mView.showMessage("No photos of such type!");
                }
            }
        });
    }

    @Override
    public void onStart() {
        mRealm = Realm.getDefaultInstance();
    }

    @Override
    public void onStop() {
        mRealm.close();
    }
}
