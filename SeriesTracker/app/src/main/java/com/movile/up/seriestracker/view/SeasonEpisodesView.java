package com.movile.up.seriestracker.view;

import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public interface SeasonEpisodesView {
    void displayEpisodes(List<Episode> episodes);
    void displaySeason(Season season);
}
