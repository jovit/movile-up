package com.movile.up.seriestracker.listener;

import android.graphics.Bitmap;

import com.movile.up.seriestracker.model.Episode;

/**
 * Created by JoaoVitorAraki on 15/07/2015.
 */
public interface EpisodeDetailsListener {
    void onEpisodeLoadSuccess(Episode episode);
    void onImageLoadSuccess(Bitmap image);

}
