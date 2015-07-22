package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Season;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public interface SeasonsCallback {
    void onSeasonsSuccess(List<Season> seasons);
}
