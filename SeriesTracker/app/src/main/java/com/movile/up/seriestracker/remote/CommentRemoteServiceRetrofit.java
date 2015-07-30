package com.movile.up.seriestracker.remote;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.EpisodeCommentsCallback;
import com.movile.up.seriestracker.model.Comment;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by android on 7/30/15.
 */
public class CommentRemoteServiceRetrofit {
    private static final String TAG = CommentRemoteServiceRetrofit.class.getSimpleName();

    public void loadEpisodeComments(Context mContext, final EpisodeCommentsCallback mListener, String show, Long season, Long episode){
        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(mContext.getString(R.string.api_url_base)).build();

        CommentRemoteService service = mAdapter.create(CommentRemoteService.class);

        service.getEpisodeComments(show, season, episode, new Callback<List<Comment>>() {
            @Override
            public void success(List<Comment> comments, Response response) {
                mListener.onEpisodeCommentsSuccess(comments);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching comments " + error.getMessage());
            }
        });
    }
}
