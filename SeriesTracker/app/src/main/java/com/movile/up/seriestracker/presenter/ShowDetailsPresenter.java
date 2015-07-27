package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.async_task.RemoteAddFavoriteAsyncTask;
import com.movile.up.seriestracker.async_task.RemoteDeleteFavoriteAsyncTask;
import com.movile.up.seriestracker.listener.FavoriteListener;
import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.loader.callback.FavoriteLoaderCallback;
import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceRetrofit;
import com.movile.up.seriestracker.remote.ShowRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by JoaoVitorAraki on 21/07/2015.
 */
public class ShowDetailsPresenter implements ShowDetailsCallback, FavoriteListener{
    private ShowDetailsView mView;
    private Context mContext;
    public ShowDetailsPresenter(Context context, ShowDetailsView view){
        mView = view;
        mContext = context;
    }

    public void loadShowDetails(String show){
        new ShowRemoteServiceRetrofit().loadShowDetails(mContext,this,show);
    }

    public void loadFavorite(String show){
        ((ShowDetailsActivity)mContext).getLoaderManager().initLoader(
                0, null, new FavoriteLoaderCallback(mContext, show, this)
        ).forceLoad();
    }

    public void addFavorite(Favorite favorite){
        new RemoteAddFavoriteAsyncTask().execute(favorite);
    }

    public void removeFavorite(String show){
        new RemoteDeleteFavoriteAsyncTask().execute(show);
    }

    @Override
    public void onShowDetailsSuccess(Show show) {
        mView.displayShow(show);
    }

    @Override
    public void onFavoriteSuccess(Favorite favorite) {
        mView.displayFavorite(favorite);
    }
}
