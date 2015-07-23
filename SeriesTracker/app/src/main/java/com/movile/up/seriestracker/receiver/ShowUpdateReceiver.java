package com.movile.up.seriestracker.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.movile.up.seriestracker.model.ShowUpdate;

/**
 * Created by android on 7/23/15.
 */
public class ShowUpdateReceiver extends BroadcastReceiver {
    public static final String EXTRA_UPDATE = "show_update_receiver_update";
    private static final String TAG = ShowUpdateReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        ShowUpdate update = (ShowUpdate)extra.get(EXTRA_UPDATE);

        Log.d(TAG, update.message());
    }
}
