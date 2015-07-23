package com.movile.up.seriestracker.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.base.BaseNavigationToolbarActivity;
import com.movile.up.seriestracker.adapter.ShowDetailsViewPagerAdapter;
import com.movile.up.seriestracker.fragment.ShowDetailsSeasonsFragment;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsPresenter;
import com.movile.up.seriestracker.view.ShowDetailsView;

import java.text.DecimalFormat;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsActivity extends BaseNavigationToolbarActivity implements ShowDetailsView{
    private String mShow;
    private ShowDetailsPresenter mPresenter;
    private ShowDetailsViewPagerAdapter mViewPagerAdapter;

    public static final String EXTRA_SHOW = "show_details_activity_extra_show";

    @Override
    public void displayShow(Show show) {
        getSupportActionBar().setTitle(show.title());
        ((TextView)findViewById(R.id.show_details_state)).setText(show.status());
        ((TextView)findViewById(R.id.show_details_rating)).setText(new DecimalFormat("#.#").format(show.rating()));
        ((TextView)findViewById(R.id.show_details_year)).setText(show.year().toString());
        Glide
                .with(this)
                .load(show.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.highlight_placeholder)
                .centerCrop()
                .into(((ImageView) findViewById(R.id.show_details_screenshot)));
        hideLoading();
    }


    private void getIntentExtraInformation(){
        Bundle extras = getIntent().getExtras();
        mShow = extras.getString(EXTRA_SHOW);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_details_activity);
        getIntentExtraInformation();

        mPresenter = new ShowDetailsPresenter(this,this);
        showLoading();
        mPresenter.loadShowDetails(mShow);

        configureToolbar();
        configureViewPager();
    }

    private void configureViewPager(){
        ViewPager mViewPager = (ViewPager) findViewById(R.id.show_details_content);
        mViewPagerAdapter = new ShowDetailsViewPagerAdapter(getSupportFragmentManager(), this, mShow);
        mViewPager.setAdapter(mViewPagerAdapter);

    }
}
