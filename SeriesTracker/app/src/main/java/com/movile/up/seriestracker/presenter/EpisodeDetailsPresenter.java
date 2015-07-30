package com.movile.up.seriestracker.presenter;

import android.content.Context;

import com.movile.up.seriestracker.listener.EpisodeCommentsCallback;
import com.movile.up.seriestracker.listener.EpisodeDetailsCallback;
import com.movile.up.seriestracker.model.Comment;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.CommentRemoteServiceRetrofit;
import com.movile.up.seriestracker.remote.EpisodeRemoteServiceRetrofit;
import com.movile.up.seriestracker.view.EpisodeDetailsView;

import java.util.List;

public class EpisodeDetailsPresenter implements EpisodeDetailsCallback, EpisodeCommentsCallback{
    private EpisodeDetailsView mView;
    private Context mContext;

    public EpisodeDetailsPresenter(Context context,EpisodeDetailsView mView) {
        this.mView = mView;
        this.mContext = context;
    }

    public void loadEpisodeDetails(String show, Long season, Long episode){
        new EpisodeRemoteServiceRetrofit().loadEpisodeDetails(mContext, this, show, season, episode);
    }

    public void loadEpisodeComments(String show, Long season, Long episode){
        new CommentRemoteServiceRetrofit().loadEpisodeComments(mContext, this, show, season, episode);
    }

    @Override
    public void onEpisodeCommentsSuccess(List<Comment> comments) {
        mView.displayEpisodeComments(comments);
    }

    @Override
    public void onEpisodeDetailsSuccess(Episode episode) {
        mView.displayEpisodeDetails(episode);
    }
}
