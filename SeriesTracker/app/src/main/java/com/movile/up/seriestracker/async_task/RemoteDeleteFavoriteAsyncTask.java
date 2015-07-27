package com.movile.up.seriestracker.async_task;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database.dbflow.dao.FavoriteDAO;

/**
 * Created by android on 7/27/15.
 */
public class RemoteDeleteFavoriteAsyncTask extends AsyncTask<String, Void, Void> {
    @Override
    protected Void doInBackground(String... show) {
        new FavoriteDAO().delete(show[0]);
        return null;
    }
}
