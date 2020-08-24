package com.bsav157.tvmaze.presenter;

import com.bsav157.tvmaze.model.entitites.Episode;
import com.bsav157.tvmaze.model.interactors.ListEpisodesInteractor;
import com.bsav157.tvmaze.presenter.interfaces.IListEpisodes;

import java.util.ArrayList;

public class ListEpisodesPresenter implements IListEpisodes.Presenter {

    private IListEpisodes.View view;

    public ListEpisodesPresenter(IListEpisodes.View view){
        this.view = view;
    }

    @Override
    public void makeQuery(int idShow) {
        ListEpisodesInteractor in = new ListEpisodesInteractor(this);
        in.makeQuery(idShow);
    }

    @Override
    public void showList(ArrayList<Episode> shows) {
        view.showEpisodes(shows);
    }
}
