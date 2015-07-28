package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.database.dbflow.entity.FavoriteEntity;
import com.movile.up.seriestracker.database.dbflow.entity.FavoriteEntity$Adapter;
import com.movile.up.seriestracker.listener.OnFavoriteClickListener;
import com.movile.up.seriestracker.model.Favorite;

/**
 * Created by android on 7/28/15.
 */
public class FavoriteListAdapter extends CursorAdapter {

    private OnFavoriteClickListener mClickListener;

    public FavoriteListAdapter(Context context, OnFavoriteClickListener clickListener) {
        super(context, null, 0);
        mClickListener = clickListener;
    }

    public void setCursor(Cursor c){
        swapCursor(c);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        ViewHolder holder;
        View view = LayoutInflater.from(context).inflate(R.layout.favorite_item, parent, false);
        holder = new ViewHolder(view);
        view.setTag(holder);

        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        FavoriteEntity$Adapter entityAdapter = new FavoriteEntity$Adapter();
        final FavoriteEntity entity = new FavoriteEntity();
        entityAdapter.loadFromCursor(cursor, entity);

        ViewHolder holder = (ViewHolder)view.getTag();

        holder.title().setText(entity.title());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClickListener.onFavoriteClick(new Favorite(entity.slug(), entity.title()));
            }
        });
    }

    private class ViewHolder{
        private TextView mTitle;
        private View mRoot;

        public ViewHolder(View root){
            mRoot = root;
            mTitle = (TextView) root.findViewById(R.id.favorite_item_title);
        }

        public TextView title(){
            return mTitle;
        }

        public View root(){
            return mRoot;
        }
    }
}
