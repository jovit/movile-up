package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonEpisodesCallback;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EpisodeRemoteServiceRetrofit {
    private static final String TAG = EpisodeRemoteServiceRetrofit.class.getSimpleName();

    public void loadEpisodeDetails(Context mContext, final EpisodeDetailsCallback mListener, String show, Long season, Long episode){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getEpisodeDetails(show, season, episode, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mListener.onEpisodeDetailsSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });
    }

    public void loadSeasonEpisodes(Context mContext, final SeasonEpisodesCallback mListener, String show, Long season){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getSeasonEpisodes(show, season, new Callback<List<Episode>>() {
            @Override
            public void success(List<Episode> episodes, Response response) {
                mListener.onSeasonEpisodesSuccess(episodes);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episodes list", error.getCause());
            }
        });
    }

    public void loadSeasonDetails(Context mContext, final SeasonDetailsCallback mListener, String show, final Long season){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getSeasonDetails(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                mListener.onSeasonDetailsSuccess(seasons.get(season.intValue() - 1));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });
    }

    public void loadShowDetails(Context mContext, final ShowDetailsCallback mListener, String show){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getShowDetails(show, new Callback<Show>() {
            @Override
            public void success(Show show, Response response) {
                mListener.onShowDetailsSuccess(show);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching show", error.getCause());
            }
        });
    }
}
