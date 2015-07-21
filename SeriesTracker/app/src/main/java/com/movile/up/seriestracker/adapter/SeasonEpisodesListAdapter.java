package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.listener.OnEpisodeClickListener;
import com.movile.up.seriestracker.model.Episode;
import com.movile.up.seriestracker.util.FormatUtil;

import java.text.Normalizer;
import java.util.Date;
import java.util.List;

/**
 * Created by android on 7/20/15.
 */
public class SeasonEpisodesListAdapter extends ArrayAdapter<Episode> {
    private List<Episode> episodes;
    private OnEpisodeClickListener mClickListener;
    private static final int TYPE_TBA = 0;
    private static final int TYPE_AIRED = 1;

    @Override
    public int getCount() {
        if(episodes == null){
            return 0;
        }
        return episodes.size();
    }

    public SeasonEpisodesListAdapter(Context context, OnEpisodeClickListener clickListener) {
        super(context, R.layout.season_episodes_item);
        mClickListener = clickListener;
    }

    public int getItemViewType(int position){
        Date date = FormatUtil.formatDate(episodes.get(position).firstAired());
        Date currentDate = new Date();
        if(date.compareTo(currentDate) > 0){
            return TYPE_TBA;
        }else{
            return TYPE_AIRED;
        }

    }

    @Override
    public long getItemId(int position) {
        return episodes.get(position).number();
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if(view == null){
            int resource = R.layout.season_episodes_item;
            int type = getItemViewType(position);
            if(type == TYPE_TBA){
                resource = R.layout.season_episodes_item_tba;
            }
            view = LayoutInflater.from(parent.getContext())
                    .inflate(resource, parent, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
        populateViewFromHolder(holder, position);
        return view;
    }
    private void populateViewFromHolder(ViewHolder holder, int position){
        final Episode episode = getItem(position);
        if(episode != null){
            holder.title().setText(episode.title());
            holder.number().setText("E".concat((episode.number() < 10 ? "0" : "") + episode.number()));
            if(getItemViewType(position) != TYPE_TBA){
                holder.root().setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mClickListener.onEpisodeClick(episode);
                    }
                });
            }
        }
    }

    @Override
    public Episode getItem(int position) {
        if(episodes == null){
            return null;
        }
        return episodes.get(position);
    }

    public void updateEpisodes(List<Episode> episodes){
        this.episodes = episodes;

        notifyDataSetChanged();
    }

    private static class ViewHolder {
        private View rootView;
        private TextView title, number;

        public ViewHolder(View root){
            rootView = root;
            title = (TextView) root.findViewById(R.id.season_episodes_item_title);
            number = (TextView) root.findViewById(R.id.season_episodes_item_number);
        }

        public TextView title(){
            return title;
        }

        public TextView number(){
            return number;
        }

        public View root(){
            return rootView;
        }
    }
}
