package com.movile.up.seriestracker.async_task;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.database.manual.dao.FavoriteDAO;

/**
 * Created by android on 7/27/15.
 */
public class RemoteDeleteFavoriteAsyncTask extends AsyncTask<String, Void, Void> {
    private Context mContext;

    public RemoteDeleteFavoriteAsyncTask(Context context) {
        this.mContext = context;
    }

    @Override
    protected Void doInBackground(String... show) {
        new FavoriteDAO(mContext).delete(show[0]);
        return null;
    }
}
