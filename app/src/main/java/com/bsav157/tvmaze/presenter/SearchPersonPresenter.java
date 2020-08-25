package com.bsav157.tvmaze.presenter;

import com.bsav157.tvmaze.model.entitites.Person;
import com.bsav157.tvmaze.model.interactors.SearchPersonInteractor;
import com.bsav157.tvmaze.presenter.interfaces.ISearchPerson;

import java.util.ArrayList;

public class SearchPersonPresenter implements ISearchPerson.Presenter {

    private ISearchPerson.View view;

    public SearchPersonPresenter(ISearchPerson.View view){
        this.view = view;
    }

    @Override
    public void makeQuery(String query) {
        SearchPersonInteractor in = new SearchPersonInteractor(this);
        in.makeQuery(query);
    }

    @Override
    public void showSearch(ArrayList<Person> people) {
        view.showSearch(people);
    }
}
