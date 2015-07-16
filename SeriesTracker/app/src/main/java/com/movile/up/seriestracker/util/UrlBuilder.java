package com.movile.up.seriestracker.util;

import android.content.Context;

import com.movile.up.seriestracker.R;

import java.text.MessageFormat;

/**
 * Created by android on 7/16/15.
 */
public class URLBuilder {

    public static String buildEpisodeURL(Context context, String serie, int season, int episode){
        String url = context.getString(R.string.api_url_base);
        url = url.concat(MessageFormat.format(context.getString(R.string.api_url_episode), serie, season, episode));
        url = url.concat("?extended=full,images");
        return url;
    }
}
