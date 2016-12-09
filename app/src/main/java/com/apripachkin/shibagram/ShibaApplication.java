package com.apripachkin.shibagram;

import android.app.Application;

import com.google.android.gms.analytics.ExceptionReporter;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Antony on 03.08.2016.
 */
public class ShibaApplication extends Application{
    private Tracker mTracker;
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(configuration);
        Thread.UncaughtExceptionHandler exceptionHandler = new ExceptionReporter(getTracker(),
                Thread.getDefaultUncaughtExceptionHandler(), this);
        Thread.setDefaultUncaughtExceptionHandler(exceptionHandler);
    }

    synchronized public Tracker getTracker() {
        if (mTracker == null) {
            GoogleAnalytics instance = GoogleAnalytics.getInstance(this);
            instance.setLocalDispatchPeriod(30);
            mTracker = instance.newTracker(getString(R.string.analytics_id));
            mTracker.enableExceptionReporting(true);
            mTracker.enableAutoActivityTracking(true);
            mTracker.setSessionTimeout(300L);
        }
        return mTracker;
    }
}
