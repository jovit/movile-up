package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.SeasonEpisodesListAdapter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.presenter.SeasonEpisodesPresenter;
import com.movile.up.seriestracker.view.SeasonEpisodesView;

import java.util.List;

public class SeasonEpisodesActivity extends AppCompatActivity implements SeasonEpisodesView {
    private SeasonEpisodesListAdapter mAdapter;
    private SeasonEpisodesPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season_episodes_activity);

        createEpisodesList();

        mPresenter = new SeasonEpisodesPresenter(this,this);
        mPresenter.loadEpisodeDetails("house-of-cards",(long)3);
    }

    private void createEpisodesList(){
        ListView episodesList = (ListView) findViewById(R.id.season_episodes_list);
        mAdapter = new SeasonEpisodesListAdapter(this);
        View header = LayoutInflater.from(this)
                .inflate(R.layout.season_episodes_header, episodesList, false);

        episodesList.addHeaderView(header,null, false);
        episodesList.setAdapter(mAdapter);
    }

    @Override
    public void displayEpisodes(List<Episode> episodes) {
        this.mAdapter.updateEpisodes(episodes);
    }
}
