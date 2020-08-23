package com.bsav157.tvmaze.presenter.interfaces;

import com.bsav157.tvmaze.model.entitites.Show;

import java.util.ArrayList;

public interface ISearchShow {

    interface View {
        void showSearch(ArrayList<Show> shows);
    }

    interface Presenter {
        void makeQuery(String query);
        void showSearch(ArrayList<Show> shows);
    }
}
