package com.movile.up.seriestracker.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.listener.FavoriteListener;
import com.movile.up.seriestracker.loader.FavoriteLoader;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class FavoriteLoaderCallback implements LoaderManager.LoaderCallbacks<Favorite> {
    private Context mContext;
    private String mSlug;
    private FavoriteListener mListener;

    public FavoriteLoaderCallback(Context context, String slug, FavoriteListener listener) {
        this.mContext = context;
        this.mSlug = slug;
        this.mListener = listener;
    }

    @Override
    public Loader<Favorite> onCreateLoader(int id, Bundle args) {
        return new FavoriteLoader(mContext,mSlug);
    }

    @Override
    public void onLoadFinished(Loader<Favorite> loader, Favorite favorite) {
        mListener.onFavoriteSuccess(favorite);
    }

    @Override
    public void onLoaderReset(Loader<Favorite> loader) {
    }
}
