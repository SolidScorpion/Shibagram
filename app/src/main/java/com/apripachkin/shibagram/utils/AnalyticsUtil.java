package com.apripachkin.shibagram.utils;

import android.content.Context;

import com.apripachkin.shibagram.ShibaApplication;
import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;

/**
 * Created by Antony on 24.10.2016.
 */

public class AnalyticsUtil {
    private static AnalyticsUtil mInstance;
    private Tracker mTracker;

    private AnalyticsUtil(Context context) {
        ShibaApplication applicationContext = (ShibaApplication) context.getApplicationContext();
        mTracker = applicationContext.getTracker();
    }

    public static AnalyticsUtil getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new AnalyticsUtil(context);
        }
        return mInstance;
    }

    //call from onResume from activity/fragment
    public void sendScreenName(String screenName) {
        sendScreenName(screenName, false);
    }

    public void sendScreenName(String screenName, boolean startNewSession) {
        mTracker.setScreenName(screenName);
        HitBuilders.ScreenViewBuilder screenViewBuilder = new HitBuilders.ScreenViewBuilder();
        if (startNewSession) {
            screenViewBuilder.setNewSession();
        }
        mTracker.send(screenViewBuilder.build());
    }

    public void sendTiming(String category, long time) {
        sendTiming(category, time, null, null);
    }

    public void sendTiming(String category, long time, String eventName, String label) {
        HitBuilders.TimingBuilder timingBuilder = new HitBuilders.TimingBuilder()
                .setCategory(category)
                .setValue(time);
        if (eventName != null) {
            timingBuilder.setVariable(eventName);
        }
        if (label != null) {
            timingBuilder.setLabel(label);
        }
        mTracker.send(timingBuilder.build());
    }


    public void sendEvent(String category, String action, String label, boolean noInteraction) {
        HitBuilders.EventBuilder eventBuilder = new HitBuilders.EventBuilder()
                .setCategory(category)
                .setAction(action);
        if (label != null) {
            eventBuilder.setLabel(label);
        }
        if (noInteraction) {
            eventBuilder.setNonInteraction(noInteraction);
        }
        mTracker.send(eventBuilder
                .build());
    }


    public void sendEvent(String category, String action) {
        sendEvent(category, action, null, false);
    }
}
