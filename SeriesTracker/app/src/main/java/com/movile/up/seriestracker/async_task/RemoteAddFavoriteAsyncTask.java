package com.movile.up.seriestracker.async_task;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.database.manual.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class RemoteAddFavoriteAsyncTask extends AsyncTask<Favorite, Void, Void> {
    private Context mContext;

    public RemoteAddFavoriteAsyncTask(Context context){
        mContext = context;
    }

    @Override
    protected Void doInBackground(Favorite... favorite) {
        new FavoriteDAO(mContext).save(favorite[0]);
        return null;
    }
}
