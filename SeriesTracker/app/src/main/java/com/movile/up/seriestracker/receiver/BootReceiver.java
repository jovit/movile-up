package com.movile.up.seriestracker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.PeriodicTask;
import com.google.android.gms.gcm.Task;
import com.movile.up.seriestracker.taskservice.SeriesTrackerTaskService;

public class BootReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        long periodSecs = 180L; // the task should be executed every 3 minutes

        long flexSecs = 15L; // the task can run as early as -15 seconds from the scheduled time

        String tag = "periodic  | SeriesTrackerTaskService: " + periodSecs + "s, f:" + flexSecs;  // a unique task identifier

        PeriodicTask periodic = new PeriodicTask.Builder()
                .setService(SeriesTrackerTaskService.class)
                .setPeriod(periodSecs)
                .setFlex(flexSecs)
                .setTag(tag)
                .setPersisted(true)
                .setRequiredNetwork(Task.NETWORK_STATE_CONNECTED)
                .setRequiresCharging(false)
                .build();

        GcmNetworkManager.getInstance(context).schedule(periodic);
    }
}
