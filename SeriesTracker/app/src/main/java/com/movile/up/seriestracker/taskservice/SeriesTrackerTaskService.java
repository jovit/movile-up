package com.movile.up.seriestracker.taskservice;

import android.content.Intent;

import com.google.android.gms.gcm.GcmNetworkManager;
import com.google.android.gms.gcm.GcmTaskService;
import com.google.android.gms.gcm.TaskParams;
import com.movile.up.seriestracker.service.UpdatesService;

/**
 * Created by android on 7/30/15.
 */
public class SeriesTrackerTaskService extends GcmTaskService {

    @Override
    public int onRunTask(TaskParams taskParams) {
        Intent serviceIntent = new Intent(this, UpdatesService.class);
        startService(serviceIntent);
        return GcmNetworkManager.RESULT_SUCCESS;
    }
}
