package com.movile.up.seriestracker.async_task;

import com.movile.up.seriestracker.model.Episode;

/**
 * Created by JoaoVitorAraki on 15/07/2015.
 */
public interface LoadEpisodeListener {
    public void onLoadEpisodeSucces(Episode episode);
}
