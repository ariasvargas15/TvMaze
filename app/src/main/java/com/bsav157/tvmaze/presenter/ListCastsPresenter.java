package com.bsav157.tvmaze.presenter;

import com.bsav157.tvmaze.model.entitites.Show;
import com.bsav157.tvmaze.model.interactors.ListCastsInteractor;
import com.bsav157.tvmaze.presenter.interfaces.IListCasts;

import java.util.ArrayList;

public class ListCastsPresenter implements IListCasts.Presenter {

    private IListCasts.View view;

    public ListCastsPresenter(IListCasts.View view){
        this.view = view;
    }

    @Override
    public void makeQuery(int idPerson) {
        ListCastsInteractor in = new ListCastsInteractor(this);
        in.makeQuery(idPerson);
    }

    @Override
    public void showList(ArrayList<Show> shows) {
        view.showCasts(shows);
    }


}
