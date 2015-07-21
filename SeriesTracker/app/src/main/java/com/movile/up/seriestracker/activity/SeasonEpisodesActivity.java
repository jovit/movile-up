package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.SeasonEpisodesListAdapter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.SeasonEpisodesPresenter;
import com.movile.up.seriestracker.view.SeasonEpisodesView;

import java.text.DecimalFormat;
import java.util.List;

public class SeasonEpisodesActivity extends BaseNavigationToolbarActivity implements SeasonEpisodesView {
    private SeasonEpisodesListAdapter mAdapter;
    private SeasonEpisodesPresenter mPresenter;
    private View mHeader;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_episodes_activity);

        createEpisodesList();
        configureToolbar();

        String show = "house-of-cards";
        Long season = new Long(3);

        getSupportActionBar().setTitle("Season ".concat(season.toString()));

        mPresenter = new SeasonEpisodesPresenter(this,this);

        showLoading();
        mPresenter.loadSeason(show, season);

    }

    private void createEpisodesList(){
        ListView episodesList = (ListView) findViewById(R.id.season_episodes_list);
        mAdapter = new SeasonEpisodesListAdapter(this);
        mHeader = LayoutInflater.from(this)
                .inflate(R.layout.season_episodes_header, episodesList, false);

        episodesList.addHeaderView(mHeader,null, false);
        episodesList.setAdapter(mAdapter);
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
    }
}
