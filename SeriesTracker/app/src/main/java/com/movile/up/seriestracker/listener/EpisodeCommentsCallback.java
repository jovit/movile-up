package com.movile.up.seriestracker.listener;

import com.movile.up.seriestracker.model.Comment;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public interface EpisodeCommentsCallback {
    void onEpisodeCommentsSuccess(List<Comment> comments);
}
