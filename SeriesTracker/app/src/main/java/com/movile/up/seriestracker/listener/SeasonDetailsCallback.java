package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;

public interface SeasonDetailsCallback {
    void onSeasonDetailsSuccess(Season season);
}
