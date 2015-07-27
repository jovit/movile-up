package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.remote.ShowRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.ShowDetailsInfoView;

/**
 * Created by android on 7/22/15.
 */
public class ShowDetailsInfoPresenter implements ShowDetailsCallback {
    ShowDetailsInfoView mView;
    Context mContext;
    public ShowDetailsInfoPresenter(Context context, ShowDetailsInfoView view){
        mContext = context;
        mView = view;
    }

    public void loadShowDetails(String show){
        new ShowRemoteServiceRetrofit().loadShowDetails(mContext, this, show);
    }

    @Override
    public void onShowDetailsSuccess(Show show) {
        mView.displayShow(show);
    }
}
