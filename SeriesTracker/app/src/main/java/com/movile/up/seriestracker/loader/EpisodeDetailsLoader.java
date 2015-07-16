package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.business.FetchLocalEpisodeDetails;
import com.movile.up.seriestracker.model.Episode;

public class EpisodeDetailsLoader extends AsyncTaskLoader<Episode>{
    private Context mContext;
    private String mUrl;

    public EpisodeDetailsLoader(Context context, String url){
        super(context);
        mContext = context;
        mUrl = url;
    }

    @Override
    public Episode loadInBackground() {
        return  new FetchLocalEpisodeDetails().get(mContext,mUrl);
    }
}
