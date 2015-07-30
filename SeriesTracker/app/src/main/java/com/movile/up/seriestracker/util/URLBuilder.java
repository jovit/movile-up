package com.movile.up.seriestracker.util;

import android.content.Context;

import com.movile.up.seriestracker.R;

import java.text.MessageFormat;

public class URLBuilder {

    public static String buildEpisodeURL(Context context, String show, int season, int episode){
        String url = context.getString(R.string.api_url_base);
        url = url.concat(MessageFormat.format(context.getString(R.string.api_url_episode), show, season, episode));
        return url;
    }
}
