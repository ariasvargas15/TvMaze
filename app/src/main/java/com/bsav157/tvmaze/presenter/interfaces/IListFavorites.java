package com.bsav157.tvmaze.presenter.interfaces;

import android.content.Context;

import com.bsav157.tvmaze.model.entitites.Show;

import java.util.ArrayList;

public interface IListFavorites {

    interface View{
        void showFavorites(ArrayList<Show> shows);
    }

    interface Presenter{
        void searchFavorites();
        void showFavorites(ArrayList<Show> shows);
    }
}
