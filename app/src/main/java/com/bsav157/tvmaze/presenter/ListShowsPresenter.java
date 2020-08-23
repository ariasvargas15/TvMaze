package com.bsav157.tvmaze.presenter;

import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.model.interactors.ListShowsInteractor;
import com.bsav157.tvmaze.presenter.interfaces.IListShows;

import java.util.ArrayList;

public class ListShowsPresenter implements IListShows.Presenter {

    private IListShows.View view;

    public ListShowsPresenter(IListShows.View view){
        this.view = view;
    }

    @Override
    public void makeQuery(int page) {
        ListShowsInteractor in = new ListShowsInteractor(this);
        in.makeQuery(page);
    }

    @Override
    public void showList(ArrayList<Show> shows) {
        view.showList(shows);
    }


}
