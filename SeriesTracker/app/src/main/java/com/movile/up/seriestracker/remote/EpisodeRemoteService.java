package com.movile.up.seriestracker.remote;

import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.util.ApiConfiguration;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

public interface EpisodeRemoteService {

    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}/seasons/{season}/episodes/{episode}?extended=full,images")
    void getEpisodeDetails(
            @Path("show") String show,
            @Path("season") Long season,
            @Path("episode") Long episode,
            Callback<Episode> callback);
}