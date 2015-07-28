package com.movile.up.seriestracker.loader;

import android.content.AsyncTaskLoader;
import android.content.Context;

import com.movile.up.seriestracker.database.dbflow.dao.FavoriteDAO;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/27/15.
 */
public class FavoriteLoader extends AsyncTaskLoader<Favorite> {
    private String mSlug;

    public FavoriteLoader(Context context, String slug){
        super(context);
        mSlug = slug;
    }

    @Override
    public Favorite loadInBackground() {
        return new FavoriteDAO().query(mSlug);
    }
}
