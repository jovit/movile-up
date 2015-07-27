package com.movile.up.seriestracker.application;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by android on 7/27/15.
 */
public class SeriesTrackerApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(this);
    }
}
