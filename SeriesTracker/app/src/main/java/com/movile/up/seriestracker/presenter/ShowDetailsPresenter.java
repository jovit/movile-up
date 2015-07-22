package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceRetrofit;
import com.movile.up.seriestracker.remote.ShowRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by JoaoVitorAraki on 21/07/2015.
 */
public class ShowDetailsPresenter implements ShowDetailsCallback{
    private ShowDetailsView mView;
    private Context mContext;
    public ShowDetailsPresenter(Context context, ShowDetailsView view){
        mView = view;
        mContext = context;
    }

    public void loadShowDetails(String show){
        new ShowRemoteServiceRetrofit().loadShowDetails(mContext,this,show);
    }

    @Override
    public void onShowDetailsSuccess(Show show) {
        mView.displayShow(show);
    }
}
