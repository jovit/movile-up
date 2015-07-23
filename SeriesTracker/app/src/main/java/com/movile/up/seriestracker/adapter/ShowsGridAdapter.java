package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.OnShowClickListener;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Show;

import java.util.List;

/**
 * Created by android on 7/23/15.
 */
public class ShowsGridAdapter extends ArrayAdapter<Show> {
    private OnShowClickListener mListener;
    private Context mContext;
    private List<Show> shows;

    public ShowsGridAdapter(Context context, OnShowClickListener listener){
        super(context, R.layout.shows_item);
        mListener = listener;
        mContext = context;
    }

    public void updateShows(List<Show> shows){
        this.shows = shows;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(shows == null){
            return 0;
        }
        return shows.size();
    }

    @Override
    public long getItemId(int position) {
        return shows.get(position).ids().trakt();
    }

    @Override
    public Show getItem(int position) {
        return shows.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.shows_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        populateViewFromHolder(holder, position);
        return convertView;
    }

    private void populateViewFromHolder(ViewHolder holder, int position){
        final Show show = shows.get(position);
        Glide
                .with(mContext)
                .load(show.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.show_item_placeholder)
                .centerCrop()
                .into(holder.item());

        holder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onShowClick(show);
            }
        });

    }


    private class ViewHolder{
        private View mRoot;
        private ImageView mItem;

        public ViewHolder(View root){
            mRoot = root;
            mItem = (ImageView) root.findViewById(R.id.shows_item);
        }

        public View root() {
            return mRoot;
        }

        public ImageView item(){
            return mItem;
        }
    }
}
