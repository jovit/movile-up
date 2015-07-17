package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.EpisodeDetailsView;

public class EpisodeDetailsPresenter implements EpisodeDetailsCallback{
    private EpisodeDetailsView mView;
    private Context mContext;

    public EpisodeDetailsPresenter(Context context,EpisodeDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    public void loadEpisodeDetails(String show, Long season, Long episode){
        new EpisodeRemoteServiceRetrofit().loadEpisodeDetails(mContext, this, show, season, episode);
    }

    @Override
    public void onEpisodeDetailsSuccess(Episode episode) {
        mView.displayEpisodeDetails(episode);
    }
}
