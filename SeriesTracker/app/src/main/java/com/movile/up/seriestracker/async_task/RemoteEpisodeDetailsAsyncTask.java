package com.movile.up.seriestracker.async_task;

import android.content.Context;
import android.os.AsyncTask;

import com.movile.up.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.listener.EpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;

public class RemoteEpisodeDetailsAsyncTask extends AsyncTask<Void,Void,Episode> {
    private Context mContext;
    private EpisodeDetailsListener mListener;

    public RemoteEpisodeDetailsAsyncTask(Context context, EpisodeDetailsListener listener){
        this.mContext = context;
        this.mListener = listener;
    }

    @Override
    protected Episode doInBackground(Void... params) {
        return new FetchLocalEpisodeDetails().get(mContext);
    }

    protected void onPostExecute(Episode result) {
        mListener.onEpisodeLoadSuccess(result);
    }

}
