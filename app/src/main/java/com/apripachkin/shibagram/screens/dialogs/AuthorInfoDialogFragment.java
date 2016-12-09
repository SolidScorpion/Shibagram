package com.apripachkin.shibagram.screens.dialogs;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatDialogFragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.apripachkin.shibagram.R;
import com.apripachkin.shibagram.adapter.RecyclerViewArrayAdapter;
import com.apripachkin.shibagram.constants.AnalyticsConsants;
import com.apripachkin.shibagram.constants.Constants;
import com.apripachkin.shibagram.utils.AnalyticsUtil;

import java.util.Arrays;

import eu.davidea.flexibleadapter.common.DividerItemDecoration;

/**
 * Created by Pripachkin on 25.09.2016.
 */

public class AuthorInfoDialogFragment extends AppCompatDialogFragment implements RecyclerViewArrayAdapter.OnOptionClickedListener {
    private static final int COPY_TO_CLIPBOARD = 0;
    private static final int GO_TO_AUTHOR_PAGE = 1;
    private String authorUrl;
    private RecyclerView mRecyclerView;

    public AuthorInfoDialogFragment() {
    }

    public static AuthorInfoDialogFragment newInstance(String authorUrl) {
        Bundle bundle = new Bundle();
        bundle.putString(Constants.AUTHOR_URL_LINK, authorUrl);
        AuthorInfoDialogFragment fragment = new AuthorInfoDialogFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            authorUrl = arguments.getString(Constants.AUTHOR_URL_LINK);
        } else {
            authorUrl = "No link!";
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.author_info_layout, container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.author_info_recycler_view);
        mRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), R.drawable.divider));
        RecyclerViewArrayAdapter<String> adapter = new RecyclerViewArrayAdapter<>(Arrays.asList(getResources().getStringArray(R.array.author_info_actions)), this);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);
    }

    @Override
    public void onOptionClicked(int position) {
        FragmentActivity activity = getActivity();
        AnalyticsUtil analyticsUtil = AnalyticsUtil.getInstance(activity);
        if (position == COPY_TO_CLIPBOARD) {
            analyticsUtil.sendEvent(AnalyticsConsants.PHOTO_INFO_CATEGORY,
                    AnalyticsConsants.EVENT_COPIED_TO_CLIPBOARD);
            ClipboardManager clipBoardManager = (ClipboardManager) activity.getSystemService(Context.CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText(null, authorUrl);
            clipBoardManager.setPrimaryClip(clipData);
            Toast.makeText(activity, R.string.url_copied_to_clipboard, Toast.LENGTH_SHORT).show();
        }
        if (position == GO_TO_AUTHOR_PAGE) {
            analyticsUtil.sendEvent(AnalyticsConsants.PHOTO_INFO_CATEGORY,
                    AnalyticsConsants.EVENT_VISIT_PHOTO_PAGE);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(authorUrl));
            if (intent.resolveActivity(activity.getPackageManager()) != null) {
                activity.startActivity(intent);
            }
        }
        dismiss();
    }
}
