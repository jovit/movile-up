package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public interface SearchShowCallback {
    void onSearchShowSuccess(List<Show> shows);
}
