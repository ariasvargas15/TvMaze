package com.bsav157.tvmaze.presenter.interfaces;

import com.bsav157.tvmaze.model.entitites.Show;

import java.util.ArrayList;

public interface IListShows {

    interface View {
        void showList(ArrayList<Show> shows);
    }

    interface Presenter {
        void makeQuery(int page);
        void showList(ArrayList<Show> shows);
    }

}
