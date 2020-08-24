package com.bsav157.tvmaze.view.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private ArrayList<Show> shows;
    private RecyclerViewOnItemClickListener recycler;

    public ShowAdapter(ArrayList<Show> shows, @NonNull RecyclerViewOnItemClickListener recycler) {
        this.shows = shows;
        this.recycler = recycler;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        Show show = shows.get(position);

        if (show.getImage() != null) {
            if(show.getImage().getMedium() != null) {
                Picasso.get().load(show.getImage().getMedium()).into(holder.image);
            } else if(show.getImage().getOriginal() != null){
                Picasso.get().load(show.getImage().getOriginal()).into(holder.image);
            }
        }
        holder.name.setText(show.getName());
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

     class ShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView image;
        private TextView name;

        public ShowViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.show_image);
            name = itemView.findViewById(R.id.show_name);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View v) {
            recycler.onClick(v, getAdapterPosition());
        }
    }
}

