package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public interface PopularShowsCallback {
    void onPopularShowsSuccess(List<Show> shows);
}
