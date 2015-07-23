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
    public static final String EXTRA_CONTEXT = "updates_service_extra_context";

    public UpdatesService(String name) {
        super(name);
    }

    public UpdatesService(){
        super("UpdatesService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Context context = (Context)extras.get(EXTRA_CONTEXT);
        ShowUpdate update = new UpdatesRemoteServiceRetrofit().getLatestUpdate(context);

        Intent intentBroadcast = new Intent("com.movile.up.seriestracker.action.SHOW_UPDATE");
        intentBroadcast.putExtra(ShowUpdateReceiver.EXTRA_UPDATE, update);
        sendBroadcast(intentBroadcast);
    }
}
