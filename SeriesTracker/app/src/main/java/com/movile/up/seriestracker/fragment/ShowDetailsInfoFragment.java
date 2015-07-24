package com.movile.up.seriestracker.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.movile.up.seriestracker.R;
import com.movile.up.seriestracker.model.Show;
import com.movile.up.seriestracker.presenter.ShowDetailsInfoPresenter;
import com.movile.up.seriestracker.view.ShowDetailsInfoView;

/**
 * Created by android on 7/21/15.
 */
public class ShowDetailsInfoFragment extends Fragment implements ShowDetailsInfoView {
    private ShowDetailsInfoPresenter mPresenter;
    public static final String SHOW_ARGUMENT = "show_details_info_show_argument";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.show_details_info_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mPresenter = new ShowDetailsInfoPresenter(this.getActivity(), this);

        mPresenter.loadShowDetails(getArguments().getString(SHOW_ARGUMENT));
    }

    @Override
    public void displayShow(Show show) {
        ((TextView)this.getActivity().findViewById(R.id.show_details_info_summary)).setText(show.overview());

        LinearLayout container = null;
        if(this.getView() != null) {
            container = (LinearLayout) this.getView().findViewById(R.id.show_details_info_genres);
        }
        LinearLayout currentRow = null;
        int itemsInRow = 0;
        for(int i=0; i<show.genres().length; i++){
            if(itemsInRow == 0){
                currentRow = new LinearLayout(this.getActivity());
                currentRow.setOrientation(LinearLayout.HORIZONTAL);
                currentRow.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            }

            View genreView = LayoutInflater.from(this.getActivity()).inflate(R.layout.show_details_info_genre, currentRow, false);
            ((TextView)genreView.findViewById(R.id.show_details_info_genre)).setText(show.genres()[i]);

            currentRow.addView(genreView);

            itemsInRow++;

            if(itemsInRow == 3 && container != null) {
                container.addView(currentRow);
                currentRow = null;
                itemsInRow = 0;
            }
        }
        if(currentRow != null && container != null){
            container.addView(currentRow);
        }

        TextView details = (TextView) this.getActivity().findViewById(R.id.show_details_info_details);
        String detailsString = "<b>Broadcasting: </b>" + show.network() + "<br/>";
        detailsString += "<b>Status: </b>" + show.status() + "<br/>";
        detailsString += "<b>Started in: </b>" + show.year() + "<br/>";
        detailsString += "<b>Country: </b>" + show.country() + "<br/>";
        detailsString += "<b>Language: </b>" + show.language() + "<br/>";
        details.setText(Html.fromHtml(detailsString));
    }
}
