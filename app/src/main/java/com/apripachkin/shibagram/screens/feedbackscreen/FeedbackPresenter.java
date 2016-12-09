package com.apripachkin.shibagram.screens.feedbackscreen;

import android.text.TextUtils;

import com.apripachkin.shibagram.R;

/**
 * Created by Antony on 12/2/2016.
 */

public class FeedbackPresenter implements FeedbackScreenContract.Presenter {
    private FeedbackScreenContract.View mView;

    public FeedbackPresenter(FeedbackScreenContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void checkInput(String text) {
        if (TextUtils.isEmpty(text)) {
            mView.showMessage(R.string.string_enter_text_first);
            return;
        }
        mView.sendEmail(text);
    }

}
