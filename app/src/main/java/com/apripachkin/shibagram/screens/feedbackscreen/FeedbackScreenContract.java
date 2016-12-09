package com.apripachkin.shibagram.screens.feedbackscreen;

/**
 * Created by Antony on 12/2/2016.
 */

public interface FeedbackScreenContract {
    interface View {
        void sendEmail(String text);

        void showMessage(String text);

        void showMessage(int messageRecourse);

        void showToast(int resource);
    }

    interface Presenter {
        void checkInput(String text);

    }
}
