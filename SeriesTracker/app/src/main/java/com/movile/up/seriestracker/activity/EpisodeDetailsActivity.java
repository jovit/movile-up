package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.async_task.LoadEpisodeAsyncTask;
import com.movile.up.seriestracker.async_task.LoadEpisodeListener;
import com.movile.up.seriestracker.model.Episode;


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

        new LoadEpisodeAsyncTask(this, new LoadEpisodeListener() {
            @Override
            public void onLoadEpisodeSucces(Episode episode) {
                ((TextView) findViewById(R.id.episode_details_title)).setText(episode.title());
                ((TextView) findViewById(R.id.episode_details_summary)).setText(episode.overview());
            }
        }).execute();

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
