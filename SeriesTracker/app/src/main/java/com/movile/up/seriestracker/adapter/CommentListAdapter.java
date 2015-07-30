package com.movile.up.seriestracker.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Comment;

import java.util.List;

/**
 * Created by android on 7/30/15.
 */
public class CommentListAdapter extends ArrayAdapter<Comment> {
    private List<Comment> comments;

    @Override
    public int getCount() {
        if(comments == null){
            return 0;
        }
        return comments.size();
    }

    public CommentListAdapter(Context context) {
        super(context, R.layout.comments_item);
    }


    @Override
    public long getItemId(int position) {
        return comments.get(position).id();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.comments_item, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder = (ViewHolder) convertView.getTag();
        populateViewFromHolder(holder, position);

        return convertView;
    }

    private void populateViewFromHolder(ViewHolder holder, int position){
        Comment comment = comments.get(position);

        holder.comment().setText(comment.comment());
        holder.user().setText(comment.user().username());
    }

    public void setComments(List<Comment> comments){
        this.comments = comments;
        notifyDataSetInvalidated();
    }


    private class ViewHolder{
        private TextView comment;
        private TextView user;
        private View root;

        public ViewHolder(View root){
            comment = (TextView) root.findViewById(R.id.comment);
            user = (TextView) root.findViewById(R.id.comment_username);
            this.root = root;
        }

        public TextView comment() {
            return comment;
        }

        public TextView user() {
            return user;
        }

        public View root() {
            return root;
        }
    }
}
