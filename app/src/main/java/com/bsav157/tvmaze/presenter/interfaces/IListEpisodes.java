package com.bsav157.tvmaze.presenter.interfaces;

import com.bsav157.tvmaze.model.entitites.Episode;

import java.util.ArrayList;

public interface IListEpisodes {
    interface View {
        void showEpisodes(ArrayList<Episode> episodes);
    }

    interface Presenter {
        void makeQuery(int idShow);
        void showList(ArrayList<Episode> episodes);
    }
}
