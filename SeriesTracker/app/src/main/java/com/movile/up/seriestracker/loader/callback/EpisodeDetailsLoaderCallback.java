package com.movile.up.seriestracker.loader.callback;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;

import com.movile.up.seriestracker.async_task.RemoteImageAsyncTask;
import com.movile.up.seriestracker.listener.EpisodeDetailsListener;
import com.movile.up.seriestracker.loader.EpisodeDetailsLoader;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;

/**
 * Created by android on 7/16/15.
 */
public class EpisodeDetailsLoaderCallback implements LoaderManager.LoaderCallbacks<Episode> {
    private String mUrl;
    private Context mContext;
    private EpisodeDetailsListener mListener;

    public EpisodeDetailsLoaderCallback(Context context,EpisodeDetailsListener listener, String url) {
        this.mContext = context;
        this.mUrl = url;
        this.mListener = listener;
    }

    public Loader<Episode> onCreateLoader(int id, Bundle bundle) {
        return new EpisodeDetailsLoader(mContext,mUrl);
    }
    public void onLoadFinished(Loader<Episode> loader, Episode result) {
        mListener.onEpisodeLoadSuccess(result);
        new RemoteImageAsyncTask(mListener).execute(result.images().screenshot().get(Images.ImageSize.FULL));
    }
    public void onLoaderReset(Loader<Episode> loader) {

    }
}
