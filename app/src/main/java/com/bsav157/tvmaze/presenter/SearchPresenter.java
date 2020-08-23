package com.bsav157.tvmaze.presenter;

import com.bsav157.tvmaze.model.entitites.Search;
import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.model.interactors.SearchInteractor;
import com.bsav157.tvmaze.presenter.interfaces.ISearchShow;

import java.util.ArrayList;

public class SearchPresenter implements ISearchShow.Presenter {

    private ISearchShow.View view;

    public SearchPresenter(ISearchShow.View view){
        this.view = view;
    }

    @Override
    public void makeQuery(String query) {
        SearchInteractor in = new SearchInteractor(this);
        in.makeQuery(query);
    }

    @Override
    public void showSearch(ArrayList<Show> shows) {
        view.showSearch(shows);
    }
}
