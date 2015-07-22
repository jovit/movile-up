package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Show;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/22/15.
 */
public class ShowRemoteServiceRetrofit {
    private static final String TAG = ShowRemoteServiceRetrofit.class.getSimpleName();

    public void loadShowDetails(Context mContext, final ShowDetailsCallback mListener, String show){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

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
