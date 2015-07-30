package com.movile.up.seriestracker.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;

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
    private String searchQuery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shows_activity);

        showLoading();

        configureNavigation();
        configureShowsGrid();

        mPresenter = new ShowsPresenter(this, this);
        if(savedInstanceState != null){
            searchShow((String) savedInstanceState.get("search-value"));
        }else{
            searchShow("");
        }


        ((EditText)findViewById(R.id.show_search)).setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                searchShow(((EditText) findViewById(R.id.show_search)).getText().toString());
                return true;
            }
        });

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("search-value", searchQuery );
        super.onSaveInstanceState(outState);
    }



    private void searchShow(String show){
        searchQuery = show;
        show = show.trim();
        if(show.equals("")){
            mPresenter.loadPopularShows();
        }else {
            mPresenter.searchShow(show);
        }
    }

    public void configureShowsGrid(){
        GridView showsGrid = (GridView) findViewById(R.id.shows_grid);
        mAdapter = new ShowsGridAdapter(this, this);
        showsGrid.setAdapter(mAdapter);
        showsGrid.setEmptyView(findViewById(R.id.show_not_found));
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
