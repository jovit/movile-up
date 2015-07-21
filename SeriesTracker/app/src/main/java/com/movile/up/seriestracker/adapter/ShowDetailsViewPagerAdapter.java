package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.fragment.ShowDetailsInfoFragment;
import com.movile.up.seriestracker.fragment.ShowDetailsSeasonsFragment;

public class ShowDetailsViewPagerAdapter extends FragmentPagerAdapter{
    private static final int SHOW_INFO_PAGE = 0;
    private static final int SHOW_SEASONS_PAGE = 1;
    private Context mContext;

    public ShowDetailsViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        if(position == SHOW_INFO_PAGE){
            return new ShowDetailsInfoFragment();
        }else if(position == SHOW_SEASONS_PAGE){
            return new ShowDetailsSeasonsFragment();
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
