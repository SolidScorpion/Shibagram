package com.apripachkin.shibagram.screens;

import android.util.Log;

import com.apripachkin.shibagram.models.ResponseConverter;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaLikedPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Antony on 07.10.2016.
 */

public abstract class BaseFilteredPresenter implements BaseContract.Presenter {
    private static final String TAG = "BaseFilteredPresenter";
    protected Realm mRealm;
    protected BaseContract.View view;

    public BaseFilteredPresenter(BaseContract.View view) {
        this.view = view;
        initRealm();
    }

    protected void initRealm() {
        Log.d(TAG, "initRealm() called");
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    protected void closeRealm() {
        Log.d(TAG, "closeRealm() called");
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    protected void convertRealmResults(Realm realm, ShibaPhotoPojo shibaPhotoPojo, List<ShibaPhoto> shibaPhotos) {
        ShibaPhoto shibaPhoto = ResponseConverter.convertPojoToViewModel(shibaPhotoPojo);
        processConvertedModel(realm, shibaPhotos, shibaPhoto);
    }

    private void processConvertedModel(Realm realm, List<ShibaPhoto> shibaPhotos, ShibaPhoto shibaPhoto) {
        checkIfLiked(shibaPhoto, realm);
        shibaPhotos.add(shibaPhoto);
    }

    private void checkIfLiked(ShibaPhoto shibaPhoto, Realm realm) {
        ShibaLikedPhotoPojo likedPhotoPojo = realm.where(ShibaLikedPhotoPojo.class).equalTo("timePosted", shibaPhoto.getTimePosted()).findFirst();
        if (likedPhotoPojo != null) {
            shibaPhoto.setLiked(true);
        }
    }

    protected void filterByService(final String serviceName) {
        initRealm();
        final ArrayList<ShibaPhoto> shibaPhotos = new ArrayList<>();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShibaPhotoPojo> results = realm.where(ShibaPhotoPojo.class).contains("serviceType", serviceName).findAll();
                Log.d(TAG, "Results of service: " + serviceName + " ammount: " + results.size());
                for (ShibaPhotoPojo shibaPhotoPojo : results) {
                    convertRealmResults(realm, shibaPhotoPojo, shibaPhotos);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (shibaPhotos.isEmpty()) {
                    view.showMessage("No photos of such type!");
                    return;
                }
                view.showData(shibaPhotos);
            }
        }, new Realm.Transaction.OnError() {
            @Override
            public void onError(Throwable error) {
                FirebaseCrash.report(error);
                FirebaseCrash.logcat(Log.ERROR, "realm error", error.toString());
                view.showMessage(error.getMessage());
            }
        });
    }

    protected void removeLikedPhotoFromDb(final ShibaPhoto photo) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(ShibaLikedPhotoPojo.class).equalTo("timePosted", photo.getTimePosted()).findFirst().deleteFromRealm();
                RealmResults<ShibaLikedPhotoPojo> all = realm.where(ShibaLikedPhotoPojo.class).findAll();
                Log.d("TAG", "Removed liked photo " + photo.getId() + " from db, current size: " + all.size());
                ShibaPhotoPojo model = realm.where(ShibaPhotoPojo.class).equalTo("timePosted", photo.getTimePosted()).findFirst();
                model.setLiked(false);
            }
        });
    }

    protected void addLikedPhotoToDb(final ShibaPhoto photo) {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ShibaPhotoPojo shibaPhotoPojo = realm.where(ShibaPhotoPojo.class).equalTo("timePosted", photo.getTimePosted()).findFirst();
                shibaPhotoPojo.setLiked(true);
                ShibaLikedPhotoPojo likedPhotoPojo = ResponseConverter.convertModelToLiked(shibaPhotoPojo);
                realm.insert(likedPhotoPojo);
                RealmResults<ShibaLikedPhotoPojo> all = mRealm.where(ShibaLikedPhotoPojo.class).findAll();
                int size = all.size();
                Log.d("TAG", "Inserted liked photo " + photo.getId() + " in db: current size: " + size);
            }
        });
    }

    @Override
    public void onShareButtonClicked(ShibaPhoto photo) {
        String text;
        String shareImgUrl = photo.getShareImgUrl();
        String authorUrl = photo.getAuthorUrl();
        if (photo.isLiked()) {
            text = "One of my favourite pictures in Shibagram: " + shareImgUrl + "\n" +
                    "original photo link: " + authorUrl;
        } else {
            text = "Cool shiba picture I found on Shibagram: " + shareImgUrl + "\n"
                    + "original photo link: " + authorUrl;
        }
        view.sharePhoto(text);
    }

    @Override
    public void onPhotoLiked(ShibaPhoto photo) {
        if (photo.isLiked()) {
            addLikedPhotoToDb(photo);
        } else {
            removeLikedPhotoFromDb(photo);
        }
    }

    @Override
    public void onResume() {
        initRealm();
    }

    @Override
    public void onStop() {
        closeRealm();
    }
}
