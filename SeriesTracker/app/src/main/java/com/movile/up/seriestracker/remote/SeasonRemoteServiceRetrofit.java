package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.SeasonDetailsCallback;
import com.movile.up.seriestracker.listener.SeasonsCallback;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/22/15.
 */
public class SeasonRemoteServiceRetrofit {
    private static final String TAG = SeasonRemoteServiceRetrofit.class.getSimpleName();

    public void loadSeasonDetails(Context context, final SeasonDetailsCallback listener, String show, final Long season){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();

        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                for(int i=0; i<seasons.size(); i++){
                    if(seasons.get(i).number() == season){
                        listener.onSeasonDetailsSuccess(seasons.get(i));
                        break;
                    }
                }

            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });
    }

    public void loadSeasons(Context context, final SeasonsCallback listener, String show){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(context.getString(R.string.api_url_base)).build();

        SeasonRemoteService service = mAdapter.create(SeasonRemoteService.class);

        service.getSeasons(show, new Callback<List<Season>>() {
            @Override
            public void success(List<Season> seasons, Response response) {
                listener.onSeasonsSuccess(seasons);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching season", error.getCause());
            }
        });
    }


}
