package com.movile.up.seriestracker.util;

import android.content.Context;

import com.movile.up.seriestracker.R;

import java.text.MessageFormat;

/**
 * Created by android on 7/16/15.
 */
public class UrlBuilder {

    public static String buildEpisodeUrl(Context context, String serie, int season, int episode){
        String url = context.getString(R.string.api_url_base);
        MessageFormat.format(R.string.api_url_episode,serie, season, episode);
        url.concat(MessageFormat.format(R.string.api_url_episode, serie, String.valueOf(season), String.valueOf(episode)));
        url.concat(?extended=full,images);
        return url;
    }
}
