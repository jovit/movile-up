package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.PopularShowsCallback;
import com.movile.up.seriestracker.listener.SearchShowCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsPresenter implements PopularShowsCallback, SearchShowCallback{
    private ShowsView mView;
    private Context mContext;

    public ShowsPresenter(Context context, ShowsView view){
        mContext = context;
        mView = view;
    }

    public void loadPopularShows(){
        new ShowRemoteServiceRetrofit().loadPopularShows(mContext, this);
    }

    public void searchShow(String query){
        new ShowRemoteServiceRetrofit().searchShow(mContext, query, this);
    }


    @Override
    public void onSearchShowSuccess(List<Show> shows) {
        mView.displayShows(shows);
    }

    @Override
    public void onPopularShowsSuccess(List<Show> shows) {
        mView.displayShows(shows);
    }
}
