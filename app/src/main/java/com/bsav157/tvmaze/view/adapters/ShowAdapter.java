package com.bsav157.tvmaze.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bsav157.tvmaze.R;
import com.bsav157.tvmaze.model.database.FavoriteDto;
import com.bsav157.tvmaze.model.entitites.Favorite;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.utils.RecyclerViewOnItemClickListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ShowAdapter extends RecyclerView.Adapter<ShowAdapter.ShowViewHolder>{

    private ArrayList<Show> shows;
    private RecyclerViewOnItemClickListener recycler;
    private Context context;

    public ShowAdapter(ArrayList<Show> shows, @NonNull RecyclerViewOnItemClickListener recycler) {
        this.shows = shows;
        this.recycler = recycler;
    }

    @Override
    public ShowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        return new ShowViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_show, parent, false));
    }

    @Override
    public void onBindViewHolder(ShowViewHolder holder, int position) {
        final Show show = shows.get(position);
        final FavoriteDto dto = FavoriteDto.getInstance(context);
        final Favorite f = dto.getFavorite(String.valueOf(show.getId()));

        boolean check = f != null;

        if (show.getImage() != null) {
            if(show.getImage().getMedium() != null) {
                Picasso.get().load(show.getImage().getMedium()).into(holder.image);
            } else if(show.getImage().getOriginal() != null){
                Picasso.get().load(show.getImage().getOriginal()).into(holder.image);
            }
        }
        holder.name.setText(show.getName());

        holder.checkBox.setOnCheckedChangeListener(null);
        holder.checkBox.setChecked(check);
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                show.setSelected(isChecked);
                if(isChecked){
                    dto.addFavorite(new Favorite(show.getId()));
                } else {
                    dto.deleteFavorite(f);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return shows.size();
    }

     class ShowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

         @BindView(R.id.item_image) ImageView image;
         @BindView(R.id.item_name) TextView name;
         @BindView(R.id.favorite) CheckBox checkBox;

        public ShowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

         @Override
         public void onClick(View v) {
            recycler.onClick(v, getAdapterPosition());
        }

    }
}

