package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Episode;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public interface SeasonEpisodesCallback {
    void onSeasonEpisodesSuccess(List<Episode> episodes);
}
