package com.movile.up.seriestracker.view;

import com.movile.up.seriestracker.model.Comment;
import com.movile.up.seriestracker.model.Episode;

import java.util.List;

public interface EpisodeDetailsView {
    void displayEpisodeDetails(Episode episode);
    void displayEpisodeComments(List<Comment> comments);
}
