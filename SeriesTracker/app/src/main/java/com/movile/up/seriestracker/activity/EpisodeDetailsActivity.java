package com.movile.up.seriestracker.activity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.EpisodeDetailsListener;
import com.movile.up.seriestracker.loader.callback.EpisodeDetailsLoaderCallback;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.util.FormatUtil;
import com.movile.up.seriestracker.util.URLBuilder;

import java.util.Date;


public class EpisodeDetailsActivity extends AppCompatActivity {
    private static final String TAG = EpisodeDetailsActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.episode_details_activity);

        Log.d(TAG, "onCreate()");
    }

    @Override
    protected void onStart() {
        super.onStart();

        getLoaderManager().initLoader(0, null, new EpisodeDetailsLoaderCallback(this,
                new EpisodeDetailsListener() {
            @Override
            public void onEpisodeLoadSuccess(Episode episode) {
                ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
                ((TextView) findViewById(R.id.episode_details_summary)).setText(episode.overview());

                Date date = FormatUtil.formatDate(episode.firstAired());
                String formattedDate = FormatUtil.formatDate(date);
                ((TextView) findViewById(R.id.episode_details_time)).setText(formattedDate);
            }

            @Override
            public void onImageLoadSuccess(Bitmap image) {
                ((ImageView) findViewById(R.id.episode_details_screenshot)).setImageBitmap(image);
            }
        }, URLBuilder.buildEpisodeURL(this, "game-of-thrones", 1, 2))).forceLoad();

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
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
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


}
