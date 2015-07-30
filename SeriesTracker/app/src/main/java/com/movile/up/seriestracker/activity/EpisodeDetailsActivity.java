package com.movile.up.seriestracker.activity;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.CommentListAdapter;
import com.movile.up.seriestracker.model.Comment;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.presenter.EpisodeDetailsPresenter;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.view.EpisodeDetailsView;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

public class EpisodeDetailsActivity extends BaseNavigationToolbarActivity implements EpisodeDetailsView{

    public static final String EXTRA_SHOW = "episode_details_show";
    public static final String EXTRA_SEASON = "episode_details_season";
    public static final String EXTRA_EPISODE = "episode_details_episode";
    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();
    private EpisodeDetailsPresenter mPresenter;

    private View header;
    private CommentListAdapter mListAdapter;

    private String mShow;
    private Long mSeason;
    private Long mEpisode;

    private void getIntentExtraInformation(){
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
        mSeason = extras.getLong(EXTRA_SEASON);
        mEpisode = extras.getLong(EXTRA_EPISODE);
    }

    @Override
    public void displayEpisodeDetails(Episode episode) {
        ((TextView) header.findViewById(R.id.episode_details_title)).setText(episode.title());
        ((TextView) header.findViewById(R.id.episode_details_summary)).setText(episode.overview());

        Date date = FormatUtil.formatDate(episode.firstAired());
        String formattedDate = FormatUtil.formatDate(date);
        ((TextView) header.findViewById(R.id.episode_details_time)).setText(formattedDate);

        Glide
                .with(this)
                .load(episode.images().screenshot().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(((ImageView) header.findViewById(R.id.episode_details_screenshot)));
        hideLoading();
    }

    @Override
    public void displayEpisodeComments(List<Comment> comments) {
        mListAdapter.setComments(comments);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        configureToolbar();
        configureCommentsList();

        showLoading();

        getIntentExtraInformation();

        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(formatToolbarTitle(mSeason, mEpisode));
        }

        mPresenter = new EpisodeDetailsPresenter(this,this);
        mPresenter.loadEpisodeDetails(mShow, mSeason, mEpisode);
        mPresenter.loadEpisodeComments(mShow,mSeason,mEpisode);

        Log.d(TAG, "onCreate()");
    }

    private void configureCommentsList(){
        mListAdapter = new CommentListAdapter(this);
        ListView commentsList = (ListView) findViewById(R.id.episode_details_comments);
        header = LayoutInflater.from(this).inflate(R.layout.episode_details_header,commentsList,false);

        commentsList.addHeaderView(header);
        commentsList.setAdapter(mListAdapter);

    }

    @Override
    protected void onStart() {
        super.onStart();



        Log.d(TAG, "onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        Log.d(TAG, "onResume()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        Log.d(TAG, "onRestart()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        Log.d(TAG, "onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        Log.d(TAG, "onStop()");
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        Log.d(TAG, "onRestoreInstanceState()");
        Log.d(TAG,savedInstanceState.getString("SAVED_STRING"));
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putString("SAVED_STRING", "Hello I'm a saved String");

        Log.d(TAG, "onSaveInstanceState()");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d(TAG,"onDestroy()");
    }

    private String formatToolbarTitle(Long season, Long episode){
        return MessageFormat.format("S{0} - E{1}", season, episode);
    }


}
