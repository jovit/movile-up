package com.movile.up.seriestracker.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.SeasonEpisodesActivity;
import com.movile.up.seriestracker.adapter.SeasonsRecyclerAdapter;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Season;
import com.movile.up.seriestracker.presenter.ShowDetailsSeasonsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsSeasonsView;

import java.util.List;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsSeasonsFragment extends Fragment implements ShowDetailsSeasonsView, OnSeasonClickListener{
    @Nullable
    public static final String SHOW_ARGUMENT = "show";
    SeasonsRecyclerAdapter mAdapter;
    ShowDetailsSeasonsPresenter mPresenter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_details_seasons_fragment,container, false);
    }

    @Override
    public void displaysSeasons(List<Season> seasons) {
        mAdapter.updateSeasons(seasons);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ShowDetailsSeasonsPresenter(this.getActivity(),this);
        configureRecycler();
        mPresenter.loadSeasons(getArguments().getString(SHOW_ARGUMENT));
    }

    public void configureRecycler(){
        if(this.getView() != null) {
            RecyclerView view = (RecyclerView) this.getView().findViewById(R.id.show_details_seasons);
            view.setLayoutManager(
                    new LinearLayoutManager(this.getActivity(), LinearLayoutManager.HORIZONTAL, false));
            mAdapter = new SeasonsRecyclerAdapter(this.getActivity(), R.layout.show_details_seasons_item, this);
            view.setAdapter(mAdapter);
        }
    }

    @Override
    public void onSeasonClick(Season season) {
        Intent intent = new Intent(this.getActivity(),SeasonEpisodesActivity.class);
        intent.putExtra(SeasonEpisodesActivity.EXTRA_SHOW, getArguments().getString(SHOW_ARGUMENT));
        intent.putExtra(SeasonEpisodesActivity.EXTRA_SEASON,season.number());
        startActivity(intent);
    }
}
