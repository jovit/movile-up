package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Episode;

/**
 * Created by JoaoVitorAraki on 15/07/2015.
 */
public interface LoadEpisodeListener {
    void onLoadEpisodeSuccess(Episode episode);
}
