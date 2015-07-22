package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonEpisodesCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceRetrofit;
import com.movile.up.seriestracker.remote.SeasonRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.SeasonEpisodesView;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class SeasonEpisodesPresenter implements SeasonEpisodesCallback, SeasonDetailsCallback{
    private SeasonEpisodesView mView;
    private Context mContext;

    public SeasonEpisodesPresenter(Context context, SeasonEpisodesView view){
        mView = view;
        mContext = context;
    }

    public void loadSeason(String show, Long season){
        new EpisodeRemoteServiceRetrofit().loadSeasonEpisodes(mContext, this, show, season);
        new SeasonRemoteServiceRetrofit().loadSeasonDetails(mContext, this, show, season);
    }

    @Override
    public void onSeasonDetailsSuccess(Season season) {
        mView.displaySeason(season);
    }

    @Override
    public void onSeasonEpisodesSuccess(List<Episode> episodes) {
        mView.displayEpisodes(episodes);
    }
}
