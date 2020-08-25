package com.bsav157.tvmaze.view.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Episode;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.text.Layout.JUSTIFICATION_MODE_INTER_WORD;


public class InfoEpisodeFragment extends Fragment {

    private static final String ARG_PARAM1 = "episode";
    private Episode episode;
    @BindView(R.id.name_episode_details) TextView name;
    @BindView(R.id.image_episode_details) ImageView image;
    @BindView(R.id.summary_episode) TextView summary;
    @BindView(R.id.season) TextView season;
    @BindView(R.id.number) TextView number;

    public InfoEpisodeFragment() {
        // Required empty public constructor
    }

    public static InfoEpisodeFragment newInstance(Episode episode) {
        InfoEpisodeFragment fragment = new InfoEpisodeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, episode);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            episode = (Episode) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_info_episode, container, false);
        ButterKnife.bind(this, view);
        setViews();
        return view;
    }

    private void setViews(){
        name.setText(Html.fromHtml("<b>" + episode.getName() +"</b>", Html.FROM_HTML_MODE_COMPACT));

        if (episode.getImage() != null) {
            if(episode.getImage().getMedium() != null) {
                Picasso.get().load(episode.getImage().getMedium()).into(image);
            } else if(episode.getImage().getOriginal() != null){
                Picasso.get().load(episode.getImage().getOriginal()).into(image);
            }
        }

        String s = "<b>Summary:</b> " +  episode.getSummary();
        summary.setText(Html.fromHtml(s, Html.FROM_HTML_MODE_COMPACT));
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            summary.setJustificationMode(JUSTIFICATION_MODE_INTER_WORD);
        }

        number.setText(Html.fromHtml("<b>Episode:</b> " + episode.getNumber(), Html.FROM_HTML_MODE_COMPACT));
        season.setText(Html.fromHtml("<b>Season:</b> " + episode.getNumber(), Html.FROM_HTML_MODE_COMPACT));

    }
}