package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.SeasonsCallback;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.remote.SeasonRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.ShowDetailsSeasonsView;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class ShowDetailsSeasonsPresenter implements SeasonsCallback{
    ShowDetailsSeasonsView mView;
    Context mContext;

    public ShowDetailsSeasonsPresenter(Context context, ShowDetailsSeasonsView view){
        mContext = context;
        mView = view;
    }

    public void loadSeasons(String show){
        new SeasonRemoteServiceRetrofit().loadSeasons(mContext,this,show);
    }

    @Override
    public void onSeasonsSuccess(List<Season> seasons) {
        mView.displaysSeasons(seasons);
    }
}
