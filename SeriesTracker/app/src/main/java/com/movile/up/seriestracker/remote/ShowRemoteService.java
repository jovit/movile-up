package com.movile.up.seriestracker.remote;

import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.util.ApiConfiguration;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by android on 7/22/15.
 */
public interface ShowRemoteService {


    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/{show}?extended=full,images")
    void getShowDetails(
            @Path("show") String show,
            Callback<Show> callback);



    @Headers({
            "trakt-api-version: " + ApiConfiguration.API_VERSION,
            "trakt-api-key: " + ApiConfiguration.API_KEY
    })
    @GET("/shows/popular?limit=50&extended=full,images")
    void getPopularShows(
            Callback<List<Show>> callback);
}
