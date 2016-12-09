package com.apripachkin.shibagram.screens.mainscreen;

/**
 * Created by Antony on 07.10.2016.
 */

public interface MainScreenContract {
    interface View {

        void showFeedbackScreen();

        void showMessage(String message);

        void showMainContentFragment();

        void showFavourites();

        void showFiltered(String serviceType);
    }

    interface Presenter {
        void processFavourites();

        void onStart();

        void onStop();

        void showFiltered(String serviceType);
    }
}
