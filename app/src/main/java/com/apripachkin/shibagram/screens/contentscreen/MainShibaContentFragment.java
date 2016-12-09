package com.apripachkin.shibagram.screens.contentscreen;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.SimpleProgressItem;
import com.apripachkin.shibagram.screens.BaseFragment;
import com.apripachkin.shibagram.utils.AnalyticsUtil;
import com.apripachkin.shibagram.utils.SharedPreferencesUtil;
import com.apripachkin.shibagram.viewholder.ShibaPhotoViewHolder;

import java.util.List;

import eu.davidea.flexibleadapter.FlexibleAdapter;

public class MainShibaContentFragment extends BaseFragment implements MainShibaContentScreenContract.View, FlexibleAdapter.EndlessScrollListener, ShibaPhoto.ShibaPhotoInteractionListener {
    private static final String TAG = MainShibaContentFragment.class.getSimpleName();
    private MainShibaContentScreenContract.Presenter mPresenter;
    private ProgressDialog mProgressDialog;

    public MainShibaContentFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPresenter = new MainShibaContentPresenterImpl(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shiba_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d(TAG, "onViewCreated() called with: " + "view = [" + view + "], savedInstanceState = [" + savedInstanceState + "]");
        mSwipeRefreshLayout.setEnabled(true);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                AnalyticsUtil.getInstance(getContext())
                        .sendEvent(AnalyticsConsants.MAIN_CONTENT_FRAGMENT_CATEGORY,
                                AnalyticsConsants.EVENT_REFRESH_CONTENT);
                if (!isConnectionAvailable()) {
                    showMessage(getString(R.string.no_internet));
                    mSwipeRefreshLayout.setRefreshing(false);
                    return;
                }
                mPresenter.refreshContent();
            }
        });
        mAdapter.setEndlessScrollListener(this, new SimpleProgressItem())
                .setEndlessScrollThreshold(1);
        if (!isConnectionAvailable()) {
            showMessage(getString(R.string.no_internet));
            mPresenter.showCacheEntries();
            return;
        }
        mPresenter.fetchData();
    }

    @Override
    public void showData(final List<ShibaPhoto> data) {
        super.showData(data);
        Log.d(TAG, "showData() called with: data = [" + data + "]");
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void loadMore(final List<ShibaPhoto> data) {
        setOnLikeListener(data);
        mAdapter.onLoadMoreComplete(data);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.onResume();
    }

    @Override
    protected String getScreenName() {
        return getString(R.string.fragment_main_content_sceen);
    }

    @Override
    public void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
        hideDialog();
    }

    @Override
    public void showDialog() {
        if (mProgressDialog == null) {
            Activity activity = getActivity();
            mProgressDialog = new ProgressDialog(activity);
            mProgressDialog.setMessage(getString(R.string.fetching_data));
            mProgressDialog.setCancelable(false);
            mProgressDialog.show();
        }
    }

    @Override
    public void hideDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
            mProgressDialog = null;
        }
    }

    @Override
    public void onLoadMore() {
        if (!isConnectionAvailable()) {
            showMessage(getString(R.string.no_internet));
            return;
        }
        final int itemCountOfTypes = mAdapter.getItemCountOfTypes(R.layout.shibaphoto_item);
        mPresenter.loadMore(itemCountOfTypes);
    }

    @Override
    public SharedPreferencesUtil getSharedPreferencesUtil() {
        return new SharedPreferencesUtil(getContext());
    }

    @Override
    public void showDoubleTap() {
        final ViewTreeObserver.OnDrawListener onDrawListener = new ViewTreeObserver.OnDrawListener() {
            @Override
            public void onDraw() {
                ShibaPhotoViewHolder shibaPhotoViewHolder = (ShibaPhotoViewHolder) mRecyclerView.getChildViewHolder(mRecyclerView.getChildAt(1));
                shibaPhotoViewHolder.doubleTapLike();
                mRecyclerView.getViewTreeObserver().removeOnDrawListener(this);
            }
        };
        mRecyclerView.getViewTreeObserver().addOnDrawListener(onDrawListener);
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
