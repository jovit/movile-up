package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.PopularShowsCallback;
import com.movile.up.seriestracker.listener.SearchShowCallback;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.SearchedShow;
import com.movile.up.seriestracker.model.Show;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.http.Query;

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

    public void loadPopularShows(Context mContext, final PopularShowsCallback mListener){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.getPopularShows(new Callback<List<Show>>() {
            @Override
            public void success(List<Show> shows, Response response) {
                mListener.onPopularShowsSuccess(shows);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching popular shows", error.getCause());
            }
        });
    }

    public void searchShow(Context mContext,String query, final SearchShowCallback mListener){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        ShowRemoteService service = mAdapter.create(ShowRemoteService.class);

        service.searchShow( query, new Callback<List<SearchedShow>>() {
            @Override
            public void success(List<SearchedShow> shows, Response response) {
                ArrayList<Show> showsList = new ArrayList<Show>();
                for(SearchedShow s : shows){
                    showsList.add(s.show());
                }
                mListener.onSearchShowSuccess(showsList);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching shows", error.getCause());
            }
        });
    }

}
