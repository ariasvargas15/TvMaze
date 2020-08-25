package com.bsav157.tvmaze.model.interactors;

import android.util.Log;

import com.bsav157.tvmaze.model.apiservice.ApiAdapter;
import com.bsav157.tvmaze.model.entitites.Person;
import com.bsav157.tvmaze.model.entitites.SearchPerson;
import com.bsav157.tvmaze.presenter.interfaces.ISearchPerson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPersonInteractor implements Callback<List<SearchPerson>> {

    private ISearchPerson.Presenter presenter;

    public SearchPersonInteractor(ISearchPerson.Presenter presenter){
        this.presenter = presenter;
    }

    public void makeQuery(String query){
        Call<List<SearchPerson>> call = ApiAdapter.getApiService().getPeople(query);
        call.enqueue(this);
    }

    @Override
    public void onResponse(Call<List<SearchPerson>> call, Response<List<SearchPerson>> response) {
        if (response.isSuccessful()) {
            List<SearchPerson> list = response.body();
            ArrayList<Person> people = new ArrayList<>();
            if(list != null) {
                for (SearchPerson s: list) {
                    people.add(s.getPerson());
                }
                presenter.showSearch(people);
            } else {
                Log.e("onResponseSearch", "Response is null");
            }

        }
    }

    @Override
    public void onFailure(Call<List<SearchPerson>> call, Throwable t) {
        Log.e("onFailureSearchPerson", "Response failed");
    }
}
