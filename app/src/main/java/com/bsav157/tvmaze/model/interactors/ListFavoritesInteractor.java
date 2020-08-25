package com.bsav157.tvmaze.model.interactors;

import android.content.Context;
import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.database.FavoriteDto;
import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.model.entitites.Favorite;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.presenter.interfaces.IListFavorites;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFavoritesInteractor implements Callback<Show> {

    private IListFavorites.Presenter presenter;
    private List<Show> shows;
    private FavoriteDto fav;
    private int value;

    public ListFavoritesInteractor(IListFavorites.Presenter presenter, Context context){
        this.presenter = presenter;
        fav = FavoriteDto.getInstance(context);
        shows = new ArrayList<>();
    }

    public void searchFavorites(){
        List<Favorite> favorites = fav.getFavorites();
        value = favorites.size();
        if (favorites.isEmpty()){
            presenter.showFavorites((ArrayList<Show>) shows);
            return;
        }
        for(Favorite f: favorites){
            getShow(f.getId());
        }
    }

    public void getShow(int id){
        value--;
        Call<Show> call = ApiAdapter.getApiService().getShow(id);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<Show> call, Response<Show> response) {
        if (response.isSuccessful()) {
            Show show = response.body();
            if(show != null) {
               shows.add(show);
               if(value == 0) {
                   Collections.sort(shows, new Comparator<Show>() {
                       @Override
                       public int compare(Show show, Show t1) {
                           return show.getName().compareTo(t1.getName());
                       }
                   });
                   presenter.showFavorites((ArrayList<Show>) shows);
               }
            } else {
                Log.e("onResponseShowList", "Response is null");
            }
        }
    }

    @Override
    public void onFailure(Call<Show> call, Throwable t) {
        Log.e("onFailureShowList", "Response failed");
    }
}
