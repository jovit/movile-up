package com.movile.up.seriestracker.receiver;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4 .app.NotificationCompat;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.activity.ShowsActivity;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.model.ShowUpdate;

/**
 * Created by android on 7/23/15.
 */
public class ShowUpdateReceiver extends BroadcastReceiver {
    public static final String EXTRA_UPDATE = "show_update_receiver_update";
    private static final String TAG = ShowUpdateReceiver.class.getSimpleName();

    private PendingIntent getPendingIntent(Context context, ShowUpdate update){
        Intent showIntent = new Intent(context, ShowDetailsActivity.class);
        showIntent.putExtra(ShowDetailsActivity.EXTRA_SHOW, update.show());
        showIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);
        stackBuilder.addParentStack(ShowDetailsActivity.class);
        stackBuilder.addNextIntent(showIntent);

        return stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle extra = intent.getExtras();
        ShowUpdate update = (ShowUpdate)extra.get(EXTRA_UPDATE);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_notification)
                .setContentTitle(update.title())
                .setContentText(update.message())
                .setContentIntent(getPendingIntent(context, update))
                .setAutoCancel(true)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(update.message()));

        Notification notification = builder.build();

        NotificationManager manager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(0, notification);


        Log.d(TAG, update.message());
    }
}
