package com.movile.up.seriestracker.activity;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.adapter.EpisodeDetailsAdapter;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.remote.EpisodeRemoteService;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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

        final EpisodeDetailsAdapter mListener = new EpisodeDetailsAdapter(this);

        RestAdapter mAdapter = new RestAdapter.Builder().setEndpoint(this.getString(R.string.api_url_base)).build();

        EpisodeRemoteService service = mAdapter.create(EpisodeRemoteService.class);

        service.getEpisodeDetails("sherlock", (long)1, (long)1, new Callback<Episode>() {
            @Override
            public void success(Episode episode, Response response) {
                mListener.onEpisodeLoadSuccess(episode);
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e(TAG, "Error fetching episode", error.getCause());
            }
        });

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
