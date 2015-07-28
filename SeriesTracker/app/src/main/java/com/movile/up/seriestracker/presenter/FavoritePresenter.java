package com.movile.up.seriestracker.presenter;

import android.app.LoaderManager;
import android.content.Context;
import android.database.Cursor;

import com.movile.up.seriestracker.listener.FavoritesListener;
import com.movile.up.seriestracker.loader.callback.FavoritesLoaderCallback;
import com.movile.up.seriestracker.view.FavoriteView;

/**
 * Created by android on 7/28/15.
 */
public class FavoritePresenter implements FavoritesListener{
    private FavoriteView mView;
    private Context mContext;

    public FavoritePresenter(Context context, FavoriteView view){
        mContext = context;
        mView = view;
    }

    public void loadFavorites(LoaderManager loaderManager){
        loaderManager.initLoader(
                0, null, new FavoritesLoaderCallback(mContext, this)
        ).forceLoad();
    }

    @Override
    public void onFavoritesSuccess(Cursor favorites) {
        mView.displayFavorites(favorites);
    }
}
