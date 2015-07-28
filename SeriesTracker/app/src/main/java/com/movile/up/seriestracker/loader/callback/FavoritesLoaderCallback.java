package com.movile.up.seriestracker.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;

import com.movile.up.seriestracker.listener.FavoritesListener;
import com.movile.up.seriestracker.loader.FavoritesLoader;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesLoaderCallback implements LoaderManager.LoaderCallbacks<Cursor> {

    Context mContext;
    FavoritesListener mListener;

    public FavoritesLoaderCallback(Context context, FavoritesListener listener){
        mContext = context;
        mListener = listener;
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new FavoritesLoader(mContext);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mListener.onFavoritesSuccess(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}
