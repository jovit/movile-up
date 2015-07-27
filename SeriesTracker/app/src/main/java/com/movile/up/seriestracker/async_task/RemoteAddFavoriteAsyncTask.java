package com.movile.up.seriestracker.async_task;

import android.os.AsyncTask;

import com.movile.up.seriestracker.database.dbflow.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class RemoteAddFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void> {
    @Override
    protected Void doInBackground(Favorite... favorite) {
        new FavoriteDAO().save(favorite[0]);
        return null;
    }
}
