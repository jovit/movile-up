package com.movile.up.seriestracker.async_task;

import android.content.Context;

import com.movile.up.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.model.Episode;

public class LoadEpisodeAsyncTask extends AsyncTAsk<Void, Void, Episode>{
    private Context mContext;

    public LoadEpisodeAsyncTask(Context context){
        this.mContext = context;
    }

    protected Episode doInBackground() {
        return new FetchLocalEpisodeDetails().get(mContext);
    }

}
