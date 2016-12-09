package com.apripachkin.shibagram.screens;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhoto;
import com.apripachkin.shibagram.models.shibaphoto.viewmodel.ShibaPhotoHeader;
import com.apripachkin.shibagram.screens.dialogs.AuthorInfoDialogFragment;
import com.apripachkin.shibagram.utils.AnalyticsUtil;

import java.util.ArrayList;
import java.util.List;

import eu.davidea.fastscroller.FastScroller;
import eu.davidea.flexibleadapter.FlexibleAdapter;
import eu.davidea.flexibleadapter.items.IFlexible;
import eu.davidea.flipview.FlipView;

/**
 * Created by Antony on 02.09.2016.
 */
public abstract class BaseFragment extends Fragment implements BaseContract.View, ShibaPhoto.ShibaPhotoInteractionListener {
    public static final String RW_STATE_KEY = "RW_STATE";
    private static final String AUTHOR_INFO_DIALOG_TAG = "AUTHOR_INFO_DIALOG";
    private static final String TAG = "BaseFragment";
    protected RecyclerView mRecyclerView;
    protected FlexibleAdapter mAdapter;
    protected SwipeRefreshLayout mSwipeRefreshLayout;
    protected FastScroller mFastScroller;
    protected LinearLayoutManager linearLayoutManager;
    private int currentPosition;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        FlipView.resetLayoutAnimationDelay(true, 1000l);
        FlipView.stopLayoutAnimation();
    }


    protected void initRecyclerView() {
        Log.d(TAG, "initRecyclerView() called");
        mAdapter = new FlexibleAdapter(new ArrayList<ShibaPhoto>(), this, true) {
            @Override
            public String onCreateBubbleText(int position) {
                IFlexible item = getItem(position);
                if (item instanceof ShibaPhoto) {
                    String title = ((ShibaPhoto) item).getHeader().getTitle();
                    return title.substring(0, 1).toUpperCase();
                } else if (item instanceof ShibaPhotoHeader) {
                    String title = ((ShibaPhotoHeader) item).getTitle();
                    return title.substring(0, 1).toUpperCase();
                }
                return null;
            }
        };
        mAdapter.initializeListeners(this)
                .setAnimationOnScrolling(true)
                .setAnimationOnReverseScrolling(true);
        mAdapter.setDisplayHeadersAtStartUp(true);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setFastScroller(mFastScroller, ContextCompat.getColor(getContext(), R.color.colorAccent));
        mRecyclerView.setItemViewCacheSize(10);
        mRecyclerView.setDrawingCacheEnabled(true);
        mRecyclerView.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator() {
            @Override
            public boolean canReuseUpdatedViewHolder(@NonNull RecyclerView.ViewHolder viewHolder) {
                return true;
            }
        });
        mAdapter.enableStickyHeaders();
    }

    protected boolean isConnectionAvailable() {
        ConnectivityManager connectionManager = (ConnectivityManager) getActivity().getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectionManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_shiba_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated() called with: view = [" + view + "], savedInstanceState = [" + savedInstanceState + "]");
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefresh);
        mSwipeRefreshLayout.setEnabled(false);
        mFastScroller = (FastScroller) view.findViewById(R.id.fast_scroller);
        mFastScroller.addOnScrollStateChangeListener(new FastScroller.OnScrollStateChangeListener() {
            @Override
            public void onFastScrollerStateChange(boolean scrolling) {
                if (scrolling) {
                    AnalyticsUtil.getInstance(getContext()).sendEvent(
                            AnalyticsConsants.PHOTO_INTERACTION_CATEGORY,
                            AnalyticsConsants.EVENT_FAST_SCROLLING
                    );
                }
            }
        });
        mFastScroller.setVisibility(View.VISIBLE);
        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getActivity(),
                R.color.colorPrimary),
                ContextCompat.getColor(getActivity(), R.color.colorAccent));
        mRecyclerView = (RecyclerView) view.findViewById(R.id.contentRv);
        linearLayoutManager = new LinearLayoutManager(getContext()) {
            @Override
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                return Resources.getSystem().getDisplayMetrics().heightPixels;
            }
        };

        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt(RW_STATE_KEY);
            Log.d(TAG, "position: " + currentPosition);
        }
        initRecyclerView();
        mRecyclerView.setLayoutManager(linearLayoutManager);
    }

    protected void setOnLikeListener(List<ShibaPhoto> data) {
        if (data != null) {
            for (ShibaPhoto shibaPhoto : data) {
                shibaPhoto.setOnLikedListener(this);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        AnalyticsUtil.getInstance(getContext()).sendScreenName(getScreenName());
    }

    protected abstract String getScreenName();

    public void showData(List<ShibaPhoto> data) {
        if (data != null) {
            Log.d(TAG, "showData() called with: position: " + currentPosition);
            setOnLikeListener(data);
            mAdapter.updateDataSet(data);
            if (currentPosition != 0) {
                mRecyclerView.scrollToPosition(currentPosition);
            }
        }
    }

    public void sharePhoto(String text) {
        AnalyticsUtil analyticsUtil = AnalyticsUtil.getInstance(getContext());
        analyticsUtil.sendEvent(AnalyticsConsants.PHOTO_INTERACTION_CATEGORY,
                AnalyticsConsants.EVENT_CLICK_SHARE_BUTTON);
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(Intent.createChooser(intent, getString(R.string.share_link_title)));
    }

    @Override
    public void showInfoDialog(ShibaPhoto shibaPhoto) {
        AnalyticsUtil.getInstance(getContext()).sendEvent(AnalyticsConsants.PHOTO_INTERACTION_CATEGORY,
                AnalyticsConsants.EVENT_CLICK_PHOTO_MENU_BUTTON);
        AuthorInfoDialogFragment dialogFragment = AuthorInfoDialogFragment.newInstance(shibaPhoto.getAuthorUrl());
        dialogFragment.show(getFragmentManager(), AUTHOR_INFO_DIALOG_TAG);
    }

    public void showMessage(String message) {
        Snackbar.make(mRecyclerView, message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onPhotoLiked(ShibaPhoto photo) {
        AnalyticsUtil instance = AnalyticsUtil.getInstance(getContext());
        String category = AnalyticsConsants.PHOTO_INTERACTION_CATEGORY;
        if (photo.isLikedFromDoubleTap()) {
            instance.sendEvent(category, AnalyticsConsants.EVENT_DOUBLE_TAP_LIKE);
        } else {
            instance.sendEvent(category, AnalyticsConsants.EVENT_LIKE_PHOTO_BUTTON);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d(TAG, "onSaveInstanceState() called with: outState = [" + outState + "]");
        super.onSaveInstanceState(outState);
//        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
//        int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
//        System.out.println("firstVisibleItemPosition = " + firstVisibleItemPosition);
//        outState.getInt(RW_STATE_KEY, firstVisibleItemPosition);
    }
}
