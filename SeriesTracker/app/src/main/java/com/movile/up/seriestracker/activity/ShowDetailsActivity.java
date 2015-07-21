package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ShowDetailsViewPagerAdapter;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);

        configureViewPager();
    }

    private void configureViewPager(){
        ViewPager mViewPager = (ViewPager) findViewById(R.id.show_details_content);
        mViewPager.setAdapter(new ShowDetailsViewPagerAdapter(getSupportFragmentManager(), this));

    }
}
