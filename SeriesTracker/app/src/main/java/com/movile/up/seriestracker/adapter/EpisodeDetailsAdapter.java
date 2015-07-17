package com.movile.up.seriestracker.adapter;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.EpisodeDetailsActivity;
import com.movile.up.seriestracker.listener.EpisodeDetailsListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.util.FormatUtil;

import java.util.Date;

public class EpisodeDetailsAdapter implements EpisodeDetailsListener {
    EpisodeDetailsActivity mContext;

    public EpisodeDetailsAdapter(EpisodeDetailsActivity context) {
        this.mContext = context;
    }

    @Override
    public void onEpisodeLoadSuccess(Episode episode) {
        ((TextView) mContext.findViewById(R.id.episode_details_title)).setText(episode.title());
        ((TextView) mContext.findViewById(R.id.episode_details_summary)).setText(episode.overview());

        Date date = FormatUtil.formatDate(episode.firstAired());
        String formattedDate = FormatUtil.formatDate(date);
        ((TextView) mContext.findViewById(R.id.episode_details_time)).setText(formattedDate);

        Glide
                .with(mContext)
                .load(episode.images().screenshot().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(((ImageView) mContext.findViewById(R.id.episode_details_screenshot)));
    }

    @Override
    public void onImageLoadSuccess(Bitmap image) {

    }
}
