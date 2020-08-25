package com.bsav157.tvmaze.presenter;

import android.content.Context;

import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.model.interactors.ListFavoritesInteractor;
import com.bsav157.tvmaze.presenter.interfaces.IListFavorites;

import java.util.ArrayList;

public class ListFavoritesPresenter implements IListFavorites.Presenter {

    private IListFavorites.View view;
    private Context context;

    public ListFavoritesPresenter(IListFavorites.View view, Context context){
        this.view = view;
        this.context = context;
    }

    @Override
    public void searchFavorites() {
        ListFavoritesInteractor in = new ListFavoritesInteractor(this, context);
        in.searchFavorites();
    }

    @Override
    public void showFavorites(ArrayList<Show> shows) {
        view.showFavorites(shows);
    }
}
