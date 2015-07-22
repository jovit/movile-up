package com.movile.up.seriestracker.presenter;

import com.movile.up.seriestracker.listener.ShowDetailsCallback;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.view.ShowDetailsView;

/**
 * Created by android on 7/22/15.
 */
public class ShowDetailsInfoPresenter implements ShowDetailsCallback {
    ShowDetailsView mView;


    @Override
    public void onShowDetailsSuccess(Show show) {
        mView.displayShow(show);
    }
}
