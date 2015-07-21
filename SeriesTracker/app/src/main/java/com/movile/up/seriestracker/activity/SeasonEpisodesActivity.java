package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.SeasonEpisodesListAdapter;
import com.movile.up.seriestracker.listener.OnEpisodeClickListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.SeasonEpisodesPresenter;
import com.movile.up.seriestracker.view.SeasonEpisodesView;

import java.text.DecimalFormat;
import java.util.List;

public class SeasonEpisodesActivity extends BaseNavigationToolbarActivity implements SeasonEpisodesView, OnEpisodeClickListener {
    private SeasonEpisodesListAdapter mAdapter;
    private View mHeader;

    private String mShow;
    private Long mSeason;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_episodes_activity);

        configureEpisodesList();
        configureToolbar();

        mShow = "arrow";
        mSeason = (long)3;
        if(getSupportActionBar() != null)
         getSupportActionBar().setTitle("Season ".concat(mSeason.toString()));

        SeasonEpisodesPresenter mPresenter = new SeasonEpisodesPresenter(this,this);

        showLoading();
        mPresenter.loadSeason(mShow, mSeason);

    }

    private void configureEpisodesList(){
        ListView episodesList = (ListView) findViewById(R.id.season_episodes_list);
        mAdapter = new SeasonEpisodesListAdapter(this,this);
        mHeader = LayoutInflater.from(this)
                .inflate(R.layout.season_episodes_header, episodesList, false);

        episodesList.addHeaderView(mHeader,null, false);
        episodesList.setAdapter(mAdapter);
    }

    @Override
    public void onEpisodeClick(Episode episode) {
        Intent mIntent = new Intent(this, EpisodeDetailsActivity.class);
        mIntent.putExtra(EpisodeDetailsActivity.EXTRA_EPISODE,episode.number());
        mIntent.putExtra(EpisodeDetailsActivity.EXTRA_SEASON, mSeason);
        mIntent.putExtra(EpisodeDetailsActivity.EXTRA_SHOW, mShow);
        startActivity(mIntent);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        this.mAdapter.updateEpisodes(episodes);
        hideLoading();
   }

    @Override
    public void displaySeason(Season season) {
        ((TextView)mHeader.findViewById(R.id.season_episodes_rating)).
                setText(new DecimalFormat("#.#").format(season.rating()));

        Glide
                .with(this)
                .load(season.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.season_episodes_screenshot)));
        Glide
                .with(this)
                .load(season.images().thumb().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.season_episodes_thumbnail)));
    }
}
