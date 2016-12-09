package com.apripachkin.shibagram.screens.feedbackscreen;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.utils.AnalyticsUtil;
import com.apripachkin.shibagram.utils.ViewUtils;

import java.util.List;

public class FeedbackActivity extends AppCompatActivity implements FeedbackScreenContract.View {
    public static final String FEEDBACK_SCREEN = "FeedbackScreen";
    private Toolbar mToolbar;
    private TextView mTextView;
    private FloatingActionButton mSendEmailButton;
    private TextInputLayout mEditText;
    private FeedbackScreenContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        init(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AnalyticsUtil.getInstance(this).sendScreenName(FEEDBACK_SCREEN);
    }


    private void init(Bundle savedInstanceState) {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitle("");
        mTextView = (TextView) findViewById(R.id.tvFeedbackTitle);
        ViewUtils.setPhotoTitleTypeFace(mTextView);
        setSupportActionBar(mToolbar);
        initToolbarTitle();
        mPresenter = new FeedbackPresenter(this);
        mSendEmailButton = (FloatingActionButton) findViewById(R.id.fab);
        mSendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.checkInput(mEditText.getEditText().getText().toString());
            }
        });
        mEditText = (TextInputLayout) findViewById(R.id.editText);
    }

    private void initToolbarTitle() {
        TextView title = (TextView) mToolbar.findViewById(R.id.tvTitle);
        Typeface fromAsset = Typeface.createFromAsset(getAssets(), "fonts/GloriaHallelujah.ttf");
        title.setTypeface(fromAsset);
        title.setText(getString(R.string.string_feedback));
    }

    @Override
    public void sendEmail(String text) {
        AnalyticsUtil analytics = AnalyticsUtil.getInstance(this);
        analytics.sendEvent(AnalyticsConsants.FEEDBACK_SCREEN_CATEGORY, AnalyticsConsants.EVENT_SEND_FEEDBACK_EMAIL);
        Intent emailIntent = createEmailIntent(true, text);
        if (canResolveIntent(emailIntent)) {
            startActivity(Intent.createChooser(emailIntent, getString(R.string.string_choose_email_client)));
        } else {
            Intent intent = createEmailIntent(false, text);
            if (canResolveIntent(intent)) {
                startActivity(Intent.createChooser(intent, getString(R.string.string_choose_email_client)));
            } else {
                showMessage(R.string.string_thanks_for_feedback);
            }
        }
    }

    private boolean canResolveIntent(Intent intent) {
        PackageManager packageManager = getPackageManager();
        List<ResolveInfo> resolveInfos = packageManager.queryIntentActivities(intent, 0);
        return !resolveInfos.isEmpty();
    }

    private Intent createEmailIntent(boolean tryDefaultEmailClient, String text) {
        Intent intent = null;
        if (tryDefaultEmailClient) {
            intent = new Intent(Intent.ACTION_SENDTO);
            Uri parse = Uri.parse("mailto:" + getString(R.string.string_feedback_email));
            intent.setData(parse);
        } else {
            intent = new Intent(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.string_feedback_email)});
        }
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, text);
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.string_feedback_email_title));
        return intent;
    }

    @Override
    public void showMessage(String text) {
        Snackbar.make(mSendEmailButton, text, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showMessage(int messageRecourse) {
        showMessage(getString(messageRecourse));
    }

    @Override
    public void showToast(int resource) {
        Toast.makeText(this, getString(resource), Toast.LENGTH_SHORT).show();
    }
}
