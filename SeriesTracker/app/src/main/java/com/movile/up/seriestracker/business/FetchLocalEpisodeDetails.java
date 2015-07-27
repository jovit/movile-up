package com.movile.up.seriestracker.business;

import android.content.Context;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.converter.ModelConverter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FetchLocalEpisodeDetails {

    private static final String TAG = FetchLocalEpisodeDetails.class.getSimpleName();
    private static final String ASSET_NAME = "episode.json";

    public Episode get(Context context) {
        Episode episode = null;
        InputStreamReader reader = null;

        try {
            InputStream stream = context.getResources().getAssets().open(ASSET_NAME);
            reader = new InputStreamReader(stream);
            episode = new ModelConverter().toEpisode(reader);
        } catch (IOException e) {
            Log.e(TAG, "Error loading local content from file", e);
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e(TAG, "Error releasing resource", e);
                }
            }
        }

        return episode;
    }

    public Episode get(Context context, String url){
        Episode episode = null;
        InputStreamReader reader = null;
        try {
            // ?extended=full,images
            HttpURLConnection connection = configureConnection(context, url);
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                InputStream stream = connection.getInputStream();
                reader = new InputStreamReader(stream);
                episode = new ModelConverter().toEpisode(reader);
            }
        } catch (IOException e) {
            Log.e(TAG, "Error loading remote content", e);
        }finally {
            try {
                if(reader != null)
                    reader.close();
            }catch(IOException e){
                Log.e(TAG, "Error closing reader", e);
            }
        }
        return episode;
    }

    private HttpURLConnection configureConnection(Context context, String url){
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) new URL(url).openConnection();

            connection.setReadTimeout(context.getResources().getInteger(R.integer.api_timeout_read));
            connection.setConnectTimeout(context.getResources().getInteger(R.integer.api_timeout_connect));
            connection.setRequestMethod("GET");
            connection.setDoInput(true);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("trakt-api-version", context.getString(R.string.api_version));
            connection.setRequestProperty("trakt-api-key", context.getString(R.string.api_key));
        }catch(Exception e){
            Log.e(TAG,e.getMessage());
        }
        return connection;
    }

}
