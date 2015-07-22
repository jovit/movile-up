package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.OnSeasonClickListener;
import com.movile.up.seriestracker.model.Images;
import com.movile.up.seriestracker.model.Season;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by android on 7/22/15.
 */
public class SeasonsRecyclerAdapter extends RecyclerView.Adapter<SeasonsRecyclerAdapter.ViewHolder> {
    private Context mContext;
    private List<Season> seasons;
    private int mLayout;
    private OnSeasonClickListener mListener;

    public SeasonsRecyclerAdapter(Context context, int layout, OnSeasonClickListener listener){
        mContext = context;
        mLayout = layout;
        mListener = listener;
    }

    public void updateSeasons(List<Season> seasons){
        this.seasons = seasons;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(mLayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        final Season season = seasons.get(i);
        viewHolder.mSeasonNumber.setText("Season " + season.number());
        viewHolder.mEpisodesNumber.setText(season.episodeCount() + " episodes");
        Glide
                .with(mContext)
                .load(season.images().poster().get(Images.ImageSize.FULL))
                .placeholder(R.drawable.season_item_placeholder)
                .centerCrop()
                .into(viewHolder.poster());

        viewHolder.root().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onSeasonClick(season);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(seasons == null){
            return 0;
        }
        return seasons.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private View mRoot;
        private ImageView mPoster;
        private TextView mSeasonNumber;
        private TextView mEpisodesNumber;
        public ViewHolder(View root) {
            super(root);
            mRoot = root;
            mPoster = (ImageView) root.findViewById(R.id.show_details_seasons_item_poster);
            mSeasonNumber = (TextView) root.findViewById(R.id.show_details_seasons_item_number);
            mEpisodesNumber = (TextView) root.findViewById(R.id.show_details_seasons_item_episodes);
        }

        public View root(){
            return mRoot;
        }

        public ImageView poster(){
            return mPoster;
        }

        public TextView seasonNumber(){
            return mSeasonNumber;
        }

        public TextView episodesNumber(){
            return mEpisodesNumber;
        }
    }
}
