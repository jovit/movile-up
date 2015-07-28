package com.movile.up.seriestracker.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.activity.ShowDetailsActivity;
import com.movile.up.seriestracker.adapter.FavoriteListAdapter;
import com.movile.up.seriestracker.listener.OnFavoriteClickListener;
import com.movile.up.seriestracker.model.Favorite;
import com.movile.up.seriestracker.presenter.FavoritePresenter;
import com.movile.up.seriestracker.view.FavoriteView;

/**
 * Created by android on 7/28/15.
 */
public class FavoritesFragment extends Fragment implements FavoriteView, OnFavoriteClickListener{
    private FavoritePresenter mPresenter;
    private FavoriteListAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.favorite_fragment, container, false);
        mPresenter = new FavoritePresenter(this.getActivity(),this);
        configureEpisodesList(view);
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        mPresenter.loadFavorites(getLoaderManager());
    }



    private void configureEpisodesList(View view){
        ListView favoritesList = (ListView) view.findViewById(R.id.favorite_list);
        mAdapter = new FavoriteListAdapter(this.getActivity(), this);

        favoritesList.setEmptyView(view.findViewById(R.id.favorite_list_empty));
        favoritesList.setAdapter(mAdapter);
    }

    @Override
    public void displayFavorites(Cursor favorites) {
        ImageView favoritesStatus = (ImageView) this.getActivity().findViewById(R.id.favorite_status);

        if(favorites.getCount() == 0) {
            favoritesStatus.setImageResource(R.drawable.favorites_header_tv_unhappy);
        }else {
            favoritesStatus.setImageResource(R.drawable.favorites_header_tv_happy);
        }
        mAdapter.setCursor(favorites);
    }

    @Override
    public void onFavoriteClick(Favorite favorite) {
        Intent intent = new Intent(this.getActivity(), ShowDetailsActivity.class);
        intent.putExtra(ShowDetailsActivity.EXTRA_SHOW,favorite.slug());
        startActivity(intent);
    }
}
