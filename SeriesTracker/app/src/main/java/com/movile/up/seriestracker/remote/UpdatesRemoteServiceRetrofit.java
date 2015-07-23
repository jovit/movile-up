package com.movile.up.seriestracker.remote;

import android.content.Context;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.ShowUpdate;


import retrofit.RestAdapter;

/**
 * Created by android on 7/23/15.
 */
public class UpdatesRemoteServiceRetrofit {

    public ShowUpdate getLatestUpdate(Context mContext){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_updates)).build();

        UpdatesRemoteService service = mAdapter.create(UpdatesRemoteService.class);

        return service.getLatest();
    }
}
