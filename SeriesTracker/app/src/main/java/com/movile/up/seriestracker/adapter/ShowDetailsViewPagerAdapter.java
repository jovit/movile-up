package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragment.ShowDetailsInfoFragment;
import com.movile.up.seriestracker.fragment.ShowDetailsSeasonsFragment;

public class ShowDetailsViewPagerAdapter extends FragmentPagerAdapter{
    public static final int SHOW_INFO_PAGE = 0;
    public static final int SHOW_SEASONS_PAGE = 1;
    private Context mContext;
    private String mShow;

    public ShowDetailsViewPagerAdapter(FragmentManager fm, Context context, String show) {
        super(fm);
        mContext = context;
        mShow = show;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == SHOW_INFO_PAGE){
            ShowDetailsInfoFragment fragment = new ShowDetailsInfoFragment();

            Bundle arguments  = new Bundle();
            arguments.putString(ShowDetailsInfoFragment.SHOW_ARGUMENT, mShow);
            fragment.setArguments(arguments);

            return fragment;
        }else if(position == SHOW_SEASONS_PAGE){
            ShowDetailsSeasonsFragment fragment = new ShowDetailsSeasonsFragment();

            Bundle arguments  = new Bundle();
            arguments.putString(ShowDetailsSeasonsFragment.SHOW_ARGUMENT, mShow);
            fragment.setArguments(arguments);

            return fragment;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        if(position == SHOW_INFO_PAGE){
            return mContext.getString(R.string.show_details_info);
        }else if(position == SHOW_SEASONS_PAGE){
            return mContext.getString(R.string.show_details_seasons);
        }
        return null;
    }
}
