package com.movile.up.seriestracker.listener;

import android.database.Cursor;

/**
 * Created by android on 7/28/15.
 */
public interface FavoritesListener {
    void onFavoritesSuccess(Cursor favorites);

}
