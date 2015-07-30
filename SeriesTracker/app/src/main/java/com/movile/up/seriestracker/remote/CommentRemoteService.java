package com.movile.up.seriestracker.remote;

import com.movile.up.seriestracker.model.Comment;
import com.movile.up.seriestracker.util.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by android on 7/30/15.
 */
public interface CommentRemoteService {
    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}/comments")
    void getEpisodeComments(
            @Path("show") String show,
            @Path("season") Long season,
            @Path("episode") Long episode,
            Callback<List<Comment>> callback);

}
