package com.bsav157.tvmaze.view.adapters;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder>{

    private ArrayList<Episode> episodes;
    private RecyclerViewOnItemClickListener recycler;

    public EpisodeAdapter(ArrayList<Episode> episodes, @NonNull RecyclerViewOnItemClickListener recycler) {
        this.episodes = episodes;
        this.recycler = recycler;
    }

    @Override
    public EpisodeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EpisodeViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_episode, parent, false));
    }

    @Override
    public void onBindViewHolder(EpisodeViewHolder holder, int position) {
        Episode episode = episodes.get(position);

        if (episode.getImage() != null) {
            if(episode.getImage().getMedium() != null) {
                Picasso.get().load(episode.getImage().getMedium()).into(holder.image);
            } else if(episode.getImage().getOriginal() != null){
                Picasso.get().load(episode.getImage().getOriginal()).into(holder.image);
            }
        }
        holder.episodeNumber.setText(Html.fromHtml("<b>Episode:</b> " + episode.getNumber(), Html.FROM_HTML_MODE_COMPACT));
        holder.episodeName.setText(Html.fromHtml("<b>"+ episode.getName() + "</b>", Html.FROM_HTML_MODE_COMPACT));
        holder.season.setText(Html.fromHtml("<b>Season:</b> "+ episode.getSeason(), Html.FROM_HTML_MODE_COMPACT));
    }

    @Override
    public int getItemCount() {
        return episodes.size();
    }

    class EpisodeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image;
        private TextView episodeName;
        private TextView season;
        private TextView episodeNumber;

        public EpisodeViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.episode_image);
            episodeName = itemView.findViewById(R.id.episode_name);
            season = itemView.findViewById(R.id.season_number);
            episodeNumber = itemView.findViewById(R.id.episode_number);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            recycler.onClick(v, getAdapterPosition());
        }
    }
}
