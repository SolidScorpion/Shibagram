package com.apripachkin.shibagram.screens.favouritesscreen;

import android.util.Log;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.models.ResponseConverter;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaLikedPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Antony on 01.09.2016.
 */
public class FavouritesPresenterImpl implements FavouritesScreenContract.Presenter {
    private static final String TAG = "FavouritesPresenterImpl";
    private FavouritesScreenContract.View mView;
    private Realm mRealm;

    public FavouritesPresenterImpl(FavouritesScreenContract.View mView) {
        this.mView = mView;
        initRealm();
        getLikedPhotos();
    }

    private void initRealm() {
        if (mRealm == null) {
            mRealm = Realm.getDefaultInstance();
        }
    }

    private void getLikedPhotos() {
        final ArrayList<ShibaPhoto> shibaPhotos = new ArrayList<>();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShibaLikedPhotoPojo> likedPhotoPojos = realm.where(ShibaLikedPhotoPojo.class).findAll();
                for (ShibaLikedPhotoPojo likedPhotoPojo : likedPhotoPojos) {
                    shibaPhotos.add(ResponseConverter.convertLikedPojoToViewModel(likedPhotoPojo));
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.showData(shibaPhotos);
            }
        });
    }

    private void addLikedPhotoToDb(final ShibaPhoto photo) {
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

    private void removeLikedPhotoFromDb(final ShibaPhoto photo) {
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

    @Override
    public void onPhotoLiked(ShibaPhoto photo) {
        if (photo.isLiked()) {
            addLikedPhotoToDb(photo);
        } else {
            removeLikedPhotoFromDb(photo);
        }
    }

    @Override
    public void onShareButtonClicked(ShibaPhoto photo) {
        String text;
        if (photo.isLiked()) {
            text = "One of my favourite pictures in Shibagram: " + photo.getImageUrl() + "\n" +
                    "original photo link: " + photo.getAuthorUrl();
        } else {
            text = "Cool shiba picture I found on Shibagram: " + photo.getImageUrl() + "\n"
                    + "original photo link: " + photo.getAuthorUrl();
        }
        mView.sharePhoto(text);
    }

    @Override
    public void onResume() {
        initRealm();
    }

    @Override
    public void onStop() {
        closeRealm();
    }

    private void closeRealm() {
        if (mRealm != null) {
            mRealm.close();
            mRealm = null;
        }
    }

    @Override
    public void filterFlickrPhotos() {
        filterByService(Constants.SERVICE_TYPE_FLICKR);
    }

    @Override
    public void filter500PxPhotos() {
        filterByService(Constants.SERVICE_TYPE_500PX);
    }

    private void filterByService(final String serviceName) {
        initRealm();
        final ArrayList<ShibaPhoto> shibaPhotos = new ArrayList<>();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShibaLikedPhotoPojo> results = realm.where(ShibaLikedPhotoPojo.class).contains("serviceType", serviceName).findAll();
                Log.d(TAG, "Results of service: " + serviceName + " ammount: " + results.size());
                for (ShibaLikedPhotoPojo shibaLikedPhotoPojo : results) {
                    convertRealmResults(realm, shibaLikedPhotoPojo, shibaPhotos);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                if (shibaPhotos.isEmpty()) {
                    mView.showMessage("No photos of such type!");
                    return;
                }
                mView.showData(shibaPhotos);
            }
        });
    }

    private void convertRealmResults(Realm realm, ShibaLikedPhotoPojo shibaLikedPhotoPojo, List<ShibaPhoto> shibaPhotos) {
        ShibaPhoto shibaPhoto = ResponseConverter.convertLikedPojoToViewModel(shibaLikedPhotoPojo);
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
}
