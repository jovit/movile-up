package com.movile.up.seriestracker.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.movile.up.seriestracker.model.ShowUpdate;
import com.movile.up.seriestracker.receiver.ShowUpdateReceiver;
import com.movile.up.seriestracker.remote.UpdatesRemoteServiceRetrofit;

/**
 * Created by android on 7/23/15.
 */
public class UpdatesService extends IntentService{
    public UpdatesService(String name) {
        super(name);
    }

    public UpdatesService(){
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        ShowUpdate update = new UpdatesRemoteServiceRetrofit().getLatestUpdate(this);
        Intent intentBroadcast = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
        intentBroadcast.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
        sendBroadcast(intentBroadcast);
    }
}
