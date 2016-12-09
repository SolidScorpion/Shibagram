package com.apripachkin.shibagram.screens.contentscreen;


import android.util.Log;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.models.shibaphoto.pojo.ShibaPhotoPojo;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.networking.NetworkRequestSender;
import com.apripachkin.shibagram.screens.BaseFilteredPresenter;
import com.apripachkin.shibagram.utils.SharedPreferencesUtil;
import com.google.firebase.crash.FirebaseCrash;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


/**
 * Created by Antony on 03.08.2016.
 */
public class MainShibaContentPresenterImpl extends BaseFilteredPresenter implements MainShibaContentScreenContract.Presenter, NetworkRequestSender.NetworkRequestCallback {
    private static final String TAG = MainShibaContentPresenterImpl.class.getSimpleName();
    private MainShibaContentScreenContract.View mView;
    private NetworkRequestSender mRequestSender = NetworkRequestSender.getInstance();
    private boolean isFetchingNewData = false;
    private SharedPreferencesUtil mSharedPreferencesUtil;
    private Realm.Transaction.OnError realmError = new Realm.Transaction.OnError() {
        @Override
        public void onError(Throwable error) {
            String realmError = "realmError";
            FirebaseCrash.logcat(Log.ERROR, realmError, "realm error caught: " + error);
            FirebaseCrash.report(error);
            Log.d(realmError, error.getMessage());
        }
    };

    public MainShibaContentPresenterImpl(MainShibaContentScreenContract.View mView) {
        super(mView);
        this.mView = mView;
    }

    private void checkForTutorial() {
        boolean isTutorialNeeded = mView.getSharedPreferencesUtil().getBoolean(Constants.DOUBLE_TAP_TUTORIAL, true);
        if (isTutorialNeeded) {
            mView.showDoubleTap();
            mSharedPreferencesUtil.saveBoolean(Constants.DOUBLE_TAP_TUTORIAL, false);
        }
    }

    @Override
    public void onDataDelivered(final List<ShibaPhotoPojo> data) {
        if (isFetchingNewData) {
            processNewFetchedData(data);
        } else {
            processInitialData(data);
        }
        mView.hideDialog();
    }

    private void processInitialData(final List<ShibaPhotoPojo> data) {
        if (mRealm == null) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.where(ShibaPhotoPojo.class).findAll().deleteAllFromRealm();
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                copyDataToDatabase(data);
            }
        });
    }

    private void processNewFetchedData(final List<ShibaPhotoPojo> data) {
        if (mRealm == null) {
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mRequestSender.clearCacheData();
                isFetchingNewData = false;
                mView.onLoadMore();
            }
        });
    }

    private void copyDataToDatabase(final List<ShibaPhotoPojo> data) {
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealm(data);
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                processDataToShow();
                mRequestSender.clearCacheData();
            }
        }, realmError);

    }

    private void processDataToShow() {
        initRealm();
        final List<ShibaPhoto> data = new ArrayList<>();
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShibaPhotoPojo> shibaPhotoPojos = realm.where(ShibaPhotoPojo.class).findAll();
                if (!shibaPhotoPojos.isEmpty()) {
                    for (int i = 0; i < 10; i++) {
                        ShibaPhotoPojo pojo = shibaPhotoPojos.get(i);
                        convertRealmResults(realm, pojo, data);
                    }
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.hideDialog();
                mView.showData(data);
                checkForTutorial();
            }
        });
    }

    @Override
    public void onFailure(Throwable throwable) {
        FirebaseCrash.logcat(Log.ERROR, "caught network related error", throwable.toString());
        FirebaseCrash.report(throwable);
        mView.showMessage(throwable.getMessage());
    }

    @Override
    public void refreshContent() {
        mRequestSender.sendBatchRequest(this);
    }

    @Override
    public void loadMore(final int currentItemCount) {
        final ArrayList<ShibaPhoto> shibaPhotos = new ArrayList<>();
        Log.d(TAG, "loadMore() called with: " + "currentItemCount = [" + currentItemCount + "]");
        if (mRealm == null) {
            mView.loadMore(null);
            return;
        }
        mRealm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ShibaPhotoPojo> data = realm.where(ShibaPhotoPojo.class).findAll();
                int size = data.size();
                Log.d(TAG, "Current database size: " + size);

                if (currentItemCount >= size) {
                    return;
                }

                int fetchAmmount = 10;
                if (fetchAmmount * 2 + currentItemCount >= size) {
                    fetchMoreNetworkData();
                }

                if (size - currentItemCount < fetchAmmount) {
                    fetchAmmount = size - currentItemCount;
                }

                for (int i = currentItemCount; i < currentItemCount + fetchAmmount; i++) {
                    ShibaPhotoPojo shibaPhotoPojo = data.get(i);
                    convertRealmResults(realm, shibaPhotoPojo, shibaPhotos);
                }
            }
        }, new Realm.Transaction.OnSuccess() {
            @Override
            public void onSuccess() {
                mView.loadMore(shibaPhotos);
            }
        });
    }

    private void fetchMoreNetworkData() {
        if (isFetchingNewData) {
            return;
        }
        mRequestSender.loadMoreData(this);
        isFetchingNewData = true;
        Log.d(TAG, "fetchMoreNetworkData() called to get more data");
    }

    @Override
    public void fetchData() {
        initRealm();
        if (hasCacheEntries()) {
            showCacheEntries();
        }
        mView.showDialog();
        mRequestSender.sendBatchRequest(this);
    }

    private boolean hasCacheEntries() {
        ShibaPhotoPojo pojo = mRealm.where(ShibaPhotoPojo.class).findFirst();
        return pojo != null;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSharedPreferencesUtil == null) {
            mSharedPreferencesUtil = mView.getSharedPreferencesUtil();
            restoreNetworkRequestPagesState();
        }
    }

    private void saveNetworkRequestPagesState() {
        int current500pxPage = mRequestSender.getCurrent500pxPage();
        int currentFlickrPage = mRequestSender.getCurrentFlickrPage();
        int total500PxPages = mRequestSender.getTotal500PxPages();
        int totalFlickrPages = mRequestSender.getTotalFlickrPages();
        mSharedPreferencesUtil.saveData(NetworkRequestSender.KEY_500PX_CURRENT_PAGE, String.valueOf(current500pxPage));
        mSharedPreferencesUtil.saveData(NetworkRequestSender.KEY_FLICKR_CURRENT_PAGE, String.valueOf(currentFlickrPage));
        mSharedPreferencesUtil.saveData(NetworkRequestSender.KEY_500PX_TOTAL_PAGES, String.valueOf(total500PxPages));
        mSharedPreferencesUtil.saveData(NetworkRequestSender.KEY_TOTAL_FLICKR_PAGES, String.valueOf(totalFlickrPages));
    }

    private void restoreNetworkRequestPagesState() {
        String defaultValue = String.valueOf(0);
        int flickrTotalPages = Integer.parseInt(mSharedPreferencesUtil.getData(NetworkRequestSender.KEY_TOTAL_FLICKR_PAGES, defaultValue));
        int px500TotalPages = Integer.parseInt(mSharedPreferencesUtil.getData(NetworkRequestSender.KEY_500PX_TOTAL_PAGES, defaultValue));
        int currentFlickrPage = Integer.parseInt(mSharedPreferencesUtil.getData(NetworkRequestSender.KEY_FLICKR_CURRENT_PAGE, defaultValue));
        int current500PxPage = Integer.parseInt(mSharedPreferencesUtil.getData(NetworkRequestSender.KEY_500PX_CURRENT_PAGE, defaultValue));
        mRequestSender.setCurrent500pxPage(current500PxPage);
        mRequestSender.setCurrentFlickrPage(currentFlickrPage);
        mRequestSender.setTotal500PxPages(px500TotalPages);
        mRequestSender.setTotalFlickrPages(flickrTotalPages);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mSharedPreferencesUtil != null) {
            saveNetworkRequestPagesState();
            mSharedPreferencesUtil = null;
        }
    }

    @Override
    public void showCacheEntries() {
        initRealm();
        processDataToShow();
    }
}