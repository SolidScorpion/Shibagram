package com.apripachkin.shibagram.screens.flickrScreen;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.screens.BaseFilteredPresenter;

/**
 * Created by Antony on 07.10.2016.
 */

public class FlickrPresenter extends BaseFilteredPresenter implements FlickrScreenContract.Presenter {
    private FlickrScreenContract.View mView;

    public FlickrPresenter(FlickrScreenContract.View view) {
        super(view);
        mView = view;
        filterByService(Constants.SERVICE_TYPE_FLICKR);
    }
}
