package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationDrawerActivity;
import com.movile.up.seriestracker.adapter.ShowsGridAdapter;
import com.movile.up.seriestracker.listener.OnShowClickListener;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowsPresenter;
import com.movile.up.seriestracker.view.ShowsView;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsActivity extends BaseNavigationDrawerActivity implements ShowsView, OnShowClickListener{
    private ShowsPresenter mPresenter;
    private ShowsGridAdapter mAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_activity);

        showLoading();

        configureNavigation();
        configureShowsGrid();

        mPresenter = new ShowsPresenter(this, this);
        mPresenter.loadPopularShows();
    }

    public void configureShowsGrid(){
        GridView showsGrid = (GridView) findViewById(R.id.shows_grid);
        mAdapter = new ShowsGridAdapter(this, this);
        showsGrid.setAdapter(mAdapter);
    }

    @Override
    public void onShowClick(Show show) {
        Intent intent = new Intent(this, ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW,show.ids().slug());
        startActivity(intent);
    }

    @Override
    public void displayShows(List<Show> shows) {
        mAdapter.updateShows(shows);
        hideLoading();
    }
}
