package com.apripachkin.shibagram.screens.mainscreen;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.screens.BaseFragment;
import com.apripachkin.shibagram.screens.contentscreen.MainShibaContentFragment;
import com.apripachkin.shibagram.screens.favouritesscreen.FavouritesFragment;
import com.apripachkin.shibagram.screens.feedbackscreen.FeedbackActivity;
import com.apripachkin.shibagram.screens.flickrScreen.FlickrFragment;
import com.apripachkin.shibagram.screens.px500screen.Px500Fragment;
import com.apripachkin.shibagram.utils.AnalyticsUtil;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {
    private static final String TAG = MainActivity.class.getSimpleName();
    private static final String SHIBA_CONTENT_TAG = "Main shiba content";
    private static final String FAVOURITES_FRAGMENT_TAG = "FavouritesFragment";
    private static final String FLICKR_TAG = "Flickr_TAG";
    private static final String PX500_TAG = "500PX_TAG";
    private static final String TOOLBAR_TITLE_KEY = "toolbarTitle";
    private CollapsingToolbarLayout collapsingToolbarLayout;
    private AppBarLayout appBarLayout;
    private MainScreenContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        mPresenter = new MainPresenterImpl(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        loadBackground();
        appBarLayout = (AppBarLayout) findViewById(R.id.appBar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsingToolbar);
        AssetManager assets = getAssets();
        Typeface fromAsset = Typeface.createFromAsset(assets, "fonts/GloriaHallelujah.ttf");
        collapsingToolbarLayout.setExpandedTitleTypeface(fromAsset);
        collapsingToolbarLayout.setCollapsedTitleTypeface(fromAsset);
        collapsingToolbarLayout.setExpandedTitleColor(ContextCompat.getColor(this, R.color.primary_light));
        collapsingToolbarLayout.setCollapsedTitleTextColor(ContextCompat.getColor(this, R.color.primary_light));
        showMainContentFragment();
    }

    private void loadBackground() {
        ImageView backgroundImg = (ImageView) findViewById(R.id.shibaPic);
        Glide.with(this).load(R.drawable.cool_yoshiro).into(backgroundImg);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPresenter.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPresenter.onStop();
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        AnalyticsUtil analyticsUtil = AnalyticsUtil.getInstance(this);
        switch (item.getItemId()) {
            case R.id.showFlickr:
                mPresenter.showFiltered(Constants.SERVICE_TYPE_FLICKR);
                analyticsUtil.sendEvent(AnalyticsConsants.MAIN_ACTIVITY_CATEGORY,
                        AnalyticsConsants.EVENT_SHOW_FLICKR);
                break;
            case R.id.show500Px:
                mPresenter.showFiltered(Constants.SERVICE_TYPE_500PX);
                analyticsUtil.sendEvent(AnalyticsConsants.MAIN_ACTIVITY_CATEGORY,
                        AnalyticsConsants.EVENT_SHOW_500PX);
                break;
            case R.id.showFavourites:
                mPresenter.processFavourites();
                analyticsUtil.sendEvent(AnalyticsConsants.MAIN_ACTIVITY_CATEGORY,
                        AnalyticsConsants.EVENT_SHOW_FAVOURITES);
                break;
            case R.id.feedback:
                showFeedbackScreen();
                analyticsUtil.sendEvent(AnalyticsConsants.MAIN_ACTIVITY_CATEGORY,
                        AnalyticsConsants.EVENT_OPEN_FEEDBACK);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showFeedbackScreen() {
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(findViewById(R.id.fragmentContainer), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMainContentFragment() {
        showFragment(SHIBA_CONTENT_TAG, false, getString(R.string.app_name));
    }

    @Override
    public void showFavourites() {
        showFragment(FAVOURITES_FRAGMENT_TAG, true, getResources().getString(R.string.fav_screen_title));
    }

    @Override
    public void showFiltered(String serviceType) {
        String tag = serviceType.equals(Constants.SERVICE_TYPE_FLICKR) ? FLICKR_TAG : PX500_TAG;
        String title = tag.equals(FLICKR_TAG) ? getString(R.string.flickr_photos) : getString(R.string.px500_photos);
        showFragment(tag, true, title);
    }

    private void showFragment(String tag, boolean addToBackStack, String title) {
        FragmentManager fm = getSupportFragmentManager();
        if (fm.findFragmentByTag(tag) == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            fm.popBackStack();
            BaseFragment baseFragment = initBaseFragment(tag);
            transaction.replace(R.id.fragmentContainer, baseFragment, tag);
            if (addToBackStack) transaction.addToBackStack(null);
            transaction.commit();
            collapsingToolbarLayout.setTitle(title);
        }
    }

    private BaseFragment initBaseFragment(String tag) {
        switch (tag) {
            case FAVOURITES_FRAGMENT_TAG:
                return new FavouritesFragment();
            case FLICKR_TAG:
                return new FlickrFragment();
            case PX500_TAG:
                return new Px500Fragment();
            case SHIBA_CONTENT_TAG:
                return new MainShibaContentFragment();
        }
        throw new IllegalArgumentException("Unknown fragment type!");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        String title = collapsingToolbarLayout.getTitle().toString();
        outState.putString(TOOLBAR_TITLE_KEY, title);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        collapsingToolbarLayout.setTitle(savedInstanceState.getString(TOOLBAR_TITLE_KEY));
    }

    @Override
    public void onBackPressed() {
        if (!collapsingToolbarLayout.getTitle().equals(R.string.app_name)) {
            collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
        }
        super.onBackPressed();
    }
}
