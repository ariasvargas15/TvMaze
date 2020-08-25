package com.bsav157.tvmaze.presenter.interfaces;

import com.bsav157.tvmaze.model.entitites.Person;

import java.util.ArrayList;

public interface ISearchPerson {

    interface View {
        void showSearch(ArrayList<Person> people);
    }

    interface Presenter {
        void makeQuery(String query);
        void showSearch(ArrayList<Person> people);
    }
}
