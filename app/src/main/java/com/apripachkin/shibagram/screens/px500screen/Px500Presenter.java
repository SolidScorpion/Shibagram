package com.apripachkin.shibagram.screens.px500screen;

import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.screens.BaseContract;
import com.apripachkin.shibagram.screens.BaseFilteredPresenter;

/**
 * Created by Pripachkin on 07.10.2016.
 */

public class Px500Presenter extends BaseFilteredPresenter implements Px500Contract.Presenter {
    public Px500Presenter(BaseContract.View view) {
        super(view);
        filterByService(Constants.SERVICE_TYPE_500PX);
    }
}
