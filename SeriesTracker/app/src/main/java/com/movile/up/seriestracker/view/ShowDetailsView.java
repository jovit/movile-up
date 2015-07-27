package com.movile.up.seriestracker.view;

import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.model.Show;

/**
 * Created by JoaoVitorAraki on 21/07/2015.
 */
public interface ShowDetailsView {
    void displayShow(Show show);
    void displayFavorite(Favorite favorite);
}
